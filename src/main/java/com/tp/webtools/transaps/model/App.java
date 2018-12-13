package com.tp.webtools.transaps.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class App implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String profile_picture;
    	
	private String title;
	
	private String description;
	
	private String content;
	
	private String author;
	
	private String division;
	
	private int downloads;
	
	private double rate;
	
	private long creation_time;
	
	private long last_update_time;
	
	private List<String> purposes;
	
	private List<String> languages;
	
	private List<String> source_file_types;
	
	private List<String> app_types;
	
	private String normalized_info;
	
	public App() {}


	@Override
	public String toString() {
		return "App [profile_picture=" + profile_picture + ", title=" + title + ", description=" + description
				+ ", content=" + content + ", author=" + author + ", division=" + division + ", downloads=" + downloads
				+ ", rate=" + rate + ", creation_time=" + creation_time + ", last_update_time=" + last_update_time
				+ ", purposes=" + purposes + ", languages=" + languages + ", source_file_types=" + source_file_types
				+ ", app_types=" + app_types + ", normalized_info=" + normalized_info + "]";
	}
	
	public void generateNormalized_info(){
		
		StringBuilder sb = new StringBuilder();
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field f : fields){
			if(f.getType().equals(String.class) && !f.getName().equals("normalized_info")){
				try {
					String text = f.get(this).toString();
					if(!text.equals("")){
						sb.append(text + " ");
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}else if(f.getType().equals(List.class)){
				try {
					List list = (List)f.get(this);
					Iterator it = list.iterator();
					while(it.hasNext()){
						Object obj = it.next();
						if(obj instanceof String){
							String text = obj.toString();
							if(!text.equals("")){
								sb.append(text + " ");
							}
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
			
		this.setNormalized_info(sb.toString().trim().toLowerCase());
	}

}
