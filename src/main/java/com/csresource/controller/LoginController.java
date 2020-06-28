package com.csresource.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resource.json.UserJson;

@RestController
public class LoginController {
	
	@PostMapping("/login")
	public void login(UserJson user) {
		
		
	}

}
