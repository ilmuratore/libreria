package it.epicode.libreria.prestiti;

import it.epicode.libreria.libri.Libro;
import it.epicode.libreria.libri.LibroRepository;
import it.epicode.libreria.persone.Persona;
import it.epicode.libreria.persone.PersonaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrestitoService {

    private final RegistroPrestitoRepository prestitoRepository;
    private final PersonaRepository personaRepository;
    private final LibroRepository libroRepository;

    // Find all utilizzando il Projection Repository
    public List<PrestitoResponsePrj> findAll(){
        return prestitoRepository.findAllBy();
    }


/*
*  Il metodo `presta` per prima cosa verifica l'esistenza della `Persona`
*  attraverso il metodo del repository `existsByCodiceFiscaleAndNomeAndCognome`
*  se la `Persona` esiste la carica, se non esiste la crea.
*  Viene creato un prestito e vengono valorizzati i campi scalari del prestito
*  (id, data del prestito) e viene associata la `Persona`
*  Si cicla l'elenco dei `Libri` prestati caricati da DB tramite
*  un finAllById al quale vengono passati gli Id's forniti dal F-E
*  in un ArrayList.
*  Recuperati i `Libri` essi vengono ciclati.
*  All'interno del ciclo si controlla la disponibilita' del libro
*  e se e' disponibile si prepara una riga di `Prestito`
*  A questo punto si associa il libro alla riga di `Prestito`
*  e si aggiunge la riga di `Prestito` al Prestito stesso
*  prestito.getRegistroPrestitoLibriList().add(registroPrestitoLibri).
*  Una volta fatto questo, si incrementa un contatore che serve a tener traccia di
*  quanti libri effettivamente sono stati prestati, ossia quelli che erano disponibili.
*  Finito il ciclo si salva il Prestito il quale a causa dei Cascade definiti in fase di relazione classi/attributi
*  se necessario creera la `Persona` e creera anche tutte le righe di prestito.
*  Alla fine creiamo la Response a cui passiamo id del prestito e numeri di libri prestati
*
* */
    @Transactional
    public Response presta(CreatePrestitoRequest request){
        Persona entity;
        List<String> titoliLibriPrestati = new ArrayList<>();
        if(!personaRepository.existsByCodiceFiscaleAndNomeAndCognome(request.getCodiceFiscale(), request.getNome(), request.getCognome())){
            entity = new Persona();
            BeanUtils.copyProperties(request, entity);
            personaRepository.save(entity);
        }
        entity = personaRepository.findByCodiceFiscaleAndNomeAndCognome(request.getCodiceFiscale(), request.getNome(), request.getCognome());
        RegistroPrestito prestito = new RegistroPrestito();
        prestito.setPersona(entity);
        prestito.setDataPrestito(LocalDate.now());
        List<Libro> libriPrestati = libroRepository.findAllById(request.getIdLibri());
        int numeroLibriPrestati = 0;
        for (Libro libro: libriPrestati) {
            if (libro.isAvailable()) {
                numeroLibriPrestati++;
                RegistroPrestitoLibri registroPrestitoLibri = new RegistroPrestitoLibri();
                registroPrestitoLibri.setLibro(libro);
                libro.setAvailable(false);
                titoliLibriPrestati.add(libro.getTitolo());
                /*
                   Non e' sufficiente aggiungere il libro al prestito ma e' necessario associare
                   al libro prestato il prestito di riferimento
                   *1,*2
                 */
                registroPrestitoLibri.setRegistroPrestito(prestito); //*1
                prestito.getRegistroPrestitoLibriList().add(registroPrestitoLibri); //*2
                System.out.println("Il libro prestato e' " + libro.getTitolo());
            }
        }
        prestitoRepository.save(prestito);
        Response response = new Response();
        response.setId(prestito.getId());
        response.setNumeroLibriPrestati(numeroLibriPrestati);
        response.setTitoliLibriPrestati(titoliLibriPrestati);
        return response;
    }


    public List<RegistroPrestito> findAll1(){
        return prestitoRepository.findAll();
    }


    public String delete(Long id){
        prestitoRepository.deleteById(id);
        return "Prestito eliminato";

    }
}
