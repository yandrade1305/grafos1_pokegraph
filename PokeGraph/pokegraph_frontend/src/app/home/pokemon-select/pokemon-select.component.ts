import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup } from '@angular/forms';
import { PokemonService } from './pokemon.service';
import { Pokemon } from './pokemons';
import {DomSanitizer} from "@angular/platform-browser";

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



  @Input()
  pokemonForm: FormGroup | undefined;

  options: any[] = [];
  imageAlt: any;
  imageSource: any;

  constructor(private pokemonService: PokemonService) {

  }

  ngOnInit(): void {
    this.pokemonService.listPokemon().subscribe((response: any) => {
      this.options = response.map((pokemon: Pokemon) => {
        this.extract(pokemon);
      });
      console.log(this.options);
    });
  }

  extract(pokemon: Pokemon) {
    return {
      id: pokemon.id,
      name: pokemon.name,
      url: pokemon.url
    };
  }



}
