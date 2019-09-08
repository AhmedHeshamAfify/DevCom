package com.devcom.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "EMAIL", unique= true, nullable = false)
	private String email;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "TYPE")
	private UserType type;
	@Column(name = "SCORE")
	private Integer score;
	
	@OneToMany(targetEntity = Post.class, mappedBy = "user")
	@JsonIgnoreProperties("user")
	private Set<Post> posts;

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(long id, String name, String email, String password, UserType type, Integer score) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = type;
		this.score = score;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public UserType getType() {
		return type;
	}

	public Integer getScore() {
		return score;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	} 
	
	
	
}
