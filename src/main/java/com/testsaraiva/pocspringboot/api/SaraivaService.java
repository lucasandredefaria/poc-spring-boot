package com.testsaraiva.pocspringboot.api;

import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;
import com.testsaraiva.pocspringboot.model.Book;

public class SaraivaService {
	
	String urlBase = "https://api.saraiva.com.br/sc/produto/pdp/";
	
	public Book getBookSaraiva(int sku) {
		String endpoint = urlBase + sku + "/0/0/1/";
		
		RestTemplate restTemplate = new RestTemplate();		
		String result = restTemplate.getForObject(endpoint, String.class);

		Book book = new Book();
		book.setSku(JsonPath.read(result, "$.sku").toString());
		book.setName(JsonPath.read(result, "$.name").toString());
		book.setBrand(JsonPath.read(result, "$.brand").toString());
		book.setPrice(JsonPath.read(result, "$.price.bestPrice.value").toString().replace(",", "."));
		
		return book;
	}
	
}
