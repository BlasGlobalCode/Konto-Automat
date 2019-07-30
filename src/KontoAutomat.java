package de.lotto_rlp.aufgabeZwei;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class KontoAutomat {

	public static void main(String[] args) {
		
		String zeichnen = ""; 		//wert der in der Datei steht
		int AuszahlSumme = 0; 		//Der eingegebene Betrag
		String Benutzereingabe="";	// Name Des Kontos
		
		//////Datum Gramm////////// 
		 GregorianCalendar aktuell = new GregorianCalendar(); 
		 SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		 System.out.println(df.format(aktuell.getTime()));
		////////////////// Datei Pfad //////////////////
		
//		String dateiName ="pfad"; ///// Datei Pfad
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("************************************************");
		
		///////////////Abfrage des BenutzerKontos
		
		System.out.println("Willkommen! Bitte geben sie Ihren Kontoname ein: ");
		Benutzereingabe = sc.next();
		
		File file = new File("pfad", Benutzereingabe +".txt"); ////
		
		if(!file.isFile()) { 	//ob BenutzerKonto nicht vorhanden
			
			System.out.println("Es tut mir leid. Aber sie besitzen kein Konto bei uns."); /// Bei Falschen KontoNamen
			
			System.out.println("************************************************");
		//////////////BenutzerKonto vorhanden     //////////////////
		}else {
//			
//		String text = "";
//		char buchstabe;    //// auslen von einzelnen Buchstaben
		try {
			
			System.out.println("Sie sind als " + Benutzereingabe.toUpperCase() + " angemeldet :)");
			System.out.println();
			
				Scanner filescanner= new Scanner (file); // Ermöglicht das Lesen des Files
						
		do {
				
			zeichnen += filescanner.nextLine();

			}while (filescanner.hasNext());
			
		////////////Ende  Des FileScaanner
		filescanner.close();
		
		////////////Auswahl    //////////////////
		System.out.println("Kontostand anzeigen ? Drücke 1");
		System.out.println();
		System.out.println("Geld einzahlen ? Drücke 2");
		System.out.println();
		System.out.println("Geld auszahlen ? Drücke 3");
		System.out.println();
		
		int Auswahl; 
		Auswahl = sc.nextInt();
		System.out.println("************************************************");
		
////////////Filereader

		/////// EinZahlungsMethode wenn "2" gedrueckt wird
		
		if(Auswahl == 1) {
			
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			ArrayList<String> liste = new ArrayList<>();
			do {
				zeichnen = br.readLine();
				if(zeichnen!=null)	liste.add(zeichnen);
				} while (zeichnen != null);
			br.close();
			fr.close();
			Collections.reverse(liste);
			
			//String
			System.out.println(df.format(aktuell.getTime()));
			System.out.println(Benutzereingabe +" Sie haben: ");
			System.out.println();
			
			for(String ausgabe : liste) {
				System.out.println(ausgabe + System.getProperty("line.separator"));
			}
			
			////////////Entfernung von BuchStaben und Sonderzeichen
			
			
			System.out.println("************************************************");
				
		}
		
		if(Auswahl == 2) {
			try {
				System.out.println("Eingabe der Einzuzahlende Summe:");
			int EinZahlSumme;
			EinZahlSumme = sc.nextInt(); ///// Eingabe Der Summe
			
			System.out.println("************************************************");
			System.out.println(df.format(aktuell.getTime()));
			 ////////      Bestätigung ///////////
			
			int KonverzierterStringKontoStandZuInt = Integer.parseInt(zeichnen);
			
			int Adieren;
		
			Adieren = EinZahlSumme + KonverzierterStringKontoStandZuInt;

			 ///// Ausgabe des Neuen KontoStands  //////////
			System.out.println();
			System.out.println("************************************************");
			System.out.println();
			///////////  Ausgabe der Neuen Daten in die Datei Pfad

			String Pfad = "pfad" + Benutzereingabe + ".txt"; /// SpeicherWeg Der Eingegebenen Daten
			
			String IhrAlterKontoStand = KonverzierterStringKontoStandZuInt + "";
//			String IhrEingezahltesGeld =EinZahlSumme+"";
			String IhrNeuerKontoStand =  Adieren + ""; 
			System.out.println(" Daten gespeichert :) ");
			System.out.println();				
			///Ersetzung der Alten Daten
			
			FileWriter fw = new FileWriter(Pfad);
			fw.write(IhrAlterKontoStand  +System.getProperty ("line.separator"));
			
			fw.write(IhrNeuerKontoStand );
			fw.close();
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Das Geld wurde nicht eingezahlt !!!");
				System.out.println("Überprüfene Sie Ihre Eingaben");
			}
			
		}
			////// Auszahl Methode Wenn "3" gedrueckt wird
		
		if(Auswahl == 3) {
			
			System.out.println("Geben sie die abzuhebende Geldsumme an!");
			AuszahlSumme = sc.nextInt();
			System.out.println();
			
			////////////Abhebesumme angeben
			
			int StringToDoubleZeichnen = 0;
			int rest = 0;
			StringToDoubleZeichnen =Integer.parseInt(zeichnen); // konverziere "Zeichnen" zu einem Integer
			
			/////////////Auggabe von RestBetrag
			System.out.println("************************************************");
			
			System.out.println(df.format(aktuell.getTime()));
			System.out.println();
			rest = StringToDoubleZeichnen - AuszahlSumme;
			String IhrAlterKontoStand = zeichnen;
			String AbgehebtesGeld = AuszahlSumme + "";
			String IhrNeuerKontoStand =rest + ""; 
			
			System.out.println("");
			System.out.println("Daten gespeichert :) ");
			System.out.println();
			///////////Daten Ausgabe in die Pfad Datei
			
			//				String Converter =rest+ "";
				String Pfad = "pfad" + Benutzereingabe + ".txt"; /// SpeicherWeg Der Eingegebenen Daten
								
				System.out.println(IhrAlterKontoStand  + " €" + System.getProperty ("line.separator"));
				System.out.println(AbgehebtesGeld  + " €" + System.getProperty ("line.separator"));
				System.out.println(IhrNeuerKontoStand + " €");
				
				FileWriter fw = new FileWriter(Pfad); 
				fw.write(IhrAlterKontoStand + System.getProperty("line.separator" ));
				fw.write(IhrNeuerKontoStand);
				fw.close();
		}
			}catch(Exception e) 
			{
				System.out.println("Das Geld wurde nicht ausgezahlt !!!");
				e.printStackTrace();
				System.out.println("Überprüfene Sie Ihre Eingaben");
			}
		}	
	}	
}
