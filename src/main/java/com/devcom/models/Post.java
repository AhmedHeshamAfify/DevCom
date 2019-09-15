package com.devcom.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Indexed
@Entity
@Inheritance(strategy=InheritanceType.JOINED)  
public abstract class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	private long id;
	@Field
	@Column(name ="TEXT")
	private String text;
	@Column(name ="DATE")
	private Date date = new Date();
	@Column(name ="VOTES_NUMBER")
	private Integer votesNumber=0;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	@JsonIgnoreProperties(value = { "posts"})
	private User user;

	public Post() {
	
	}
	
	
	
	public Post(long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}



	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public Date getDate() {
		return date;
	}

	public Integer getVotesNumber() {
		return votesNumber;
	}

	public User getUser() {
		return user;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setVotesNumber(Integer votesNumber) {
		this.votesNumber = votesNumber;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
