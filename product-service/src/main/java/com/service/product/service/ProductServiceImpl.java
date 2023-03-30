package com.service.product.service;

import com.service.product.adapter.ProductAdapter;
import com.service.product.dto.ProductDTO;
import com.service.product.model.Product;
import com.service.product.repository.ProductRepository;
import com.service.product.utils.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = Product.builder().name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice()).build();
        Product saveProduct = productRepository.save(product);
        log.info("product " + saveProduct.getId() + "is saved");
        return ProductDTO.builder().name(saveProduct.getName()).description(saveProduct.getDescription()).price(saveProduct.getPrice()).build();
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        return ProductAdapter.getProductDTOListFromProductList(productList);
    }

    @Override
    public ProductDTO getProductId(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = null;
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        }else {
            throw new ApiException("Product with id = " + id  + " cannot found", HttpStatus.NOT_FOUND);
        }
        return ProductAdapter.getProductDTOFromProduct(product);
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Optional<Product> updateProduct = productRepository.findById(id);
        Product product =null;
        try {
            if (updateProduct.isPresent()) {
                product = ProductAdapter.getProductFromProductDTO(productDTO);
                productRepository.save(product);
            }else {
                throw new ApiException("Product with id = " + id  + " cannot found", HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            throw new ApiException("Product cannot update ",HttpStatus.NOT_FOUND);
        }

        return ProductAdapter.getProductDTOFromProduct(product);
    }

    @Override
    public void deleteProduct(String id) {

        Optional<Product> deleteProduct = productRepository.findById(id);
        if (deleteProduct.isPresent()) {
            productRepository.deleteById(id);

        }else {
            throw new ApiException("Product with id = " + id  + " cannot found", HttpStatus.NOT_FOUND);

        }
    }
}
