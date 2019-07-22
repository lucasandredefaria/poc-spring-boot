package com.testsaraiva.pocspringboot.api;

import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;
import com.testsaraiva.pocspringboot.exception.BookNotFoundException;
import com.testsaraiva.pocspringboot.model.Book;

public class SaraivaService {
	
	static String urlBase = "https://api.saraiva.com.br/sc/produto/pdp/";
	static RestTemplate restTemplate = new RestTemplate();	
	
	public Book getBookSaraiva(int sku) {
		String endpoint = urlBase + sku + "/0/0/1/";
		
		String result = restTemplate.getForObject(endpoint, String.class);
		
		Book book = new Book();
		book.setSku(Integer.parseInt(JsonPath.read(result, "$.sku")));
		book.setName(JsonPath.read(result, "$.name"));
		book.setBrand(JsonPath.read(result, "$.brand"));		
		book.setPrice(Double.parseDouble(JsonPath.read(result, "$.price.bestPrice.value").toString().replace(",", ".")));
		
		return book;
	}
	
}
