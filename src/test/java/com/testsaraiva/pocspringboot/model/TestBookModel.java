package com.testsaraiva.pocspringboot.model;

import lombok.Data;

@Data
public class TestBookModel {
	
	private int sku;
	private String name;
	private String brand;
	private TestPrice price;
	private TestBestPrice bestPrice;
	
}
