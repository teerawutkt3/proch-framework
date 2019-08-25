package com.proch.project.ws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baeldung.springsoap.gen.Country;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest-ws")
public class RestController {

	@Autowired
	private RestService restService;
	
	@PostMapping("/")
	public  Country getCountry(@RequestBody Request rest) {
		return restService.getCountry(rest);
	}
}
