package com.seuestilo.rembg.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Categoria {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long categoriaID;

    @OneToMany(mappedBy = "IDcategoria")
    private Set<TipoPeca> tipoPecas;

    @Column
    private String descricao;
}
