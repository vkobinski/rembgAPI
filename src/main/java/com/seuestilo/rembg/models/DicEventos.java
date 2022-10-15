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

    @OneToMany(mappedBy = "IDdic")
    private Set<FirebaseEvento> firebaseEventos;

    private String descEvento;
    private String acaoFirebase;
    private String parametro;
}
