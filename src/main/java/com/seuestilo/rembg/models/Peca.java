package com.seuestilo.rembg.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "peca")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Peca {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    @ManyToOne
    @JoinColumn(name = "IDtamanho")
    private Tamanho IDtamanho;

    @OneToMany(mappedBy = "IDPecaInferior")
    private Set<Look> looksInferior;

    @OneToMany(mappedBy = "IDPecaSuperior")
    private Set<Look> looksSuperior;

    @OneToMany(mappedBy = "IDPecaUnica")
    private Set<Look> looksUnica;

    @OneToMany(mappedBy = "IDPecaSobreposicao")
    private Set<Look> looksSobreposicao;
}
