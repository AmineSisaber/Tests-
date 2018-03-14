package com.captain.entieties;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Candidate implements Serializable{
	@Id @GeneratedValue
	private Long id ; 
	private String name,prenom,adresse,longitude,lang;
	
	public Candidate() {
		super();
	}
	public Candidate(long id, String name, String prenom, String adresse) {
		super();
		this.id = id;
		this.name = name;
		this.prenom = prenom;
		this.adresse = adresse;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
}
