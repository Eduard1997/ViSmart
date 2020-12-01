package com.viSmart.viSmart;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Account {

	@JsonIgnore
	private @Id @GeneratedValue Long id;
	private String username;
	private String email;
	private String picture_src;
	private String first_name;
	private String last_name;

	public Long getId() {
		return this.id;
	}
	public String getUsername() {
		return this.username;
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

}