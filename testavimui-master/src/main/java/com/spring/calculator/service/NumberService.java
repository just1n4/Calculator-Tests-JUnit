package com.spring.calculator.service;

import java.util.List;

import com.spring.calculator.model.Number;

public interface NumberService {
	
	List<Number> getAll();
	
	void save(Number number);

	Number getById(int id);
	
	void update(Number number);
	
	void delete(int id);

}
