package com.captain.entieties;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conseillere implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String name, prenom, adresse;
	private double lat, lgt;

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLgt() {
		return lgt;
	}

	public void setLgt(double lgt) {
		this.lgt = lgt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conseillere() {
		super();
	}

	public Conseillere(long id, String name, String prenom, String adresse) {
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
