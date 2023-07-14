package com.iiht.training.auction.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.auction.dto.ProductDto;
import com.iiht.training.auction.entity.ProductEntity;
import com.iiht.training.auction.exceptions.ProductNotFoundException;
import com.iiht.training.auction.repository.ProductRepository;
import com.iiht.training.auction.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductDto saveProduct(ProductDto productDto) {
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(productDto, productEntity);
		productRepository.save(productEntity);
		return productDto;
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto) {
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(productDto, productEntity);
		productRepository.save(productEntity);
		return productDto;
	}

	@Override
	public Boolean deleteProduct(Long productId) {
		ProductDto productById = getProductById(productId);
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(productById, productEntity);
		productRepository.delete(productEntity);
		return true;
	}

	@Override
	public ProductDto getProductById(Long productId) {
		Optional<ProductEntity> findById = productRepository.findById(productId);
		if (findById.isPresent()) {
			ProductDto productDto = new ProductDto();
			BeanUtils.copyProperties(findById, productDto);
			return productDto;
		} else {
			throw new ProductNotFoundException("Product with id " + productId + " does not exists");
		}
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<ProductEntity> products = productRepository.findAll();
		List<ProductDto> productDtos = new ArrayList<>();
		for (ProductEntity entity : products) {
			ProductDto productDto = new ProductDto();
			BeanUtils.copyProperties(entity, productDto);
			productDtos.add(productDto);
		}
		return productDtos;
	}

	@Override
	public List<ProductDto> getProductsBySeller(Long sellerId) {
		List<ProductEntity> products = productRepository.getBySellerId(sellerId);
		List<ProductDto> productDtos = new ArrayList<>();
		for (ProductEntity entity : products) {
			ProductDto productDto = new ProductDto();
			BeanUtils.copyProperties(entity, productDto);
			productDtos.add(productDto);
		}
		return productDtos;
	}

	@Override
	public List<ProductDto> getProductsByCategory(String category) {
		List<ProductEntity> products = productRepository.findByCategory(category);
		List<ProductDto> productDtos = new ArrayList<>();
		for (ProductEntity entity : products) {
			ProductDto productDto = new ProductDto();
			BeanUtils.copyProperties(entity, productDto);
			productDtos.add(productDto);
		}
		return productDtos;
	}

}
