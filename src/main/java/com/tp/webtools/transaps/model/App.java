package com.tp.webtools.transaps.model;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class App implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String profilePicture;
	private String title;
	private String description;
	private String content;
	private String author;
	private String division;
	private String purpose;
	private int downloads;
	private int rate;
	private long creationTime;
	private long lastUpdateTime;
	private List<String> languages;
	private List<String> sourceFileTypes;
	private List<String> appTypes;
	
	public App(String title, String description, String author){
		
		this.title = title;
		this.description = description;
		this.author = author;
	}
	
	public App(String title, String description, String author, int downloads, int rate, String division, String purpose,
			List<String> languages, List<String> sourceFileTypes, List<String> appTypes	){
		
		this.title = title;
		this.description = description;
		this.author = author;
		this.downloads = downloads;
		this.rate = rate;
		this.division = division;
		this.purpose = purpose;
		this.languages = languages;
		this.sourceFileTypes = sourceFileTypes;
		this.appTypes = appTypes;
		this.creationTime = new Date().getTime();
		this.lastUpdateTime = new Date().getTime();
	}

}
