package com.bsf.e_commerce.repository;

import com.bsf.e_commerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String>{
}
