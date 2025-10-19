package com.ryan.estacionamento_0.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nome;
    private String cpf;
    private String telefone;
    private Date dataNasc;
    private String email;
    private String plano;

    @OneToOne
    @JoinColumn(name = "end_id", referencedColumnName = "id")
    private Endereco end;

    @OneToMany
    private List<Veiculo> veiculo;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal limiteDeCompra;

}
