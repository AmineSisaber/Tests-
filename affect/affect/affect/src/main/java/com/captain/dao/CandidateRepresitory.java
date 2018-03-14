package com.captain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.captain.entieties.Candidate;

@Repository
public interface CandidateRepresitory extends JpaRepository<Candidate,Long>  {

	@Transactional
	@Modifying
	@Query( "UPDATE Candidate t SET t.lat= :latcandi, t.longt= :lngcandi, t.fiable= :fiable WHERE t.id = :id  ")
	
	  public void updateGeo (@Param ("id")Long id , @Param ("latcandi") double latcandi , @Param ("lngcandi") double lngcandi,@Param ("fiable") int fiable);
}
