package com.bsf.e_commerce.controller;

import com.bsf.e_commerce.Client.Client;
import com.bsf.e_commerce.Client.ClientRepository;
import com.bsf.e_commerce.Client.ClientRequestDTO;
import com.bsf.e_commerce.Client.ClientResponseDTO;
import com.bsf.e_commerce.product.Product;
import com.bsf.e_commerce.product.ProductRequestDTO;
import com.bsf.e_commerce.product.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ClientResponseDTO> getAll(){
        List<ClientResponseDTO> clients = repository.findAll().stream().map(ClientResponseDTO::new).collect(Collectors.toList());;
        return clients;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id_client}")
    public void updateClient(@PathVariable String id_client, @RequestBody ClientRequestDTO data) {

        Client client = repository.findById(id_client).orElseThrow(() -> new RuntimeException("Client not found"));

        client.setName_client(data.name_client());
        client.setCpf(data.cpf());
        client.setEmail(data.email());
        client.setUsername(data.username());
        client.setPassword(data.password());
        client.setBirth_date(data.birth_date());
        client.setBalance(data.balance());


        repository.save(client);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id_client}")
    public void deleteClient(@PathVariable("id_client") String id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + id));

        repository.delete(client);
    }

}
