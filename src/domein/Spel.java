package domein;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import dto.DominotegelDTO;
import dto.SpelerDTO;
import utils.Kleur;

public class Spel 
{  	
	/*
	 * INFO OPBOUW ALLE METHODES IN KLASSE SPEL:
	 * 1. eerst alle soorten attributen
	 * 2. spel constructor
	 * 3. verzameling methodes: ALS SPELER DEELNEMEN
	 * 4. verzameling methodes: OPROEPEN DEELNEMENDE SPELERS
	 * 5. verzameling methodes: SPEL EN NODIGE ELEMENTEN AANMAKEN
	 * 6. verzameling methodes: SPELVOLGORDE BEPALEN EN SPELER AAN DE BEURT
	 * 7. verzameling methodes: SPEEL RONDE
	 * 8. verzameling methodes: SPEEL BEURT
	 * 9. verzameling methodes: TONEN OVERZICHTEN
	 * 10. verzameling methodes: EINDE SPEL FASE
	 */
	
	/*
	 * SPELERS
	 * =======
	 */
	public static final int MAX_SPELERS = 4;
    public static final int MIN_SPELERS = 3;
	
	private List<Speler> spelers;
	private List<Speler> beschikbareSpelers;
    private List<Speler> deelnemendeSpelers;
    private List<Speler> beschikbareDeelnemendeSpelers;
    private List<Speler> spelVolgorde;
    private List<SpelerDTO> beschikbareSpelersDTO;
    private Speler gekozenSpeler;
    private SpelerDTO huidigeSpeler;
    
    /*
	 * KLEUREN
	 * =======
	 */
    private List<Kleur> alleKleuren;
    private List<Kleur> beschikbareKleuren;
    
    /*
	 * DOMINOTEGELS
	 * ============
	 */
    private List<Dominotegel> dominotegels;
    private List<Dominotegel> trekstapel;
    private List<Dominotegel> startkolom;
    private List<Dominotegel> eindkolom;
    
    /*
	 * KONINKRIJK
	 * ==========
	 */
    private List<Koninkrijk> koninkrijken;
    
    /*
	 * DIVERS
	 * ======
	 */
    SecureRandom secureRandom = new SecureRandom();
    private int ronde;
    private int teller;
    
    
    /**
     * UC2
     * ===
     * Deze constructor wordt aangeroepen in de domeincontroller om een nieuw spel te starten. 
     * In deze constructor importeert het de lijst van alle spelers en de mapper vanuit de spelerRepository via de domeincontroller. 
     * Verschillende lijsten worden aangemaakt die later worden gevuld en gebruikt in andere methoden. 
     * Daarnaast worden de trekstapel en de startkolom ook aangemaakt via de vermelde methoden.
     * @param alleSpelers (de spelers die in de databank zitten)
     */
    public Spel(List<Speler> alleSpelers) 
    {
        spelers = alleSpelers;
        koninkrijken = new ArrayList<>();
        
        deelnemendeSpelers = new ArrayList<>();
        beschikbareSpelersDTO = new ArrayList<>();
        beschikbareSpelers = spelers;
        alleKleuren = Kleur.geefBeschikbareKleuren();
        beschikbareKleuren = alleKleuren;
        
        trekstapel = new ArrayList<>();
        startkolom = new ArrayList<>();
        eindkolom = new ArrayList<>();
    }
    
    
    
    
    
    // ========== ALS SPELER DEELNEMEN ==========
    /**
     * UC2
     * ===
     * Deze methode retourneert een lijst met resterende spelers, zodat deze kunnen worden weergegeven in de StartSpel applicatie, 
     * waardoor de gebruiker kan zien tussen welke spelers hij nog kan kiezen.
     * @return lijst van resterende spelers (DTO)
     */
    public List<SpelerDTO> geefResterendeSpelers()
    {
        List<SpelerDTO> resterendeSpelers = new ArrayList<>();
        for (Speler s : beschikbareSpelers)
        {
            SpelerDTO spelerDTO = new SpelerDTO(s.getGebruikersnaam(), s.getGeboortejaar(), s.getAantalGewonnen(), s.getAantalGespeeld(), s.getScore(), s.getKleur());
            resterendeSpelers.add(spelerDTO);
        }
        return resterendeSpelers;
    }
    
    /**
     * UC2
     * ===
     * Deze methode retourneert een lijst met resterende kleuren voor weergave in StartSpel.
     * @return lijst van alle resterende kleuren
     */
    public List<Kleur> geefResterendeKleuren() {return beschikbareKleuren;}
    
    /**
     * UC2
     * ===
     * Deze methode wordt aangeroepen in de StartSpel applicatie en krijgt twee integers mee, die de gewenste keuzes zijn 
     * voor de speler en de kleur waar je graag mee wilt spelen.
     * @param speler
     * @param kleur
     */
    public void kiesSpelerEnKleur(int speler, int kleur)
    {
        deelnemendeSpelers = voegDeelnemendeSpelerToe(speler, deelnemendeSpelers);
        gekozenSpeler.setKleur(beschikbareKleuren.get(kleur-1));
        beschikbareKleuren.remove(kleur-1);
    }
    
    /**
     * UC2
     * ===
     * Deze methode voegt de geselecteerde speler toe aan een lijst met deelnemende spelers
     * en verwijdert tegelijk deze optie uit de lijst beschikbare spelers.
     * @param keuze
     * @param deelnemendeSpelers
     * @return vernieuwde lijst van deelnemende spelers
     */
    private List<Speler> voegDeelnemendeSpelerToe(int keuze, List<Speler> deelnemendeSpelers) 
    {
        deelnemendeSpelers.add(spelers.get(keuze - 1));
        gekozenSpeler = beschikbareSpelers.remove(keuze-1);
        return deelnemendeSpelers;
    }
       
    
 // ========== OPROEPEN DEELNEMENDE SPELERS ==========
    /**
     * UC2
     * ===
     * Deze methode geeft een lijst van reeds deelnemende spelers terug om weer te geven 
     * wie er aan dit nieuwe Spel meedoet.
     * @return lijst van deelnemende spelers (DTO)
     */
    public List<SpelerDTO> getDeelnemendeSpelers() 
    {
        List<SpelerDTO> spelersDTO = new ArrayList<>();
        for (Speler s : deelnemendeSpelers)
        {
            SpelerDTO spelerDTO = new SpelerDTO(s.getGebruikersnaam(), s.getGeboortejaar(), s.getAantalGewonnen(), s.getAantalGespeeld(), s.getScore(), s.getKleur());
            spelersDTO.add(spelerDTO);
        }
        return spelersDTO;
    }
    
    public List<Speler> geefDeelnemendeSpelers()
    {
        return deelnemendeSpelers;
    }
    
    
    
    // ========== SPEL EN NODIGE ELEMENTEN AANMAKEN ==========
    /**
     * UC2
     * ===
     * De maakSpel methode vult de trekstapel en de startkolom voor dit spel aan de hand van het aantal deelnemende spelers.
     * Ook wordt aan het spel doorgegeven hoeveel deelnemende spelers er daadwerkelijk meedoen.
     * @param alleDominotegels
     */
    public void maakSpel(List<Dominotegel> alleDominotegels) 
    {
        dominotegels = alleDominotegels;
        spelVolgorde = new ArrayList<>();
        beschikbareDeelnemendeSpelers = new ArrayList<>();
        beschikbareDeelnemendeSpelers.addAll(deelnemendeSpelers);       
        trekstapel = maakTrekstapel();
        startkolom = maakStartkolom();
        eindkolom = new ArrayList<>();
        for(Speler s : beschikbareDeelnemendeSpelers)
            koninkrijken.add(new Koninkrijk(s.getKleur()));
        ronde = 0;
        teller = 0;
    }
    
    /**
     * UC3
     * ===
     * Hierin wordt het aantal gespeelde spelletjes van iedere deelnemende speler verhoogd met 1
     */
    public void startSpel() 
    {
        for(Speler s : deelnemendeSpelers) 
        {	
        	int waarde = s.getAantalGespeeld() + 1;
            s.setAantalGespeeld(waarde);
        }
    }
    
