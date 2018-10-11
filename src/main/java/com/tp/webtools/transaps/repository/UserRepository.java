package com.tp.webtools.transaps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.webtools.transaps.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
    Optional<User> findByName(String name);
 
}
