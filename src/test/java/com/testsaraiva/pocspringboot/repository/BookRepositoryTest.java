package com.testsaraiva.pocspringboot.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.testsaraiva.pocspringboot.model.TestBookModel;

public interface BookRepositoryTest extends CrudRepository<TestBookModel, Integer>{

	List<TestBookModel> findBySku(int sku);

    @Query("select b from Book b where c.sku = :sku")
    Stream<TestBookModel> findByEmailReturnStream(@Param("sku") int sku);
	
}
