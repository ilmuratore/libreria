package it.epicode.libreria.libri;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Request {

    private String titolo;
    private List<Long> idCategorie;
    private Long idAutore;
    private Long idCasaEditrice;
    private Long idSaga;

}
