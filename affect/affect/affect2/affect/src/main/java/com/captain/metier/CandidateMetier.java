package com.captain.metier;

import java.util.List;


import com.captain.entieties.Candidate;

public interface CandidateMetier {
public Candidate saveCandidate(Candidate c);
public List<Candidate> getCandidate();
//public Candidate getCandidateByid(String id);
}
