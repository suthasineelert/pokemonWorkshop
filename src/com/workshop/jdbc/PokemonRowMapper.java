package com.workshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.workshop.model.Pokemon;

public class PokemonRowMapper implements RowMapper<Pokemon> {

	@Override  
	 public Pokemon mapRow(ResultSet resultSet, int line) throws SQLException {  
	  PokemonExtractor pokemonExtractor = new PokemonExtractor();  
	  return pokemonExtractor.extractData(resultSet);  
	 }  

}
