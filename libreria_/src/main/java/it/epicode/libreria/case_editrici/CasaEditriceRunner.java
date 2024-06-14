package it.epicode.libreria.case_editrici;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(2)
public class CasaEditriceRunner implements ApplicationRunner {

    @Autowired
    private CasaEditriceRepository casaEditriceRepository;

    @Autowired
    private CasaEditriceService casaEditriceService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (casaEditriceRepository.count() == 0) {
            List<Request> caseEditrici = Arrays.asList(
                    new Request("Casa Editrice A", "Via Roma 1", "Roma", "00100"),
                    new Request("Casa Editrice B", "Via Milano 2", "Milano", "20100"),
                    new Request("Casa Editrice C", "Via Napoli 3", "Napoli", "80100"),
                    new Request("Casa Editrice D", "Via Torino 4", "Torino", "10100"),
                    new Request("Casa Editrice E", "Via Firenze 5", "Firenze", "50100"),
                    new Request("Casa Editrice F", "Via Bologna 6", "Bologna", "40100"),
                    new Request("Casa Editrice G", "Via Genova 7", "Genova", "16100"),
                    new Request("Casa Editrice H", "Via Venezia 8", "Venezia", "30100"),
                    new Request("Casa Editrice I", "Via Bari 9", "Bari", "70100"),
                    new Request("Casa Editrice J", "Via Palermo 10", "Palermo", "90100")
            );

            caseEditrici.forEach(request -> casaEditriceService.create(request));
            System.out.println("--- Case Editrici inserite --- ");
        } else {
            System.out.println("--- Case Editrici gia' inserite --- ");
        }
    };
}

