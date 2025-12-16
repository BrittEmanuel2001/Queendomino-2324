package cui;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import domein.DomeinController;
import domein.Spel;
import dto.DominotegelDTO;
import dto.SpelerDTO;
import utils.Kleur;

public class StartSpel 
{
	private DomeinController dc;
	private ResourceBundle taal;
    Calendar cal = GregorianCalendar.getInstance();
    Scanner input = new Scanner(System.in);
    
	public StartSpel(DomeinController dc, ResourceBundle taal) 
	{
		this.dc = dc; 
		this.taal = taal;
	}

	//TODO 5 GUI maken
	
	/**
     * KINGDOMINO: DEELNEMENDE SPELERS KIEZEN
     * ======================================
     */
	public void start() 
	{		
		dc.startKingdomino(); // Aanmaken nieuw Spel object
		outerLoop: // Nodig om uit de loop te geraken als je geen 4de speler wil toevoegen
			
		// Minimum 3, maximum 4 spelers toevoegen
		for (int aantalDeelnemendeSpelers = 0 ; aantalDeelnemendeSpelers < 4 ; aantalDeelnemendeSpelers++) 
		{
			geefSpelers(dc.geefResterendeSpelers()); 				// Print alle nog niet gekozen spelers
			geefBeschikbareKleuren(dc.geefResterendeKleuren()); 	// Print alle nog niet gekozen kleuren
			int keuzeSpeler = geefKeuzeSpeler();					// Speler selecteren
			int keuzeKleur = geefKeuzeKleur();						// Kleur selecteren
			dc.kiesSpelerEnKleur(keuzeSpeler, keuzeKleur);			// Speler met kleur toevoegen aan deelnemende spelers
			
			// 4de speler kiezen of niet?
			if(dc.geefDeelnemendeSpelers().size() == Spel.MIN_SPELERS)
			{
				boolean invoerOK = false;
				do 
				{
					try 
					{
						// Menu "nog toevoegen?"
						System.out.printf("%n%s%n1.%s%n2.%s%n%s: ", 
								taal.getString("NogToevoegen"), 
								taal.getString("Ja"), 
								taal.getString("Nee"), 
								taal.getString("GeefNummer"));
						String keuzeString = input.nextLine();
						int keuze = Integer.parseInt(keuzeString);
						
						// Indien je geen 4de speler wenst
						if (keuze == 2) break outerLoop;
						
						// Indien ongeldige keuze
						if (keuze > 2 || keuze < 1) throw new IllegalArgumentException();
						invoerOK = true;
						
					} catch (NumberFormatException nfe) {
						System.out.println(taal.getString("geheelGetal"));
					} catch (IllegalArgumentException iae) {
						System.out.println(taal.getString("notValid"));
					} 
				} while (!invoerOK);
			}
			
			// Indien max aantal spelers bereikt
			if(dc.geefDeelnemendeSpelers().size() == Spel.MAX_SPELERS) break;
		}
		
		// Overzicht deelnemende spelers
		System.out.printf("%n%s%n", taal.getString("DeelnemendeSpelers"));
	    for(SpelerDTO s : dc.geefDeelnemendeSpelers())
	    {
	    	System.out.printf("- %s %s%s%n", 
	    			s.gebruikersnaam(), taal.getString("GekozenKleur"), 
	    			taal.getLocale().getLanguage().equals("eng") ? s.kleur().getEngels() : s.kleur().getNederlands());
	    }
	    
	    startNieuwSpel();
	}

	
	
