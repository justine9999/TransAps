package com.tp.webtools.transaps.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tag {
	
	private String text;
	
	private String type;
	
	public Tag(String text, String type){
		this.text = text;
		this.type = type;
	}
}
