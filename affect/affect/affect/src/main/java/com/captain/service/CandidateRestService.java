package com.captain.service;

import java.io.IOException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.captain.metier.AffectationMetier;
import com.captain.metier.CandidateMetier;
import com.captain.metier.ConseillereMetier;
import com.google.maps.errors.ApiException;

@RestController
public class CandidateRestService {

	@Autowired
	private CandidateMetier candidatemetier;
	@Autowired
	private ConseillereMetier conseilmetier;
	@Autowired
	
	private AffectationMetier affectation;
	@RequestMapping(value="/geo",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ArrayList getCandidate() throws ApiException, InterruptedException, IOException {
		return affectation.GetAffectation();
	
	}
}
