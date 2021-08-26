package com.jumia.dao.customer;

import java.util.List;

import javax.enterprise.inject.Typed;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jumia.entity.Customer;
import com.jumia.exception.ResourceNotFoundException;
import com.jumia.model.PaginatedSearchResult;
import com.jumia.repository.ICustomerRepository;
import com.jumia.utils.SqlUtils;
@Component
@Typed({ Object.class, SQLCustomerDao.class })
public class SQLCustomerDao implements ICustomerDao {
	
	@Autowired
	protected ICustomerRepository customerRepository;
	
	@Autowired
	private SqlUtils sqlUtils;
	
	@Transactional
	public Customer findByIdentifier(String identifier) {
		Customer customer;
		customer = customerRepository.findByIdentifier(identifier);
		if (customer == null) {
			throw new ResourceNotFoundException();
		}
		return customer;
	}

	@Transactional
	public PaginatedSearchResult<Customer> search(String customerName, String phoneNumber, Boolean isValidPhoneNumber, String countryName
			, String countryCode, int page,  int pageSize) {
		List<Customer> customers = customerRepository.search(sqlUtils.formMatchQueryString(customerName)
				, sqlUtils.formMatchQueryString(phoneNumber), isValidPhoneNumber, sqlUtils.formMatchQueryString(countryName)
				, sqlUtils.formMatchQueryString(countryCode), String.valueOf(pageSize + 1), String.valueOf((page - 1) * pageSize));
		boolean hasNext = pageSize < customers.size();
		if(customers.size() > pageSize) {
			customers.remove(customers.size() - 1);
		}
		return new PaginatedSearchResult<>(customers, hasNext);
	}
}