/**
* Piirtää tiedot robotin näytölle.
*
* @author  Tuomas Pöyry
*/

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Naytto extends Thread {
	private Panos panos;
	private Kaukosaato kaukosaato;
	
	/**
	* Palauttaa lukituksen arvon.
	* 
	* @param panos Panos olio josta näytön tiedot haetaan.
	* @param kaukosaato Kaukosaato olio josta näyttö kuuntelee koneen lukituksen tilaa.
	*/
	public Naytto(Panos panos, Kaukosaato kaukosaato) {
		this.panos = panos;
		this.kaukosaato = kaukosaato;
	}
	
	/**
	* Aloittaa näytön piirtämisen.
	* Piirtää tiedot uudestaan 2 kertaa sekunnissa.
	*/
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			Draw();
			Delay.msDelay(500);
		}
	}
	
	/**
	* Piirtää näytön tiedot uudestaan
	*/
	public void Draw() {
		LCD.clear();
		
		if (!kaukosaato.getLocked()) {
			LCD.drawString("Sinulla on " + panos.getRahaString() + "e", 1, 1);
			LCD.drawString("Panoksesi on " + panos.getPanosString() + "e", 1, 3);
		} else {
			LCD.drawString("Todista", 1, 1);
			LCD.drawString("henkilollisyys!", 1, 2);
		}
	}
}
