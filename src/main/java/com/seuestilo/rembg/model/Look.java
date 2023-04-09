package com.seuestilo.rembg.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "look")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Look {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lookID;

    @ManyToOne
    @JoinColumn(name = "IDPecaInferior")
    private Peca pecaInferior;

    @ManyToOne
    @JoinColumn(name = "IDPecaSuperior")
    private Peca pecaSuperior;

    @ManyToOne
    @JoinColumn(name = "IDPecaUnica")
    private Peca pecaUnica;

    @ManyToMany
    private Set<Peca> acessorios;

    @ManyToOne
    @JoinColumn(name = "IDPecaSobreposicao")
    private Peca pecaSobreposicao;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private Usuario usuario;

    @Column
    private Integer status;
}
