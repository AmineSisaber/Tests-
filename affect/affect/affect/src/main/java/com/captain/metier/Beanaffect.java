package com.captain.metier;

import org.springframework.stereotype.Service;

@Service
public class Beanaffect {

	Long idcan,idcons;
	double note;
	public Long getIdcan() {
		return idcan;
	}
	public void setIdcan(Long idcan) {
		this.idcan = idcan;
	}
	public Long getIdcons() {
		return idcons;
	}
	public void setIdcons(Long idcons) {
		this.idcons = idcons;
	}
	public double getNote() {
		return note;
	}
	public void setNote(double note) {
		this.note = note;
	}

	
}
