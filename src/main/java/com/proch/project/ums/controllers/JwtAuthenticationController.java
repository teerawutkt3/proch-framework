package com.proch.project.ums.controllers;
import com.proch.project.common.beans.ResponseData;
import com.proch.project.common.constants.ProjectConstant;
import com.proch.project.common.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proch.project.common.entities.JwtRequest;
import com.proch.project.common.entities.JwtResponse;
import com.proch.project.common.utils.JwtTokenUtil;
import com.proch.project.ums.services.UserDetailsServiceCustom;

@RestController
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsServiceCustom userDetailsService;

	@PostMapping(value = "/authenticate")
	public ResponseData<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		ResponseData responseData = new ResponseData();
		responseData.setData(new JwtResponse(token));
		MessageUtil.setMessageSuccess(responseData, ProjectConstant.ResponseMessage.SUCCESS);
		return responseData;
	}

	private void authenticate(String username, String password) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {

			throw new Exception("USER_DISABLED", e);

		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}