    /**
     * UC2
     * ===
     * In deze methode wordt de trekstapel die gemaakt werd in de constructor opgevuld met dominotegels uit de dominotegelRepo. 
     * Als er maar 3 spelers meedoen met het spel worden er 12 dominotegels verwijderd van deze lijst.
     * Zo zijn er 48 dominotegels in de trekstapel voor 4 spelers, 36 dominotegels voor 3 spelers.
     * @return trekstapel van huidig spel
     */
    private List<Dominotegel> maakTrekstapel()
    {
        trekstapel = new ArrayList<>(dominotegels);
        if (deelnemendeSpelers.size() == MIN_SPELERS)
            for (int i = 0; i < 12; i++)
                trekstapel.remove(0);
        return trekstapel;
    }
        
    /** 
     * UC2
     * ===
     * maakStartkolom voegt aan de lijst startkolom (aangemaakt in constructor) het benodigde aantal dominotegels toe.
     * Het aantal tegels in de startkolom wordt bepaald aan de hand van het aantal deelnemende spelers.
     * Met andere woorden, de eerste 3 (of 4) tegels van de trekstapel worden van de trekstapel gehaald en toegevoegd aan de startkolom.
     * @return startkolom met 3 of 4 tegels
     */
    private List<Dominotegel> maakStartkolom()
    {
        if (!trekstapel.isEmpty())
            for (int i = 0; i < deelnemendeSpelers.size(); i++) 
               startkolom.add(trekstapel.remove(0));
        
        return startkolom;
    }
    
    /**
     * UC2
     * === 
     * In deze methode wordt een nieuwe eindkolom aangemaakt en gereturned.
     * @return eindkolom
     */
    private List<Dominotegel> maakEindkolom() 
    {
        eindkolom = new ArrayList<>();
        while (!trekstapel.isEmpty() && eindkolom.size() < deelnemendeSpelers.size())
            eindkolom.add(trekstapel.remove(0));
        
        return eindkolom;
    }
    
    /**
     * UC4
     * ===
     * Deze methode sorteert de start/eindkolom
     * @param kolom
     * @return gesorteerde lijst van start of eindkolom
     */
    private List<Dominotegel> sorteerKolom(List<Dominotegel> kolom)
    {
    	Collections.sort(kolom, new Comparator<Dominotegel>() 
   	 	{
   	        @Override
   	        public int compare(Dominotegel tegel1, Dominotegel tegel2) 
   	        {
   	            // Vergelijk de nummers van de tegels en sorteer van groot naar klein
   	            return Integer.compare(tegel1.getNummer(), tegel2.getNummer());
   	        }
   	 	});
    	return kolom;
    }
    
    /**
	 * UC4
	 * ===
	 * Deze methode is aangeroepen bij de klasse Domeincontroler en zorgt ervoor dat de startkolom gereset wordt als isEindeRonde true
	 * retourneert. Dan wordt de startkolom eindkolom.
	 */
	public void resetStartkolom() 
	{
		if(isEindeRonde())
			startkolom = eindkolom;
		        
		// eindkolom = maakEindkolom();
		spelVolgorde.removeAll(spelVolgorde);
	}
    
    /**
     * UC2
     * ===
     * Deze methode zet de trekstapel om naar een lijst van dominotegelDTO's en returnt deze.
     * Deze methode wordt aangeroepen in de domeincontroller.
     * @return Trekstapel (DTO)
     */
    public List<DominotegelDTO> geefTrekstapel() 
    {
    	List<DominotegelDTO> dominotegelsDTO = new ArrayList<>();
        for (Dominotegel d : trekstapel)
        {
            DominotegelDTO dominotegelDTO = new DominotegelDTO(d.getNummer(), d.getVak1(), d.getVak2(), d.getKoning());
            dominotegelsDTO.add(dominotegelDTO);
        }
        
        return dominotegelsDTO;
    }
    
    /**
     * UC2
     * ===
     * Deze methode zet de startkolom om naar een lijst van dominotegelDTO's en returnt deze.
     * Deze methode wordt aangeroepen in de domeincontroller.
     * @return Startkolom (DTO)
     */
    public List<DominotegelDTO> geefStartkolom() 
    {
    	List<DominotegelDTO> startkolomDTO = new ArrayList<>();
        sorteerKolom(startkolom);
        for (Dominotegel d : startkolom)
        {
            DominotegelDTO dominotegelDTO = new DominotegelDTO(d.getNummer(), d.getVak1(), d.getVak2(), d.getKoning());
            startkolomDTO.add(dominotegelDTO);
        }
        
        return startkolomDTO;
    }
    
    /**
     * UC2
     * ===
     * Deze methode zet eerst de eindkolom om naar een lijst met DTO's en returned deze dan.
     * @return eindkolomDTO
     */
    public List<DominotegelDTO> geefEindkolom() 
    {
        List<DominotegelDTO> eindkolomDTO = new ArrayList<>();
        sorteerKolom(eindkolom);
        if (eindkolom != null) 
        {
            for (Dominotegel d : eindkolom) 
            {
                DominotegelDTO dominotegelDTO = new DominotegelDTO(d.getNummer(), d.getVak1(), d.getVak2(), d.getKoning());
                eindkolomDTO.add(dominotegelDTO);
            }
        }
        
        return eindkolomDTO;
    }
    
    
    
    
    
    // ========== SPELVOLGORDE BEPALEN EN SPELER AAN DE BEURT ==========
    /**
     * UC2
     * ===
     * Deze methode bepaalt willekeurig welke speler aan de beurt is.
     * Eens een speler gekozen is wordt deze verwijderd uit de lijst van beschikbareDeelnemendeSpelers.
     * Dit om te vermijden dat een speler opnieuw gekozen kan worden.
     * @param teller
     * @return speler aan de beurt (DTO)
     */
    public SpelerDTO geefSpelerAanDeBeurt(int teller) 
    {       
    	if(ronde == 0)
    	{
    		Speler speler = beschikbareDeelnemendeSpelers.get(secureRandom.nextInt(beschikbareDeelnemendeSpelers.size()));
        	beschikbareDeelnemendeSpelers.remove(speler);
        	
        	huidigeSpeler = new SpelerDTO(speler.getGebruikersnaam(), speler.getGeboortejaar(), speler.getAantalGewonnen(), speler.getAantalGespeeld(),speler.getScore(), speler.getKleur());
        	beschikbareSpelersDTO.add(huidigeSpeler);

            return huidigeSpeler;
    	}
    	else 
    	{
    		Speler speler = spelVolgorde.get(teller);    		
    		huidigeSpeler = new SpelerDTO(speler.getGebruikersnaam(), speler.getGeboortejaar(), speler.getAantalGewonnen(), speler.getAantalGespeeld(), speler.getScore(), speler.getKleur());
    		return huidigeSpeler;	
    	}
    }
    
    /**
	 * UC4
	 * ===
	 * Deze methode wordt aangeroepen in de Domeincontroller en is verantwoordelijk voor het bepalen van de spelvolgorde 
	 * voor de volgende ronde. Eerst wordt de lijst spelvolgorde volledig geleegd. Vervolgens wordt voor de startkolom alles doorlopen: 
	 * eerst wordt de koning geselecteerd, gevolgd door het doorlopen van de deelnemende spelers en ze toevoegen aan de spelvolgorde.
	 */
	public void bepaalSpelvolgordeStart() 
	{
		spelVolgorde.removeAll(spelVolgorde);
        for(Dominotegel d : startkolom) 
        {
        	Kleur kleur = d.getKoning().getKleur();
        	for(Speler s : deelnemendeSpelers) 
        		if(kleur.equals(s.getKleur()))
            		spelVolgorde.add(s);	
        }
        ronde++;
	}
	
	/**
	 * UC4
	 * ===
	 * Deze methode wordt aangeroepen in de domeinController. Deze methode is verantwoordelijk voor het genereren van een nieuwe lijst 
	 * van DTO's. Vervolgens zal het de spelVolgorde doorlopen en de spelers afdrukken.
	 * @return spelVolgordeDTO
	 */
	public List<SpelerDTO> geefSpelvolgorde()
	{
		List<SpelerDTO> spelVolgordeDTO = new ArrayList<>();
	   
	    for (Speler s : spelVolgorde)
	    {
	    	SpelerDTO spelerDTO = new SpelerDTO(s.getGebruikersnaam(), s.getGeboortejaar(), s.getAantalGewonnen(), s.getAantalGespeeld(), s.getScore(), s.getKleur());
	        spelVolgordeDTO.add(spelerDTO);
	    }
	    
	    return spelVolgordeDTO;
	}
    
	
	
	
    
