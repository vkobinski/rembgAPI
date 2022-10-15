package com.seuestilo.rembg.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cor")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long corID;

    @Column
    private String descricao;
}
