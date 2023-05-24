package com.banking.sys.service;

import java.util.List;

import com.banking.sys.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.banking.sys.dto.UserRegistrationDto;
import com.banking.sys.model.User;

public interface UserService extends UserDetailsService {

	User save(UserRegistrationDto registrationDto);

	List<User> getAll();

	User getById(String userId);

	User getByEmail(String userEmail);

}
