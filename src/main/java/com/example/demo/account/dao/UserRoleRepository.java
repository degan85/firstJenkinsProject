package com.example.demo.account.dao;

import com.example.demo.account.vo.UserRole;
import com.example.demo.common.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRoleRepository {
	
	@Autowired
	CommonDAO commonDAO;
	
	public Optional<UserRole> findDefaultRole(String role) throws Exception {
		UserRole userRole = (UserRole) commonDAO.select("findDefaultRole", role);
		Optional<UserRole> op_userRole = Optional.ofNullable(userRole);
		return op_userRole;
	}

}
