package com.csresource.repositories;

import org.springframework.data.repository.CrudRepository;
import com.csresource.jpa.User;

public interface UserRepository  extends CrudRepository<User, String> {

	

}
