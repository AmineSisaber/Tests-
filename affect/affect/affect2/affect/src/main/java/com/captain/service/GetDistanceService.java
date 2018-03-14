package com.captain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.captain.metier.DistanceMaxMetier;

@RestController
public class GetDistanceService {
	
	@Autowired
	DistanceMaxMetier dis;
	
	@Autowired CandidateRestService candservice;
	
	@RequestMapping(value="/distance",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public String getDistance() {
		try {
		return dis.getMaxDistance()+""+candservice.getCandidate();	
			
		} catch (Exception e) {
			return null;
		}
	}
}