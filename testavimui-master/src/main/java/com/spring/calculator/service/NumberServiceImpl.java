package com.spring.calculator.service;

import com.spring.calculator.model.NumberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import com.spring.calculator.model.Number;

// @Service - servisų sluoksnis biznio logikai
// Po serviso sluoksniu kreipiamės į DAO
@Service
public class NumberServiceImpl implements NumberService {
	// autowire- naudojamas automatinei priklausomybių injekcijai
	// Kad panaudoti @Autowired anotaciją, reikia pirmiausiai turėti apsirašius @Bean @Configuration klasėje
	@Autowired
	// @Qualifier anotacija kartu su @Autowired patikslina su kuriuo konkrečiai bean susieti priklausomybę.
	// Jeigu @Configuration klasėje yra daugiau negu vienas bean, @Qualifier anotacija yra privaloma,
	// kitu atveju metama klaida:
	// 'Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans,
	// or using @Qualifier to identify the bean that should be consumed'
	@Qualifier("NumberDAO")
	private NumberDAO numberDAO;

	@Override
	public List<Number> getAll() {
		return numberDAO.findEntities();
	}

	@Override
	public void save(Number number) {
		numberDAO.insertEntity(number);
	}

	@Override
	public Number getById(int id) {
		return numberDAO.findEntityByID(id);
	}

	@Override
	public void update(Number number) {
		numberDAO.updateEntity(number);
	}

	@Override
	public void delete(int id) {
		numberDAO.removeEntityByID(id);
	}

}
