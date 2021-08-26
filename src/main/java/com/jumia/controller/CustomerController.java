package com.jumia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jumia.handler.CustomerHandler;

/**
 * @author Amr ELbassiouni
 *
 */
@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerHandler customerHandler;

	/**
	 * Get endpoint to find customer by identifier
	 * @param identifier
	 * @return ResponseEntity<Object>
	 */
	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<Object> findByIdentifier(@PathVariable String identifier) {
		return customerHandler.findByIdentifier(identifier);
	}
	
	/**
	 * Get endpoint to validate customer phone number using phone number identifier
	 * @param identifier
	 * @return ResponseEntity<Object>
	 */
	@CrossOrigin
	@GetMapping("/{identifier}/validatephonenumber")
	public ResponseEntity<Object> validatePhoneNumberByIdentifier(@PathVariable String identifier) {
		return customerHandler.validatePhoneNumberByIdentifier(identifier);
	}
	
	/**
	 * Get endpoint to search customers documents
	 * @param customerName
	 * @param phoneNumber
	 * @param isValidPhoneNumber
	 * @param countryName
	 * @param countryCode
	 * @param page
	 * @param pageSize
	 * @return ResponseEntity<Object>
	 */
	@CrossOrigin
	@GetMapping("/search")
	public ResponseEntity<Object> search(@RequestParam(name = "customerName", required = false) String customerName
			, @RequestParam(name = "phoneNumber", required = false) String phoneNumber
			, @RequestParam(name = "isValidPhoneNumber", required = false) Boolean isValidPhoneNumber
			, @RequestParam(name = "countryName", required = false) String countryName
			, @RequestParam(name = "countryCode", required = false) String countryCode
			, @RequestParam(name = "page") int page
			, @RequestParam(name = "pageSize") int pageSize) {
		return customerHandler.search(customerName, phoneNumber, isValidPhoneNumber, countryName, countryCode, page, pageSize);
		
	}
}