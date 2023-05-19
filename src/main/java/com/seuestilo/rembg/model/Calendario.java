package com.seuestilo.rembg.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "calendario")
@Getter
@Setter
public class Calendario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long calendarioID;

    @Column
    private Date data;

    @ManyToOne
    @JoinColumn(name = "lookID")
    private Look look;

    @ManyToOne
    @JoinColumn(name = "userID")
    private Usuario usuario;

}
