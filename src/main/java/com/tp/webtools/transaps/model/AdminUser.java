package com.tp.webtools.transaps.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ADMIN_USER")
public class AdminUser extends User{
	
	private static final long serialVersionUID = 1L;
	
	public AdminUser(String name, int age, double salary){
		super(name, age, salary);
	}
}
