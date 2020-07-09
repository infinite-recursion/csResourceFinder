package com.csresource.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.csresource.jpa.User;
import com.csresource.jpa.Userlike;
import com.csresource.repositories.UserLikesRepository;
import com.csresource.repositories.UserRepository;
import com.resource.json.UserJson;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserLikesRepository userLikesRepo;

	@PostMapping("/login")
	public boolean login(@RequestBody UserJson userJson) {
		
		boolean loginSuccess = false;
		Optional<User> userval = userRepo.findById(userJson.getUsername());

		if (userval.isPresent()) {

			User user = userval.get();

			// TODO: Redirect to Home page
			if (user.getPassword().equals(userJson.getPassword())) {
				loginSuccess = true;
			}
			// TODO: Redirect to Login error page
			else {
				loginSuccess = false;
			}
		}
		
		
		return loginSuccess;

	}
	
	@PostMapping("/logout")
	public String logout(@RequestBody UserJson userJson) {

		//TODO: redirect to the login screen
		return "logout";

	}
	
	@PostMapping("/getUserLikes")
	public HashMap<String,String> getUserLikes(@RequestBody String username) {

		//Key is the id of the content that the user liked
		//value is the id of the Userlike in the database
		HashMap<String,String> userLikes = new HashMap<String,String>();

		User user = userRepo.findById(username).get();
		
		LinkedList<Userlike> likes = userLikesRepo.findByUser(user);
		
		if(likes!=null) {
			
			for(Userlike userLike : likes) {
				
				userLikes.put(userLike.getContentid(), userLike.getId());
			}
		}
		
		return userLikes;
	}

}
