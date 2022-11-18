import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {PokemonService} from './pokemon.service';
import {Pokemon} from './pokemons';
import {MatSelectChange} from "@angular/material/select";
import {MatDialog} from "@angular/material/dialog";
import {ModalWinnerComponent} from "../modal-winner/modal-winner.component";


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
  pokemon1: any = null;
  pokemon2: any = null;

  pokemonForm = this.formBuilder.group({
    pokemon1: '',
    pokemon2: '',
  });

  constructor(
    private pokemonService: PokemonService,
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
  ) {

  }

  ngOnInit(): void {
    console.log(this.pokemon1)
    this.pokemonService.listPokemon().subscribe((response: any) => {
      response.map((pokemon: Pokemon) => {
        this.extract(pokemon);
      });
      this.options = response
      console.log(this.options)
    });
  }

  openDialog(response: any): void {
    const dialogRef = this.dialog.open(ModalWinnerComponent, {
      width: '500px',
      height: '500px',
      data: response,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
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
    if($event.value !== null){
      this.imageSourceSelf = $event.value.url
      this.pokemon1 = $event.value.id
    } else {
      this.pokemon1 = $event.value
    }
  }
  loadPokemonOther($event: MatSelectChange) {
    if($event.value !== null){
      this.imageSourceOther = $event.value.url
      this.pokemon2 = $event.value.id
    } else {
      this.pokemon2 = $event.value
    }
  }

  battle() {
    this.pokemonService.battlePokemon(this.pokemon1, this.pokemon2).subscribe((response: any) => {
      this.openDialog(response);
    });

  }
}
