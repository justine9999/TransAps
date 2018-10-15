package com.tp.webtools.transaps.model;

public class UserFactory {
	
	public static User transformUser(String type, User user) {
		User transformed_user = null;
		if(type.equals(UserType.GENERIC.name())) {
			transformed_user = new UserGeneric();
		}else if(type.equals(UserType.ADMIN.name())) {
			transformed_user = new UserAdmin();
		}else {
			return null;
		}
		
		transformed_user.setName(user.getName());
		transformed_user.setAge(user.getAge());
		transformed_user.setSalary(user.getSalary());
		transformed_user.setId(user.getId());
		
		return transformed_user;
	}
}
