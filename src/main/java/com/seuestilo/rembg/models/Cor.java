package com.seuestilo.rembg.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cor")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long corID;

    @OneToMany(mappedBy = "IDcor")
    private Set<Peca> pecas;

    @Column
    private String descricao;
}
