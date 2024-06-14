package it.epicode.libreria.persone;


import it.epicode.libreria.prestiti.RegistroPrestito;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "persone")

public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    private String indirizzo;

    private String cap;

    private String citta;

    @Column(nullable = false)
    private String codiceFiscale;

    @OneToMany(mappedBy = "persona")
    private List<RegistroPrestito> prestiti;
    // RegistroPrestito | Persona | One
    // Persona          | RegistroPrestito | Many

}
