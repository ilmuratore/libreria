package it.epicode.libreria.autori;

import lombok.Data;

@Data
public class Response {
    private Long id;
    private String nome;
    private String cognome;
}


// AutoreResponse
// Questo oggetto viene utilizzato quando si desidera resituire i dati di un'autore all'utente.
// Quando si recupera un autore dal db, le proprieta' dell'entita autore vengono copiate in un oggetto AutoreRequest e poi restituito all'utente.
// AutoreResponse rappresenta il DTO dei dati in uscita che l'utente visualizza sulla sua applicazione riguardo i dati di un Utente


