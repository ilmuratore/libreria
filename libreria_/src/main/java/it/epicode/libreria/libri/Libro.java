package it.epicode.libreria.libri;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.libreria.autori.Autore;
import it.epicode.libreria.case_editrici.CasaEditrice;
import it.epicode.libreria.categoria.Categoria;
import it.epicode.libreria.saga.Saga;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "libri")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String titolo;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // Cascade PERSIST crea la sotto-entita' quando non presente, MERGE modifica la sotto-entity *vedi metodo bookAndAuthorModify nel serviceLibro.
    @ToString.Exclude // aggiungere sempre il ToString e il JsonProperties alla creazione di una entity relazionabile ad altra entity
    @JsonIgnoreProperties({"libri","id"}) // possibile sia att. con {"x"} per piu elementi, che singolo "x"
    // @JsonIgnore // elimina dalla response l'intera entity non molto utile in caso di relazioni bidirezionali preferibile approccio con DtoS
    private Autore autore;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnoreProperties("libro")
    private CasaEditrice casaEditrice;

    @ManyToMany
    @ToString.Exclude
    @JsonIgnoreProperties("libro")
    private List<Categoria> categorie;

    @ManyToOne
    @ToString.Exclude // serve ad escludere l'oggetto (saga) dal ToString del Libro, quindi se viene utilizzato un systemOut lo stesso non va in Loop.
    @JsonIgnoreProperties("libri")
    private Saga saga;

    private boolean isAvailable = true;

}
