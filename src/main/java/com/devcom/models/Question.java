package com.devcom.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Indexed
@Entity
@PrimaryKeyJoinColumn(name="ID")  
public class Question extends Post{

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "ID")
//	private long id;
	@Field(termVector = TermVector.YES)
	@Column(name = "TITLE")
	private String title;
	@Column(name = "VIEWS_NUMBER")
	private Integer viewsNumber = 0;
	
	@OneToMany(targetEntity = Answer.class, mappedBy = "question")
	@Cascade({CascadeType.REMOVE})
	@JsonIgnoreProperties({"question"})
	private Set<Answer> answers;

	@ManyToMany
	@JoinTable(name = "Question_Category", joinColumns = { @JoinColumn(name = "QUESTION_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "CATEGORY_ID") })
	@JsonIgnoreProperties({"questions"})
	private Set<Category> categories;

	public Question(long id, String text) {
	super(id, text);
	}
	
	public Question() {
		
	}
	
	
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
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
