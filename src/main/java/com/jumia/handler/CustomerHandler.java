package com.jumia.handler;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jumia.entity.Customer;
import com.jumia.exception.ResourceNotFoundException;
import com.jumia.model.FailureResponse;
import com.jumia.model.PaginatedSearchResult;
import com.jumia.model.Response;
import com.jumia.model.SuccessResponse;
import com.jumia.service.CustomerService;

@Service
public class CustomerHandler {

	@Autowired
	private CustomerService customerService;

	@Transactional
	public ResponseEntity<Object> findByIdentifier(String identifier) {
		try {
			Customer customer = customerService.findByIdentifier(identifier);
			return new ResponseEntity<>(customer, 
					HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(new Response(FailureResponse.NOT_FOUND.getStatus()
					, FailureResponse.NOT_FOUND.getDescription()),  
					HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			return new ResponseEntity<>(
					new Response(FailureResponse.ERROR.getStatus()
							, FailureResponse.ERROR.getDescription()), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<Object> validatePhoneNumberByIdentifier(String identifier) {
		try {
			boolean isVaild = customerService.validatePhoneNumberByIdentifier(identifier);
			if(isVaild) {
				return new ResponseEntity<>(new Response(SuccessResponse.VALID_CUSTOMER_PHONE_NUMBER.getStatus()
						, SuccessResponse.VALID_CUSTOMER_PHONE_NUMBER.getDescription()),  
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new Response(FailureResponse.INVALID_CUSTOMER_PHONE_NUMBER.getStatus()
						, FailureResponse.INVALID_CUSTOMER_PHONE_NUMBER.getDescription()),  
						HttpStatus.OK);
			}
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(new Response(FailureResponse.NOT_FOUND.getStatus()
					, FailureResponse.NOT_FOUND.getDescription()),  
					HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			return new ResponseEntity<>(
					new Response(FailureResponse.ERROR.getStatus()
							, FailureResponse.ERROR.getDescription()), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<Object> search(String customerName, String phoneNumber, Boolean isValidPhoneNumber, String countryName
			, String countryCode, int page,  int pageSize) {
		try {
			PaginatedSearchResult<Customer> customers = customerService.search(customerName, phoneNumber, isValidPhoneNumber, countryName
					, countryCode, page, pageSize);
			return new ResponseEntity<>(customers,  
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new Response(FailureResponse.ERROR.getStatus()
							, FailureResponse.ERROR.getDescription()), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}