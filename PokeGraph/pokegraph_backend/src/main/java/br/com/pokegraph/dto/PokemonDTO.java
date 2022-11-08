package br.com.pokegraph.dto;

import br.com.pokegraph.enumeration.Type;
import br.com.pokegraph.model.Pokemon;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PokemonDTO {
    private Long id;
    private String name;
    private Type firstType;
    private Type secondType;

    public PokemonDTO(Pokemon pokemon){
        this.id = pokemon.getId();
        this.name = pokemon.getName();
        this.firstType = pokemon.getFirstType();
        this.secondType = pokemon.getSecondType();
    }
}

