package com.workshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.workshop.jdbc.TeamRowMapper;
import com.workshop.model.Team;

public class TeamDaoImpl implements TeamDao {

	@Autowired  
	DataSource dataSource;  
	
	@Override
	public Team getTeam(int teamId) {
		List<Team> teamList = new ArrayList<Team>();
		String sql = "SELECT * FROM WS_TEAM WHERE TEAM_ID=" + teamId;
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		teamList = jdbcTemplate.query(sql, new TeamRowMapper());
		return teamList.get(0);
	}

	@Override
	public List<Team> getTeamList() {
		 List<Team> teamList = new ArrayList<Team>();  
		  
		  String sql = "SELECT * from WS_TEAM";  
		  
		  JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);  
		  teamList = jdbcTemplate.query(sql, new TeamRowMapper());  
		  return teamList;  
	}
	
	@Override
	public List<Map<String, Object>> getPlayerandPokemonCount(int teamId) {  
		  String sql = "SELECT a.TEAM_ID, TEAM_NAME, COUNT(DISTINCT b.USER_ID) AS COUNT_USER , "
		  		+ "SUM(HUNT_COUNT) AS HUNT_COUNT"
		  		+ " FROM WS_TEAM a LEFT JOIN WS_USER b ON a.TEAM_ID = b.TEAM_ID"
		  		+ " LEFT JOIN WS_USER_POKEDEX c ON c.USER_ID = b.USER_ID"
		  		+ " GROUP BY a.TEAM_ID , a.TEAM_NAME HAVING a.TEAM_ID = " + teamId;  
		  
		  JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);  
		  return jdbcTemplate.queryForList(sql);   
	}

}
