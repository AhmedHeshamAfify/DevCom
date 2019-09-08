package com.devcom.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public abstract class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	private long id;
	@Column(name ="TEXT")
	private String text;
	@Column(name ="DATE")
	private Date date;
	@Column(name ="VOTES_NUMBER")
	private Integer votesNumber;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	@JsonIgnoreProperties(value = { "posts"})
	private User user;

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
