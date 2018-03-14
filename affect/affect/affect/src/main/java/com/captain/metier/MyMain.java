package com.captain.metier;

import java.io.IOException;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;

public class MyMain {

	public static <E> void main(String[] args) throws ApiException, InterruptedException, IOException {
		
			DistanceMetier d = new DistanceMetier();
			LatLng a = d.GetGeoCode("castelane marseille");
			LatLng b = d.GetGeoCode("paris");

			System.out.println("distaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaannnnnnnnnnnnnnnnnnnnnce"+	d.GetGeoCode("castelane marseille"));
			d.distanceBird(a.lat, a.lng, b.lat, b.lng);
			System.out.println(""+d.ReversGeoCode(d.GetGeoCode("castelane marseille")));
			System.out.println("la distance est dis"+(d.distanceBird(a.lat, a.lng, b.lat, b.lng)));
			
			AffectationMetier<E> affect = new AffectationMetier<>();
			
			affect.GetAffectation();
			
			
			
	}
	
}
