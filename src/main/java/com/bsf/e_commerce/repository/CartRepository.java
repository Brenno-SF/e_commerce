package com.bsf.e_commerce.repository;

import com.bsf.e_commerce.entity.Cart;
import com.bsf.e_commerce.response.CartResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, String>{
    @Query("SELECT c FROM cart c WHERE c.client.idClient = :clientId")
    List<CartResponseDTO> findByClientId(@Param("clientId") String clientId);

    @Modifying
    @Transactional
    @Query("DELETE FROM cart c WHERE c.client.idClient = :idClient")
    void deleteByClientId(String idClient);

}
