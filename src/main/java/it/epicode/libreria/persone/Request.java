package it.epicode.libreria.persone;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {

    @NotEmpty(message = "il campo nome e' obbligatorio")
    private String nome;

    @NotEmpty(message = "il campo cognome e' obbligatorio")
    private String cognome;

    @NotEmpty(message = "il compo email e' obbligatorio")
    @Email
    private String email;

    @NotEmpty(message = "il campo indirizzo e' obbligatorio")
    private String indirizzo;

    @NotEmpty(message = "il campo cap e' obbligatorio")
    private String cap;

    @NotEmpty(message = "il campo citta e' obbligatorio")
    private String citta;

    @NotEmpty(message = "il campo codice fiscale e' obbligatorio")
    private String codiceFiscale;
}
