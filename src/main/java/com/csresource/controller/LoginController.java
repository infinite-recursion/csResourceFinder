package com.csresource.controller;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.csresource.jpa.User;
import com.csresource.repositories.UserRepository;
import com.resource.json.UserJson;

@RestController
public class LoginController {

	@Autowired
	UserRepository userRepo;

	@PostMapping("/login")
	public String login(@RequestBody UserJson userJson) {

		var userval = userRepo.findById(userJson.getUsername());

		if (userval.isPresent()) {

			User user = userval.get();

			// TODO: Redirect to Home page
			if (user.getPassword().equals(userJson.getPassword())) {
				return "Success";
			}
			// TODO: Redirect to Login error page
			else {
				return "Error";
			}
		}
		// TODO: Redirect them to login error page
		else {
				return "Error";
		}

	}
	
	@PostMapping("/logout")
	public String logout(@RequestBody UserJson userJson) {

		//TODO: redirect to the login screen
		return "logout";

	}

}
