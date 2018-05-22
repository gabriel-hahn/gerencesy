package com.gabrielhahn.gerencesy.model;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gabrielhahnschaeffer
 */
@Entity
@Table(name = "board")
@SequenceGenerator(name = "board_seq", sequenceName = "board_seq", allocationSize = 1)
public class Board implements Entidade {
    
    @Id
    @GeneratedValue(generator = "board_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "O nome do board é obrigatório")
    @Column(name = "nome")
    private String nome;

    @NotNull(message = "O status do board é obrigatório")
    @Column(name = "status")
    private String status;

    @Transient
    private double progresso;

    @Transient
    private double progressoTempo;
    
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "idBoard")
    private List<Cartao> cartoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getProgresso() {
        return progresso;
    }

    public void setProgresso(double progresso) {
        this.progresso = progresso;
    }

    public double getProgressoTempo() {
        return progressoTempo;
    }

    public void setProgressoTempo(double progressoTempo) {
        this.progressoTempo = progressoTempo;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

   
}

