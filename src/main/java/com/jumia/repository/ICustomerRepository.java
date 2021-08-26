package com.jumia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jumia.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer>{

	public Customer findByIdentifier(String identifier);

	@Query(value = "SELECT *\r\n"
			+ "  FROM customer cu\r\n"
			+ "  inner join country co\r\n"
			+ "  on cu.country_id = co.id\r\n"
			+ " WHERE cu.name LIKE IFNULL(:customerName, cu.name)\r\n"
			+ " AND cu.phone_number LIKE IFNULL(:phoneNumber, cu.phone_number)\r\n"
			+ " AND cu.valid_phone_number = IFNULL(:isValidPhoneNumber, cu.valid_phone_number)\r\n"
			+ " AND co.name LIKE IFNULL(:countryName, co.name)"
			+ " AND co.code LIKE IFNULL(:countryCode, co.code)"
			+ " LIMIT :limit OFFSET :offset" , nativeQuery = true)
	public List<Customer> search(@Param("customerName") String customerName, @Param("phoneNumber") String phoneNumber
			, @Param("isValidPhoneNumber") Boolean isValidPhoneNumber, @Param("countryName") String countryName
			, @Param("countryCode") String countryCode, @Param("limit") String limit, @Param("offset") String offset);
}