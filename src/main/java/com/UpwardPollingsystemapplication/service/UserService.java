package com.UpwardPollingsystemapplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UpwardPollingsystemapplication.DTO.LoginDto;
import com.UpwardPollingsystemapplication.Entity.User;
import com.UpwardPollingsystemapplication.repository.UserRepo;

@Service
public class UserService implements Userserviceinter{

	@Autowired
	private UserRepo repo;
	@Override
	public void saveUser(User user) {
		
		repo.save(user);
	}
	public Optional<User> checkUserDetailsByUseridandPassword(LoginDto logindto) {
		Optional<User> user = repo.findByUseridAndPassword(logindto.getUserid(),logindto.getPassword());
		return user;
	}
	 

}
