package br.com.pokegraph.service;

import br.com.pokegraph.dto.PokemonDTO;
import br.com.pokegraph.exception.EmptyListPokemonException;
import br.com.pokegraph.model.Pokemon;
import br.com.pokegraph.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {
    private final PokemonRepository repository;

    public PokemonService(PokemonRepository repository) {
        this.repository = repository;
    }

    public List<PokemonDTO> listAll(){
        List<PokemonDTO> pokemonDTOList = new ArrayList<>();
        List<Pokemon> pokemonList = repository.findAll();
        pokemonList.forEach(pokemon -> pokemonDTOList.add(new PokemonDTO(pokemon)));
        if (pokemonDTOList.isEmpty()) {
            throw new EmptyListPokemonException("Não existe pokêmon's cadastrados!");
        }
        return pokemonDTOList;
    }
}
