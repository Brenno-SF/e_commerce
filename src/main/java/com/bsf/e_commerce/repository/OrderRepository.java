package com.bsf.e_commerce.repository;

import com.bsf.e_commerce.entity.Cart;
import com.bsf.e_commerce.entity.Order;
import com.bsf.e_commerce.response.OrderResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {
    @Query("SELECT c FROM orders c WHERE c.client.idClient = :clientId")
    List<OrderResponseDTO> findByClientId(@Param("clientId") String clientId);
}
