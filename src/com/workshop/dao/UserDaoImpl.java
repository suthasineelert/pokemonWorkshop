package com.workshop.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.workshop.jdbc.PokemonRowMapper;
import com.workshop.jdbc.UserRowMapper;
import com.workshop.model.Pokemon;
import com.workshop.model.User;

public class UserDaoImpl implements UserDao {

	@Autowired
	DataSource dataSource;

	@Override
	public void insertData(User user) {

		String sql = "INSERT INTO WS_USER "
				+ "(USER_NAME, USER_PASSWORD, USER_GENDER, PLAYER_NAME, TEAM_ID, "
				+ "CURRENT_LEVEL, CURRENT_EXP, CURRENT_POCKET_SLOT, MAX_LEVEL_EXP, MAX_POCKET_SLOT ) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql,
				new Object[] { user.getUsername(), user.getPassword(), user.getGender(), user.getPlayerName(),
						user.getTeamId(), user.getCurrentLevel(), user.getCurrentExp(), user.getCurrentPocketSlot(), 
						user.getMaxExp(), user.getMaxPocketSlot() });
	}

	@Override
	public void updateData(User user) {
		String sql = "UPDATE WS_USER SET USER_NAME=?,USER_PASSWORD=?,USER_GENDER=?,PLAYER_NAME=?,"
				+ "TEAM_ID=?,CURRENT_LEVEL=?,CURRENT_EXP=?,CURRENT_POCKET_SLOT=?,MAX_LEVEL_EXP=?,"
				+ "MAX_POCKET_SLOT=? WHERE USER_ID = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql,
				new Object[] { user.getUsername(), user.getPassword(), user.getGender(), user.getPlayerName(),
						user.getTeamId(), user.getCurrentLevel(), user.getCurrentExp(), user.getCurrentPocketSlot(),
						user.getMaxExp(), user.getMaxPocketSlot(), user.getId() });

	}

	@Override
	public User getUser(int id) {
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT * FROM WS_USER WHERE USER_ID=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		userList = jdbcTemplate.query(sql, new UserRowMapper());
		if(userList.size()>0) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public List<User> getUserList() {
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT * FROM WS_USER";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		userList = jdbcTemplate.query(sql, new UserRowMapper());
		return userList;
	}

	@Override
	public User getUser(String username) {
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT * FROM WS_USER WHERE USER_NAME='" + username +"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		userList = jdbcTemplate.query(sql, new UserRowMapper());
		if(userList.size()>0) {
			return userList.get(0);
		}
		return null;
	}
	
	@Override
	public User getUser(String username,String password) {
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT * FROM WS_USER WHERE USER_NAME='" + username +"' AND USER_PASSWORD='"+password+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		userList = jdbcTemplate.query(sql, new UserRowMapper());
		if(userList.size()>0) {
			return userList.get(0);
		}
		return null;
	}
	
	@Override
	public List<Map<String, Object>> getPokedex(int userId){
		
		String sql = "SELECT c.POKEMON_ID,c.POKEMON_NAME,c.POKEMON_TYPE,c.POKEMON_IMAGE,c.POKEMON_LEVEL,c.POKEMON_MIN_CP,c.POKEMON_MAX_CP,b.HUNT_COUNT "
				+ "FROM ws_user_pokedex b LEFT JOIN ws_pokemon c ON b.POKEMON_ID = c.POKEMON_ID "
				+ "WHERE b.USER_ID = " + userId;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);  
		return jdbcTemplate.queryForList(sql);
	}
	
	

}
