package com.viSmart.viSmart;

import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Account {

	protected @Id @GeneratedValue int id;
	protected String password;
	protected String email;
	protected String picture_src;
	protected String first_name;
	protected String last_name;
	protected String study_group;
	protected int role;

	public int getId() {
		return this.id;
	}
	public String getEmail() {
		return this.email;
	}

	public String getPicture_src(){
		return this.picture_src;
	}

	public String getFirst_name(){
		return this.first_name;
	}

	public String getLast_name(){
		return this.last_name;
	}

	public int getRole() {
		return role;
	}


	public String getPassword() {
		return password;
	}
	public String getStudy_group() {
		return study_group;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPicture_src(String picture_src) {
		this.picture_src = picture_src;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setStudy_group(String study_group) {
		this.study_group = study_group;
	}

	public void setRole(int role) {
		this.role = role;
	}
}