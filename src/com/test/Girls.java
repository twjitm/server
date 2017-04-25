package com.test;

import java.io.Serializable;

public class Girls implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer id;
private String name;
private Integer age;
public String nickname;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getAge() {
	return age;
}
public void setAge(Integer age) {
	this.age = age;
}
@Override
public String toString() {
	return "Girls [id=" + id + ", name=" + name + ", age=" + age + "]";
}




}
