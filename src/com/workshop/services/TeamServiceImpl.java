package com.workshop.services;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.workshop.dao.TeamDao;
import com.workshop.model.Team;

public class TeamServiceImpl implements TeamService {

	@Autowired  
	TeamDao teamdao; 
	
	@Override
	public List<Team> getTeamList() {
		return teamdao.getTeamList();  
	}

	@Override
	public Team getTeam(int id) {
		return teamdao.getTeam(id); 
	}
	
	@Override
	public String[][] getPlayerandPokemonCount() { 
		//Row - teamId, column - teamName, playerCount, pokemonCount, huntPerPlayer
		String[][] result = new String[3][4]; 
		List<Map<String, Object>> team1 = teamdao.getPlayerandPokemonCount(1);
		List<Map<String, Object>> team2 = teamdao.getPlayerandPokemonCount(2);
		List<Map<String, Object>> team3 = teamdao.getPlayerandPokemonCount(3);
		
		String teamName = "";
		int playerCount = 0, pokemonCount = 0;
		String huntPerPlayer = "0";
		DecimalFormat df = new DecimalFormat("0.00");
		
		for (Map<String, Object> t : team1) {
			teamName = t.get("TEAM_NAME").toString();
			playerCount = Integer.valueOf(t.get("COUNT_USER").toString());

			if(t.get("HUNT_COUNT") != null) {
				pokemonCount = Integer.valueOf(t.get("HUNT_COUNT").toString());
			}
			else pokemonCount = 0;
			
			huntPerPlayer = df.format((double)playerCount/pokemonCount);
	    }
		result[0][0] = teamName;
		result[0][1] = Integer.toString(playerCount);
		result[0][2] = Integer.toString(pokemonCount);
		result[0][3] = huntPerPlayer;
	
		for (Map<String, Object> t : team2) {
			teamName = t.get("TEAM_NAME").toString();
			playerCount = Integer.valueOf(t.get("COUNT_USER").toString());

			if(t.get("HUNT_COUNT") != null) {
				pokemonCount = Integer.valueOf(t.get("HUNT_COUNT").toString());
			}
			else pokemonCount = 0;
			
			huntPerPlayer = df.format((double)playerCount/pokemonCount);
	    }
		result[1][0] = teamName;
		result[1][1] = Integer.toString(playerCount);
		result[1][2] = Integer.toString(pokemonCount);
		result[1][3] = huntPerPlayer;
		
		for (Map<String, Object> t : team3) {
			teamName = t.get("TEAM_NAME").toString();
			playerCount = Integer.valueOf(t.get("COUNT_USER").toString());

			if(t.get("HUNT_COUNT") != null) {
				pokemonCount = Integer.valueOf(t.get("HUNT_COUNT").toString());
			}
			else pokemonCount = 0;
			
			huntPerPlayer = df.format((double)playerCount/pokemonCount);
	    }
		result[2][0] = teamName;
		result[2][1] = Integer.toString(playerCount);
		result[2][2] = Integer.toString(pokemonCount);
		result[2][3] = huntPerPlayer;
		
		return result;   
	}

}
