package com.workshop.services;

import java.util.List;

import com.workshop.model.Item;

public interface ItemService { 
   public Item getItem(int id);
	public List<Item> getItemList(); 
}
