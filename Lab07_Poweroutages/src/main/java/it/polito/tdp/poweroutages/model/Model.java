package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutage;
import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	List<PowerOutage> soluzioneOttima;
	private int maxPersone;
	private static int annoMinore;
	private static int annoMaggiore;

	public Model() {
		podao = new PowerOutageDAO();
		soluzioneOttima = new ArrayList<>();
		annoMinore = podao.getMinYear();
		annoMaggiore = podao.getMaxYear();
		maxPersone = 0;
	}

	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<PowerOutage> getPOList(Nerc nerc) {
		return this.podao.getPowerOutageInNERC(nerc);
	}

	public List<PowerOutage> cercaMassimoPersone(Nerc nerc, int X, int Y) {

		List<PowerOutage> parziale = new ArrayList<>();
		List<PowerOutage> POList = this.podao.getPowerOutageInNERC(nerc);
		cerca(parziale, POList, X, Y);

		return soluzioneOttima;
	}

	private void cerca(List<PowerOutage> parziale, List<PowerOutage> POList, int X, int Y) {
		int persone = 0;
		for (PowerOutage po : parziale) {
			persone += po.getCustomers_affected();
		}
		if (persone > this.maxPersone) {
			maxPersone = persone;
			this.soluzioneOttima = new ArrayList<>(parziale);

		}
		/*
		 * if (!this.controllaLimiti(parziale, X, Y)) { return; }
		 */
		if (parziale.size() == POList.size()) {
			return;
		}
		for (PowerOutage po : POList) {
			if (!parziale.contains(po)) {
				parziale.add(po);
				if (this.controllaLimiti(parziale, X, Y)) {
					cerca(parziale, POList, X, Y);
					parziale.remove(parziale.size() - 1);
				}
			}
		}

	}

	private boolean controllaLimiti(List<PowerOutage> parziale, int X, int Y) {
		int secondiMax = Y * 3600;
		int secondiParziale = 0;
		//int minore = annoMaggiore;
	//	int maggiore = annoMinore;
		for (PowerOutage po : parziale) {
			secondiParziale += po.getEvent_duration().getSeconds();

			/*
			 * if (po.getDate_event_began().getYear() < minore) { minore =
			 * po.getDate_event_began().getYear(); } if (po.getDate_event_began().getYear()
			 * > maggiore) { maggiore = minore = po.getDate_event_began().getYear(); }
			 */
		}

		if (parziale.size() > 1 && (parziale.get(parziale.size() - 1).getDate_event_began().getYear()
				- parziale.get(0).getDate_event_began().getYear() + 1) > X) {
			return false;

		}

		if (secondiParziale <= secondiMax) {
			return true;
		}
		return false;
	}
}
