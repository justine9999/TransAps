package com.tp.webtools.transaps.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "GENERIC_USER")
public class GenericUser extends User{

	private static final long serialVersionUID = 1L;
	
	public GenericUser(String name, int age, double salary){
		super(name, age, salary);
	}
}
