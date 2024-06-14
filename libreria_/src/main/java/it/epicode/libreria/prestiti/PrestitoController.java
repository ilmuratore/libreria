package it.epicode.libreria.prestiti;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.List;

@RestController
@RequestMapping("/prestiti")
public class PrestitoController {

    @Autowired
    private PrestitoService service;

     @GetMapping
        public ResponseEntity<List<RegistroPrestito>> findAll(){
         return ResponseEntity.ok(service.findAll1());
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<String> delete(@PathVariable Long id){
         return ResponseEntity.ok(service.delete(id));
     }
}
