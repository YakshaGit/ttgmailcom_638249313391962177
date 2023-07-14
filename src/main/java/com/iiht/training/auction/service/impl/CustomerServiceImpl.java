package com.iiht.training.auction.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.auction.dto.CustomerDto;
import com.iiht.training.auction.entity.CustomerEntity;
import com.iiht.training.auction.exceptions.CustomerNotFoundException;
import com.iiht.training.auction.repository.CustomerRepository;
import com.iiht.training.auction.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerDto registerCustomer(CustomerDto customerDto) {
		CustomerEntity customerEntity = new CustomerEntity();
		BeanUtils.copyProperties(customerDto, customerEntity);
		customerRepository.save(customerEntity);
		return customerDto;
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto) {
		CustomerEntity customerEntity = new CustomerEntity();
		BeanUtils.copyProperties(customerDto, customerEntity);
		customerRepository.save(customerEntity);
		return customerDto;
	}

	@Override
	public Boolean deleteCustomer(Long id) {
		CustomerDto customerDto = getCustomerById(id);
		CustomerEntity customerEntity = new CustomerEntity();
		BeanUtils.copyProperties(customerDto, customerEntity);
		customerRepository.delete(customerEntity);
		return true;
	}

	@Override
	public CustomerDto getCustomerById(Long id) {
		Optional<CustomerEntity> findById = customerRepository.findById(id);
		if (findById.isPresent()) {
			CustomerDto customerDto = new CustomerDto();
			BeanUtils.copyProperties(findById, customerDto);
			return customerDto;
		} else {
			throw new CustomerNotFoundException("Customer with id " + id + " does not exists");
		}
	}

	@Override
	public List<CustomerDto> getAllCustomers() {

		List<CustomerEntity> customers = customerRepository.findAll();
		List<CustomerDto> customerDtos = new ArrayList<>();
		for (CustomerEntity customerEntity : customers) {
			CustomerDto customerDto = new CustomerDto();
			BeanUtils.copyProperties(customerEntity, customerDto);
			customerDtos.add(customerDto);
		}
		return customerDtos;
	}

}
