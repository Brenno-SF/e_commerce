package com.bsf.e_commerce.controller;

import com.bsf.e_commerce.request.AuthLoginRequestDTO;
import com.bsf.e_commerce.services.AuthClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("auth")
public class AuthLoginUserController {

    @Autowired
    private AuthClientService authClientService;

    @PostMapping("client")
    public ResponseEntity<Object> create(@RequestBody AuthLoginRequestDTO authLoginRequestDTO){
        try {
             var result = this.authClientService.execute(authLoginRequestDTO);
             return ResponseEntity.ok().body(result);
        } catch (AuthenticationException e) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
