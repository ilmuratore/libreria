package it.epicode.libreria.prestiti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PrestitoRunner implements ApplicationRunner {

    @Autowired
    private RegistroPrestitoRepository registroPrestitoRepository;

    @Autowired
    private PrestitoService prestitoService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (registroPrestitoRepository.count() == 0) {
            List<CreatePrestitoRequest> prestiti = Arrays.asList(
                    new CreatePrestitoRequest("Mario", "Rossi", "Via Roma 1", "00100", "Roma", "MRARSS00A01H501U", Arrays.asList(1L, 2L)),
                    new CreatePrestitoRequest("Luigi", "Verdi", "Via Milano 2", "20100", "Milano", "LGVRDI00A01F205S", Arrays.asList(3L, 4L)),
                    new CreatePrestitoRequest("Anna", "Bianchi", "Via Napoli 3", "80100", "Napoli", "NNBCHN00A01L736Z", Arrays.asList(1L, 3L)),
                    new CreatePrestitoRequest("Giulia", "Neri", "Via Torino 4", "10100", "Torino", "GLNRI00A01L219H", Arrays.asList(2L, 4L)),
                    new CreatePrestitoRequest("Marco", "Gialli", "Via Firenze 5", "50100", "Firenze", "MCGLLI00A01H501Y", Arrays.asList(1L, 4L)),
                    new CreatePrestitoRequest("Laura", "Blu", "Via Bologna 6", "40100", "Bologna", "LRBLU00A01L736J", Arrays.asList(2L, 3L)),
                    new CreatePrestitoRequest("Simone", "Viola", "Via Genova 7", "16100", "Genova", "SMNVLA00A01H501P", Arrays.asList(1L, 3L)),
                    new CreatePrestitoRequest("Sara", "Rosa", "Via Venezia 8", "30100", "Venezia", "SRRSA00A01L736X", Arrays.asList(2L, 4L)),
                    new CreatePrestitoRequest("Francesco", "Azzurri", "Via Bari 9", "70100", "Bari", "FRZURR00A01H501E", Arrays.asList(1L, 2L)),
                    new CreatePrestitoRequest("Elena", "Marrone", "Via Palermo 10", "90100", "Palermo", "LNMN00A01L736V", Arrays.asList(3L, 4L))
            );

            prestiti.forEach(request -> prestitoService.presta(request));
            System.out.println("--- Prestiti inseriti --- ");
        } else {
            System.out.println("--- Prestiti già presenti nel database --- ");
        }
    }
}
