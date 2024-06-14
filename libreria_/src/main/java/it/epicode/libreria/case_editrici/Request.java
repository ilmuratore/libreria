package it.epicode.libreria.case_editrici;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {
    private String nome;
    private String indirizzo;
    private String citta;
    private String cap;
}

