package com.ryan.estacionamento_0.repository.filter;

import lombok.Data;

@Data
public class ClienteFilter {
    private String id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String dataNasc;
    private String plano;
    private String endereco;
}