    // ========== SPEEL RONDE ==========
    /**
	 * UC4
	 * ===
	 * Hierin word de eindkolom aangemaakt, het aantal ronders verhoogd en op het einde de wordt de eindkolom de nieuwe startkolom.
	 */
	public void speelRonde() 
    {        
		// Teller voor overlopen tegels startkolom op 0 zetten.
		teller = 0;
        
        // 1. Stelt eindkolom in
        if(ronde > 0)
            eindkolom = maakEindkolom();
            
        // 2. lijst bepalen/vullen volgorde spelers deze ronde 
        bepaalSpelvolgordeStart();
    }
	
	/**
	 * UC4
	 * ===
	 * Deze methode returnt hij de ronde.
	 * @return
	 */
	public int getRonde() {return ronde;}

	/**
	 * UC4
	 * ===
	 * In deze methode wordt het einde van een ronde bepaald, 
	 * zolang er nog tegels uit de eindkolom zijn die geen koning hebben is het einde nog niet bereikt.
	 */
	public boolean isEindeRonde() 
	{
		for(Dominotegel d : this.eindkolom)
			if(d.getKoning() == null)
				return false;
		return true;
	}
    
	
	
	
	
	// ========== SPEEL BEURT ==========
	/**
	 * UC5
	 * ===
	 * In iedere ronde speelt iedere speler zijn beurt.
	 * @param dominotegelDTO
	 * @param kolom
	 * @param kleur
	 */
	public void speelBeurt(DominotegelDTO dominotegelDTO, List<DominotegelDTO> kolom, Kleur kleur) 
	{
		Dominotegel tegelInBezitSpeler = geefDominotegelGelinktAanKoning(kleur);
		verplaatsKoning(dominotegelDTO, kolom, tegelInBezitSpeler);
	}
	
	/**
     * UC2
     * ===
     * Deze methode wordt aangeroepen in de domeincontroller en ontvangt de keuze van de speler (gemaakt in StartSpel). 
     * Vervolgens wordt gekeken welke dominotegel overeenkomt met het nummer van de gekozen dominotegel. 
     * Aan deze dominotegel wordt dan de koning van de huidige speler gekoppeld met behulp van de methode setKoning 
     * vanuit de klasse Dominotegel.
     * @param dominotegelDTO
     * @param kolom
     */
    public void kiesDominotegel(DominotegelDTO dominotegelDTO, List<DominotegelDTO> kolom) 
    {  
        for(DominotegelDTO d : kolom)
        {
        	if(d.nummer() == dominotegelDTO.nummer())
        	{
        		for(Dominotegel tegel : dominotegels)
        			if(tegel.getNummer() == d.nummer())
        			{
        				tegel.setKoning(new Koning(huidigeSpeler.kleur()));
        				break;
        			}
        		break;
        	}
        }
    }
    
    /**
     * UC2
     * ===
     * Deze methode wordt aangeroepen in de domeincontroller. Voor iedere dominotegel uit de trekstapel 
     * controleert hij of de kleur van de koning overeenkomt met de kleur die hij heeft ontvangen van de deelnemende spelers. 
     * Als dit het geval is, retourneert hij alle informatie van de desbetreffende tegel.
     * @param kleur
     * @return tekstuele informatie van de pas gelinkte dominotegel
     */
    public Dominotegel geefDominotegelGelinktAanKoning(Kleur kleur) 
    {
    	for(Dominotegel dominotegel : dominotegels) 
    	{
            Koning koning = dominotegel.getKoning();
            if(koning != null && koning.getKleur() == kleur) 
            	return dominotegel;
        }
    	
    	return null;
    }
	
	/**
	 * UC5
	 * ===
	 * Deze methode verplaatst de koning van de speler. Dit wordt gedaan met behulp van een for-lus. 
	 * Binnen de lus wordt de kolom doorlopen, en als de kolom gelijk is aan de dominotegelDTO, 
	 * wordt er nog een for-lus doorlopen om de dominotegels te doorlopen en ervoor te zorgen dat de koning wordt verplaatst.
	 * @param dominotegelDTO
	 * @param kolom
	 * @param tegelInBezitSpeler
	 */
	private void verplaatsKoning(DominotegelDTO dominotegelDTO, List<DominotegelDTO> kolom, Dominotegel tegelInBezitSpeler) 
	{
		// Koning wordt van geselecteerde tegel uit startkolom gehaald
		tegelInBezitSpeler.setKoning(null);
		
		// Koning wordt op nieuwe tegel uit eindkolom gezet
		// Het attribuut koning van de gekozen Dominotegel (DominotegelDTO) uit de eindkolom kreeg een nieuwe waarde.
		kiesDominotegel(dominotegelDTO, kolom);
	}
	
	/**
	 * UC5
	 * ===
	 * In deze methode wordt de gekozen dominotegel in de startkolom in het koninkrijk van de speler geplaatst,
	 * a.d.h.v. de kolom en rij. Of dit toegelaten is volgens de regels wordt ook hier gecontroleerd.
	 * @param kleur
	 * @param row1
	 * @param col1
	 * @param row2
	 * @param col2
	 */
	public void plaatsDominotegel(Kleur kleur, int row1, int col1, int row2, int col2) 
	{
		// Controle of bij de 1ste ronde de tegel langs 1 vak naast het kasteel wordt geplaatst
        if (getRonde() == 2 && !(isTegelAangrenzendAanKasteel(kleur, row1, col1) || isTegelAangrenzendAanKasteel(kleur, row2, col2))) {
        	throw new IllegalArgumentException("eersteTegel");
        }
                 
        // Voor controle neem je het betreffende koninkrijk vast
        Koninkrijk kingdom = null;
        for (Koninkrijk k : koninkrijken)
			if (k.getKleur().equals(kleur))
			{
				kingdom = k;
				break;
			}
        
		Vak[][] array = kingdom.getArrayKoninkrijk();
		
		// Voor controle hou je ook de te plaatsen tegel bij
		// Ook hou je de landschapstype van elk vak bij
		List<DominotegelDTO> tePlaatsenTegels = geefStartkolom();
		DominotegelDTO tegel = tePlaatsenTegels.get(teller-1);
		String landschapstype1 = tegel.vak1().getLandschapstype();
		String landschapstype2 = tegel.vak2().getLandschapstype();
			
		
		// --- EFFECTIEVE CONTROLE ---
        // Boolean bijhouden om te weten of tegel geplaatst kan worden
		boolean kanGeplaatstWorden = true;
		boolean naastElkaar = false;
		
		// Controle 1: Worden de vakjes naast elkaar geplaatst?
		if(row1 == row2 && (Math.abs(col1 - col2) == 1 || Math.abs(col2 - col1) == 1)) {
			naastElkaar = true;
			
		}
		else if (col1 == col2 && (Math.abs(row1 - row2) == 1 || Math.abs(row2 - row1) == 1)) {
			naastElkaar = true;
		}
		
		if(!naastElkaar) {
			kanGeplaatstWorden = false;
			throw new IllegalArgumentException("nietNaastElkaar");
		}
			
		// Controle 1: Is het gewenste vak leeg?
		if (!array[row1][col1].getLandschapstype().isBlank()
			|| !array[row2][col2].getLandschapstype().isBlank()
			|| !array[row1][col1].getLandschapstype().isEmpty()
			|| !array[row2][col2].getLandschapstype().isEmpty()) 
		{
			kanGeplaatstWorden = false;
			throw new IllegalArgumentException("alTegelOpPlaats");
		}

		// Controle 2: Zijn de 2 gekozen coördinaten verschillend van elkaar?
		if (row1 == row2 && col1 == col2) 
		{
			kanGeplaatstWorden = false;
			throw new IllegalArgumentException("zelfdeVak");
		}

		// Controle 3: Liggen de gekozen vakken niet diagonaal?
		if (row1 != row2 && col1 != col2) 
		{
			kanGeplaatstWorden = false;
			throw new IllegalArgumentException("nietDiagonaal");
		}

		// Controle 4: Grenst 1 v.d. 2 vakken aan eenzelfde landschapstype?
		if (!controleerVak(row1, col1, array, landschapstype1, kleur)) 
		{
			if (!controleerVak(row2, col2, array, landschapstype2, kleur)) 
			{
				kanGeplaatstWorden = false;
				throw new IllegalArgumentException("aangrenzendeTypes"); // checken of grenst aan zelfde landschapstype 
			}
		}
		
		// Kreeg elke controle een "Ja" als antwoord?
		// Dan kan de tegel geplaatst worden
		if (kanGeplaatstWorden) 
		{
			Vak vak1 = tegel.vak1();
			Vak vak2 = tegel.vak2();
			kingdom.voegToeAanKoninkrijk(row1, col1, row2, col2, vak1, vak2);
		}
	}
        
