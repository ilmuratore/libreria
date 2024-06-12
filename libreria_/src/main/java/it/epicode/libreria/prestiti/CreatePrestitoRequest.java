package it.epicode.libreria.prestiti;

import it.epicode.libreria.persone.Persona;
import lombok.Data;

import java.util.List;

@Data
public class CreatePrestitoRequest {

    private String nome;
    private String cognome;
    private String indirizzo;
    private String cap;
    private String citta;
    private String codiceFiscale;
    private List<Long> idLibri;

}
