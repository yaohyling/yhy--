package com.boot.yhy.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.boot.yhy.repository.UserRepository;

@Service
public class UserService {
	@Resource
	private UserRepository repository;

	@Transactional
	public void updateUser(Integer id) {
		repository.updateUser(id);
	}
}
