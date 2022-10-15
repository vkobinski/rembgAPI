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
public class DicEventos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @Column
    private String descEvento;

    @Column
    private String acaoFirebase;

    @Column
    private String parametro;
}
