package com.captain.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.captain.dao.CandidateRepresitory;
import com.captain.entieties.Candidate;


@Service
public class CandidateMetierImp implements CandidateMetier{

	@Autowired
	private CandidateRepresitory candidaterep;
	@Override
	public Candidate saveCandidate(@RequestParam Candidate c) {
		// TODO Auto-generated method stub
		return candidaterep.save(c);
	}

	@Override
	public List<Candidate> getCandidate() {
		
		return candidaterep.findAll();
		
	}

//	@Override
//	public Candidate getCandidateByid(String id) {
//		
//		return candidaterep.GetCandidateByID(id);
//	}


}
