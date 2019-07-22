package com.testsaraiva.pocspringboot.api;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.testsaraiva.pocspringboot.model.TestBookModel;
import com.testsaraiva.pocspringboot.repository.BookRepositoryTest;

@SpringBootTest
public class TestSaraivaService {

	public static void main(String[] args) throws Exception {
		// getJsonTestSaraivaElement();
		System.out.println(teste());
	}

	private void getJsonTestSaraivaElement() {
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject("https://api.saraiva.com.br/sc/produto/pdp/9731880/0/0/1/",
				String.class);

		System.out.println("sku: " + JsonPath.read(result, "$.sku").toString());
		System.out.println("name: " + JsonPath.read(result, "$.name").toString());
		System.out.println("brand: " + JsonPath.read(result, "$.brand").toString());
		System.out.println("price: " + JsonPath.read(result, "$.price.bestPrice.value").toString());
	}

	@Autowired
	DataSource dataSource;

	@Autowired
	BookRepositoryTest bookRepository;

	public static String teste() throws NotFoundException {
		RestTemplate restTemplate = new RestTemplate();

		String result = null;
		result = restTemplate.getForObject("https://api.saraiva.com.br/sc/produto/pdp/50504/0/0/1/", String.class);

		System.out.println("sku: " + JsonPath.read(result, "$.sku").toString());
		System.out.println("name: " + JsonPath.read(result, "$.name").toString());
		System.out.println("brand: " + JsonPath.read(result, "$.brand").toString());
		System.out.println("price: " + JsonPath.read(result, "$.price.bestPrice.value").toString());
		return result;
	}
}
