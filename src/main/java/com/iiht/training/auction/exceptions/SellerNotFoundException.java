package com.iiht.training.auction.exceptions;

public class SellerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SellerNotFoundException() {
		super();
	}

	public SellerNotFoundException(String message) {
		super(message);
	}

}
