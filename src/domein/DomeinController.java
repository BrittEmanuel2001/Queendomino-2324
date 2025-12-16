package domein;

import java.util.List;
import java.util.ResourceBundle;

import dto.DominotegelDTO;
import dto.SpelerDTO;
import utils.Kleur;

public class DomeinController 
{
    private Spel spel;
    private final SpelerRepository spelerRepo;
    private final DominotegelRepository dominotegelRepo;
    
    /**
     * UC1 
     * ===
     * De Domeincontroller maakt een nieuwe spelerRepository aan 
     * waarin de gebruikersnaam en het geboortejaar van de 
     * gebruiker in komen.
     */
    public DomeinController() 
    {
        spelerRepo = new SpelerRepository();
        dominotegelRepo = new DominotegelRepository();
    }
    
    /**
     * UC1 
     * ===
     * De methode voegt een nieuwe speler toe in de spelerRepository.
     * Deze methode gaat ook het geboortejaar en de gebruikersnaam door van de gebruiker toevoegen.
     * @param gebruikersnaam
     * @param geboortejaar
     */
    public void registreerSpeler(String gebruikersnaam, int geboortejaar) 
    {
        spelerRepo.voegToe(new Speler(gebruikersnaam, geboortejaar));
    }
    
    /**
     * UC2
     * ===
     * Deze methode wordt aangeroepen wanneer de keuze wordt gemaakt om een nieuw spel te starten.
     * De methode maakt een nieuw spel aan met een lijst van alle spelers en een lijst van alle dominotegels (= Trekstapel).
     * Ook gaat hij de mapper meegeven zodat de gekozen kleur van een speler kan worden toegevoegd aan de database.
     */
    public void startKingdomino()
    {
        spel = new Spel(spelerRepo.getSpelers());
    }
    
    /**
     * UC2
     * ===
     * Het maakt alle nodige stapels aan om het spel te kunnen starten.
     * Hierin wordt de trekstapel en de startkolom aangemaakt naargelang het aantal deelnemende spelers.
     */
    public void maakSpel()
    {
        spel.maakSpel(dominotegelRepo.geefTrekstapel());
    }
    
    /**
     * UC2 
     * ===
     * De methode zal een lijst in de UI met daarin alle nog niet geselecteerde spelers returnen.
     * Hierdoor zal er een lijst 
     * @return lijst van resterende spelers
     */
    public List<SpelerDTO> geefResterendeSpelers()
    {
        return spel.geefResterendeSpelers();
    }
    
    /**
     * UC3
     * ===
     * Deze methode zorgt ervoor dat we in de GUI de knop voor start een nieuw spel kunnen disablen. Het spel mag pas kunnen starten
     * als er minstens 3 spelers in de databank zitten.
     * @return hoeveel spelers in de databank zitten
     */
    public int geefAantalSpelersInDatabank()
    {
    	return spelerRepo.getSpelers().size();
    }
    
    /**
     * UC2 
     * ===
     * De methode geeft een lijst in de UI met daarin alle speler die deelnemen aan het spel.
     * @return lijst van deelnemende spelers
     */
    public List<SpelerDTO> geefDeelnemendeSpelers()
    {
        return spel.getDeelnemendeSpelers();
    }
    
    /**
     * UC2 
     * ===
     * De methode geeft een lijst in de UI met daarin alle kleuren die nog niet gekozen zijn.
     * @return lijst van resterende kleuren
     */
    public List<Kleur> geefResterendeKleuren()
    {
        return spel.geefResterendeKleuren();
    }

    /**
     * UC2
     * ===
     * De methode zal 2 integers doorgeven die corresponderen met het nummer
     * van de speler in de UI en een gekozen kleur in de UI.
     * @param speler
     * @param kleur
     */
    public void kiesSpelerEnKleur(int speler, int kleur)
    {
        spel.kiesSpelerEnKleur(speler, kleur);
        List<Speler> deelnemers = spel.geefDeelnemendeSpelers();
        spelerRepo.pasAan(deelnemers);
    }
    
    /**
     * UC2
     * ===
     * De methode zal de speler aan de beurt weergeven. Deze methode zal ervoor zorgen dat in de eerste ronde de deelnemende
     * spelers random worden gekozen. Na de eerste ronde wordt de startkolom overlopen om te volgorde te bepalen.
     * @param teller
     * @return spelerDTO van de speler die aan de beurt is
     */
    public SpelerDTO geefSpelerAanDeBeurt(int teller) 
    {
        return spel.geefSpelerAanDeBeurt(teller);
    }
        
