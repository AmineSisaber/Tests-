package com.captain.metier;

import java.io.IOException;
import java.util.TreeMap;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;

public interface IDistanceMetier<V, K> {

	public double getDistance(String source, String Dest);

	public LatLng GetGeoCode(String adresse) throws ApiException, InterruptedException, IOException;

	public boolean camparAdres(LatLng cor, String adresse);

	public double giveNote(double dist);

	public double distanceBird(double lat1, double lon1, double lat2, double lon2);
	public TreeMap<K, V> randomSlect(TreeMap<K, V> list);

}
