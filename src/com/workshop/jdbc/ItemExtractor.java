package com.workshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.workshop.model.Item;

public class ItemExtractor implements ResultSetExtractor<Item> {

	@Override
	public Item extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		Item item = new Item();
		item.setId(resultSet.getInt(1));
		item.setName(resultSet.getString(2));
		item.setPrice(resultSet.getString(3));
		item.setNoPokeball(resultSet.getInt(4));
		return item;
	}

}
