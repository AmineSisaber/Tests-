package com.captain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.captain.entieties.Candidate;
import com.captain.metier.CandidateMetier;


@RestController
public class CandidateRestService {

	@Autowired
	private CandidateMetier candidatemetier;

	@RequestMapping(value="/candidate",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Candidate saveCandidate(@RequestBody Candidate c) {
		try {
			return candidatemetier.saveCandidate(c);	
			
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/candidate",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Candidate> getCandidate() {
		try {
			
		return candidatemetier.getCandidate();	
			
		} catch (Exception e) {
			return null;
		}
	}
//	@RequestMapping(value="/candidate",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
//	public Candidate getCandidate(String id) {
//		return candidatemetier.getCandidateByid(id);
//
//	}
}
