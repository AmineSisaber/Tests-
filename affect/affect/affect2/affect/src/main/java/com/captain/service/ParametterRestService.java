package com.captain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.captain.entieties.Parametter;
import com.captain.metier.ParametterMetier;

@Controller
@RestController
public class ParametterRestService {
	
	@Autowired
	private ParametterMetier parametterMetier ;
	@RequestMapping(value="/parametterSave",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Parametter saveParam(@RequestParam("parametterMetier") Parametter p) {	
		Parametter param = parametterMetier.saveParam(new Parametter());
		return param;
	}
	@RequestMapping(value="/parametter",method=RequestMethod.GET)
		public List<Parametter> getParam(){
		return parametterMetier.getAllParam();
	}
}
