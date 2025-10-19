// ...existing code...
package com.ryan.estacionamento_0.repository.imp; 

import com.ryan.estacionamento_0.domain.Usuario; 
import com.ryan.estacionamento_0.repository.filter.UsuarioFilter; 
import com.ryan.estacionamento_0.repository.query.UsuarioRepositoryQuery; 

import jakarta.persistence.EntityManager; // EntityManager JPA
import jakarta.persistence.PersistenceContext; // anotação para injetar o EntityManager
import jakarta.persistence.TypedQuery; // TypedQuery para executar a query tipada
import jakarta.persistence.criteria.CriteriaBuilder; // CriteriaBuilder para montar a Criteria API
import jakarta.persistence.criteria.CriteriaQuery; // CriteriaQuery representa a consulta
import jakarta.persistence.criteria.Predicate; // Predicate representa condições WHERE
import jakarta.persistence.criteria.Root; // Root representa a entidade raiz na consulta
import org.springframework.data.domain.Page; // Page do Spring Data
import org.springframework.data.domain.PageImpl; // PageImpl para construir um Page a partir da lista
import org.springframework.data.domain.Pageable; // Pageable para paginação
import org.springframework.stereotype.Repository; 
import org.springframework.transaction.annotation.Transactional; 

import java.util.ArrayList; // ArrayList para acumular Predicates
import java.util.List; // List genérico

@Repository // registra a classe como bean do Spring do tipo repository
@Transactional(readOnly = true) // indica que os métodos são somente leitura por padrão
public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery { // implementação do contrato customizado

    @PersistenceContext // injeta o EntityManager gerenciado pelo container JPA
    private EntityManager manager; // referência ao EntityManager

    @Override // indica que este método implementa a assinatura da interface
    public Page<Usuario> filtrar(UsuarioFilter filter, Pageable pageable) { // método público que retorna Page<Usuario>
        CriteriaBuilder builder = manager.getCriteriaBuilder(); // obtém um CriteriaBuilder do EntityManager

        CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class); // cria CriteriaQuery para Usuario
        Root<Usuario> root = criteria.from(Usuario.class); // define a raiz (FROM Usuario)

        List<Predicate> predicates = new ArrayList<>(); // lista para armazenar Econdições WHERE dinamicamente

        if (filter != null) { // verifica se o filtro foi fornecido
            if (filter.getId() != null && !filter.getId().isBlank()) { // se id foi passado e não é vazio
                try {
                    Integer idVal = Integer.parseInt(filter.getId().trim()); // tenta converter id para Integer
                    predicates.add(builder.equal(root.get("id"), idVal)); // adiciona predicate id = valor
                } catch (NumberFormatException ignored) { } // ignora id inválido (não adiciona predicate)
            }

            if (filter.getNome() != null && !filter.getNome().isBlank()) { // se nome foi passado
                predicates.add( // adiciona predicate para nome LIKE %valor% (case-insensitive)
                        builder.like(
                                builder.lower(root.get("nome")), // transforma a coluna em minúsculas para comparação
                                "%" + filter.getNome().trim().toLowerCase() + "%" // valor do filtro em minúsculas com curingas
                        )
                );
            }

            if (filter.getEmail() != null && !filter.getEmail().isBlank()) { // se email foi passado
                predicates.add( // adiciona predicate para email LIKE %valor% (case-insensitive)
                        builder.like(
                                builder.lower(root.get("email")),
                                "%" + filter.getEmail().trim().toLowerCase() + "%"
                        )
                );
            }
        }

        criteria.where(predicates.toArray(new Predicate[0])); // aplica os predicates ao WHERE da query
        criteria.orderBy(builder.desc(root.get("id"))); // aplica ordenação padrão (id desc)

        // --- query de contagem (para preencher total do Page) ---
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class); // cria CriteriaQuery para contagem
        Root<Usuario> countRoot = countQuery.from(Usuario.class); // raiz da query de contagem
        countQuery.select(builder.count(countRoot)); // SELECT count(*)

        List<Predicate> countPredicates = new ArrayList<>(); // predicados para a query de contagem (espelho dos acima)
        if (filter != null) { // reaplica lógica de criação de predicados para a query de contagem
            if (filter.getId() != null && !filter.getId().isBlank()) {
                try {
                    Integer idVal = Integer.parseInt(filter.getId().trim());
                    countPredicates.add(builder.equal(countRoot.get("id"), idVal));
                } catch (NumberFormatException ignored) { }
            }
            if (filter.getNome() != null && !filter.getNome().isBlank()) {
                countPredicates.add(
                        builder.like(
                                builder.lower(countRoot.get("nome")),
                                "%" + filter.getNome().trim().toLowerCase() + "%"
                        )
                );
            }
            if (filter.getEmail() != null && !filter.getEmail().isBlank()) {
                countPredicates.add(
                        builder.like(
                                builder.lower(countRoot.get("email")),
                                "%" + filter.getEmail().trim().toLowerCase() + "%"
                        )
                );
            }
        }
        countQuery.where(countPredicates.toArray(new Predicate[0])); // aplica predicados à query de contagem
        Long total = manager.createQuery(countQuery).getSingleResult(); // executa count e obtém total

        TypedQuery<Usuario> typedQuery = manager.createQuery(criteria); // cria TypedQuery a partir da Criteria principal

        // paginação: calcula índice do primeiro resultado e limita a quantidade retornada
        int pageNumber = pageable.getPageNumber(); // número da página (0-based)
        int pageSize = pageable.getPageSize(); // quantidade de itens por página
        int firstResult = pageNumber * pageSize; // índice do primeiro registro da página
        typedQuery.setFirstResult(firstResult); // aplica deslocamento
        typedQuery.setMaxResults(pageSize); // aplica limite

        List<Usuario> list = typedQuery.getResultList(); // executa a query e obtém a lista de resultados

        return new PageImpl<>(list, pageable, total); // constrói e retorna o Page com conteúdo, paginação e total
    }
}
