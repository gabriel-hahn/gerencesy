package com.gabrielhahn.gerencesy.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gabrielhahnschaeffer
 */
@Entity
@Table(name = "cartao")
@SequenceGenerator(name = "cartao_seq", sequenceName = "cartao_seq", allocationSize = 1)
public class Cartao implements Entidade {
    
    @Id
    @GeneratedValue(generator = "cartao_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "O nome do cartão é obrigatório")
    @Column(name = "nome")
    private String nome;

    @NotNull(message = "O ID do board é obrigatório")
    @Column(name = "idBoard")
    private Long idBoard;

    @NotNull(message = "O status do cartão é obrigatório")
    @Column(name = "status")
    private String status;

    @NotNull(message = "O tempo da atividade é obrigatório")
    @Column(name = "tempo")
    private Long tempo;

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
    
    public Long getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(Long idBoard) {
        this.idBoard = idBoard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTempo() {
        return tempo;
    }

    public void setTempo(Long tempo) {
        this.tempo = tempo;
    }
    
}
