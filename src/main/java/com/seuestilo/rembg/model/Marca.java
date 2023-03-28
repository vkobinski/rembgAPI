package com.seuestilo.rembg.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "marca")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long marcaId;
    @Column
    private String descricao;
}
