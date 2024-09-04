package com.bsf.e_commerce.controller;

import com.bsf.e_commerce.product.Product;
import com.bsf.e_commerce.product.ProductRepository;
import com.bsf.e_commerce.product.ProductRequestDTO;
import com.bsf.e_commerce.product.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveProduct(@RequestBody ProductRequestDTO data) {
        Product productData = new Product(data);
        repository.save(productData);
        return;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ProductResponseDTO> getAll(){
        List<ProductResponseDTO> products = repository.findAll().stream().map(ProductResponseDTO::new).collect(Collectors.toList());;
        return products;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id_product}")
    public void updateProduct(@PathVariable String id_product, @RequestBody ProductRequestDTO data) {

        Product product = repository.findById(id_product).orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName_product(data.name_product());
        product.setPrice(data.price());

        repository.save(product);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id_product}")
    public void deleteProduct(@PathVariable("id_product") String id) {
        Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        repository.delete(product);
    }


}
