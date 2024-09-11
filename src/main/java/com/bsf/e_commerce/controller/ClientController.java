package com.bsf.e_commerce.controller;

import com.bsf.e_commerce.Client.Client;
import com.bsf.e_commerce.Client.ClientRepository;
import com.bsf.e_commerce.Client.ClientRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    private ClientRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveProduct(@RequestBody ClientRequestDTO data) {
        Client clientData = new Client(data);
        repository.save(clientData);
        return;
    }

}
