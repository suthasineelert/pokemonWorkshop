package com.workshop.dao;

import java.util.List;

import com.workshop.model.Pokemon;
import com.workshop.model.User;

public interface PokeballReloadDao {
	void updateData(int userId, String timestamp);
	void insertData(int userId, String timestamp);
	void removeData(int userId);
	String getTimestamp(int userId);
}
