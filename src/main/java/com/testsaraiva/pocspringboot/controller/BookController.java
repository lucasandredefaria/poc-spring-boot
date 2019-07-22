package com.testsaraiva.pocspringboot.controller;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.PathNotFoundException;
import com.testsaraiva.pocspringboot.api.SaraivaService;
import com.testsaraiva.pocspringboot.exception.BookNotFoundException;
import com.testsaraiva.pocspringboot.model.Book;
import com.testsaraiva.pocspringboot.repository.BookRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/book")
public class BookController {
	
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@Autowired
	BookRepository bookRepository;

	@PostMapping
	@ApiOperation(consumes="application/json", produces="application/json", protocols="http", value = "addBook")
	public int addBook(@ApiParam("Book Sku, Can not be null") @RequestParam int sku) 
			throws PathNotFoundException {
		SaraivaService saraivaService = new SaraivaService();
		Book book = null;
		try {
			book = saraivaService.getBookSaraiva(sku);
		} catch (PathNotFoundException e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND).getStatusCodeValue();
		}
		bookRepository.save(book);
		return new ResponseEntity(HttpStatus.CREATED).getStatusCodeValue();
	}
	
	@DeleteMapping("/{sku}")
	@ApiOperation(consumes="application/json", produces="application/json",protocols="http", value = "deleteBook" )
	public int deleteBook(@ApiParam("Book Sku, Can not be null") @PathVariable int sku) 
			throws BookNotFoundException {
		bookRepository.deleteById(sku);
		return new ResponseEntity(HttpStatus.NO_CONTENT).getStatusCodeValue();
	}
	
	@GetMapping("/{sku}")
	@ApiOperation(consumes = "application/json", produces = "application/json", protocols = "http", value = "getBook")
	public String getBook(@ApiParam("Book Sku, Can not be null") @PathVariable int sku)
			throws BookNotFoundException {
		Book book = bookRepository.findById(sku)
				.orElseThrow(() -> new BookNotFoundException("Book not found for this id :: " + sku));
		return gson.toJson(ResponseEntity.ok().body(book));
	}
	
	@GetMapping
	@Transactional
	@ApiOperation(consumes="application/json", produces="application/json", protocols="http", value = "getBooks")
	public String getBooks(@ApiParam("Book Price and Search Limit") 
						   @RequestParam(value="price", required=false) Double price,
						   @RequestParam(value="limit", required=false) Integer limit) {
		
		System.out.println("Limit: " + limit + " price: " + price);
		
		int countTotal = bookRepository.findAll().size();
		
		if (limit != null && price != null) {
			List<Book> books = bookRepository.findByPriceLessThanEqual(price);
			System.out.println(books);
			return gson.toJson(books.subList(0, limit < countTotal ? limit : countTotal));
			
		} else if (limit != null && price == null) {
			List<Book> books = bookRepository.findAll();
			return gson.toJson(books.subList(0, limit < countTotal ? limit : countTotal));
			
		} else if (price != null && limit == null) {
			List<Book> books = bookRepository.findByPriceLessThanEqual(price);
			return gson.toJson(books);
			
		} else {
			return gson.toJson(countTotal);
		}
	}
	
}
