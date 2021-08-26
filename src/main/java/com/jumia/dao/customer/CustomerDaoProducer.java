package com.jumia.dao.customer;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.jumia.dao.DBlist;

public class CustomerDaoProducer  {

	@Autowired
	private Instance<ICustomerDao> customerDao;

	private static final String DAO = "SQLITE";

	@Produces
	public ICustomerDao produce() {

		if(DAO.equals(DBlist.SQLITE.toString())) {
			return customerDao.select(SQLCustomerDao.class).get();
		}
		
		return null;
	}
}