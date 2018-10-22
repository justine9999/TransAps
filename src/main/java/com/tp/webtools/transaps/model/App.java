package com.tp.webtools.transaps.model;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class App implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter private String profilePicture;
	@Getter @Setter private String title;
	@Getter @Setter private String description;
	@Getter @Setter private String content;
	@Getter @Setter private String author;
	@Getter @Setter private String division;
	@Getter @Setter private String purpose;
	@Getter @Setter private int downloads;
	@Getter @Setter private int rate;
	@Getter @Setter private long  creationTime;
	@Getter @Setter private long  lastUpdateTime;
	@Getter @Setter private List<String> languages;
	@Getter @Setter private List<String> sourceFileTypes;
	@Getter @Setter private List<String> appTypes;
	
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
