package com.testsaraiva.pocspringboot.api;

import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;
import com.testsaraiva.pocspringboot.model.TestBookModel;

public class TestSaraivaService {

	public static void main(String[] args) {
		//System.out.println(getJsonTestSaraiva());
		getJsonTestSaraivaElement();
	}
	
	private static String getJsonTestSaraiva() {
		RestTemplate restTemplate = new RestTemplate();
        TestBookModel book = restTemplate.getForObject(
        		"https://api.saraiva.com.br/sc/produto/pdp/9731880/0/0/1/", TestBookModel.class);
        System.out.println(book.toString());
		return book.toString();
	}
	
	private static void getJsonTestSaraivaElement() {
		RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(
        		"https://api.saraiva.com.br/sc/produto/pdp/9731880/0/0/1/", String.class);
        
        System.out.println("sku: " + JsonPath.read(result, "$.sku").toString());
        System.out.println("name: " + JsonPath.read(result, "$.name").toString());
        System.out.println("brand: " + JsonPath.read(result, "$.brand").toString());
        System.out.println("price: " + JsonPath.read(result, "$.price.bestPrice.value").toString());

	}
}
