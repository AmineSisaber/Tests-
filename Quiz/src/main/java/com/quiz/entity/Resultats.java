package com.quiz.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Resultats implements Serializable{
@Id
@GeneratedValue
	int id ; 
	double resultats;
	
	public Resultats(int id, double resultats) {
		super();
		this.id = id;
		this.resultats = resultats;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getResultats() {
		return resultats;
	}
	public void setResultats(double resultats) {
		this.resultats = resultats;
	} 
	
	
}