	/**
	 * UC5
	 * ===
	 * Deze hulpmethode controleert of een van de aangrenzende vakken hetzelfde landschapstype heeft.
	 * @param row
	 * @param col
	 * @param array
	 * @param type
	 * @param kleur
	 */
	private boolean controleerVak(int row, int col, Vak[][] array, String type, Kleur kleur)
    {
        if (isTegelAangrenzendAanKasteel(kleur, row, col))
            return true; // Als het vak grenst aan het kasteel, retourneer true
        
        else 
        {
            // Controleer of een van de aangrenzende vakjes hetzelfde landschapstype heeft
            if (row - 1 >= 0 && array[row - 1][col] != null && array[row - 1][col].getLandschapstype().equals(type)) return true;
            if (row + 1 < array.length && array[row + 1][col] != null && array[row + 1][col].getLandschapstype().equals(type)) return true;
            if (col - 1 >= 0 && array[row][col - 1] != null && array[row][col - 1].getLandschapstype().equals(type)) return true;
            if (col + 1 < array[row].length && array[row][col + 1] != null && array[row][col + 1].getLandschapstype().equals(type)) return true;
            return false;
        }
    }
	
	/**
	 * UC5
	 * ===
	 * Deze hulpmethode controleert of 1 van de 2 vakken aan het kasteel grenst.
	 * @param kleur
	 * @param row
	 * @param col
	 */
	private boolean isTegelAangrenzendAanKasteel(Kleur kleur, int row, int col) 
	{
		// Positie van het kasteel
	    int starttegelRij = 2;
	    int starttegelKol = 2;
	    
	    for (Koninkrijk k : koninkrijken) 
	    {
	    	if(k.getKleur().equals(kleur)) 
	    	{
	    		starttegelRij = k.getPositieStarttegel()[0];
	    		starttegelKol = k.getPositieStarttegel()[1];
	    		break;
	    	}
	    }
	    
	    // Controleer of de tegel aangrenzend is aan het kasteel
	    if((Math.abs(row - starttegelRij) == 1 && col == starttegelKol) || (row == starttegelRij && Math.abs(col - starttegelKol) == 1))
	    	return true;
	    return false;
	}
	
	/*
	 * UC5
	 * ===
	 * Controleert of de te plaatsen tegel kan worden gezet in het koninkrijk volgens de spelregels.
	 * 1. Koninkrijk wordt vastgenomen.
	 * 2. De te plaatsen tegel wordt vastgenomen.
	 * 3. Controle of 2 vakken overblijven en of ze naast elkaar liggen.
	 * 4. Controle of de 2 vakken geplaatst kunnen worden volgens bepaalde regels.
	 */
	
	/**
	 * UC5
	 * ===
	 * In deze methode controleren we of er nog mogelijke plekken zijn in het koninkrijk om een dominotegel te plaatsen. 
	 * We controleren of het landschapstype van het vakje ERBOVEN gelijk is aan een van de vakjes van de tegel, 
	 * en of het landschapstype van het vakje ERONDER, LINKS en RECHTS gelijk is aan een van de vakjes van de tegel.
	 * @param kleur
	 * @return
	 */
	public boolean zijnErNogMogelijkeZetten(Kleur kleur) 
    {	
		// Voor controle neem je het betreffende koninkrijk vast
        Koninkrijk kingdom = null;
        for (Koninkrijk k : koninkrijken)
            if (k.getKleur().equals(kleur))
            {
                kingdom = k;
                break;
            }
        Vak[][] array = kingdom.getArrayKoninkrijk();
        
        // Voor controle hou je ook de te plaatsen tegel bij
     	// Ook hou je de landschapstype van elk vak bij
     	List<DominotegelDTO> tePlaatsenTegels = geefStartkolom();
        DominotegelDTO tegel = tePlaatsenTegels.get(teller-1);
        String type1 = tegel.vak1().getLandschapstype();
        String type2 = tegel.vak2().getLandschapstype();
        
        // Zoek naar een leeg vak en controleer of 1 van de vakken daarrond ook empty is.
        // Ook controle of deze vakken ook voldoen aan de nodige regels.
        for (int row = 0; row < array.length; row++) 
        {
            for (int col = 0; col < array[row].length; col++) 
            {
                if (array[row][col].getLandschapstype().isBlank() || array[row][col].getLandschapstype().isEmpty()) 
                {
                	if ((row - 1 >= 0 && (array[row-1][col].getLandschapstype().isBlank() || array[row-1][col].getLandschapstype().isEmpty())) && controleerLandschapstype(row, col, array, kleur, type1, type2))
                		return true;
                	if ((row + 1 < array.length && (array[row+1][col].getLandschapstype().isBlank() || array[row+1][col].getLandschapstype().isEmpty())) && controleerLandschapstype(row, col, array, kleur, type1, type2))
                		return true;
                	if ((col - 1 >= 0 && (array[row][col-1].getLandschapstype().isBlank() || array[row][col-1].getLandschapstype().isEmpty())) && controleerLandschapstype(row, col, array, kleur, type1, type2))
                		return true;
                	if ((col + 1 < array[row].length && (array[row][col+1].getLandschapstype().isBlank() || array[row][col+1].getLandschapstype().isEmpty())) && controleerLandschapstype(row, col, array, kleur, type1, type2))
                		return true;
                }
            }
        }
        return false;
    }
	
	/**
	 * UC5
	 * ===
	 * In deze methode controleert hij of het landschapstype van een van de aanliggende vakjes (LINKS, RECHTS, BOVEN of ONDER) 
	 * overeenkomt met het landschapstype van het vakje dat de speler wil plaatsen.
	 * @param row
	 * @param col
	 * @param array
	 * @param kleur
	 * @param type1
	 * @param type2
	 * @return true als er een vakje hetzelfde landschapstype heeft
	 */
	private boolean controleerLandschapstype(int row, int col, Vak[][] array, Kleur kleur, String type1, String type2)
	{
		//Als het vak grenst aan het kasteel, retourneer true
        if (isTegelAangrenzendAanKasteel(kleur, row, col)) return true;
        
        // Als landschapstype v. vakje ERBOVEN gelijk is aan 1 v.d. vakjes van de tegel
        if (row - 1 >= 0 && (array[row - 1][col].getLandschapstype().equals(type1) || array[row - 1][col].getLandschapstype().equals(type2))) return true;
        // Als landschapstype v. vakje ERONDER gelijk is aan 1 v.d. vakjes van de tegel
        if (row + 1 < array.length && (array[row + 1][col].getLandschapstype().equals(type1) || array[row + 1][col].getLandschapstype().equals(type2))) return true;
        // Als landschapstype v. vakje LINKS gelijk is aan 1 v.d. vakjes van de tegel
        if (col - 1 >= 0 && (array[row][col - 1].getLandschapstype().equals(type1) || array[row][col - 1].getLandschapstype().equals(type2))) return true;
        // Als landschapstype v. vakje RECHTS gelijk is aan 1 v.d. vakjes van de tegel
        if (col + 1 < array[row].length && (array[row][col + 1].getLandschapstype().equals(type1) || array[row][col + 1].getLandschapstype().equals(type2))) return true;
        
        return false;
	}
	
	 /**
	 * UC5
     * ===
     * In deze methode kiezen we welke richting we willen uitbreiden (links, rechts, boven of onder). 
     * Vervolgens verwijst deze methode door naar de specifieke methode die voor die richting zal werken.
	 * @param kleur
	 * @param uitbreidKant
	 * @return
	 */
    public boolean breidKoninkrijkUit(Kleur kleur, int uitbreidKant)
    {
        // Voor uitbreiden eerst weten over welk koninkrijk het gaat
    	boolean laatsteKeer = false;
        Koninkrijk kingdom = null;
        for (Koninkrijk k : koninkrijken)
            if (k.getKleur().equals(kleur))
            {
                kingdom = k;
                break;
            }
        
        switch(uitbreidKant)
        {
            case 1 -> laatsteKeer = breidBovenUit(kingdom);
            case 2 -> laatsteKeer = breidRechtsUit(kingdom);
            case 3 -> laatsteKeer = breidOnderUit(kingdom);
            case 4 -> laatsteKeer = breidLinksUit(kingdom);
        }
        
        return laatsteKeer;
    }
    
