package com.seuestilo.rembg.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "tipoPeca")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TipoPeca {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long tipoPecaID;

    @Column
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "categoria")
    private Categoria categoria;
}
