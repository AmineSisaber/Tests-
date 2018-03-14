package com.captain.metier;

import java.util.List;

import com.captain.entieties.Conseillere;

public interface ConseillereMetier {
	public Conseillere  saveConseillere(Conseillere c);
	public List<Conseillere> ListConseillere();
}
