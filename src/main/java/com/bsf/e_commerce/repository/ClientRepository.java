package com.bsf.e_commerce.repository;

import com.bsf.e_commerce.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
