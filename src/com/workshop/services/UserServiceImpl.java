package com.workshop.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.workshop.dao.UserDao;
import com.workshop.model.Pokemon;
import com.workshop.model.User;

public class UserServiceImpl implements UserService {

	@Autowired  
	UserDao userdao; 
	
	@Override
	public void insertData(User user) {
		userdao.insertData(user);
	}

	@Override
	public void updateData(User user) {
		userdao.updateData(user);
	}

	@Override
	public User getUser(int id) {
		return userdao.getUser(id);
	}

	@Override
	public List<User> getUserList() {
		return userdao.getUserList();
	}

	@Override
	public User getUser(String username) {
		return userdao.getUser(username);
	}
	
	@Override
	public User getUser(String username,String password) {
		return userdao.getUser(username,password);
	}

	@Override
	public List<Map<String, Object>> getPokedex(int userId) {
		return userdao.getPokedex(userId);
	}

}
