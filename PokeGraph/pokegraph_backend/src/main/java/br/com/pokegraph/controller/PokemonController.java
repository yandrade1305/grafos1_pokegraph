package br.com.pokegraph.controller;

import br.com.pokegraph.exception.EmptyListPokemonException;
import br.com.pokegraph.service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pokemon")
public class PokemonController {
    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping(path = "/pokemons")
    public ResponseEntity<?> listAllPokemon(){
        try {
            return ResponseEntity.ok(service.listAll());
        } catch (EmptyListPokemonException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
