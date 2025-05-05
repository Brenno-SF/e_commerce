package com.bsf.e_commerce.repository;

import com.bsf.e_commerce.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByUsername(String username);
}
