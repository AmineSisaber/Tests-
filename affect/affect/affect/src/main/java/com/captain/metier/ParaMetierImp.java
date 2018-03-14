package com.captain.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.captain.dao.ParamRepresitory;
import com.captain.entieties.Parametter;

@Service
public class ParaMetierImp implements ParaMetier{

	@Autowired
	private ParamRepresitory paramrep;

	@Override
	public List<Parametter> GetParam() {
		
		return paramrep.findAll();
		
}
	
}