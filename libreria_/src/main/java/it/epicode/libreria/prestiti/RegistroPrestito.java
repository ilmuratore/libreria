package it.epicode.libreria.prestiti;

import it.epicode.libreria.persone.Persona;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "prestiti")
public class RegistroPrestito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private double numeroPrestito;

    private LocalDate dataPrestito;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Persona persona;
    // Persona | RegistroPrestito | Many
    // RegistroPrestito | Persona | One

    @OneToMany(mappedBy = "libri_prestati", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<RegistroPrestitoLibri> registroPrestitoLibriList = new ArrayList<>();
    // RegistroPrestitoLibri | RegistroPrestito | One
    // RegistroPrestito      | RegistroPrestitoLibri | Many
}
