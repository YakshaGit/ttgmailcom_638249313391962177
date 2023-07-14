package com.iiht.training.auction.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.auction.dto.BidsDto;
import com.iiht.training.auction.dto.ProductDto;
import com.iiht.training.auction.entity.BidsEntity;
import com.iiht.training.auction.entity.ProductEntity;
import com.iiht.training.auction.repository.BidsRepository;
import com.iiht.training.auction.repository.ProductRepository;
import com.iiht.training.auction.service.BidsService;
import com.iiht.training.auction.service.ProductService;

@Service
public class BidsServiceImpl implements BidsService {

	@Autowired
	private BidsRepository bidsRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public BidsDto placeBid(BidsDto bidsDto) {
		BidsEntity bidsEntity = new BidsEntity();
		BeanUtils.copyProperties(bidsDto, bidsEntity);
		bidsRepository.save(bidsEntity);
		return bidsDto;
	}

	@Override
	public List<BidsDto> getAllBidsOnProduct(Long porductId) {
		List<BidsEntity> bids = bidsRepository.getBidsByProductId(porductId);
		List<BidsDto> bidsDtos = new ArrayList<>();
		for (BidsEntity entity : bids) {
			BidsDto bidsDto = new BidsDto();
			BeanUtils.copyProperties(entity, bidsDto);
			bidsDtos.add(bidsDto);
		}
		return bidsDtos;
	}

	@Override
	public List<BidsDto> getAllBidsAfterProductBiddingEndDate(Long porductId) {
		List<BidsEntity> bids = bidsRepository.getBidsByProductId(porductId);
		List<BidsDto> bidsDtos = new ArrayList<>();
		Optional<ProductEntity> productDto = productRepository.findById(porductId);
		System.out.println("My Product===>" + productDto.get().getLastDateOfBidding());
		for (BidsEntity entity : bids) {
			if (entity.getBiddingDate().isBefore(productDto.get().getLastDateOfBidding())
					&& productDto.get().getLastDateOfBidding().isBefore(LocalDate.now())) {
				BidsDto bidsDto = new BidsDto();
				BeanUtils.copyProperties(entity, bidsDto);
				bidsDtos.add(bidsDto);
			}
		}
		return bidsDtos;
	}

}