	/**
     * SPELVERLOOP KINGDOMINO
     * ======================
     * 1. het spel wordt aangemaakt met alle nodige onderdelen
     * 2. UC2 : speler kiest dominotegel uit startkolom en plaatst koning
     * 3. UC3 : aanroep "spelen van rondes", bepalen winnaar en tonen eindoverzicht
     */
	public void startNieuwSpel() 
	{
	    dc.maakSpel(); 	// Maakt alle nodige onderdelen van een spel aan
	    dc.startSpel(); // Verhoogt aantal gespeelde spellen bij deelnemende spelers

	    // Aanmaken allereerste startkolom zonder ronde gespeeld
	    List<DominotegelDTO> startkolom = dc.geefStartkolom();
	    // Aantal beschikbare tegels in startkolom
	    int tegelsStartKolom = startkolom.size();
	    // Teller voor aantal gespeelde rondes te bepalen
		int ronde = 0;
		
		// --- UC2 ---
		// Spelen van ronde "0"
		// Enkel startkolom bestaat. Spelers plaatsen hun koning
	    do 
	    {       	
        	boolean invoerOk = false;
        	String spelerAanDeBeurt = dc.geefSpelerAanDeBeurt(0).gebruikersnaam();
        	
	        do 
	        {
				try 
				{
					// Keuze dominotegel vragen aan speler aan de beurt
			    	System.out.printf("%n%s%s%n%s%n", 
			    			taal.getString("SpelerAanDeBeurt"), 
			    			spelerAanDeBeurt,
			    			taal.getString("KeuzeDominotegel"));
			    	
			    	// Overzicht te kiezen tegels in startkolom
					int optie = 1;
					for (DominotegelDTO d : startkolom)
						System.out.printf("%s %d: %s%n", taal.getString("dominotegel"), optie++, d.toString());
					
					// 1. Keuze dominotegel van speler aan de beurt
					// 2. Koning wordt geplaatst op dominotegel
					int keuze = geefKeuze(dc.geefStartkolom());
					dc.kiesDominotegel(startkolom.get(keuze-1), startkolom);
					// Gekozen tegel wordt verwijderd uit beschikbare tegels
					startkolom.remove(keuze - 1);
					tegelsStartKolom--;
					invoerOk = true;
					
				} catch (IndexOutOfBoundsException | NumberFormatException ex) {
					System.out.println(taal.getString("notValid"));
				} catch (IllegalArgumentException e) {
					System.out.println(taal.getString("notValid"));
				} 
			} while (!invoerOk);
	    } while(tegelsStartKolom != 0);
	    
	    // Eerste spelvolgorde van de spelers wordt ingesteld naargelang vorige keuze dominotegels
	    dc.bepaalSpelvolgordeStart();
	    geefOverzicht();

	    
	    // --- UC3 ---
	    // Spelen van ronde 1-11
		do
		{ speelRondes(ronde);
    	} while(!dc.isEindeSpel());
		
		// Spelen laatste ronde (heeft enkel startkolom)
		//gekozenTegel = dc.geefDominotegelGelinktAanKoning(null)
		speelLaatsteRonde(ronde);

		dc.berekenScores();
		
		//scores tonen:
		for(SpelerDTO d : dc.geefDeelnemendeSpelers())
			System.out.printf("%s score: %d%n", d.gebruikersnaam(), d.score());
		
	    // Wanneer spel gedaan is: winnaars geven
		System.out.print(dc.geefWinnaars(taal));
		
	    // Wanneer spel gedaan is: eindoverzicht printen
		System.out.printf("%n%n%s%n=========%n%n", taal.getString("overzicht"));
	    for(SpelerDTO s : dc.geefDeelnemendeSpelers()) 
	    	System.out.print(dc.geefOverzichtSpel(s, taal));
	    System.out.println(taal.getString("goedGespeeld"));
	    dc.isEindeSpel();
	}

	
	
	/**
	 * OVERZICHT VAN EEN RONDE
	 * =======================
	 */
	private void geefOverzicht()
	{
		System.out.printf("%n%n%s%n=========%n%n%s %n%s%n%s %n%s%n%n", 
                taal.getString("overzicht"),taal.getString("startkolom"), dc.printStartkolom(taal).isEmpty() ? taal.getString("leeg") : dc.printStartkolom(taal), 
                		taal.getString("eindkolom"), dc.printEindkolom(taal).isEmpty() ? taal.getString("leeg") : dc.printEindkolom(taal));
		
    	for(SpelerDTO s : dc.geefSpelvolgorde())
    	    System.out.printf("%s%s%n", dc.geefOverzichtRonde(s, taal), dc.geefKoninkrijk(s.kleur(), taal));
	}
	
	
	
