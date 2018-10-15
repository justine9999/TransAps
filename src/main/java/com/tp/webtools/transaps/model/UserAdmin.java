package com.tp.webtools.transaps.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue(value = "ADMIN")
public class UserAdmin extends User{
	
	private static final long serialVersionUID = 1L;
	
	private String type;
    
	public UserAdmin() {}
	
	public UserAdmin(String name, int age, double salary){
		super(name, age, salary);
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
