package com.UpwardPollingsystemapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.UpwardPollingsystemapplication.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	@Query("SELECT u FROM User u WHERE u.userid = ?1 AND u.password = ?2")
    Optional<User>findByUseridAndPassword(String userid, String password);
	
}
