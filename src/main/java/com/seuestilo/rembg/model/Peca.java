package com.seuestilo.rembg.model;

import com.seuestilo.rembg.service.MarcaService;
import com.seuestilo.rembg.service.TamanhoService;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "peca")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Peca {

    public Peca(Long pecaID, Cor IDcor, TipoPeca categoriaTipo, String nome, String localCompra, Marca marca, Usuario usuario, Long tecido, Integer status) {
        this.pecaID = pecaID;
        this.cor = IDcor;
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
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] imagemSemTrat;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] imagemComTrat;

    @ManyToOne
    @JoinColumn(name = "IDcor")
    private Cor cor;

    @ManyToOne
    @JoinColumn(name = "IDcategoriaTipo")
    private TipoPeca categoriaTipo;

    @Column
    private String nome;

    @Column
    private String localCompra;

    @ManyToOne
    @JoinColumn(name = "IDMarca")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "IDusuario")
    @NonNull
    private Usuario usuario;

    @Column
    private Long tecido;

    @ManyToOne
    @JoinColumn(name = "IDTamanho")
    private Tamanho tamanho;

    @Column
    private Integer status;


}
