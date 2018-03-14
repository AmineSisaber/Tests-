package com.quiz.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question implements Serializable{
	@Id
	@GeneratedValue
int id ; 
String question,reponse;

public Question( String question, String reponse) {
	super();
	this.id = id;
	this.question = question;
	this.reponse = reponse;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getQuestion() {
	return question;
}

public void setQuestion(String question) {
	this.question = question;
}

public String getReponse() {
	return reponse;
}

public void setReponse(String reponse) {
	this.reponse = reponse;
}
	
}
