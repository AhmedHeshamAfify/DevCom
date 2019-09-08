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
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "VIEWS_NUMBER")
	private Integer viewsNumber;
	
	@OneToMany(targetEntity = Answer.class, mappedBy = "question")
	@JsonIgnoreProperties({"question"})
	private Set<Answer> answers;

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Integer getViewsNumber() {
		return viewsNumber;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setViewsNumber(Integer viewsNumber) {
		this.viewsNumber = viewsNumber;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
	
	
	
	
}