    /**
     * UC5
     * ===
     * Deze methode controleert of het koninkrijk nog kan uitbreiden naar boven. 
     * Hiervoor bekijkt de methode of het koninkrijk een lege rij heeft helemaal onderaan, zodat deze naar boven kan worden verplaatst.
     * @param kleur
     * @return true als je kan uitbreiden
     */
    public boolean kanUitbreidenBoven(Kleur kleur) 
    {
    	Koninkrijk kingdom = null;
		for (Koninkrijk k : koninkrijken)
            if (k.getKleur().equals(kleur))
            {
                kingdom = k;
                break;
            }
		
		Vak[][] array = kingdom.getArrayKoninkrijk();
		for (int i = 0; i < array.length; i++)
            if (!array[array.length-1][i].getLandschapstype().isBlank() || !array[array.length-1][i].getLandschapstype().isEmpty())
            	 return false;
    	return true;
    }
    
    /**
     * UC5
     * ===
     * Deze methode controleert of het koninkrijk nog kan uitbreiden naar boven. 
     * Hiervoor bekijkt de methode of het koninkrijk een lege rij heeft helemaal onderaan, zodat deze naar boven kan worden verplaatst.
     * @param kleur
     * @return true als je kan uitbreiden
     */
    public boolean kanUitbreidenRechts(Kleur kleur) 
    {
    	Koninkrijk kingdom = null;
		for (Koninkrijk k : koninkrijken)
            if (k.getKleur().equals(kleur))
            {
                kingdom = k;
                break;
            }
		
		Vak[][] array = kingdom.getArrayKoninkrijk();
		for (int i = 0; i < array.length; i++)
	        if (!array[i][0].getLandschapstype().isBlank() || !array[i][0].getLandschapstype().isEmpty())
	        	return false;
    	return true;
    }
 
    /**
     * UC5
     * ===
     * Deze methode controleert of het koninkrijk nog kan uitbreiden naar boven. 
     * Hiervoor bekijkt de methode of het koninkrijk een lege rij heeft helemaal onderaan, zodat deze naar boven kan worden verplaatst.
     * @param kleur
     * @return true als je kan uitbreiden
     */
    public boolean kanUitbreidenOnder(Kleur kleur) 
    {
    	Koninkrijk kingdom = null;
		for (Koninkrijk k : koninkrijken)
            if (k.getKleur().equals(kleur))
            {
                kingdom = k;
                break;
            }
		
		Vak[][] array = kingdom.getArrayKoninkrijk();
		for (int i = 0; i < array.length; i++)
            if (!array[0][i].getLandschapstype().isBlank() || !array[0][i].getLandschapstype().isEmpty())
            	 return false;
    	return true;
    }
 
    /**
     * UC5
     * ===
     * Deze methode controleert of het koninkrijk nog kan uitbreiden naar boven. 
     * Hiervoor bekijkt de methode of het koninkrijk een lege rij heeft helemaal onderaan, zodat deze naar boven kan worden verplaatst.
     * @param kleur
     * @return true als je kan uitbreiden
     */
    public boolean kanUitbreidenLinks(Kleur kleur) 
    {
    	Koninkrijk kingdom = null;
		for (Koninkrijk k : koninkrijken)
            if (k.getKleur().equals(kleur))
            {
                kingdom = k;
                break;
            }
    	
    	Vak[][] array = kingdom.getArrayKoninkrijk();
    	for (int i = 0; i < array.length; i++)
            if (!array[i][array.length -1].getLandschapstype().isBlank() || !array[i][array.length -1].getLandschapstype().isEmpty()) 
            	return false;
    	return true;
    }
    
	/**
	 * UC5
	 * ===
	 * Deze methode wordt aangeroepen in de methode koninkrijkUitbreiden. 
	 * De methode voegt een kolom toe helemaal aan de linkerkant. Echter, voordat we dit doen, controleren we eerst of de rechtse kolom 
	 * volledig leeg is, wat betekent dat er geen dominotegels of vakken in de kolom zijn. Als dit het geval is, 
	 * verwijderen we de rechtse kolom en voegen we de nieuwe kolom toe aan de linkerkant.
	 * @param kingdom
	 * @param taal 
	 */
	private boolean breidLinksUit(Koninkrijk kingdom) 
	{
		boolean mogelijk = true;
		boolean laatsteKeer = false;
        Vak[][] array = kingdom.getArrayKoninkrijk();
        
        // controle of rechtse kolom leeg is
        for (int i = 0; i < array.length; i++)
        {
            if (!array[i][array.length -1].getLandschapstype().isBlank() || !array[i][array.length -1].getLandschapstype().isEmpty()) {
            	 mogelijk = false;
            	 throw new IllegalArgumentException("nietUitbreiden");
            } 
        }
        
        // uitbreiden indien mogelijk
        if (mogelijk)
        {
            //Koninkrijk met extra kolom
            Vak[][] newKoninkrijk = new Vak[5][5];
            
            for (int rij = 0; rij < array.length; rij++)
                for (int kol = 0; kol < array.length -1 ; kol++)
                    newKoninkrijk[rij][kol + 1] = array[rij][kol];
            
            // Voeg een nieuwe kolom toe aan de linkerkant van het koninkrijk
            for (int rij = 0; rij < array.length; rij++)
                newKoninkrijk[rij][0] = new Vak(); // Nieuwe kolom met lege vakken
            
            kingdom.breidKoninkrijkUit(newKoninkrijk);
            
            array = kingdom.getArrayKoninkrijk();
            for (int i = 0; i < array.length; i++)
            {
                if (!array[i][array.length -1].getLandschapstype().isBlank() || !array[i][array.length -1].getLandschapstype().isEmpty()) {
                	 laatsteKeer = true;
                } 
            }
        }
        
        return laatsteKeer;
	}

	
	/**
     * UC5
     * ===
     * Deze methode wordt aangeroepen in de methode koninkrijkUitbreiden. Deze methode gaat ervoor zorgen dat er boven een 
	 * rij wordt toegevoegd MAAR we controleren eerst of de onderste rij helemaal leeg is (dus geen dominotegels/vakken in de rij).
	 * Als dit het geval is verwijderen we de onderste rij en zetten we de rij aan de bovenkant erbij.
     * @param kingdom
	 * @param taal 
     */
    private boolean breidBovenUit(Koninkrijk kingdom) 
    {
        boolean mogelijk = true;
        boolean laatsteKeer = false;
        Vak[][] array = kingdom.getArrayKoninkrijk();
        
        // controle of onderste rij leeg is
        for (int i = 0; i < array.length; i++)
        {
            if (!array[array.length-1][i].getLandschapstype().isBlank() || !array[array.length-1][i].getLandschapstype().isEmpty()) {
            	 mogelijk = false;
            	 throw new IllegalArgumentException("nietUitbreiden");
            } 
        }
        
        // uitbreiden indien mogelijk
        if (mogelijk)
        {
            //Koninkrijk met extra rij
            Vak[][] newKoninkrijk = new Vak[5][5];
            
            for (int rij = 0; rij < array.length - 1; rij++) 
            {
                for (int kol = 0; kol < array.length; kol++) 
                {
                    newKoninkrijk[rij + 1][kol] = array[rij][kol];
                }
            }
            
            // Voeg een nieuwe rij toe aan de bovenkant van het koninkrijk
            for (int kol = 0; kol < array.length; kol++)
                newKoninkrijk[0][kol] = new Vak(); // Nieuwe rij met lege vakken
            
            kingdom.breidKoninkrijkUit(newKoninkrijk);
            
            array = kingdom.getArrayKoninkrijk();
            for (int i = 0; i < array.length; i++)
            {
                if (!array[array.length-1][i].getLandschapstype().isBlank() || !array[array.length-1][i].getLandschapstype().isEmpty()) {
                	laatsteKeer = true;
                } 
            }
        }
        
        return laatsteKeer;
    }

