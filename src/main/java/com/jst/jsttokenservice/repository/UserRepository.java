package com.jst.jsttokenservice.repository;

import com.jst.jsttokenservice.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<AppUser, Integer> {

  Optional<AppUser> findByUsername(String email);

}
