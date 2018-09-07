package com.workshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.workshop.model.Item;

public class ItemRowMapper implements RowMapper<Item> {

	@Override
	public Item mapRow(ResultSet resultSet, int line) throws SQLException {
		ItemExtractor itemExtractor = new ItemExtractor();  
		return itemExtractor.extractData(resultSet);
	}

}
