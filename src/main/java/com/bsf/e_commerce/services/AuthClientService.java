package com.bsf.e_commerce.services;

import javax.naming.AuthenticationException;

import com.bsf.e_commerce.repository.ClientRepository;
import com.bsf.e_commerce.request.AuthLoginRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Object execute(AuthLoginRequestDTO authLoginRequestDTO) throws AuthenticationException {
        var client = this.clientRepository
                .findByUsername(authLoginRequestDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/Password incorrect"));



        var passwordMatches = this.passwordEncoder.matches(authLoginRequestDTO.password(), client.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }else{
            System.out.println("bateu saporra");
        }
        return client;
    }

}
