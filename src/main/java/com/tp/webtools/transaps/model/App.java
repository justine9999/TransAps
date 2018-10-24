package com.tp.webtools.transaps.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="APP")
public class App implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;
	
    @Column(name="PROFILE_PICTURE")
	private String profilePicture;
	
	@NotEmpty
    @Column(name="TITLE", nullable=false)
	private String title;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="AUTHOR")
	private String author;
	
	@Column(name="DIVISION")
	private String division;
	
	@Column(name="DOWNLOADS")
	private int downloads;
	
	@Column(name="RATE")
	private int rate;
	
	@Column(name="CREATION_TIME")
	private long creationTime;
	
	@Column(name="LAST_UPDATE_TIME")
	private long lastUpdateTime;
	
	@Column(name="PURPOSES")
	private List<String> purpose;
	
	@Column(name="LANGUAGES")
	private List<String> languages;
	
	@Column(name="SOURCE_FILE_TYPES")
	private List<String> sourceFileTypes;
	
	@Column(name="APP_TYPES")
	private List<String> appTypes;
	
	public App(String title, String description, String author){
		
		this.title = title;
		this.description = description;
		this.author = author;
	}
	
	public App(String title, String description, String author, int downloads, int rate, String division, List<String> purpose,
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
