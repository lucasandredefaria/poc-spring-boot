package com.testsaraiva.pocspringboot.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Book {
	
	@Id
	private int sku;
	
	private String name;
	
	private String brand;
	
	private double price;
		
}
