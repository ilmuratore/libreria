package it.epicode.libreria.autori;

import it.epicode.libreria.libri.Libro;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "autori")
public class Autore {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50)
    private String nome;
    @Column(length = 50)
    private String cognome;

    @OneToMany(mappedBy = "autore")
    @ToString.Exclude
    private List<Libro> libri;
}
