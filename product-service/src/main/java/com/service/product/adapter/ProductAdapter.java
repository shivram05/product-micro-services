package com.service.product.adapter;

import com.service.product.dto.ProductDTO;
import com.service.product.model.Product;

import java.util.List;

public class ProductAdapter {

    public static ProductDTO getProductDTOFromProduct(Product product){
        if(product == null) return null;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());

        return productDTO;
    }

    public static Product getProductFromProductDTO(ProductDTO productDTO){
        if(productDTO == null) return null;
        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());

        return product;
    }

    public static List<ProductDTO> getProductDTOListFromProductList (List<Product> productList){
        if(productList == null) return null;
        return productList.stream().map(ProductAdapter::getProductDTOFromProduct).toList();
    }

    public static List<Product> getBadgeListFromBadgeDTOList (List<ProductDTO> productDTOList){
        if(productDTOList == null) return null;
        return productDTOList.stream().map(ProductAdapter::getProductFromProductDTO).toList();
    }
}
