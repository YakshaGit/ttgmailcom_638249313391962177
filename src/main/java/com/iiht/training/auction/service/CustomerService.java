package com.iiht.training.auction.service;

import java.util.List;

import com.iiht.training.auction.dto.CustomerDto;

public interface CustomerService {
	public CustomerDto registerCustomer(CustomerDto customerDto);

	public CustomerDto updateCustomer(CustomerDto customerDto);

	public Boolean deleteCustomer(Long id);

	public CustomerDto getCustomerById(Long id);

	public List<CustomerDto> getAllCustomers();

}
