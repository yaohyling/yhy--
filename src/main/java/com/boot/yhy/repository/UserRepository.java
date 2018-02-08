package com.boot.yhy.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.boot.yhy.entity.Role;
import com.boot.yhy.entity.User;

@SuppressWarnings("rawtypes")
public interface UserRepository extends PagingAndSortingRepository<User, Integer>,JpaSpecificationExecutor{
	
	/*	User getByAge(Integer age);*/
		
		@Modifying
		@Query("update User u set u.age=1 where u.id = :id")
		void updateUser(@Param("id") Integer id);
		@Query("select count(*) from Role r where r.user.id=11")
		Long getIdsnumByuser();
}