	/**
	 * WEERGAVE VAN DE GEWENSTE SPELERS
	 * ================================
	 * @param spelerslijst
	 */
	private void geefSpelers(List<SpelerDTO> spelers) 
	{
		System.out.printf("%n%s%n===================%n", taal.getString("B_Spelers"));
	
		int optie = 1;
		for(SpelerDTO s : spelers) 
		{
			System.out.printf("%d. %s: %s | %s: %d%n",
					optie, taal.getString("gebruikersnaam"),
					s.gebruikersnaam(), taal.getString("leeftijd"),
					cal.get(Calendar.YEAR) - s.geboortejaar());
			optie++;
		}
	}
	
	
	
	/**
	 * WEERGAVE VAN DE BESCHIKBARE KLEUREN
	 * ===================================
	 * @param kleuren
	 */
	private void geefBeschikbareKleuren(List<Kleur> kleuren) 
	{
		System.out.printf("%n%s%n===================%n", taal.getString("B_Kleuren"));
		
		int optie = 1;
		for(Kleur k : kleuren) 
			System.out.printf("%d. %s%n", optie++, 
					taal.getLocale().getLanguage().equals("eng") ?
					k.getEngels() : k.getNederlands());

		System.out.println();
	}
	
	
	
	/**
	 * KEUZE VRAGEN VAN GEWENSTE SPELER
	 * ================================
	 * @return nummer van gekozen speler
	 */
	private int geefKeuzeSpeler() 
	{
		int keuze = 0;
		boolean invoerOK = false;
		
		do 
		{
			try 
			{
				System.out.printf("%s %s %s: ", taal.getString("Kies"), taal.getString("Speler"), taal.getString("GeefNummer"));
				String keuzeString = input.nextLine();
				keuze = Integer.parseInt(keuzeString);

				if (keuze > dc.geefResterendeSpelers().size() || keuze < 1)
					throw new IllegalArgumentException();

				invoerOK = true;
				
			} catch (NumberFormatException nfe) {
				System.out.println(taal.getString("geheelGetal"));
			} catch (IllegalArgumentException iae) {
				System.out.println(taal.getString("notValid"));
			} 
		} while (!invoerOK);
		
		return keuze;
	}
	
	
	
	/**
	 * KEUZE VRAGEN VAN GEWENSTE KLEUR
	 * ===============================
	 * @return nummer van gekozen kleur
	 */
	private int geefKeuzeKleur() 
	{
		int keuze = 0;
		boolean invoerOK = false;
		
		do 
		{
			try 
			{
				System.out.printf("%s %s %s: ", taal.getString("Kies"), taal.getString("Kleur"), taal.getString("GeefNummer"));
				String keuzeString = input.nextLine();
				keuze = Integer.parseInt(keuzeString);

				if (keuze > dc.geefResterendeKleuren().size() || keuze < 1)
					throw new IllegalArgumentException();
				
				invoerOK = true;
				
			} catch (NumberFormatException nfe) {
				System.out.println(taal.getString("geheelGetal"));
			} catch (IndexOutOfBoundsException ioobe) {
				System.out.println(taal.getString("notValid"));
			} catch (IllegalArgumentException iae) {
				System.out.println(taal.getString("notValid"));
			} 
		} while (!invoerOK);
		
		return keuze;
	}
	
	
	
