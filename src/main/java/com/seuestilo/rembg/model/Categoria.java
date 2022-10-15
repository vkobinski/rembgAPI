package com.seuestilo.rembg.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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

    @Column
    private String descricao;
}
