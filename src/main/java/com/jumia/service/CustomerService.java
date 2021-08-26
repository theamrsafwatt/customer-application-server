package com.jumia.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumia.dao.customer.ICustomerDao;
import com.jumia.entity.Customer;
import com.jumia.model.PaginatedSearchResult;

@Service
public class CustomerService {
	
	@Autowired
	private ICustomerDao customerDao;
	
	@Autowired
	private com.jumia.utils.StringUtils stringUtils;
	
	@Transactional
	public Customer findByIdentifier(String identifier) {
		return customerDao.findByIdentifier(identifier);
	}

	@Transactional
	public boolean validatePhoneNumberByIdentifier(String identifier) {
		Customer customer = findByIdentifier(identifier);
		if(customer.getValidPhoneNumber() != null) {
			return customer.getValidPhoneNumber();
		} else {
			return validatePhoneNumber(customer.getPhoneNumber(), customer.getCountry().getPhoneNumberRegex());
		}
	}

	@Transactional
	public PaginatedSearchResult<Customer> search(String customerName, String phoneNumber, Boolean isValidPhoneNumber, String countryName
			, String countryCode, int page,  int pageSize) {
		return customerDao.search(customerName, phoneNumber, isValidPhoneNumber, countryName
				, countryCode, page, pageSize);
	}
	
	public boolean validatePhoneNumber(String phoneNumber, String phoneNumberRegex) {
		return stringUtils.validateStringAgainstRegex(phoneNumber, phoneNumberRegex);
	}
}