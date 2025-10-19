package com.ryan.estacionamento_0.repository.imp;

import com.ryan.estacionamento_0.domain.Caixa;
import com.ryan.estacionamento_0.repository.filter.CaixaFilter;
import com.ryan.estacionamento_0.repository.query.CaixaRepositoryQuery;

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

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CaixaRepositoryImpl implements CaixaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Caixa> filtrar(CaixaFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        // query principal
        CriteriaQuery<Caixa> criteria = builder.createQuery(Caixa.class);
        Root<Caixa> root = criteria.from(Caixa.class);

        List<Predicate> predicates = buildPredicates(filter, builder, root);
        criteria.where(predicates.toArray(new Predicate[0]));
        criteria.orderBy(builder.desc(root.get("dia")));

        // count query
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<Caixa> countRoot = countQuery.from(Caixa.class);
        countQuery.select(builder.count(countRoot));
        List<Predicate> countPredicates = buildPredicates(filter, builder, countRoot);
        countQuery.where(countPredicates.toArray(new Predicate[0]));
        Long total = manager.createQuery(countQuery).getSingleResult();

        TypedQuery<Caixa> typedQuery = manager.createQuery(criteria);
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int firstResult = pageNumber * pageSize;
        typedQuery.setFirstResult(firstResult);
        typedQuery.setMaxResults(pageSize);

        List<Caixa> result = typedQuery.getResultList();
        return new PageImpl<>(result, pageable, total);
    }

    // constroi predicados reutilizáveis para consulta e contagem
    private List<Predicate> buildPredicates(CaixaFilter filter, CriteriaBuilder builder, Root<Caixa> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter == null) {
            return predicates;
        }

        // id
        if (notBlank(filter.getId())) {
            Integer idVal = parseInteger(filter.getId());
            if (idVal != null) {
                predicates.add(builder.equal(root.get("id"), idVal));
            }
        }

        // cliente (pode ser id ou nome)
        if (notBlank(filter.getCliente())) {
            Integer cid = parseInteger(filter.getCliente());
            if (cid != null) {
                predicates.add(builder.equal(root.get("cliente").get("id"), cid));
            } else {
                String txt = likePattern(filter.getCliente());
                predicates.add(builder.like(builder.lower(root.get("cliente").get("nome")), txt));
            }
        }

        // tipo
        if (notBlank(filter.getTipo())) {
            predicates.add(builder.equal(builder.lower(root.get("tipo")), filter.getTipo().trim().toLowerCase()));
        }

        // formaPagamento (id ou nome)
        if (notBlank(filter.getFormaPagamento())) {
            Integer fpId = parseInteger(filter.getFormaPagamento());
            if (fpId != null) {
                predicates.add(builder.equal(root.get("formaPagamento").get("id"), fpId));
            } else {
                String txt = likePattern(filter.getFormaPagamento());
                predicates.add(builder.like(builder.lower(root.get("formaPagamento").get("nome")), txt));
            }
        }

        // condicaoPagamento (id ou descricao)
        if (notBlank(filter.getCondicaoPagamento())) {
            Integer cpId = parseInteger(filter.getCondicaoPagamento());
            if (cpId != null) {
                predicates.add(builder.equal(root.get("condicaoPagamento").get("id"), cpId));
            } else {
                String txt = likePattern(filter.getCondicaoPagamento());
                predicates.add(builder.like(builder.lower(root.get("condicaoPagamento").get("descricao")), txt));
            }
        }

        // controleFluxo (id)
        if (notBlank(filter.getControleFluxo())) {
            Integer cfId = parseInteger(filter.getControleFluxo());
            if (cfId != null) {
                predicates.add(builder.equal(root.get("controleFluxo").get("id"), cfId));
            }
        }

        // valor (suporta "min-max" ou valor exato)
        if (notBlank(filter.getValor())) {
            String v = filter.getValor().trim();
            if (v.contains("-")) {
                String[] parts = v.split("-", 2);
                BigDecimal min = parseBigDecimal(parts[0]);
                BigDecimal max = parseBigDecimal(parts[1]);
                if (min != null) {
                    predicates.add(builder.greaterThanOrEqualTo(root.get("valor"), min));
                }
                if (max != null) {
                    predicates.add(builder.lessThanOrEqualTo(root.get("valor"), max));
                }
            } else {
                BigDecimal exact = parseBigDecimal(v);
                if (exact != null) {
                    predicates.add(builder.equal(root.get("valor"), exact));
                }
            }
        }

        // dia (aceita ISO-8601 datetime ou yyyy-MM-dd) -> filtra pelo dia completo (start..start+1)
        if (notBlank(filter.getDia())) {
            OffsetDateTime start = parseToStartOfDay(filter.getDia());
            if (start != null) {
                OffsetDateTime end = start.plusDays(1);
                predicates.add(builder.greaterThanOrEqualTo(root.get("dia"), start));
                predicates.add(builder.lessThan(root.get("dia"), end));
            }
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

    private static BigDecimal parseBigDecimal(String s) {
        if (s == null) return null;
        try {
            return new BigDecimal(s.trim());
        } catch (Exception ex) {
            return null;
        }
    }

    private static String likePattern(String s) {
        return "%" + s.trim().toLowerCase() + "%";
    }

    private static OffsetDateTime parseToStartOfDay(String input) {
        if (input == null) return null;
        String t = input.trim();
        try {
            // try full OffsetDateTime first
            return OffsetDateTime.parse(t);
        } catch (DateTimeParseException ignore) {
        }
        try {
            // try LocalDate (yyyy-MM-dd)
            LocalDate ld = LocalDate.parse(t);
            LocalDateTime ldt = ld.atStartOfDay();
            ZoneOffset offset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
            return OffsetDateTime.of(ldt, offset);
        } catch (DateTimeParseException ignore) {
        }
        return null;
    }
}