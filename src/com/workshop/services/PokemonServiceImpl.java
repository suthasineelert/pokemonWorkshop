package com.workshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.workshop.dao.PokemonDao;
import com.workshop.model.Pokemon;

public class PokemonServiceImpl implements PokemonService {

	@Autowired  
	PokemonDao pokemondao; 
	
	@Override
	public void insertData(Pokemon pokemon) {
		pokemondao.insertData(pokemon);
	}

	@Override
	public void updateData(Pokemon pokemon) {
		pokemondao.updateData(pokemon);
	}

	@Override
	public Pokemon getPokemon(int id) {
		return pokemondao.getPokemon(id);
	}

	@Override
	public List<Pokemon> getPokemonList() {
		return pokemondao.getPokemonList();
	}
	
	@Override
	public Pokemon getRandomPokemon() {
		return pokemondao.getRandomPokemon();
	}

}
