package com.testsaraiva.pocspringboot.api;

import org.springframework.web.client.RestTemplate;

import com.testsaraiva.pocspringboot.model.TestBookModel;

public class TestSaraivaService {

	public static void main(String[] args) {
		System.out.println(getJsonTestSaraiva());
	}
	
	private static String getJsonTestSaraiva() {
		RestTemplate restTemplate = new RestTemplate();
        TestBookModel book = restTemplate.getForObject(
        		"https://api.saraiva.com.br/sc/produto/pdp/9731880/0/0/1/", TestBookModel.class);
        System.out.println(book.toString());
		return book.toString();
	}
}
