package com.captain.metier;

import java.util.List;


import com.captain.entieties.Candidate;

public interface CandidateMetier<E> {
public Candidate saveCandidate(Candidate c);
public List<Candidate> getCandidate();
public Candidate getCandidateByid(Long id);
public void updateGeo(Long id, double latcandi, double lngcandi,int fiable);


}
		