	/**
	 * SPEEL RONDES 1 T.E.M. 11 (UC4)
	 * ==============================
	 * 1. De eindkolom wordt gevuld en de spelvolgorde wordt bepaald
	 * 2. De speler kiest een tegel uit de eindkolom
	 * 3. De speler plaatst zijn vorig gekozen tegel in zijn koninkrijk.
	 *    Hierbij wordt "speelBeurt" (UC5) aangeroepen.
	 * 4. De eindkolom wordt de startkolom voor de volgende ronde
	 * @param ronde
	 */
	private void speelRondes(int ronde) 
	{
		// Nieuwe eindkolom wordt gevuld en spelvolgorde ronde wordt bepaald
		dc.speelRonde();
		
		// Dit overzicht wordt getoond wanneer niet 1ste ronde
		if(dc.geefRonde() != 2) geefOverzicht();
		// Tussentitel indicatie welke ronde er gespeeld wordt
		System.out.printf("%n%s %d: %n==========%n", taal.getString("ronde"), dc.geefRonde()-1);
			
		// Gevulde eindkolom wordt geïmporteerd
	    List<DominotegelDTO> eindkolom = dc.geefEindkolom();
	    
	    
	    // --- UC4 ---
	 	// Logica van het spelen van 1 ronde
	 	// Hierin wordt "speelBeurt" (UC5) aangeroepen
	    do
	    {
	    	try 
	    	{
	    		// Aanspreken speler aan de beurt
    			SpelerDTO spelerAanDeBeurt = dc.geefSpelerAanDeBeurt(ronde);
    			System.out.printf("%n%s%s%n", taal.getString("SpelerAanDeBeurt"), spelerAanDeBeurt.gebruikersnaam());
    			
    	    	System.out.print(dc.geefOverzichtRonde(spelerAanDeBeurt, taal));
    	    	System.out.print(dc.geefKoninkrijk(spelerAanDeBeurt.kleur(), taal));
    			
    			// Overzicht te kiezen tegels in eindkolom
    			System.out.println(taal.getString("KeuzeDominotegelEindkolom"));
    			int optie = 1;
    			for(DominotegelDTO d : eindkolom) 
    				System.out.printf("%s %d: %s%n", taal.getString("dominotegel"), optie++, d.toString());
	                
    			// 1. Keuze dominotegel van speler aan de beurt
    			// 2. Bijhouden gekozen tegel voor later te kunnen plaatsen
    			// 3. Koning wordt geplaatst op dominotegel eindkolom
    			
    			int keuze = geefKeuze(dc.geefEindkolom());
    			DominotegelDTO gekozenTegel = eindkolom.get(keuze-1);
                dc.kiesDominotegel(gekozenTegel, eindkolom);
	        
    			vraagUitbreiden(spelerAanDeBeurt.kleur());
                
    			// --- UC5 ---
                // Speler vragen of de tegel nog geplaatst kan worden en dan controleren via methode
                // Speler wordt gevraagd op welke positie hij de tegel in zijn koninkrijk wil plaatsen
    			plaatsTegel(spelerAanDeBeurt);
    			// Tegel wordt geplaatst in het koninkrijk
    			dc.speelBeurt(gekozenTegel, eindkolom, spelerAanDeBeurt.kleur());
    			
    			// Gekozen tegel wordt verwijderd uit beschikbare tegels
    			eindkolom.remove(keuze-1); 
    			ronde++;
    			
		    } catch (IndexOutOfBoundsException | NumberFormatException ex) {
				System.out.println(taal.getString("notValid"));
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
	    } while(!dc.isEindeRonde());
	    
	    // De eindkolom wordt de nieuwe startkolom voor de volgende ronde
	    dc.resetStartkolom();
	}	

	
	
	private void vraagUitbreiden(Kleur kleur) 
	{
		boolean invoerOk = false;
		int keuze;
		do 
		{
			try 
			{
				System.out.printf("%n%s%n1. %s%n2. %s%n%s",taal.getString("gridUitbreiden"), taal.getString("Ja"), taal.getString("Nee"), taal.getString("MenuGeefKeuze"));
				String keuzeString = input.next();
				keuze = Integer.parseInt(keuzeString);
				if(keuze == 1 || keuze == 2) 
				{
					if(keuze ==1)
						breidGridUit(kleur);
					invoerOk = true;
				}	
				else
					throw new IllegalArgumentException("notValid");
			} catch (NumberFormatException | InputMismatchException ex) {
				System.out.println(taal.getString("notValid"));
			} catch (IllegalArgumentException iae) {
				System.out.println(taal.getString(iae.getMessage()));
			} 
		}while(!invoerOk);
	}

	
	
	private void breidGridUit(Kleur kleur) 
	{
		boolean invoerOk = false;
		int keuze;
		do 
		{
			try 
			{
				System.out.printf("1. %s%n2. %s%n3. %s%n4. %s%n%s ", taal.getString("Boven"), taal.getString("Rechts"), taal.getString("Onder"),taal.getString("Links"), taal.getString("MenuGeefKeuze"));
				String keuzeString = input.next();
				keuze = Integer.parseInt(keuzeString);
				if(keuze < 5 && keuze > 0) 
				{
					invoerOk = true;
					dc.breidKoninkrijkUit(kleur, keuze);
					System.out.printf("%n%s", dc.geefKoninkrijk(kleur, taal));
				}	
				else
					throw new IllegalArgumentException("notValid");
			} catch (IllegalArgumentException iae) {
				System.out.println(taal.getString(iae.getMessage()));
			}
		}while(!invoerOk);
		vraagUitbreiden(kleur);
	}

	
	
	/**
	 * COORDINATEN VRAGEN VOOR PLAATSEN DOMINOTEGEL + DEGELIJK PLAATSEN (UC5)
	 * ======================================================================
	 * 1. De speler krijgt een optie menu: "Kan je nog een tegel plaatsen of niet?"
	 * 2. Indien dit antwoord strookt met de waarheid krijg je een mededeling
	 * 3. KAN JE PLAATSEN? Coördinaten worden gevraagd en de tegel wordt geplaatst
	 * 4. KAN JE NIET PLAATSEN? Speler krijgt een bijpassende melding en plaatst niets
	 * @param spelerAanDeBeurt
	 * @param gekozenTegel
	 */
	private void plaatsTegel(SpelerDTO spelerAanDeBeurt) 
	{ 
	    int row1, col1, row2, col2, kanPlaatsen;
	    boolean invoerOk = false;
	    dc.geefVolgendeTegelUitStartkolom();
	    
	    do 
	    {
	        try 
	        {		
	        	boolean keuzeOk = false;
	        	// Menu oproepen met de vraag of je de tegel nog kan plaatsen of niet
				do 
				{
					System.out.printf("%n%s%n"
							+ "1. %s%n"
							+ "2. %s%n"
							+ "%s", taal.getString("tegelPlaatsen"), taal.getString("Ja"), taal.getString("Nee"), taal.getString("MenuGeefKeuze"));
					String keuzeString = input.next();
					kanPlaatsen = Integer.parseInt(keuzeString);
					
					if(kanPlaatsen == 1 || kanPlaatsen == 2)
						keuzeOk = true;
					else
						throw new IllegalArgumentException("notValid");
				} while (!keuzeOk);
				
				
				// Controleren via het systeem of je degelijk nog iets kan plaatsen of niet
                boolean kanNog = dc.zijnErNogMogelijkeZetten(spelerAanDeBeurt.kleur());
                
                // Exception gooien indien jouw antwoord niet strookt met de werkelijkheid
                if((kanPlaatsen == 2 && kanNog) || (kanPlaatsen == 1 && !kanNog))
                	throw new IllegalArgumentException("beterKijken");
                
                // KAN JE PLAATSEN?
                // Dan wordt gevraagd aan de speler om de coördinaten op te geven
                if(kanNog) 
                {               	
	                //kan nog plaatsen
		        	System.out.println("\n" + taal.getString("plaatsDominotegel"));
		        
		        	// Invoer coördinaten vak 1
		            System.out.print(taal.getString("geefRij") + taal.getString("vak1"));
		            row1 = input.nextInt()-1;
		            System.out.print(taal.getString("geefKolom") + taal.getString("vak1"));
		            col1 = input.nextInt()-1;
		        
		            // Invoer coördinaten vak 2
		            System.out.print(taal.getString("geefRij") + taal.getString("vak2"));
		            row2 = input.nextInt()-1;
		            System.out.print(taal.getString("geefKolom") + taal.getString("vak2"));
		            col2 = input.nextInt()-1;
	
		            // Plaats de dominotegel in koninkrijk
	                dc.plaatsDominoTegel(spelerAanDeBeurt.kleur(), row1, col1, row2, col2);
		            invoerOk = true;
                }
                
                // KAN JE NIET MEER PLAATSEN?
                // Dan krijgt de speler een bijpassende melding en wordt er geen tegel geplaatst
                else
                {
                	System.out.print(taal.getString("skillIssue") + "\n");
                	invoerOk = true;
                }
	        }catch (InputMismatchException ex) {
                System.out.println(taal.getString("geheelGetal") + "\n");
                input.nextLine();
	        }catch (NumberFormatException nfe) {
	            System.out.println(taal.getString("geheelGetal"));
	        }catch (IndexOutOfBoundsException ioobe) {
	        	System.out.println(taal.getString("notValid"));
	        }catch (IllegalArgumentException e) {
	            System.out.println(taal.getString(e.getMessage()));
	        }
	    } while (!invoerOk);

	    // Overzicht nieuwe situatie koninkrijk van speler
	    System.out.printf("%n%s", dc.geefKoninkrijk(spelerAanDeBeurt.kleur(), taal));
	}
	
	
	
	/**
	 * SPEEL DE LAATSTE RONDE (12) (UC4)
	 * =================================
	 * 1. De eindkolom wordt gevuld en de spelvolgorde wordt bepaald
	 * 2. De speler kiest een tegel uit de eindkolom
	 * 3. De speler plaatst zijn vorig gekozen tegel in zijn koninkrijk
	 * 4. De eindkolom wordt de startkolom voor de volgende ronde
	 * @param ronde
	 */
	private void speelLaatsteRonde(int ronde) 
	{				
		// Nieuwe eindkolom wordt gevuld en spelvolgorde ronde wordt bepaald
		dc.speelRonde();
		
		// Tonen overzicht
		geefOverzicht();
		
		// Tussentitel indicatie welke ronde er gespeeld wordt
		System.out.printf("%n%s %d: %n==========%n", taal.getString("ronde"), dc.geefRonde()-1);
		
		// Bijhouden hoeveel spelers hun laatste beurt nog moeten afwerken
		int reedsGespeeld = 0;

		// Alle spelers worden gevraagd hun laatste tegels te plaatsen
		do
		{
			try 
			{
				// Bepalen speler aan de beurt
				SpelerDTO spelerAanDeBeurt = dc.geefSpelerAanDeBeurt(ronde);
				System.out.printf("%n%s%s%n", taal.getString("SpelerAanDeBeurt"), spelerAanDeBeurt.gebruikersnaam());
				
                System.out.print(dc.geefOverzichtRonde(spelerAanDeBeurt, taal));
                System.out.print(dc.geefKoninkrijk(spelerAanDeBeurt.kleur(), taal));
				
				// Speler wordt gevraagd op welke positie hij de tegel in zijn koninkrijk wil plaatsen
				plaatsTegel(spelerAanDeBeurt);
				
				reedsGespeeld++;
				ronde++;
			} catch (IndexOutOfBoundsException | NumberFormatException ex) {
					System.out.println(taal.getString("notValid"));
			} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
			}
		} while(reedsGespeeld < dc.geefDeelnemendeSpelers().size());
	}

	
	
	/**
	 * KEUZE VRAGEN VAN GEWENSTE DOMINOTEGEL UIT EEN KOLOM
	 * ===================================================
	 * @param kolom
	 * @return nummer van gekozen dominotegel
	 */
	private int geefKeuze(List<DominotegelDTO> kolom)
	{
		int keuze = 0;
		try 
		{
			System.out.print(taal.getString("MenuGeefKeuze"));
			String keuzeString = input.next();
			keuze = Integer.parseInt(keuzeString);

			if (keuze > kolom.size() || keuze < 1)
				throw new IllegalArgumentException(taal.getString("notValid"));
				
		} catch (InputMismatchException | IndexOutOfBoundsException ex) {
			System.out.println(taal.getString("notValid"));
		}
		
		return keuze;
	}
}