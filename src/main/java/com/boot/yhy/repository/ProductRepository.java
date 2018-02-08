package com.boot.yhy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.boot.yhy.entity.Product;
import com.boot.yhy.entity.Role;
import com.boot.yhy.entity.User;

@SuppressWarnings("rawtypes")
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>,JpaSpecificationExecutor<Product>{
	
	/*	User getByAge(Integer age);*/
	@Query("select id from Product")
	List<Long> getIds();
}
