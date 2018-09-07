package com.workshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.workshop.dao.PokedexDao;
import com.workshop.model.Pokedex;

public class PokedexServiceImpl implements PokedexService {

	@Autowired
	PokedexDao pokedexdao;
	
	@Override
	public void insertOrUpdateData(Pokedex pokedex) {
		Pokedex p = pokedexdao.getPokedex(pokedex.getUserId(), pokedex.getPokemonId());

		if(p!=null) {
			//Not first time catching this pokemon -> increment huntCount
			p.setHuntCount(p.getHuntCount()+1);
			pokedexdao.updateData(p);
			return;
		}
		//First time catching this pokemon -> huntCount = 1
		pokedex.setHuntCount(1);
		pokedexdao.insertData(pokedex);
	}

	@Override
	public Pokedex getPokedex(int userId, int pokemonId) {
		return pokedexdao.getPokedex(userId, pokemonId);
	}

	@Override
	public List<Pokedex> getPokedexList() {
		return pokedexdao.getPokedexList();
	}

}
