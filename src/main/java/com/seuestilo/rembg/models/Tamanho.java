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
public class Tamanho {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tamanhoID;

    @OneToMany(mappedBy = "IDtamanho")
    private Set<Peca> pecas;

    @Column
    private String descricao;
}
