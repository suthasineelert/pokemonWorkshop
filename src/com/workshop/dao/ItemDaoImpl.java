package com.workshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.workshop.dao.ItemDao;
import com.workshop.jdbc.ItemRowMapper;
import com.workshop.model.Item;

public class ItemDaoImpl implements ItemDao {
	  
	@Autowired  
	DataSource dataSource; 
	
	@Override
	public Item getItem(int id) {
		List<Item> itemList = new ArrayList<Item>();
		String sql = "SELECT * FROM WS_SHOP WHERE ITEM_ID=" + id;
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		itemList = jdbcTemplate.query(sql, new ItemRowMapper());
		return itemList.get(0);
	}

	@Override
	public List<Item> getItemList() {
		List<Item> itemList = new ArrayList<Item>();
		String sql = "SELECT * FROM WS_SHOP";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		itemList = jdbcTemplate.query(sql, new ItemRowMapper());
		return itemList;
	}

}