	/**
	 * UC5
	 * ===
	 * Deze methode wordt aangeroepen in de methode koninkrijkUitbreiden. Deze methode gaat ervoor zorgen dat er helemaal rechts een 
	 * kolom wordt toegevoegd MAAR we controleren eerst of de linkse kolom helemaal leeg is (dus geen dominotegels/vakken in de kolom).
	 * Als dit het geval is verwijderen we de rechtse kolom en zetten we de kolom de linkse kant erbij.
	 * @param kingdom 
	 * @param taal 
	 */
	private boolean breidRechtsUit(Koninkrijk kingdom) 
	{
		boolean mogelijk = true;
		boolean laatsteKeer = false;
        Vak[][] array = kingdom.getArrayKoninkrijk();
        
        // controle of linkse kolom leeg is
        for (int i = 0; i < array.length; i++)
        {
            if (!array[i][0].getLandschapstype().isBlank() || !array[i][0].getLandschapstype().isEmpty()) {
            	 mogelijk = false;
            	 throw new IllegalArgumentException("nietUitbreiden");
            } 
        }
        
        // uitbreiden indien mogelijk
        if (mogelijk)
        {
            //Koninkrijk met extra kolom
            Vak[][] newKoninkrijk = new Vak[5][5];
            
            for (int rij = 0; rij < array.length; rij++) 
            {
                for (int kol = 1; kol < array.length ; kol++) 
                {
                    newKoninkrijk[rij][kol - 1] = array[rij][kol];
                }
            }
            
            // Voeg een nieuwe kolom toe aan de rechterkant van het koninkrijk
            for (int rij = 0; rij < array.length; rij++)
                newKoninkrijk[rij][array.length-1] = new Vak(); // Nieuwe kolom met lege vakken
            
            kingdom.breidKoninkrijkUit(newKoninkrijk);
            
            array = kingdom.getArrayKoninkrijk();
            for (int i = 0; i < array.length; i++)
            {
                if (!array[i][0].getLandschapstype().isBlank() || !array[i][0].getLandschapstype().isEmpty()) {
                	laatsteKeer = true;
                } 
            }
        }
        
        return laatsteKeer;
	}

	/**
	 * UC5
	 * ===
	 * Deze methode wordt aangeroepen in de methode koninkrijkUitbreiden. Deze methode gaat ervoor zorgen dat er onderaan een 
	 * rij wordt toegevoegd MAAR we controleren eerst of de bovenste rij helemaal leeg is (dus geen dominotegels/vakken in de rij).
	 * Als dit het geval is verwijderen we de bovenste rij en zetten we de rij aan de onderkant erbij.
	 * @param kingdom
	 * @param taal 
	 */
	private boolean breidOnderUit(Koninkrijk kingdom) 
	{
		boolean mogelijk = true;
		boolean laatsteKeer = false;
        Vak[][] array = kingdom.getArrayKoninkrijk();
        
        // controle of bovenste rij leeg is
        for (int i = 0; i < array.length; i++)
        {
            if (!array[0][i].getLandschapstype().isBlank() || !array[0][i].getLandschapstype().isEmpty()) {
            	 mogelijk = false;
            	 throw new IllegalArgumentException("nietUitbreiden");
            } 
        }
        
        // uitbreiden indien mogelijk
        if (mogelijk)
        {
            //Koninkrijk met extra rij
            Vak[][] newKoninkrijk = new Vak[5][5];
            
            for (int rij = 1; rij < array.length; rij++) 
            {
                for (int kol = 0; kol < array.length; kol++) 
                {
                    newKoninkrijk[rij - 1][kol] = array[rij][kol];
                }
            }
            
            // Voeg een nieuwe rij toe aan de onderkant van het koninkrijk
            for (int kol = 0; kol < array.length; kol++)
                newKoninkrijk[array.length-1][kol] = new Vak(); // Nieuwe rij met lege vakken
            
            kingdom.breidKoninkrijkUit(newKoninkrijk);
            
            array = kingdom.getArrayKoninkrijk();
            for (int i = 0; i < array.length; i++)
            {
                if (!array[0][i].getLandschapstype().isBlank() || !array[0][i].getLandschapstype().isEmpty()) {
                	 laatsteKeer = true;
                } 
            }
        }
        
        return laatsteKeer;
	}
	
	
	      
    // ========== TONEN OVERZICHTEN ==========
    /**
     * UC2
     * ===
     * Na elke ronde wordt een overzicht getoond met alle informatie van de spelers, de trekstapel, en de eind- en startkolom.     * @param speler
     * @param taal
     * @param speler
     * @return geeft een String weer dat het overzicht van de ronde toont
     */
	public String geefOverzichtRonde(SpelerDTO speler, ResourceBundle taal) 
	{
		String overzicht = String.format(taal.getString("gekozenTegel") +"%s%n%n", 
                geefDominotegelGelinktAanKoning(speler.kleur()).toString(taal, speler.kleur()));
		
		return overzicht;
	}
	
	/**
	 * UC3
	 * ===
	 * Na ieder spel wordt een overzicht getoond van de deelnemende spelers en hun aantal gewonnen en gespeelde spelletjes
	 * @param speler
     * @param taal
     * @return geeft een String weer dat het overzicht van het spel toont
	 */
	public String geefOverzichtSpel(SpelerDTO speler, ResourceBundle taal) 
	{
		String overzicht = String.format(taal.getString("Speler") + ": %s(%s)%n" + taal.getString("GespeeldeSpelletjes") + "%d%n" 
				+ taal.getString("GewonnenSpelletjes") + "%d%n%n", speler.gebruikersnaam(), 
				taal.getLocale().getLanguage().equals("eng") ? speler.kleur().getEngels() : speler.kleur().getNederlands(),
				speler.aantalGespeeld(), speler.aantalGewonnen());
		return overzicht;
	}
	
	
	/**
	 * UC5
	 * ===
	 * In deze methode wordt de toString-methode van de klasse Koninkrijk aangeroepen om de koninkrijken af te drukken met symbolen, 
	 * zodat ze overzichtelijk worden weergegeven.
	 * @param kleur
	 * @param taal 
	 * @return geeft het koninkrijk weer met die bepaalde kleur en indien de kleur niet gevonden is returnen we 'null'
	 */
	public String geefKoninkrijk(Kleur kleur, ResourceBundle taal)
	{
		for(Koninkrijk k : koninkrijken)
		{
			if(k.getKleur().equals(kleur))
				return k.toString(taal, kleur);
		}
		return null;
	}
	
	/**
	 * UC5
	 * ===
	 * Deze methode zorgt ervoor dat we in de GUI de koninkrijken kunnen afdrukken met de afbeeldingen.
	 * @param kleur
	 * @return
	 */
	public Vak[][] geefKoninkrijkHuidigeSpeler(Kleur kleur)
    {
        for(Koninkrijk k : koninkrijken)
        {
            if(k.getKleur().equals(kleur))
                return k.getArrayKoninkrijk();
        }
        return null;
    }
	
	/**
	 * UC4
	 * ===
	 * In deze methode wordt de toString-methode opgeroepen om de startkolom af te drukken. 
	 * Deze methode loopt de startkolom door en drukt de informatie af van de dominotegels die zich in de startkolom bevinden.
	 * @param taal
	 * @return een String van de startkolom
	 */
	public String printStartkolom(ResourceBundle taal) 
	{
		String string = "";
		sorteerKolom(startkolom);
		for(Dominotegel d : startkolom)
			string += String.format(d.toString(taal, d.getKoning().getKleur()) + "%n");
		
		return string;
	}
	
	/**
	 * UC4
	 * ===
	 * In deze methode wordt de toString-methode aangeroepen om de eindkolom af te drukken. 
	 * Deze methode doorloopt de eindkolom en drukt de informatie af van de dominotegels die zich in de eindkolom bevinden.
	 * @param taal
	 * @return een String van de eindkolom
	 */
	public String printEindkolom(ResourceBundle taal) 
	{
		String string = "";
		sorteerKolom(eindkolom);
		for(Dominotegel d : eindkolom)
			string += String.format(d.toString(taal, Kleur.GEEN_KLEUR) + "%n");
		
		return string;
	}
	
	// ========== EINDE SPEL FASE ==========
	/**
	 * UC3
	 * ===
	 * Het spel eindigt wanneer de trekstapel leeg is. Deze methode controleert of de trekstapel.
	 */
	public boolean isEindeSpel() 
    {
        if(trekstapel.isEmpty() && !eindkolom.isEmpty())
            return true;
        
        if(trekstapel.isEmpty() && eindkolom.isEmpty())
        {
            for(Dominotegel d : startkolom)
                d.setKoning(null);
            return true;
        }
        
        return false;
    }
		
