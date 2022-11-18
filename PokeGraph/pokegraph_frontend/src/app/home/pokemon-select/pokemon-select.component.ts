import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {PokemonService} from './pokemon.service';
import {Pokemon} from './pokemons';
import {MatSelectChange} from "@angular/material/select";

interface Food {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-pokemon-select',
  templateUrl: './pokemon-select.component.html',
  styleUrls: ['./pokemon-select.component.css']
})
export class PokemonSelectComponent implements OnInit {

  options: any[] = [];
  imageAlt: any;
  imageSourceSelf: any;
  imageSourceOther: any;
  pokemon1: any;
  pokemon2: any;

  pokemonForm = this.formBuilder.group({
    pokemon1: '',
    pokemon2: '',
  });

  constructor(
    private pokemonService: PokemonService,
    private formBuilder: FormBuilder,
  ) {

  }

  ngOnInit(): void {
    this.pokemonService.listPokemon().subscribe((response: any) => {
      response.map((pokemon: Pokemon) => {
        this.extract(pokemon);
      });
      this.options = response
      console.log(this.options)
    });
  }

  extract(pokemon: Pokemon) {
    return {
      id: pokemon.id,
      name: pokemon.name,
      url: pokemon.url
    };
  }


  loadPokemonSelf($event: MatSelectChange) {
    this.imageSourceSelf = $event.value.url
    this.pokemon1 = $event.value.id
  }
  loadPokemonOther($event: MatSelectChange) {
    this.imageSourceOther = $event.value.url
    this.pokemon2 = $event.value.id

  }

  battle() {
    this.pokemonService.battlePokemon(this.pokemon1, this.pokemon2).subscribe((response: any) => {
      console.log(response)
    });

  }
}
