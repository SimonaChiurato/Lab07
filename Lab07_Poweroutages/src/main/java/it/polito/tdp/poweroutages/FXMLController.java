package it.polito.tdp.poweroutages;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.DAO.PowerOutage;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ChoiceBox<Nerc> boxChoice;

	@FXML
	private TextField txtYears;

	@FXML
	private TextField txtHours;

	@FXML
	private Button btnAnalysis;

	@FXML
	private TextArea txtResult;

	private Model model;

	@FXML
	void doAnalysis(ActionEvent event) {
		this.txtResult.clear();
		Nerc nerc = this.boxChoice.getValue();
		String yearsString = this.txtYears.getText();
		String hoursString = this.txtHours.getText();
		int years = 0;
		int hours = 0;
		if (yearsString.matches("[0-9]*") && hoursString.matches("[0-9]*")) {
			years = Integer.parseInt(yearsString);
			hours = Integer.parseInt(hoursString);

		}
		List<PowerOutage> soluzione = this.model.cercaMassimoPersone(nerc, years, hours);
		String s = "";
		int personeTot = 0;
		int secondiTot = 0;
		for (PowerOutage po : soluzione) {
			s += po + "\n";
			personeTot += po.getCustomers_affected();

			Duration d = Duration.between(po.getDate_event_began().toLocalTime(),
					po.getDate_event_finished().toLocalTime());
			secondiTot += d.getSeconds();
		}
		double ore= Math.abs(secondiTot/3600);
		this.txtResult.appendText("Persone totali: "+personeTot+"\nOre totali: "+ore+"\n"+s);

	}

	public void SetModel(Model model) {
		this.model = model;
		this.boxChoice.getItems().addAll(this.model.getNercList());
	}

	@FXML
	void initialize() {
		assert boxChoice != null : "fx:id=\"boxChoice\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnAnalysis != null : "fx:id=\"btnAnalysis\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

	}
}