    /**
     * UC2
     * ===
     * Deze methode geeft een lijst van DominotegelDTO's die alle dominotegels van de trekstapel bevat.
     * @return lijst die de trekstapel van het spel bevat
     */
    public List<DominotegelDTO> geefTrekstapel()
    {
        return spel.geefTrekstapel();
    }
    
    /**
     * UC2
     * ===
     * Deze methode geeft een lijst van DominotegelDTO's die alle dominotegels van de startkolom bevat.
     * @return lijst die de startkolom van het spel bevat
     */
    public List<DominotegelDTO> geefStartkolom()
    {
        return spel.geefStartkolom();
    }
  
    /**
     * UC2
     * ===
     * De methode zal ervoor zorgen dat de speler een dominotegel kan kiezen. Hij overloopt de startkolom en de speler kan dan uit
     * de dominotegels kiezen. Als de huidige speler een dominotegel gekozen heeft zal hij de koning ook op de tegel zetten.
     * @param dominotegelDTO 
     * @param kolom
     */
    public void kiesDominotegel(DominotegelDTO dominotegelDTO, List<DominotegelDTO> kolom) 
    {
        spel.kiesDominotegel(dominotegelDTO, kolom);
    } 
    
    /**
     * UC2
     * ===
     * Deze methode wordt aangeroepen in het overzicht in StartSpel nadat iedereen een dominotegel heeft gekozen.
     * Hij krijgt de kleur van de deelnemende spelers mee en returnt een String met alle nodige informatie van 
     * de gekozen dominotegel die aan deze kleur gelinkt is.
     * @param kleur van de deelnemende speler
     * @return alle nodige informatie over de net gekozen dominotegel die aan de kleur werd gelinkt
     */
    public String geefDominotegelGelinktAanKoning(Kleur kleur) 
    {
    	return spel.geefDominotegelGelinktAanKoning(kleur).toString();
    }
    
    /**
     * UC5
     * ===
     * Deze methode wordt gebruikt in de GUI. Hij zorgt ervoor dat de dominotegel wordt gelinkt aan een bepaalde koning.
     * Hij krijgt de kleur van de deelnemende spelers mee en returnt een DominotegelDTO met alle nodige informatie van 
     * de gekozen dominotegel die aan deze kleur gelinkt is. 
     * @param kleur
     * @return
     */
    public DominotegelDTO geefDominotegelDTOGelinktAanKoning(Kleur kleur) 
    {
    	Dominotegel d = spel.geefDominotegelGelinktAanKoning(kleur);
    	DominotegelDTO dominotegelDTO = new DominotegelDTO(d.getNummer(), d.getVak1(), d.getVak2(), d.getKoning());
    	return dominotegelDTO;
    }
    
    /**
     * UC3
     * ===
     * Hier wordt per deelnemende speler het aantalGespeeld met 1 verhoogd. Dit wordt dan ook aangepast in de databank. 
     * Zo kunnen we bijhouden hoe vaak die bepaalde speler het spel heeft gespeeld.
     */
	public void startSpel() 
	{
		spel.startSpel();
		List<Speler> deelnemers = spel.geefDeelnemendeSpelers();
		spelerRepo.pasAan(deelnemers);
	}
	
	/**
     * UC2
     * ===
     * Deze methode gaat de startkolom die we hebben omzetten naar een eindkolom. Daarna gaat de lijst omgezet worden in een lijst
     * van DTO's en dan worden ze gereturnd.
     * @return lijst die de eindkolom van het spel bevat
     */
    public List<DominotegelDTO> geefEindkolom()
    {
        return spel.geefEindkolom();
    }
    
    /**
     * UC2
     * ===
     * Deze methode krijgt 2 parameters mee: de speler en de taal. De methode in het spel zal er vervolgens voor
     * zorgen dat er een overzicht wordt getoond van wat er in die ronde is gebeurd. 
     * De koninkrijken worden weergegeven, evenals de gekozen dominotegel.
     * @param speler
     * @param taal
     * @return
     */
	public String geefOverzichtRonde(SpelerDTO speler, ResourceBundle taal) 
	{
		return spel.geefOverzichtRonde(speler, taal);
	}	
	
	/**
	 * UC3
	 * ===
	 * Hij krijgt 2 parameters mee: de speler en de taal. De methode zal ervoor zorgen dat er 
     * een overzicht wordt getoond van hoe het spel is geëindigd.
	 * @param speler
	 * @param taal
	 * @return een overzicht van wat er in het spel gebeurd is (wie is er gewonnen? Welke score heeft elke speler?) ook aantalgewonnen/gespeeld 
	 */
	public String geefOverzichtSpel(SpelerDTO speler, ResourceBundle taal) 
	{
		return spel.geefOverzichtSpel(speler, taal);
	}
	
