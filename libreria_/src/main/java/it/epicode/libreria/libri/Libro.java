package it.epicode.libreria.libri;


import it.epicode.libreria.autori.Autore;
import it.epicode.libreria.case_editrici.CasaEditrice;
import it.epicode.libreria.categoria.Categoria;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "libri")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String titolo;

    @ManyToOne
    private Autore autore;

    @ManyToOne
    private CasaEditrice casaEditrice;

    @ManyToMany
    private List<Categoria> categorie;
}
