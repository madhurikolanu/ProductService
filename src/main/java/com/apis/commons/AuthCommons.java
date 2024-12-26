package com.apis.commons;

import com.apis.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component // or @Service
public class AuthCommons {
    private RestTemplate restTemplate;
    AuthCommons(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String tokenValue){
        //call the userservice to validate token.

        ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity(
                "http://localhost:8181/users/validateToken/" + tokenValue, UserDto.class);
        if(responseEntity.getBody() == null){
            return null;
        }
        return responseEntity.getBody();

    }
}