	/**
     * UC3
     * ===
     * Deze methode zal true retourneren als de trekstapel leeg is.
     * @return retourneert true als trekstapel leeg is
     */
	public boolean isEindeSpel() 
	{
		return spel.isEindeSpel();
	}

	/**
     * UC3
     * ===
     * In deze methode zal de score berekend worden aan de hand van de spelregels.
     */	
	public void berekenScores()
	{
		spel.berekenScores();
	}

	/**
     * UC3
     * ===
     * In deze methode zal de winnaar(s) geretourneerd worden.
     * @param taal
     * @return geeft de winnaar(s) van het spel
     */
	public String geefWinnaars(ResourceBundle taal) 
	{
		String winnaars = spel.geefWinnaars(taal);
		List<Speler> deelnemers = spel.geefDeelnemendeSpelers();
		spelerRepo.pasAan(deelnemers);
		return winnaars;
	}
	
	/**
	 * UC3
     * ===
	 * De methode is voor de GUI. hij geeft een lijst van spelerDTO's terug. De lijst zal de winnaar returnen.
	 */
	public List<SpelerDTO> geefWinnaarsLijst()
	{
		return spel.geefWinnaarsLijst();
	}

	/**
     * UC4
     * ===
     * Deze methode zal ervoor zorgen dat de eindkolom wordt gemaakt: de aantal van rondes wordt met 1 verhoogd en
     * bij de laatste ronde zal de eindkolom de nieuwe startkolom worden.
     */
	public void speelRonde() 
	{
		spel.speelRonde();
	}
	
	/**
     * UC4
     * ===
     * In deze methode zal men de volgorde bepalen van wie er mag beginnen. Dit wordt gedaan door de startkolom te overlopen en dan te 
     * kijken wie er als eerste staat en deze wordt dan in een lijst gestoken zodat deze lijst dan kan worden overlopen om de volgende
     * ronde te spelen
     */
	public void bepaalSpelvolgordeStart() 
	{
		spel.bepaalSpelvolgordeStart();
	}
	
	/**
     * UC4
     * ===
     * In deze methode zal de startkolom worden omgezet naar de eindkolom en gaat de spelVolgorde weer een lege lijst worden.
     */
	public void resetStartkolom() 
	{
		spel.resetStartkolom();
	}
	
	/**
     * UC4
     * ===
     * In deze methode wordt het einde van een ronde bepaald. Zolang er dominotegels in de eindkolom zitten
     * zonder een koning, zal deze methode false retourneren. 
     * Als alle koningen zijn geplaatst en dus alle dominotegels bezet zijn, zal deze true retourneren.
     * @return geeft true als elke dominotegel van de eindkolom een koning bevat
     */
	public boolean isEindeRonde() 
	{
		return spel.isEindeRonde();
	}
	
	/**
     * UC4
     * ===
     * In deze methode wordt de ronde geretourneerd.
     * @return de hoeveelste ronde we zitten
     */
	public int geefRonde() 
	{
		return spel.getRonde();
	}
	
	/**
     * UC4
     * ===
     * Deze methode zal de startkolom, die we hebben gemaakt, omzetten naar een String. 
     * Het zal de toString-methode van elke dominotegel aanroepen om een gestructureerde zin te verkrijgen.
     * @param taal
     * @return geeft een String van de startkolom
     */
	public String printStartkolom(ResourceBundle taal) 
	{
		return spel.printStartkolom(taal);
	}
	
	/**
     * UC4
     * ===
     * Deze methode zal de eindkolom, die we hebben gemaakt, omzetten naar een String. 
     * Het zal de toString-methode van elke dominotegel aanroepen om een gestructureerde zin te verkrijgen.
     * @param taal
     * @return geeft een String van eindkolom
     */
	public String printEindkolom(ResourceBundle taal) 
	{
		return spel.printEindkolom(taal);
	}
	
	/**
     * UC5
     * ===
     * Deze methode controleert welke dominotegel overeenkomt met het nummer van de gekozenDominotegel. 
     * Vervolgens wordt de koning van de huidige speler aan deze tegel gekoppeld via de methode setKoning vanuit de klasse dominotegel. 
     * @param dominotegelDTO
     * @param kolom
     * @param kleur
     */
	public void speelBeurt(DominotegelDTO dominotegelDTO, List<DominotegelDTO> kolom, Kleur kleur) 
	{
		spel.speelBeurt(dominotegelDTO, kolom, kleur);
	}
	
