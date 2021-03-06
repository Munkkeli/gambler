/**
* Pääohjelma joka käynnistaa kaikki sivuohjelmat ja hoitaa pelaamisen
*
* @author  Tuomas Pöyry
*/

import lejos.hardware.Sound;
import lejos.utility.Delay;

public class Gambler {
	
	/**
	* Palauttaa lukituksen arvon.
	* 
	* @param args Ohjelman käynnistys argumentit
	*/
	public static void main(String[] args) {
		Panos laskuri = new Panos();
		laskuri.start();
		
		Panoskey panos = new Panoskey(laskuri);
		panos.start();
		
		
		Kaukosaato kaukosaato = new Kaukosaato();
		kaukosaato.start();
		
		Pyoritys pyoritys = new Pyoritys(laskuri);
		
		Naytto naytto = new Naytto(laskuri, kaukosaato);
		naytto.start();
		
		Vipu vipu = new Vipu();
		
		while (true) {
			if (!vipu.WaitForButton()) break;
			
			if (!laskuri.hasRaha(1) || kaukosaato.getLocked()) {
				Sound.beep();
				Delay.msDelay(500);
			} else {
				laskuri.vahennaRaha();
				pyoritys.Spin();
			}
		}
		
		naytto.interrupt();
		pyoritys.interrupt();
		laskuri.interrupt();
		panos.interrupt();
		kaukosaato.interrupt();
		vipu.interrupt();
		
		return;
	}
}
