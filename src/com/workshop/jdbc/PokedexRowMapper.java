package com.workshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.workshop.model.Pokedex;

public class PokedexRowMapper implements RowMapper<Pokedex> {
	 @Override  
	 public Pokedex mapRow(ResultSet resultSet, int line) throws SQLException {  
		 PokedexExtractor pExtractor = new PokedexExtractor();  
	  return pExtractor.extractData(resultSet);  
	 }  
}
