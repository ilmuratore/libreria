package it.epicode.libreria.libri;

import it.epicode.libreria.categoria.Categoria;
import lombok.Data;

import java.util.List;

@Data
public class CompleteResponse {
    private Long id;
    private String titolo;
    private it.epicode.libreria.autori.Response autore;
    private it.epicode.libreria.case_editrici.LightResponse casaEditrice;
    private List<Categoria> categorie;
    private it.epicode.libreria.saga.Response saga;

}
