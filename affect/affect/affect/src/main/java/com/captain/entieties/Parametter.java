package com.captain.entieties;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Parametter implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String NameP;
	private String valueP;

	public Parametter(Long id, String nameP, String valueP) {
		super();
		this.id = id;
		NameP = nameP;
		this.valueP = valueP;
	}

	public Parametter() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameP() {
		return NameP;
	}

	public void setNameP(String nameP) {
		NameP = nameP;
	}

	public String getValueP() {
		return valueP;
	}

	public void setValueP(String valueP) {
		this.valueP = valueP;
	}

}
