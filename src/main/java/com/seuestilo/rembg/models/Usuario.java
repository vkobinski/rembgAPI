package com.seuestilo.rembg.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;

    @OneToMany(mappedBy = "IDusuario")
    private Set<Peca> pecas;

    @OneToMany(mappedBy = "idUser")
    private Set<Look> looks;

    @Column
    private String nome;

    @Column
    private String email;

    @OneToMany(mappedBy = "userID")
    private Set<FirebaseEvento> firebaseEventos;
}
