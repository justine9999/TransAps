package com.tp.webtools.transaps.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.InheritanceType;

@Entity
@Table(name="USER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;
 
    @NotEmpty
    @Column(name="NAME", nullable=false)
    protected String name;
 
    @Column(name="AGE", nullable=false)
    protected Integer age;
 
    @Column(name="SALARY", nullable=false)
    protected double salary;
    
    @Column(name = "USER_TYPE", insertable = false, updatable = false)
    private String type;
    
    public User() {}
    
    public User(String name, int age, double salary){
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
    
    public void setId(Long id){
    	this.id = id;
    }
    
    public Long getId(){
    	return id;
    }
    
    public void setName(String name){
    	this.name = name;
    }
    
    public String getName(){
    	return name;
    }
    
    public void setAge(int age){
    	this.age = age;
    }
    
    public int getAge(){
    	return age;
    }
    
    public void setSalary(double salary){
    	this.salary = salary;
    }
    
    public double getSalary(){
    	return salary;
    }
    
    public void setType(String type){
    	this.type = type;
    }
    
    public String getType(){
    	return type;
    }
}
