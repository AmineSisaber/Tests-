package com.captain.metier;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.captain.dao.ParamettreRepresitory;
import com.captain.entieties.Parametter;

@Service
public class ParametterMetierImp implements ParametterMetier{
	
	@Autowired
	private ParamettreRepresitory ParamettreRepresitory;

	@Override
	public List<Parametter> getAllParam() {
		
		return ParamettreRepresitory.findAll();
	}

	@Override
	public Parametter getParam(Long id) {
		
	 return ParamettreRepresitory.getOne(id);
	}

	@Override
	public Parametter saveParam(Parametter p) {
		
		return ParamettreRepresitory.save(p);
		
	}

}
