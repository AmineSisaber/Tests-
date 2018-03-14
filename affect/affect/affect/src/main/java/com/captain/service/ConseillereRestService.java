package com.captain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.captain.entieties.Conseillere;
import com.captain.metier.ConseillereMetier;


@RestController
public class ConseillereRestService {
	@Autowired
	private ConseillereMetier conseilleremetier;
	
	@PostMapping(value="/conseillere",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Conseillere saveConseillere(@RequestBody Conseillere c) {		 
		Conseillere saveConseillere = conseilleremetier.saveConseillere(c);
		return saveConseillere;
	}
	
	@RequestMapping(value="/conseillere",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Conseillere> ListConseillere() {
		return conseilleremetier.ListConseillere();
	}
	
	
}
