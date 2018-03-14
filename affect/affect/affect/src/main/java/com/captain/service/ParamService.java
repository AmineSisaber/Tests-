package com.captain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.captain.entieties.Parametter;
import com.captain.metier.ParaMetier;

@RestController
public class ParamService {

	@Autowired
	private ParaMetier paraMetier;
	
	@RequestMapping(value="/param",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Parametter> GetParam() {
		return paraMetier.GetParam(); 
		
	}
}
