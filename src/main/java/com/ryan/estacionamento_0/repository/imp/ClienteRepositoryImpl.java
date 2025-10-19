package com.ryan.estacionamento_0.repository.imp;

import com.ryan.estacionamento_0.domain.Cliente;
import com.ryan.estacionamento_0.repository.filter.ClienteFilter;
import com.ryan.estacionamento_0.repository.query.ClienteRepositoryQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ClienteRepositoryImpl implements ClienteRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Cliente> filtrar(ClienteFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        // query principal
        CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
        Root<Cliente> root = criteria.from(Cliente.class);

        List<Predicate> predicates = buildPredicates(filter, builder, root);
        criteria.where(predicates.toArray(new Predicate[0]));
        criteria.orderBy(builder.desc(root.get("id")));

        // count query
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<Cliente> countRoot = countQuery.from(Cliente.class);
        countQuery.select(builder.count(countRoot));
        List<Predicate> countPredicates = buildPredicates(filter, builder, countRoot);
        countQuery.where(countPredicates.toArray(new Predicate[0]));
        Long total = manager.createQuery(countQuery).getSingleResult();

        TypedQuery<Cliente> typedQuery = manager.createQuery(criteria);
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int firstResult = pageNumber * pageSize;
        typedQuery.setFirstResult(firstResult);
        typedQuery.setMaxResults(pageSize);

        List<Cliente> result = typedQuery.getResultList();
        return new PageImpl<>(result, pageable, total);
    }

    // constrói predicados reutilizáveis para consulta e contagem
    private List<Predicate> buildPredicates(ClienteFilter filter, CriteriaBuilder builder, Root<Cliente> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter == null) {
            return predicates;
        }

        // id (aceita string na filter)
        if (notBlank(filter.getId())) {
            Integer idVal = parseInteger(filter.getId());
            if (idVal != null) {
                predicates.add(builder.equal(root.get("id"), idVal));
            }
        }

        // nome (like, case-insensitive)
        if (notBlank(filter.getNome())) {
            String txt = likePattern(filter.getNome());
            predicates.add(builder.like(builder.lower(root.get("nome")), txt));
        }

        // cpf (match exato ou like breve)
        if (notBlank(filter.getCpf())) {
            String cpf = filter.getCpf().trim();
            if (cpf.contains("%")) {
                predicates.add(builder.like(builder.lower(root.get("cpf")), cpf.toLowerCase()));
            } else {
                predicates.add(builder.equal(builder.lower(root.get("cpf")), cpf.toLowerCase()));
            }
        }

        // telefone
        if (notBlank(filter.getTelefone())) {
            String txt = likePattern(filter.getTelefone());
            predicates.add(builder.like(builder.lower(root.get("telefone")), txt));
        }

        // email
        if (notBlank(filter.getEmail())) {
            String txt = likePattern(filter.getEmail());
            predicates.add(builder.like(builder.lower(root.get("email")), txt));
        }

        // plano
        if (notBlank(filter.getPlano())) {
            String txt = likePattern(filter.getPlano());
            predicates.add(builder.like(builder.lower(root.get("plano")), txt));
        }

        // dataNasc (aceita yyyy-MM-dd) -> compara igualdade na data (sem horário)
        if (notBlank(filter.getDataNasc())) {
            Date sqlDate = parseToSqlDate(filter.getDataNasc());
            if (sqlDate != null) {
                predicates.add(builder.equal(root.get("dataNasc"), sqlDate));
            }
        }

        // endereco: busca por logradouro, bairro ou cidade (campo 'end' na entidade Cliente)
        if (notBlank(filter.getEndereco())) {
            String txt = likePattern(filter.getEndereco());
            predicates.add(builder.or(
                    builder.like(builder.lower(root.get("end").get("logradouro")), txt),
                    builder.like(builder.lower(root.get("end").get("bairro")), txt),
                    builder.like(builder.lower(root.get("end").get("cidade")), txt)
            ));
        }

        return predicates;
    }

    private static boolean notBlank(String s) {
        return s != null && !s.isBlank();
    }

    private static Integer parseInteger(String s) {
        if (s == null) return null;
        try {
            return Integer.valueOf(s.trim());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private static String likePattern(String s) {
        return "%" + s.trim().toLowerCase() + "%";
    }

    private static Date parseToSqlDate(String input) {
        if (input == null) return null;
        String t = input.trim();
        try {
            LocalDate ld = LocalDate.parse(t); // espera yyyy-MM-dd
            return Date.valueOf(ld);
        } catch (DateTimeParseException ignore) {
            return null;
        }
    }
}