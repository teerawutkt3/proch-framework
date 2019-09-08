package com.proch.project.ws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baeldung.springsoap.gen.Country;
import com.proch.project.ws.soap.CountryRepository;

@Service
public class RestService {

	@Autowired
	private CountryRepository countryRepository;
	
	public Country getCountry(Request request) {
		return countryRepository.findCountry(request.getName());
	}
}