	/**
     * UC5
     * ===
     * Deze methode plaatst de gekozen dominotegel uit de startkolom in het koninkrijk van de huidige speler. 
     * Dit gebeurt door middel van de kolom, rij en rotatie van de tegel.
     * @param kleur
     * @param row1
     * @param row2
     * @param col1
     * @param col2
     */
	public void plaatsDominoTegel(Kleur kleur, int row1, int col1, int row2, int col2) 
	{
		spel.plaatsDominotegel(kleur, row1, col1, row2, col2);
	}
	
	/**
	 * UC5
	 * ===
	 * Deze methode controleert of de te plaatsen dominotegel kan worden geplaatst volgens de regels.
	 * @param kleur
	 */
	public boolean zijnErNogMogelijkeZetten(Kleur kleur) 
	{
		return spel.zijnErNogMogelijkeZetten(kleur);
	}

	/**
	 * UC5
	 * ===
	 * Deze methode stelt spelers in staat om te kiezen welke kant ze hun koninkrijk willen uitbreiden. 
	 * Vervolgens verwijst deze methode door naar de juiste methode om het koninkrijk daadwerkelijk uit te breiden.
	 * @param kleur
	 * @param uitbreidKant
	 */
	public boolean breidKoninkrijkUit(Kleur kleur, int uitbreidKant) 
	{
		return spel.breidKoninkrijkUit(kleur, uitbreidKant);
	}
	
	/**
	 * UC5
	 * ===
	 * Deze methode retourneert true als het arrayKoninkrijk een lege rij heeft onderaan, zodat het naar boven kan worden verplaatst.
	 * @param kleur
	 * @return
	 */
	public boolean kanUitbreidenBoven(Kleur kleur) {
		return spel.kanUitbreidenBoven(kleur);
	}
	
	/**
	 * UC5
	 * ===
	 * Deze methode retourneert true als het arrayKoninkrijk een lege rij heeft links, zodat het naar rechts kan worden verplaatst.
	 * @param kleur
	 * @return
	 */
	public boolean kanUitbreidenRechts(Kleur kleur) {
		return spel.kanUitbreidenRechts(kleur);
	}
	
	/**
	 * UC5
	 * ===
	 * Deze methode retourneert true als het arrayKoninkrijk een lege rij heeft rechts, zodat het naar links kan worden verplaatst.
	 * @param kleur
	 * @return
	 */
	public boolean kanUitbreidenLinks(Kleur kleur) {
		return spel.kanUitbreidenLinks(kleur);
	}
	
	/**
	 * UC5
	 * ===
	 * Deze methode retourneert true als het arrayKoninkrijk een lege rij heeft bovenaan, zodat het naar onderaan kan worden verplaatst.
	 * @param kleur
	 * @return
	 */
	public boolean kanUitbreidenOnder(Kleur kleur) {
		return spel.kanUitbreidenOnder(kleur);
	}
	
	/**
     * UC4
     * ===
     * Deze methode wordt doorverwezen naar de klasse Spel. In deze methode zal de spelVolgorde een nieuwe lijst van DTO's maken en dan de
     * spelVolgorde overlopen om zo de volgorde van de volgende ronde te kunnen krijgen.
     * @return Een lijst van deelnemende spelers in welke volgorde ze het spel spelen
     */
	public List<SpelerDTO> geefSpelvolgorde()
	{
		return spel.geefSpelvolgorde();
	}
	
	/**
     * UC5
     * ===
     * Deze methode doorloopt de koninkrijken en geeft deze weer met behulp van de toString-methode.
	 * @param kleur 
	 * @param taal
     */
	public String geefKoninkrijk(Kleur kleur, ResourceBundle taal)
	{
		return spel.geefKoninkrijk(kleur, taal);
	}
	
	/**
     * UC5
     * ===
	 * Deze methode is bedoeld voor de GUI. Het retourneert het koninkrijk van de speler die op dat moment aan de beurt is.
	 * @param kleur
	 * @return
	 */
	public Vak[][] geefKoninkrijkHuidigeSpeler(Kleur kleur)
	{
		return spel.geefKoninkrijkHuidigeSpeler(kleur);
	}
	

	/**
	 * UC5
	 * ===
	 * Deze methode verhoogt de teller om door de startkolom te gaan in de ronde. Hierdoor kunnen we de volgende tegel uit de startkolom halen.	 */
	public void geefVolgendeTegelUitStartkolom()
	{
		spel.geefVolgendeTegelUitStartkolom();
	}
}