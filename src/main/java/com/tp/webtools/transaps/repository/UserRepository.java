package com.tp.webtools.transaps.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.webtools.transaps.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
    User findByName(String name);
 
}
