package com.csresource.repositories;

import java.util.LinkedList;

import org.springframework.data.repository.CrudRepository;

import com.csresource.jpa.User;
import com.csresource.jpa.Userlike;

public interface UserLikesRepository extends CrudRepository<Userlike, String> {

	LinkedList<Userlike> findByUser(User user);
}
