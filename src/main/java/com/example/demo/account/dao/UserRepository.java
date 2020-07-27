package com.example.demo.account.dao;

import com.example.demo.account.vo.User;
import com.example.demo.common.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class UserRepository {
	
	@Autowired
	CommonDAO commonDAO;

	public Optional<User> findById(String userid) throws Exception {
		User user = (User) commonDAO.select("findByEmail", userid);
		Optional<User> account = Optional.ofNullable(user);
		return account;
	}

	public User findUserById(String userid) throws Exception {
		User user = (User) commonDAO.select("findByEmail", userid);
		return user;
	}

	@Transactional
	public User registation(User account) throws Exception {
		commonDAO.insert("registation", account);
		return account;
	}
	
}
