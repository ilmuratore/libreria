package it.epicode.libreria.prestiti;

import org.springframework.beans.factory.annotation.Value;

public interface PrestitoResponsePrj {

    public String getDataPrestito();

    @Value("#{target.persona.nome")
    public String getNomePersona();

    @Value("#{target.persona.cognome}")
    public String getCognomePersona();

    @Value("#{target.persona.indirizzo}")
    public String getIndirizzoPersona();
}
