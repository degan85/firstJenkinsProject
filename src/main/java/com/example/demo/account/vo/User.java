package com.example.demo.account.vo;

import com.example.demo.common.CommonVO;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Alias("user")
public class User extends CommonVO {

    private Long id;

    private String name;
    private String username;
    private String nickname;
    
    private String email;
    private String phone;
    private String imageUrl;

    private String remark;
    private String loginyn;
    
    private String password;
    private String passwordconfirm;
    
    private String type;
    
    private String workman_yn;
    private String client_yn;
    private String workmanid;
    private String client_id;
    
    private List<UserRoleMap> userRoles = new ArrayList<>();

    @Builder
    public User(String username, String email, String imageUrl, List<UserRoleMap> userRoles) {
        this.username = username;
        this.email = email;
        this.imageUrl = imageUrl;
        this.userRoles = userRoles;
    }

    public void addRole(UserRole userRole){
        if(userRoles == null){
            userRoles = new ArrayList<>();
        }
        userRoles.add(new UserRoleMap(this, userRole));
    }
}