	/**
	 * UC3
	 * ===
	 * In deze methode wordt de score per speler berekend door het aantal aangrenzende vakjes met hetzelfde landschapstype 
	 * te vermenigvuldigen met het aantal kronen in dat domein. Vervolgens worden alle punten per domein opgeteld.
	 */
	public void berekenScores() {
	    for (Koninkrijk k : koninkrijken) {
	        Vak[][] koninkrijk = k.getArrayKoninkrijk();
	        int totaleScore = 0;
	        for (String landschapstype : Arrays.asList("bos", "gras", "akker", "moeras", "mijn", "water")) {
	            totaleScore += berekenScorePerLandschapstype(koninkrijk, landschapstype);
	        }

	        for (Speler s : deelnemendeSpelers) {
	            if (s.getKleur() == k.getKleur()) {
	                // Optellen bij de bestaande score in plaats van overschrijven
	                s.setScore(s.getScore() + totaleScore);
	            }
	        }
	    }
	}

	
	/**
	 * UC3
	 * ===
	 * Deze methode berekent de score per landschapstype door de methoden vindGebieden en gebiedBerekening aan te roepen, 
	 * die helpen bij het berekenen van de score.
	 * @param array
	 * @param landschapstype
	 * @return de tussentijdseScore zodat we die altijd bij het volgende kunnen optellen
	 */
	private int berekenScorePerLandschapstype(Vak[][] koninkrijk, String landschapstype) 
	{
	    List<List<Integer>> gebieden = vindGebieden(koninkrijk, landschapstype);
	    int tussenTijdseScore = 0;
	    for (List<Integer> gebied : gebieden)
	    	tussenTijdseScore += berekenGebied(gebied, koninkrijk);
	    return tussenTijdseScore;
	}

	/**
	 * UC3
	 * ===
	 * Deze methode zoekt naar gebieden in ons koninkrijk. Hiermee worden de landschapstypes in een lijst van integers geplaatst, 
	 * waarbij elk gebied wordt geïdentificeerd met een nummer. 
	 * Dit stelt ons in staat om later te bepalen hoeveel vakjes hetzelfde landschapstype hebben.
	 * @param array
	 * @param landschapstype
	 * @return Arraylist met alle gebieden
	 */
	private List<List<Integer>> vindGebieden(Vak[][] koninkrijk, String landschapstype) 
	{
	    List<List<Integer>> gebieden = new ArrayList<>();
	    boolean[][] bezocht = new boolean[koninkrijk.length][koninkrijk[0].length];
	    
	    // de boolean array wordt gevuld met allemaal "false" waarden (aka nog niet bezocht)
	    for (int i = 0 ; i < bezocht.length; i++)
		    for(int j = 0 ; j < bezocht[0].length ; j++)
		         bezocht[i][j] = false;

	    // Indien nog niet bezocht: 
	    for (int row = 0; row < koninkrijk.length; row++)
	        for (int col = 0; col < koninkrijk[row].length; col++) 
	        { 
	        	//een vakje met een landschapstype dat nog niet bezocht is -> nieuw gebied aangemaakt
	            if (!bezocht[row][col] && koninkrijk[row][col].getLandschapstype().equals(landschapstype)) 
	            {
	                List<Integer> gebied = new ArrayList<>();
	                vindGebied(koninkrijk, row, col, landschapstype, bezocht, gebied);
	                gebieden.add(gebied);
	            }
	        }
	    
	    return gebieden;
	}
	

	/**
	 * UC3
	 * ===
	 * Deze methode zorgt ervoor dat we een gebied kunnen definiëren. 
	 * We onderzoeken hierbij of de vakken aangrenzende vakken met hetzelfde landschapstype hebben.
	 * @param array
	 * @param row
	 * @param col
	 * @param landschapstype
	 * @param bezocht
	 * @param gebied
	 */
	private void vindGebied(Vak[][] koninkrijk, int rowVak, int colVak, String landschapstype, boolean[][] bezocht, List<Integer> gebied) 
	{
		//-> als het al bezocht is of niet hetzelfde landschapstype heeft dan uit deze methode gaan
	    if (rowVak < 0 || rowVak >= koninkrijk.length || colVak < 0 || colVak >= koninkrijk[rowVak].length || bezocht[rowVak][colVak] 
	    		|| !koninkrijk[rowVak][colVak].getLandschapstype().equals(landschapstype))
	        return;
	    
	    bezocht[rowVak][colVak] = true; //bezocht op true zetten en toevoegen aan gebied
	    gebied.add(rowVak);
	    gebied.add(colVak);
	    
	    // Zoek naar aangrenzende vakjes met hetzelfde landschapstype en voeg ze toe aan het huidige gebied door dezelfde methode aan te roepen
	    vindGebied(koninkrijk, rowVak + 1, colVak, landschapstype, bezocht, gebied);
	    vindGebied(koninkrijk, rowVak - 1, colVak, landschapstype, bezocht, gebied);
	    vindGebied(koninkrijk, rowVak, colVak + 1, landschapstype, bezocht, gebied);
	    vindGebied(koninkrijk, rowVak, colVak - 1, landschapstype, bezocht, gebied);
	}
	
	/**
	 * UC3
	 * ===
	 * Deze methode bevat de logica voor het berekenen van een gebied. 
	 * Hierbij onderzoeken we hoeveel vakken zich in zo'n gebied bevinden en hoeveel kronen erin aanwezig zijn.
	 * @param gebied
	 * @param array
	 * @return de score * de aantal kronen dat in het gebied staan
	 */
	private int berekenGebied(List<Integer> gebied, Vak[][] koninkrijk)  
	{
		int score = gebied.size()/2;
		int kronen = 0;
		
		for (int i = 0; i < gebied.size() - 1; i += 2) 
        {
            int row1 = gebied.get(i);
            int col1 = gebied.get(i + 1);
        	kronen += koninkrijk[row1][col1].getAantalKronen();
        }

		return score * kronen;
	}

	/**
	 * UC3
	 * ===
	 * De speler met het meeste punten is de winnaar. Als twee spelers dezelfde score hebben, wint degene met het grootste domein.
	 * Als het dan nog gelijk is, wint degene met het grootste aantal kronen in zijn koninkrijk. 
	 * Als het dan nog steeds gelijk is, winnen beide spelers.
	 * @param taal
	 * @return Geeft een String van de winnaar(s) 
	 */
	public String geefWinnaars(ResourceBundle taal) 
	{
		List<Speler> voorlopig = new ArrayList<>();
		List<Speler> spelersMetGrootsteDomein = new ArrayList<>();
		List<Speler> spelersMetMeesteKronen = new ArrayList<>();
		List<String> winnaars = new ArrayList<>();
		
		int hoogsteScore = 0;
		
		for (Speler s : deelnemendeSpelers)
		{
			if (s.getScore() >= hoogsteScore)
				hoogsteScore = s.getScore();
		}
		
		for (Speler s : deelnemendeSpelers)
		{
			if (s.getScore() == hoogsteScore)
				voorlopig.add(s);
		}
		
		if (voorlopig.size() == 1)
			stelWinnaarIn(voorlopig.get(0), winnaars);
		
		else if(voorlopig.size() > 1)
		{
			spelersMetGrootsteDomein = bepaalSpelersMetGrootsteDomein(voorlopig);
			spelersMetMeesteKronen = bepaalSpelersMetMeesteKronen(voorlopig);
			
			if(spelersMetGrootsteDomein.size() == 1)
				stelWinnaarIn(spelersMetGrootsteDomein.get(0), winnaars);
			
			else if(spelersMetMeesteKronen.size() == 1)
				stelWinnaarIn(spelersMetMeesteKronen.get(0), winnaars);
			
			else
			{
				for(Speler s : voorlopig)
				{
					winnaars.add(s.getGebruikersnaam());
					s.setAantalGewonnen(s.getAantalGewonnen() + 1);
				}
			}
		}
		
		String winnaarsString = "";
		
		if(winnaars.size() == 1)
			winnaarsString += String.format("%s%s%n", taal.getString("winnaar"), winnaars.get(0));
		else
		{
			winnaarsString += String.format("%s", taal.getString("winnaars"));
			for(String winnaar : winnaars)
				winnaarsString += String.format("%s", winnaars.indexOf(winnaar) != winnaars.size()-1 ? winnaar+", " : winnaar);
			winnaarsString += String.format("%n");
		}
		
		return winnaarsString;
	}
	
