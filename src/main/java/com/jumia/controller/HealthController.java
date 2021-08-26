package com.jumia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Amr Elbassiouni
 *
 */
@RestController
@RequestMapping("health")
public class HealthController {
	
	/**
	 * Health endpoint
	 * @return ResponseEntity<Object>
	 */
	@GetMapping("/up")
    public ResponseEntity<Object> healthcheck() {
		return new ResponseEntity<>(HttpStatus.OK);
    }
}
