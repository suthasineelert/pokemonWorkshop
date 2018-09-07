package com.workshop.services;

import java.util.List;
import java.util.Map;

import com.workshop.model.Pokemon;
import com.workshop.model.User;

public interface UserService {
	public void insertData(User user);
	public void updateData(User user);
	public User getUser(int id);
	public List<User> getUserList();
	public List<Map<String, Object>> getPokedex(int userId);
	public User getUser(String username);
	public User getUser(String username, String password);
}
