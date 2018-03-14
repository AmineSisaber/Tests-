package com.captain.metier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.captain.entieties.Candidate;
import com.captain.entieties.Conseillere;
import com.captain.entieties.Parametter;
import com.google.maps.errors.ApiException;

@Service
public class AffectationMetier<E> {
	final double maxdist = 200;
	final double maxdistancevol = 200;

	private ArrayList<E> list;

	@Autowired
	private CandidateMetier candidate;
	@Autowired
	private Beanaffect bean;
	@Autowired
	private ParaMetier parm;

	@Autowired
	private ConseillereMetier conseillere;

	@Autowired
	private IDistanceMetier distance;

	public AffectationMetier() {
		super();
	}

	public ArrayList<E> GetAffectation() throws ApiException, InterruptedException, IOException {

		int ecart = 2;

		List<Candidate> listCand = candidate.getCandidate();
		List<Conseillere> listCons = conseillere.ListConseillere();
		List<Parametter> listP = parm.GetParam();
		List<E> listmp = new ArrayList<>();
		double dist;
		String tmp;
		double latcandi = 0;
		double lngcandi = 0;
		double latsav = 0;
		double lngsav = 0;
		double distancevol = 0.0;
		TreeMap<Double, Beanaffect> tm = new TreeMap<>();
		for (int i = 0; i < listCand.size(); i++) {

			// *Test si c'est deja fiabiliséé
			if (listCand.get(i).isFiable() == 0) {

				// *********** ** Fiabilisation des la localisation

				// *********** ** calculer les cord de la candidate

				latcandi = distance.GetGeoCode(listCand.get(i).getAdresse()).lat;
				lngcandi = distance.GetGeoCode(listCand.get(i).getAdresse()).lng;

				// *********** ** recuprer les cord existants dans la BD

				latsav = listCand.get(i).getLat();
				lngsav = listCand.get(i).getLongt();

				// *********** ** verifier s'il ya un ecart

				if (latsav - latcandi > ecart || lngcandi - lngsav > ecart) {

					// ** ecart alors MAJ des CORD
					System.out.println("*************** Mettre a jour les coooooor ************************");

					System.out.println(listCand.get(i).getId());

					candidate.updateGeo((long) listCand.get(i).getId(), latcandi, lngcandi, 1);

				}
			}

			for (int j = 0; j < listCons.size(); j++) {
				// ** cALCULE DISTANCE vol de Oiseau

				distancevol = distance.distanceBird(latsav, lngsav, listCons.get(j).getLat(), listCons.get(j).getLat());

				// if(distancevol <= maxdistancevol) {

				dist = distance.getDistance(listCand.get(i).getAdresse(), listCons.get(j).getAdresse());
				bean.setIdcan(listCand.get(i).getId());
				bean.setIdcons(listCons.get(j).getId());
				double note = distance.giveNote(dist);
				bean.setNote(note);
				tm.put(note, bean);

				listmp.add((E) ("distancevol   " + distancevol));
				listmp.add((E) ("dist route   " + dist));
				listmp.add((E) ("note distance   " + note));

			}

			distance.randomSlect(tm);

		}
		// }

		return (ArrayList<E>) listmp;

	}

}
