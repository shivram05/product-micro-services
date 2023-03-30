package com.service.product.controller;

import com.service.product.dto.ProductDTO;
import com.service.product.service.ProductService;
import com.service.product.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO){

        ProductDTO productDTO1 = productService.createProduct(productDTO);
        return new ResponseEntity<>(productDTO1, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<?> getAllProduct(){
        List<ProductDTO> productDTOList = productService.getAllProduct();
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(name = "id") String id){
        ProductDTO productDTO = productService.getProductId(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable(name = "id") String id, @RequestBody ProductDTO productDTO){
        ProductDTO updateProduct = productService.updateProduct(id,productDTO);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") String id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(new ResponseMessage("Product Delete successfully"), HttpStatus.OK);
    }
}
