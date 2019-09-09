package com.devcom.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@PrimaryKeyJoinColumn(name="ID")  
public class Answer extends Post {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "ID")
//	private long id;
	@Column(name = "VERIFIED")
	private Boolean verified;
	@Column(name = "TYPE")
	private AnswerType type = AnswerType.Answer;

	@ManyToOne
	@JoinColumn(name = "QUESTION_ID")
	@JsonIgnoreProperties({ "answers" })
	private Question question;


	public Boolean getVerified() {
		return verified;
	}

	public AnswerType getType() {
		return type;
	}

	public Question getQuestion() {
		return question;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public void setType(AnswerType type) {
		this.type = type;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
