package com.iiht.training.auction.service;

import java.util.List;

import com.iiht.training.auction.dto.ProductDto;

public interface ProductService {
	public ProductDto saveProduct(ProductDto productDto);

	public ProductDto updateProduct(ProductDto productDto);

	public Boolean deleteProduct(Long productId);

	public ProductDto getProductById(Long productId);

	public List<ProductDto> getAllProducts();

	public List<ProductDto> getProductsBySeller(Long sellerId);
	public List<ProductDto> getProductsByCategory(String category);

}
