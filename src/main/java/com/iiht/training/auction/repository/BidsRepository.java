package com.iiht.training.auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.training.auction.entity.BidsEntity;

@Repository
public interface BidsRepository extends JpaRepository<BidsEntity, Long> {

	List<BidsEntity> getBidsByProductId(Long productId);
}
