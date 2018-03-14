package com.captain.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.captain.dao.ConseillereRepresitory;
import com.captain.entieties.Conseillere;

@Service
public class ConseillereMetierImp implements ConseillereMetier{
	@Autowired
	private ConseillereRepresitory conseillererep;
	@Override
	public Conseillere saveConseillere(Conseillere c) {
		
		return conseillererep.save(c);
	}

	@Override
	public List<Conseillere> ListConseillere() {
		
		return conseillererep.findAll();
	}
}
