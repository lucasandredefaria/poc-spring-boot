package com.testsaraiva.pocspringboot.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Data;

@Data
public class BookModel {
	
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	private int sku;
	private String name;
	private String brand;
	private Price price;
	
	public String toJson(BookModel bookModel) {
		return gson.toJson(bookModel);
	}
		
}
