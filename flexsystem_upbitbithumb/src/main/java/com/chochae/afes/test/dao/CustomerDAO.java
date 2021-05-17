package com.chochae.afes.test.dao;

import com.chochae.afes.test.model.Customer;

public interface CustomerDAO
{
	public void insert(Customer customer);
	public Customer findByCustomerId(int custId);
}