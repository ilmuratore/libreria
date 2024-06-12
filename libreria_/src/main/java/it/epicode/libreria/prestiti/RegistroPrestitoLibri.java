package it.epicode.libreria.prestiti;

import it.epicode.libreria.libri.Libro;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "libri_prestati")
public class RegistroPrestitoLibri {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany // ricordati di inserire la relazione nella entity
    private Libro libro;

    private boolean isRestituito = false;

    @ManyToOne
    private RegistroPrestito registroPrestito;


}
