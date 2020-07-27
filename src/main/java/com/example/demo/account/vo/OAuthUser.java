package com.example.demo.account.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OAuthUser implements Serializable {

    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private String name;
    @JsonProperty("picture")
    private String picture;
    @JsonProperty("imageUrl")
    private String imageurl;

    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("profile_image")
    private String profile_image;
    @JsonProperty("thumbnail_image")
    private String thumbnail_image;
    @JsonProperty("thumbnail_image_url")
    private String thumbnail_image_url;
    @JsonProperty("profile_image_url")
    private String profile_image_url;
    @JsonProperty("has_email")
    private String has_email;
    @JsonProperty("email_needs_agreement")
    private String email_needs_agreement;
    @JsonProperty("is_email_valid")
    private String is_email_valid;
    @JsonProperty("is_email_verified")
    private String is_email_verified;
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("has_age_range")
    private String has_age_range;
    @JsonProperty("age_range_needs_agreement")
    private String age_range_needs_agreement;
    @JsonProperty("age_range")
    private String age_range;
    @JsonProperty("has_gender")
    private String has_gender;
    @JsonProperty("gender_needs_agreement")
    private String gender_needs_agreement;
    
    public User toEntity(){
        return User.builder()
                .email(email)
                .username(name)
                .imageUrl(picture)
                .build();
    }

    public User toKakaoEntity(){
    	return User.builder()
    			.email(email)
    			.username(name)
    			.imageUrl(picture)
    			.build();
    }
}