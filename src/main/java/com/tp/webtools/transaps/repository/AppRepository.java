package com.tp.webtools.transaps.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.webtools.transaps.model.App;

@Repository
public interface AppRepository extends JpaRepository<App, Long> {
 
    App findByName(String name);
 
}
