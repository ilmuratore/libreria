package it.epicode.libreria.saga;

import it.epicode.libreria.libri.Libro;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "saghe")
public class Saga {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private int numeroVolumi;

    @OneToMany(mappedBy = "saga")
    @ToString.Exclude
    private List<Libro> libri;
}
