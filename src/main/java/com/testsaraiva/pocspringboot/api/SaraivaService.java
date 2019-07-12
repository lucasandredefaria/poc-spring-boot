package com.testsaraiva.pocspringboot.api;

import org.springframework.web.client.RestTemplate;
import com.testsaraiva.pocspringboot.model.BookModel;

public class SaraivaService {
	
	String urlBase = "https://api.saraiva.com.br/sc/produto/pdp/";
	
	public BookModel getBookSaraiva(int sku) {
		String endpoint = urlBase + sku + "/0/0/1/";
		
		RestTemplate restTemplate = new RestTemplate();		
		BookModel book = restTemplate.getForObject(endpoint, BookModel.class);
		
		return book;
	}
	
}
