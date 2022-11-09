package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final LinkedList<Stationnement> myStationnements = new LinkedList<Stationnement>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage
	 * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) {
		// Et si la voiture est déjà dans un garage ?
		Stationnement dernierStat = myStationnements.getLast();
		if (dernierStat.estEnCours()) {
			throw new IllegalArgumentException("La voiture est déjà dans un garage");
		}
		else {
		Stationnement s = new Stationnement(this, g);
		myStationnements.add(s);
		}
	}

	/**
	 * Fait sortir la voiture du garage
	 * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() {
		Stationnement dernierStat = myStationnements.getLast();

		if (dernierStat.estEnCours() == false) {
			throw new IllegalArgumentException("La voiture est déjà hors d'un garage");
		}
		else {
			myStationnements.getLast().terminer();
		}
	}

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public HashSet<String> garagesVisites() {
		HashSet<String> garageVisites = new HashSet<String>();
		for (int i=0; i < myStationnements.size(); i++) {
			String garage = myStationnements.get(i).getGarage().getName();
			garageVisites.add(garage);
		}
		return garageVisites;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
		boolean estDansUnGarage = false;
		Stationnement dernierStat = myStationnements.getLast();
		if (dernierStat.estEnCours() == true)
			estDansUnGarage = true;
		return estDansUnGarage;
		// Vrai si le dernier stationnement est en cours
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des
	 * dates d'entrée / sortie dans ce garage
	 * <br>
	 * Exemple :
	 * 
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
		System.out.println(garagesVisites());
	}

}
