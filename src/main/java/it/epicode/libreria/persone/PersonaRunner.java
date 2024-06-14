package it.epicode.libreria.persone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PersonaRunner implements ApplicationRunner {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaService personaService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (personaRepository.count() == 0) {
            List<Request> persone = Arrays.asList(
                    new Request("Mario", "Rossi", "mario.rossi@example.com", "Via Roma 1", "00100", "Roma", "MRARSS00A01H501U"),
                    new Request("Luigi", "Verdi", "luigi.verdi@example.com", "Via Milano 2", "20100", "Milano", "LGVRDI00A01F205S"),
                    new Request("Anna", "Bianchi", "anna.bianchi@example.com", "Via Napoli 3", "80100", "Napoli", "NNBCHN00A01L736Z"),
                    new Request("Giulia", "Neri", "giulia.neri@example.com", "Via Torino 4", "10100", "Torino", "GLNRI00A01L219H"),
                    new Request("Marco", "Gialli", "marco.gialli@example.com", "Via Firenze 5", "50100", "Firenze", "MCGLLI00A01H501Y"),
                    new Request("Laura", "Blu", "laura.blu@example.com", "Via Bologna 6", "40100", "Bologna", "LRBLU00A01L736J"),
                    new Request("Simone", "Viola", "simone.viola@example.com", "Via Genova 7", "16100", "Genova", "SMNVLA00A01H501P"),
                    new Request("Sara", "Rosa", "sara.rosa@example.com", "Via Venezia 8", "30100", "Venezia", "SRRSA00A01L736X"),
                    new Request("Francesco", "Azzurri", "francesco.azzurri@example.com", "Via Bari 9", "70100", "Bari", "FRZURR00A01H501E"),
                    new Request("Elena", "Marrone", "elena.marrone@example.com", "Via Palermo 10", "90100", "Palermo", "LNMN00A01L736V")
            );

            persone.forEach(request -> personaService.create(request));
            System.out.println("--- Persone inserite --- ");
        } else {
            System.out.println("--- Persone gia' inserite --- ");
        }
    }
}
