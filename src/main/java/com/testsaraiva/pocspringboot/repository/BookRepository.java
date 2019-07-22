package com.testsaraiva.pocspringboot.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.testsaraiva.pocspringboot.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{

	Book findBySku(int sku);
	
	Book findByName(String name);
	
	List<Book> findByPriceLessThanEqual(double price);
	
	List<Book> findAll();
	
	@Query(value="select * from Book price <= ?1 limit ?2", nativeQuery = true)
	Stream<Book> findByPriceLimit(@Param("price") double price, @Param("limit") int limit);

    @Query("select b from Book b where b.sku = :sku")
    Stream<Book> findBySkuReturnStream(@Param("sku") int sku);
	
}
