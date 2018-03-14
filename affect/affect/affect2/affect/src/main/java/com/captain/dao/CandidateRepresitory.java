package com.captain.dao;

 import org.springframework.data.jpa.repository.JpaRepository;

import com.captain.entieties.Candidate;


public interface CandidateRepresitory extends JpaRepository<Candidate,Long>  {

//	@Query("select c from Candidate c where c.id = id")
//	public Candidate GetCandidateByID(@Param("id")String id);
}
