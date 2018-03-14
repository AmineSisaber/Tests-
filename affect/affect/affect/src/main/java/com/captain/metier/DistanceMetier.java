package com.captain.metier;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.httpclient.util.URIUtil;
import org.springframework.stereotype.Service;
import com.captain.entieties.DistanceMatrixApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.mysql.fabric.xmlrpc.base.Array;

import net.bytebuddy.matcher.LatentMatcher;

@SuppressWarnings("unused")
@Service
public class DistanceMetier<V, K> implements IDistanceMetier<Object, Object> {

	final String apiKey = "AIzaSyBJB5Qw1LKwvM7dm0OCl47GFzjjB98YNlc";
	final int note = 100;

	public double getDistance(String source, String Dest) {
		GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
		double resulat = 0;
		String distmetter = null;
		try {

			DistanceMatrixApiRequest req;
			req = DistanceMatrixApi.newRequest(context);

			DistanceMatrix trix = req.origins("" + source).destinations("" + Dest).mode(TravelMode.DRIVING)
					.language("fr-FR").await();
			distmetter = trix.rows[0].elements[0].distance.humanReadable;
			distmetter = distmetter.replaceAll("km", "");
			distmetter = distmetter.trim();
			resulat = Double.parseDouble(distmetter);

		} catch (ApiException e) {
			System.out.println(e);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return resulat;

	}

	public LatLng GetGeoCode(String source) throws ApiException, InterruptedException, IOException {

		GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
		GeocodingResult[] results = GeocodingApi.geocode(context, source).await();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		return (results[0].geometry.location);
	}

	public String ReversGeoCode(LatLng cor) throws ApiException, InterruptedException, IOException {

		GeoApiContext context = new GeoApiContext().setApiKey(apiKey);

		GeocodingResult[] results = GeocodingApi.reverseGeocode(context, cor).await();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		return (gson.toJson(results[0].addressComponents));

	}

	public boolean camparAdres(LatLng cor, String adresse) {

		return false;

	}

	public double giveNote(double dist) {

		return (100 - (Math.sqrt(dist) / 4));

	}

	public double distanceBird(double lat1, double lon1, double lat2, double lon2) {

		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
		return (dist);
	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

	@Override
	public TreeMap randomSlect(@SuppressWarnings("rawtypes") TreeMap list) {
		double some = 0;
		int cpt = 0;
		Array tab = new Array();
		double taux=0;

		Set<?> set = list.entrySet();
		Iterator<?> i = set.iterator();
		Set<Double> keys = list.keySet();
		
		 for(Double key: keys){
	            System.out.println("La valeur de "+key+" est: "+list.get(key));
	        }
		 
		while (i.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry me = (Map.Entry) i.next();
			Beanaffect b = (Beanaffect) me.getValue();
			System.out.println("note" + b.getNote());
			System.out.println("idcan" + b.getIdcan());
			System.out.println("idcan" + b.getIdcons());
			some = some + b.getNote();
			cpt++;

		}
		
		System.out.println("la somme de toutes les notes est "+some);

//		i = set.iterator();
//		while (i.hasNext()) {
//			@SuppressWarnings("rawtypes")
//			Map.Entry me = (Map.Entry) i.next();
//			Beanaffect b = (Beanaffect) me.getValue();
//			taux = (b.getNote()/some)*100; 
//			long j = Math.round(taux);
//			System.out.println("le nombre de case pour la note "+b.getNote()+" est"+taux);
//				}

		return list;
	}

}
