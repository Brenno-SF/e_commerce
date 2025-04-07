package com.bsf.e_commerce.controller;

import com.bsf.e_commerce.request.ProductRequestDTO;
import com.bsf.e_commerce.entity.Product;
import com.bsf.e_commerce.repository.ProductRepository;
import com.bsf.e_commerce.response.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody ProductRequestDTO data) {
        Product productData = new Product(data);
        productRepository.save(productData);
        return ResponseEntity.status(HttpStatus.CREATED).body(productData);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll(){
        List<ProductResponseDTO> products = productRepository.findAll().stream().map(ProductResponseDTO::new).collect(Collectors.toList());;
        return ResponseEntity.ok(products);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id_product}")
    public ResponseEntity<String> updateProduct(@PathVariable String id_product, @RequestBody ProductRequestDTO data) {

        Product product = productRepository.findById(id_product).orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName_product(data.name_product());
        product.setPrice(data.price());

        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("The "+ product.getName_product() + " has been successfully updated");
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id_product}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id_product") String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        productRepository.delete(product);
        return ResponseEntity.status(HttpStatus.OK).body("The "+ product.getName_product()+ " has been successfully deleted");
    }


}
