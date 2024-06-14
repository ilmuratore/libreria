package it.epicode.libreria.autori;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(1) // annotazione che permette l'esecuzione del runner in avvio secondo un preciso ordine
public class AutoreRunner implements ApplicationRunner {

    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private AutoreService autoreService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (autoreRepository.count() == 0) {
            List<Request> autori = Arrays.asList(
                    new Request("Giovanni", "Bianchi"),
                    new Request("Maria", "Rossi"),
                    new Request("Luca", "Verdi"),
                    new Request("Elena", "Neri"),
                    new Request("Marco", "Gialli"),
                    new Request("Sara", "Azzurri"),
                    new Request("Paolo", "Rossi"),
                    new Request("Anna", "Bianchi"),
                    new Request("Stefano", "Neri"),
                    new Request("Chiara", "Verdi")
            );

            autori.forEach(request -> autoreService.create(request));
            System.out.println("--- Autori inseriti ---");
        } else {
            System.out.println("--- Autori già inseriti ---");
        }
    }
}
