package com.captain.metier;

import java.util.List;

import com.captain.entieties.Parametter;

public interface ParametterMetier {
	
	public List<Parametter> getAllParam();
	public Parametter saveParam(Parametter p);
	public Parametter getParam(Long id);

}
