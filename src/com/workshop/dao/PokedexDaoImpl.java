package com.workshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.workshop.jdbc.PokedexRowMapper;
import com.workshop.model.Pokedex;

public class PokedexDaoImpl implements PokedexDao {

	@Autowired
	DataSource dataSource;

	@Override
	public void insertData(Pokedex pokedex) {
		String sql = "INSERT INTO WS_USER_POKEDEX (USER_ID, POKEMON_ID, HUNT_COUNT)"
				+ " VALUES (?,?,?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, new Object[] { pokedex.getUserId(), pokedex.getPokemonId(), pokedex.getHuntCount() });
	}

	@Override
	public void updateData(Pokedex pokedex) {
		String sql = "UPDATE WS_USER_POKEDEX SET HUNT_COUNT=? WHERE USER_ID=? AND POKEMON_ID=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, new Object[] { pokedex.getHuntCount(), pokedex.getUserId(), pokedex.getPokemonId() });
	}

	@Override
	public Pokedex getPokedex(int userId, int pokemonId) {
		List<Pokedex> pList = new ArrayList<Pokedex>();
		String sql = "SELECT * FROM WS_USER_POKEDEX WHERE USER_ID=" + userId + " AND POKEMON_ID=" + pokemonId;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		pList = jdbcTemplate.query(sql, new PokedexRowMapper());
		if(pList.size() >0) return pList.get(0);
		return null;
	}

	@Override
	public List<Pokedex> getPokedexList() {
		List<Pokedex> pList = new ArrayList<Pokedex>();
		String sql = "SELECT * FROM WS_USER_POKEDEX";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		pList = jdbcTemplate.query(sql, new PokedexRowMapper());
		return pList;
	}

}
