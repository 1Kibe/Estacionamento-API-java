package com.ryan.estacionamento_0.repository.filter;

import lombok.Data;

@Data
public class CaixaFilter {
    private String id;
    private String cliente;
    private String tipo;
    private String formaPagamento;
    private String condicaoPagamento;
    private String controleFluxo;
    private String dia;
    private String valor;
}
