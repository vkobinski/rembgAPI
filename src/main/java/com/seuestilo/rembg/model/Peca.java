package com.seuestilo.rembg.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "peca")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Peca {

    public Peca(Long pecaID, Cor IDcor, TipoPeca categoriaTipo, String nome, String localCompra, Long marca, Usuario usuario, Long tecido, Integer status) {
        this.pecaID = pecaID;
        this.IDcor = IDcor;
        this.categoriaTipo = categoriaTipo;
        this.nome = nome;
        this.localCompra = localCompra;
        this.marca = marca;
        this.usuario = usuario;
        this.tecido = tecido;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long pecaID;

    @Lob
    private Byte[] imagemSemTrat;

    @Lob
    private Byte[] imagemComTrat;

    @ManyToOne
    @JoinColumn(name = "IDcor")
    private Cor IDcor;

    @ManyToOne
    @JoinColumn(name = "IDcategoriaTipo")
    private TipoPeca categoriaTipo;

    @Column
    private String nome;

    @Column
    private String localCompra;

    @Column
    private Long marca;

    @ManyToOne
    @JoinColumn(name = "IDusuario")
    private Usuario usuario;

    @Column
    private Long tecido;

    @Column
    private Integer status;


}
