package com.ryan.estacionamento_0.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data                     // gera getters, setters, toString, equals e hashCode
@NoArgsConstructor         // gera construtor sem argumentos
@AllArgsConstructor  
public class Caixa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Cliente cliente;

    private OffsetDateTime dia;
    private BigDecimal valor;
    private String tipo;

    @ManyToOne
    private FormaPagamento formaPagamento;

    @ManyToOne
    private CondicaoPagamento condicaoPagamento;

    @OneToOne
    private ControleFluxo controleFluxo;
}
