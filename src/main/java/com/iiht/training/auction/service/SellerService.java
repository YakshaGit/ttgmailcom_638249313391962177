package com.iiht.training.auction.service;

import java.util.List;

import com.iiht.training.auction.dto.SellerDto;

public interface SellerService {

	public SellerDto registerSeller(SellerDto sellerDto);

	public SellerDto updateSeller(SellerDto sellerDto);

	public Boolean deleteSeller(Long sellerId);

	public SellerDto getSellerById(Long sellerId);

	public List<SellerDto> getAllSellers();

}
