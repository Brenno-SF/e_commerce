package com.bsf.e_commerce.controller;

import com.bsf.e_commerce.entity.Address;
import com.bsf.e_commerce.entity.Client;
import com.bsf.e_commerce.repository.ClientRepository;
import com.bsf.e_commerce.request.ClientRequestDTO;
import com.bsf.e_commerce.response.ClientResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> saveClient(@RequestBody ClientRequestDTO data) {
        Client clientData = new Client(data);
        Address address = new Address(data.address());

        clientData.setAddress(address);

        repository.save(clientData);
        return ResponseEntity.status(HttpStatus.CREATED).body("The " + clientData.getUsername() + " has been successfully saved");
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAll(){
        List<ClientResponseDTO> clients = repository.findAll().stream().map(ClientResponseDTO::new).collect(Collectors.toList());;
        return ResponseEntity.ok(clients);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id_client}")
    public ResponseEntity<String> updateClient(@PathVariable String id_client, @RequestBody ClientRequestDTO data) {

        Client client = repository.findById(id_client).orElseThrow(() -> new RuntimeException("Client not found"));

        client.setName_client(data.name_client());
        client.setCpf(data.cpf());
        client.setEmail(data.email());
        client.setUsername(data.username());
        client.setPassword(data.password());
        client.setBirth_date(data.birth_date());
        client.setBalance(data.balance());


        repository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body("The "+client.getUsername() + " has been successfully updated");

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id_client}")
    public ResponseEntity<String> deleteClient(@PathVariable("id_client") String id) {
        Client client = repository.findById(id).orElseThrow(() -> new RuntimeException("Client not found with ID: " + id));

        repository.delete(client);

        return ResponseEntity.status(HttpStatus.OK).body("The "+ client.getUsername() + " has been successfully deleted");
    }

}
