package com.tp.webtools.transaps.model;

import java.io.Serializable;

public class App implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String description;
	private String author;
	
	public App(String title, String description, String author){
		this.title = title;
		this.description = description;
		this.author = author;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public String getAuthor(){
		return this.author;
	}
}
