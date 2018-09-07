package com.workshop.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.workshop.model.Pokedex;
import com.workshop.model.User;

public interface PokeballReloadService {
	public void insertData(int userId, String timestamp);
	public void updateData(int userId, String timestamp);
	public void deleteData(int userId);
	public String getTimestamp(int userId);
	User checkPokeball(User user);
	void setTimer(User user);
	void stopTimer();
	String[] findDiffTime(String oldTime);
	void findDiffTime(HttpSession session, String oldTime, long oldTimeleft);
}
