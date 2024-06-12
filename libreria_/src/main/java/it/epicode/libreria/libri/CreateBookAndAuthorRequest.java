package it.epicode.libreria.libri;

import lombok.Data;

import java.util.List;

@Data
public class CreateBookAndAuthorRequest {
    private String titolo;
    private Long idCasaEditrice;
    private List<Long> idCategorie;
    private Long idSaga;
    private String nome;
    private String cognome;

}
