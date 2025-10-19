// ...existing code...
package com.ryan.estacionamento_0.repository.imp;

import com.ryan.estacionamento_0.domain.CondicaoPagamento;
import com.ryan.estacionamento_0.repository.filter.CondicaoPagamentoFilter;
import com.ryan.estacionamento_0.repository.query.CondicaoPagamentoRepositoryQuery;

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

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CondicaoPagamentoRepositoryImpl implements CondicaoPagamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<CondicaoPagamento> filtrar(CondicaoPagamentoFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        // query principal
        CriteriaQuery<CondicaoPagamento> criteria = builder.createQuery(CondicaoPagamento.class);
        Root<CondicaoPagamento> root = criteria.from(CondicaoPagamento.class);

        List<Predicate> predicates = new ArrayList<>();
        if (filter != null) {
            // id (aceita string no filtro)
            if (filter.getId() != null && !filter.getId().isBlank()) {
                try {
                    Integer idVal = Integer.parseInt(filter.getId().trim());
                    predicates.add(builder.equal(root.get("id"), idVal));
                } catch (NumberFormatException ignored) { }
            }

            // descricao (like case-insensitive)
            if (filter.getDescricao() != null && !filter.getDescricao().isBlank()) {
                String txt = "%" + filter.getDescricao().trim().toLowerCase() + "%";
                predicates.add(builder.like(builder.lower(root.get("descricao")), txt));
            }
        }

        criteria.where(predicates.toArray(new Predicate[0]));
        criteria.orderBy(builder.desc(root.get("id")));

        // count query
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<CondicaoPagamento> countRoot = countQuery.from(CondicaoPagamento.class);
        countQuery.select(builder.count(countRoot));

        List<Predicate> countPredicates = new ArrayList<>();
        if (filter != null) {
            if (filter.getId() != null && !filter.getId().isBlank()) {
                try {
                    Integer idVal = Integer.parseInt(filter.getId().trim());
                    countPredicates.add(builder.equal(countRoot.get("id"), idVal));
                } catch (NumberFormatException ignored) { }
            }
            if (filter.getDescricao() != null && !filter.getDescricao().isBlank()) {
                String txt = "%" + filter.getDescricao().trim().toLowerCase() + "%";
                countPredicates.add(builder.like(builder.lower(countRoot.get("descricao")), txt));
            }
        }
        countQuery.where(countPredicates.toArray(new Predicate[0]));
        Long total = manager.createQuery(countQuery).getSingleResult();

        TypedQuery<CondicaoPagamento> typedQuery = manager.createQuery(criteria);
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int firstResult = pageNumber * pageSize;
        typedQuery.setFirstResult(firstResult);
        typedQuery.setMaxResults(pageSize);

        List<CondicaoPagamento> result = typedQuery.getResultList();
        return new PageImpl<>(result, pageable, total);
    }

}