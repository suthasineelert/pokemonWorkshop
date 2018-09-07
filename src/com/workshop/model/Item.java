package com.workshop.model;

public class Item {
	private int id;
	private String name;
	private String price;
	private int noPokeball;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getNoPokeball() {
		return noPokeball;
	}
	public void setNoPokeball(int noPokeball) {
		this.noPokeball = noPokeball;
	}
	
	@Override
	  public String toString() {
	    StringBuilder buf = new StringBuilder(30);
	    buf.append("{");
	    buf.append(id);
	    buf.append(", ");
	    buf.append(name);
	    buf.append(", ");
	    buf.append(price);
	    buf.append(", ");
	    buf.append(noPokeball);
	    buf.append("}");
	    return buf.toString();
	  }
	
}