	/**
	 * UC3
	 * ===
	 * VOOR GUI
	 * ========
	 * De speler met het meeste punten is de winnaar.
	 * Als 2 spelers dezelfde score hebben wint diegene met een grootste domein
	 * Als het dan nog altijd gelijk is wint diegene met het meeste aantal kronen in zijn koningkrijk
	 * Als het dan nog gelijk is winnen ze alle 2.
	 * @param taal
	 * @return Geeft een lijst van SpelerDTO terug met de winnaar(s) in
	 */
	public List<SpelerDTO> geefWinnaarsLijst() 
	{
		List<Speler> voorlopig = new ArrayList<>();
		List<Speler> spelersMetGrootsteDomein = new ArrayList<>();
		List<Speler> spelersMetMeesteKronen = new ArrayList<>();
		List<SpelerDTO> winnaars = new ArrayList<>();
		
		int hoogsteScore = 0;
		
		for (Speler s : deelnemendeSpelers)
		{
			if (s.getScore() >= hoogsteScore)
				hoogsteScore = s.getScore();
		}
		
		for (Speler s : deelnemendeSpelers)
		{
			if (s.getScore() == hoogsteScore)
				voorlopig.add(s);
		}
		
		if (voorlopig.size() == 1)
			stelWinnaarInDTO(voorlopig.get(0), winnaars);
		
		else if(voorlopig.size() > 1)
		{
			spelersMetGrootsteDomein = bepaalSpelersMetGrootsteDomein(voorlopig);
			spelersMetMeesteKronen = bepaalSpelersMetMeesteKronen(voorlopig);
			
			if(spelersMetGrootsteDomein.size() == 1)
				stelWinnaarInDTO(spelersMetGrootsteDomein.get(0), winnaars);
			
			else if(spelersMetMeesteKronen.size() == 1)
				stelWinnaarInDTO(spelersMetMeesteKronen.get(0), winnaars);
			
			else
			{
				for(Speler s : voorlopig)
					stelWinnaarInDTO(s, winnaars);
			}
		}
		return winnaars;
	}
	
	/**
	 * UC3
	 * ===
	 * Deze methode verhoogt het aantal gewonnen spellen met 1 en voegt de gebruikersnaam van de speler toe aan de lijst van winnaars.	 
	 * @param speler
	 * @param winnaars
	 */
	private void stelWinnaarIn(Speler speler, List<String> winnaars)
	{
		winnaars.add(speler.getGebruikersnaam());
		speler.setAantalGewonnen(speler.getAantalGewonnen() + 1);
	}
	
	/**
	 * UC3
	 * ===
	 * Deze methode zorgt ervoor dat de winnaar wordt vertegenwoordigd als een SpelerDTO.	 * @param speler
	 * @param winnaars
	 */
	private void stelWinnaarInDTO(Speler speler, List<SpelerDTO> winnaars)
	{
		SpelerDTO spelerDTO = new SpelerDTO(speler.getGebruikersnaam(),
				speler.getGeboortejaar(), speler.getAantalGewonnen(), 
				speler.getAantalGespeeld(), speler.getScore(), speler.getKleur());
		
		winnaars.add(spelerDTO);
	}

	/**
	 * UC3
	 * ===
	 * Deze methode bepaalt welke speler het grootste domein heeft, wat nodig is om de winnaar te kunnen bepalen. 
	 * Hij doorloopt de koninkrijken en telt voor elk landschapstype hoeveel tegels ervan bestaan.
	 * @param voorlopig
	 * @return
	 */
	private List<Speler> bepaalSpelersMetGrootsteDomein(List<Speler> voorlopig) 
	{
	    List<Speler> spelersMetGrootsteDomein = new ArrayList<>();
	    List<Integer> alleGrootsteDomeinen = new ArrayList<>();
	    List<Integer> indexGrootsteGebied = new ArrayList<>();
	    int max = 0;
	   
	    
	    for (Speler s : voorlopig) 
	    { 
	    	List<List<Integer>> alleGebieden = new ArrayList<>();
	    	Vak[][] koninkrijk = null;
	    	int grootsteDomein = 0;
	    	
	    	//koninkrijk bepalen
	    	for(Koninkrijk k : koninkrijken) 
	    	{
	    		if(s.getKleur().equals(k.getKleur()))
	    		{
	    			koninkrijk = k.getArrayKoninkrijk();
	    			break;
	    		}
	    	}
	        
	    	//voor ieder landschapstype gebieden vinden en in een lijst steken
	        for (String landschapstype : Arrays.asList("bos", "gras", "akker", "moeras", "mijn", "water")) 
	        {
	            List<List<Integer>> gebieden = vindGebieden(koninkrijk, landschapstype);
	            alleGebieden.addAll(gebieden);
	        }
	        
	        //het grootste gebied van de speler bepalen
            for (List<Integer> g : alleGebieden) 
            {                
                int sizeGebied = 0;
                for (int i = 0; i < g.size() - 1; i += 2) 
                    sizeGebied++;
                
                if(sizeGebied > grootsteDomein) 
                    grootsteDomein = sizeGebied;
            }
	        
	        //het grootste gebied in een lijst steken -> index van deze gebieden komt overeen met index van lijst met spelers met het meeste punten
	        alleGrootsteDomeinen.add(grootsteDomein);
	    }

	    //het grootste domein van de grootste domeinen van de spelers bepalen en toevoegen aan lijst met indexen
	    for (int i = 0; i < alleGrootsteDomeinen.size(); i++) 
	    {
	        if (alleGrootsteDomeinen.get(i) >= max) 
	        {
	        	if (alleGrootsteDomeinen.get(i) > max)
	        		max = alleGrootsteDomeinen.get(i);
	            indexGrootsteGebied.add(i);
	        }
	    }
	    
	    //de spelers met het grootste gebied toevoegen (index) aan de lijst van spelers met de grootste domeinen
	    for(int i = 0; i < indexGrootsteGebied.size(); i++) 
	    	spelersMetGrootsteDomein.add(voorlopig.get(i));
	
	    return spelersMetGrootsteDomein;
	}

	/**
	 * UC3
	 * ===
	 * In deze methode wordt bepaald welke speler de meeste kronen heeft in zijn gebied.
	 * @param voorlopig
	 * @return
	 */
	private List<Speler> bepaalSpelersMetMeesteKronen(List<Speler> voorlopig)
	{
		List<Speler> spelersMetMeesteKronen = new ArrayList<>();
	    List<Integer> alleAantalKronen = new ArrayList<>();
	    List<Integer> indexMeesteAantalKronen = new ArrayList<>();
	    int max = 0;
		
	    for (Speler s : voorlopig) 
	    { 
	    	Vak[][] koninkrijk = null;
	    	int aantalKronen = 0;
	    	
	    	//koninkrijk bepalen
	    	for(Koninkrijk k : koninkrijken) 
	    	{
	    		if(s.getKleur().equals(k.getKleur()))
	    		{
	    			koninkrijk = k.getArrayKoninkrijk();
	    			break;
	    		}
	    	}
	    	
	    	for (int i = 0 ; i < koninkrijk.length; i++)
	            for(int j = 0 ; j < koninkrijk.length ; j++)
	            {
	               aantalKronen += koninkrijk[i][j].getAantalKronen();
	            }
	    	
	    	alleAantalKronen.add(aantalKronen);
	    }
	    
	    //het meeste aantal kronen van het aantal kronen van de spelers bepalen en toevoegen aan lijst met indexen
	    for (int i = 0; i < alleAantalKronen.size(); i++) 
	    {
	        if (alleAantalKronen.get(i) >= max) 
	        {
	        	if (alleAantalKronen.get(i) > max)
	        		max = alleAantalKronen.get(i);
	            indexMeesteAantalKronen.add(i);
	        }
	    }
	    
	    //de spelers met het grootste gebied toevoegen (index) aan de lijst van spelers met de grootste domeinen
	    for(int i = 0; i < indexMeesteAantalKronen.size(); i++) 
	    	spelersMetMeesteKronen.add(voorlopig.get(i));
	
	    return spelersMetMeesteKronen;
	}


	/**
	 * UC5
	 * ===
	 * In deze methode wordt de teller met 1 verhoogd zodat we bv. de volgende ronde kunnen beginnen.
	 */
	public void geefVolgendeTegelUitStartkolom()
	{
		// teller wordt verhoogd voor startkolom af te gaan in ronde
     	teller+=1;
	}
}