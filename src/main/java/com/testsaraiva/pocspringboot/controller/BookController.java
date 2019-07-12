package com.testsaraiva.pocspringboot.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.testsaraiva.pocspringboot.api.SaraivaService;
import com.testsaraiva.pocspringboot.model.Book;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/book")
public class BookController {
	
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@PostMapping
	@ApiOperation(consumes="application/json", produces="application/json", protocols="http", value = "addBook")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Added Book"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found")
	})
	public String addBook(@ApiParam("Book Sku, Can not be null") @RequestParam int sku) {
		
		return "Book Added successfully :: " +sku;
	}
	
	@DeleteMapping("/{sku}")
	@ApiOperation(consumes="application/json", produces="application/json",protocols="http", value = "deleteBook" )
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Deleted Book"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found")
	})
	public String deleteBook(@ApiParam("Book Sku, Can not be null") @PathVariable int sku) {
		return "Book Deleted successfully :: " + sku; 
	}
	
	@GetMapping("/{sku}")
	@ApiOperation(consumes="application/json", produces="application/json", protocols="http", value = "getBook")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved Book"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found")
	})
	public String getBook(@ApiParam("Book Sku, Can not be null") @PathVariable int sku) {
		
		SaraivaService saraivaService = new SaraivaService();
		Book bookModel = saraivaService.getBookSaraiva(sku);
		return gson.toJson(bookModel);
	}
	
	@GetMapping
	@ApiOperation(consumes="application/json", produces="application/json", protocols="http", value = "getBooks")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved Book"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found")
	})
	public String getBooks(@ApiParam("Book Price and Search Limit") 
						   @RequestParam(value="price", required=false) Double price,
						   @RequestParam(value="limit", required=false) Integer limit) {
		return "Book Findded successfully :: " + price + " price " + limit + " limit ";
	}
	
}
