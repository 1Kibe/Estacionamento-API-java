package com.ryan.estacionamento_0.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "financeiro") // nome da tabela no banco
@Data // gera getters, setters, toString, equals e hashCode
@NoArgsConstructor // gera construtor sem argumentos
@AllArgsConstructor // gera construtor com todos os argumentos
public class Financeiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private FormaPagamento formaPagamento;

    @ManyToOne
    private CondicaoPagamento condPagamento;

    @Column(name = "data_emissao", columnDefinition = "DATETIME")
    private OffsetDateTime dataEmissao;
    @Column(name = "data_vencimento", columnDefinition = "DATETIME")
    private OffsetDateTime dataVencimento;
    @Column(name = "data_pagamento", columnDefinition = "DATETIME")
    private OffsetDateTime dataPagamento;
    @Column(precision = 10, scale = 2)
    private BigDecimal valorOriginal;
    @Column(precision = 10, scale = 2)
    private BigDecimal valorJuros;
    @Column(precision = 10, scale = 2)
    private BigDecimal valorDespesas;
    @Column(precision = 10, scale = 2)

    private BigDecimal valorDesconto;
    private String status;

}
