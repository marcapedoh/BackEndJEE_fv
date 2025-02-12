package com.example.backend.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String username;
     private String password;

     public String getUsername(){
         return this.username;
     }
     public String getPassword(){
         return this.password;
     }
}
