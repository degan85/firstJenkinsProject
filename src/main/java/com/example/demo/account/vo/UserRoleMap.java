package com.example.demo.account.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRoleMap {
	 private Long id;

	 private User user;

	 private UserRole userRole;

	 public UserRoleMap(User user, UserRole userRole) {
	     this.user = user;
	     this.userRole = userRole;
	 }
}
