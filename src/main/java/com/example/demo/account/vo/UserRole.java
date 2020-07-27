package com.example.demo.account.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Alias("userRole")
public class UserRole {
	private Long id;

    private String role;

    public UserRole(String role) {
        this.role = role;
    }
}
