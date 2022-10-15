package com.seuestilo.rembg.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tipoPeca")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TipoPeca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tipoPecaID;

    @OneToMany(mappedBy = "categoriaTipo")
    private Set<Peca> pecas;

    @Column
    private String descricao;

    @Column
    @ManyToOne
    @JoinColumn(name = "IDcategoria")
    private Categoria categoria;
}
