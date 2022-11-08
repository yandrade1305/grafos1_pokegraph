package br.com.pokegraph.service;

import br.com.pokegraph.dto.PokemonDTO;
import br.com.pokegraph.enumeration.Type;
import br.com.pokegraph.exception.EmptyListPokemonException;
import br.com.pokegraph.exception.NoExistentPokemonException;
import br.com.pokegraph.model.Pokemon;
import br.com.pokegraph.repository.PokemonRepository;
import br.com.pokegraph.utils.MatrixUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public PokemonDTO battlePokemon(Long firstPokemonId, Long secondPokemonId) {
        Optional<Pokemon> firstPokemon = repository.findById(firstPokemonId);
        Optional<Pokemon> secondPokemon = repository.findById(secondPokemonId);
        PokemonDTO winnerPokemon = new PokemonDTO();
        if (firstPokemon.isPresent() && secondPokemon.isPresent()) {
            // 0 = Sem dano
            // 0.5 = Pouco efetivo
            // 1 = Efetivo
            // 2 = Super Efetivo
            // i = 18 e j = 17

            // pegamos o id do tipo que será a linha que compararemos

            // Pokemon 1 ataca o pokemon 2 usando seu primeiro tipo
            int i1 = Math.toIntExact(firstPokemon.get().getFirstType().getId());
            int j1 = Math.toIntExact(secondPokemon.get().getFirstType().getId());

            Integer result1 = MatrixUtils.result(i1, j1);


            // Pokemon 1 ataca o pokemon 2 usando seu segundo tipo
            int i2 = Math.toIntExact(firstPokemon.get().getSecondType().getId());
            int j2 = Math.toIntExact(secondPokemon.get().getFirstType().getId());

            Integer result2 = MatrixUtils.result(i2, j2);

            // Pokemon 2 ataca o pokemon 1 usando seu primeiro tipo
            int i3 = Math.toIntExact(secondPokemon.get().getFirstType().getId());
            int j3 = Math.toIntExact(firstPokemon.get().getFirstType().getId());

            Integer result3 = MatrixUtils.result(i3, j3);

            // Pokemon 2 ataca o pokemon 1 usando seu segundo tipo
            int i4 = Math.toIntExact(secondPokemon.get().getSecondType().getId());
            int j4 = Math.toIntExact(firstPokemon.get().getSecondType().getId());

            Integer result4 = MatrixUtils.result(i4, j4);

            System.out.println("Resultado 1:" + result1);
            System.out.println("Resultado 2:" + result2);
            System.out.println("Resultado 3:" + result3);
            System.out.println("Resultado 4:" + result4);

        } else {
            throw new NoExistentPokemonException("Não existe pokêmon's cadastrados para esse id: " + firstPokemon);
        }

        return winnerPokemon;

    }
}
