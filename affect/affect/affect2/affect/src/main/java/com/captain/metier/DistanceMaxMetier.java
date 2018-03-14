package com.captain.metier;

import java.io.IOException;
import java.net.MalformedURLException;
import org.springframework.stereotype.Service;

@Service
public interface DistanceMaxMetier {
	

public String getMaxDistance() throws MalformedURLException, IOException;
	
}
