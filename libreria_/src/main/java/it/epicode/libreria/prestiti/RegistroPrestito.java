package it.epicode.libreria.prestiti;

import it.epicode.libreria.persone.Persona;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
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

    @ManyToOne
    private Persona persona;

    @OneToMany
    private List<RegistroPrestitoLibri> registroPrestitoLibriList;
}
