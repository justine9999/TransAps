package com.tp.webtools.transaps.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class App implements Serializable{

	private static final long serialVersionUID = 1L;
	
    protected Long id;
	
	private String profile_picture;
    	
	private String title;
	
	private String description;
	
	private String content;
	
	private String author;
	
	private String division;
	
	private int downloads;
	
	private int rate;
	
	private long creation_time;
	
	private long last_update_time;
	
	private List<String> purposes;
	
	private List<String> languages;
	
	private List<String> source_file_types;
	
	private List<String> app_types;
	
	public App() {}
	
	public App(String title, String description, String author){
		
		this.title = title;
		this.description = description;
		this.author = author;
	}

	@Override
	public String toString() {
		return "App [id=" + id + ", profile_picture=" + profile_picture + ", title=" + title + ", description="
				+ description + ", content=" + content + ", author=" + author + ", division=" + division
				+ ", downloads=" + downloads + ", rate=" + rate + ", creation_time=" + creation_time
				+ ", last_update_time=" + last_update_time + ", purposes=" + purposes + ", languages=" + languages
				+ ", source_file_types=" + source_file_types + ", app_types=" + app_types + "]";
	}
	
	/*public App(String title, String description, String author, int downloads, int rate, String division, List<String> purpose,
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
	}*/

}
