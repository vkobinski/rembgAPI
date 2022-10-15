package com.seuestilo.rembg.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "firebaseEvento")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FirebaseEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long IDevento;

    private String evento;

    @ManyToOne
    @JoinColumn(name = "IDdic")
    private DicEventos dicEventos;

    @ManyToOne
    @JoinColumn(name = "userID")
    private Usuario usuario;
}
