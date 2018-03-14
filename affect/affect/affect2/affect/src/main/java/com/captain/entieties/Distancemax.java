package com.captain.entieties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Distancemax {
	@Id@GeneratedValue
private Long id;
private String code,distanceMax,distmaxMaraine;

public Distancemax() {
	super();
}

public String getDistmaxMaraine() {
	return distmaxMaraine;
}

public void setDistmaxMaraine(String distmaxMaraine) {
	this.distmaxMaraine = distmaxMaraine;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

public String getDistanceMax() {
	return distanceMax;
}

public void setDistanceMax(String distanceMax) {
	this.distanceMax = distanceMax;
}
}
