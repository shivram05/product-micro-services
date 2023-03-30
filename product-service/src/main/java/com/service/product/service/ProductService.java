package com.service.product.service;

import com.service.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProduct();

    ProductDTO getProductId(String id);

    ProductDTO updateProduct(String id, ProductDTO productDTO);

    void deleteProduct(String id);
}
