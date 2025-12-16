package domein;

import java.util.Calendar;
import java.util.GregorianCalendar;

import utils.Kleur;

public class Speler 
{
    // ATTRIBUTEN
    private String gebruikersnaam;
    private int geboortejaar;
    private int aantalGewonnen, aantalGespeeld;
    private Kleur kleur;
    private Koning koning; 
    private int score;
    
    // CONTROLE ATTRIBUTEN
    public static final int MINIMUM_LEEFTIJD = 6;
    public static final int MINIMUM_AANTAL_KARAKTERS_NAAM = 6;
    public static final int MAXIMUM_AANTAL_KARAKTERS_NAAM = 254;
    Calendar cal = GregorianCalendar.getInstance();
    boolean juisteNaam, juisteJaar;

    // CONSTRUCTORS    
    /**
     * UC1
     * ===
     * construeert een Speler met een waarde geset voor gebruikersnaam en geboortejaar
     * maar geen andere waarden, dit wordt gebruikt bij de registratie van een nieuwe speler
     * @param gebruikersnaam
     * @param geboortejaar
     */
    public Speler(String gebruikersnaam, int geboortejaar) 
    {
        this(gebruikersnaam,geboortejaar,0,0, 0, Kleur.GEEN_KLEUR);
    }
    
    /**
     * UC1
     * ===
     * construeert een Speler met een waarde geset voor gebruikersnaam, geboortejaar, aantalGewonnen en aantalGespeeld.
     * Deze constructor wordt bv. gebruikt om aantalGewonnen, aantalGespeeld te veranderen.
     * @param gebruikersnaam
     * @param geboortejaar
     * @param aantalGewonnen
     * @param aantalGespeeld
     */
    public Speler(String gebruikersnaam, int geboortejaar, int aantalGewonnen, int aantalGespeeld) 
    {
        this(gebruikersnaam,geboortejaar, aantalGewonnen, aantalGespeeld, 0, Kleur.GEEN_KLEUR);
    }
    
    /**
     * UC1
     * ===
     * Maakt een Speler aan met de meegegeven gebruikersnaam en geboortejaar. 
     * Iedere speler houdt ook bij hoevaak hij reeds is gewonnen en hoevaak hij al heeft gespeeld.
     * Iedere speler kan ook een kleur aan worden toegekend.
     * @param gebruikersnaam
     * @param geboortejaar
     * @param aantalGewonnen
     * @param aantalGespeeld
     * @param kleur
     */
    public Speler(String gebruikersnaam, int geboortejaar, int aantalGewonnen, int aantalGespeeld, int score, Kleur kleur) 
    {
        setGebruikersnaam(gebruikersnaam);
        setGeboortejaar(geboortejaar);
        setAantalGewonnen(aantalGewonnen);
        setAantalGespeeld(aantalGespeeld);
        setKleur(kleur);
        setScore(score);
        
        koning = new Koning(kleur);
    }
    
    // GETTERS
    public String getGebruikersnaam() {return gebruikersnaam;}
    public int getGeboortejaar() {return geboortejaar;}
    public int getAantalGewonnen() {return aantalGewonnen;}
    public int getAantalGespeeld() {return aantalGespeeld;}
    public Kleur getKleur() {return kleur;}
    public Koning getKoning() {return koning;}
    public int getScore() {return score;}
    
    // SETTERS
    /**
     * UC1
     * ===
     * setGebruikersnaam zorgt ervoor dat de gebruikersnaam wordt ingesteld indien de meegegeven gebruikersnaam geldig is.
     * Deze wordt vóór het instellen gecontroleerd door de controleerGebruikersnaam methode.
     * @param gebruikersnaam
     */
    private void setGebruikersnaam(String gebruikersnaam) 
    {
        controleerGebruikersnaam(gebruikersnaam);
        this.gebruikersnaam = gebruikersnaam;
    }
    
    /**
     * UC1
     * ===
     * setGeboortejaar zorgt ervoor dat het geboortejaar wordt ingesteld indien het meegegeven geboortejaar geldig is.
     * Deze wordt vóór het instellen gecontroleerd door de controleerJaar methode.
     * @param geboortejaar
     */
    private void setGeboortejaar(int geboortejaar) 
    {
        controleerJaar(geboortejaar);
        this.geboortejaar = geboortejaar;
    }
    
    /**
     * UC1
     * ===
     * setAantalGewonnen zorgt ervoor dat aantalGewonnen wordt ingesteld.
     * @param aantalGewonnen
     */
    public final void setAantalGewonnen(int aantalGewonnen) 
    {
        this.aantalGewonnen = aantalGewonnen;
    }
    
    /**
     * UC1
     * ===
     * setAantalGespeeld zorgt ervoor dat aantalGespeeld wordt ingesteld.
     * @param aantalGespeeld
     */
    public final void setAantalGespeeld(int aantalGespeeld) 
    {
        this.aantalGespeeld = aantalGespeeld;
    }
    
    
    /**
     * UC2
     * ===
     * setKleur zorgt ervoor dat kleur een waarde uit de enum klasse Kleur als waarde krijgt.
     * Kleur heeft een paar vaste waarden waaruit gekozen kan worden door de speler.
     * @param kleur
     */
    public final void setKleur(Kleur kleur) 
    {    
        this.kleur = kleur;
    }
    
    /**
     * UC2
     * ===
     * Deze methode zorgt ervoor dat de waarde van score in score wordt gezet.
     * @param score
     */
    public final void setScore(int score) 
    {
		this.score = score;
	}
    
    
    // ANDERE METHODES
    
    /**
     * UC1
     * ===
     * controleerGebruikersnaam controleert of de gebruikersnaam niet leeg is
     * en of de gebruikersnaam meer dan het minimum aantal karakters (in deze applicatie 6) bevat.
     * @param gebruikersnaam
     */
    private void controleerGebruikersnaam(String gebruikersnaam) 
    {
        if(gebruikersnaam == null || gebruikersnaam.isBlank() || gebruikersnaam.isEmpty())
            throw new IllegalArgumentException("GebruikersnaamFout");
        
        if(gebruikersnaam.length() < MINIMUM_AANTAL_KARAKTERS_NAAM)
            throw new IllegalArgumentException("GebruikersnaamKort");
        
        if(gebruikersnaam.length() > MAXIMUM_AANTAL_KARAKTERS_NAAM)
            throw new IllegalArgumentException("GebruikersnaamLang");
    }
    
    /**
     * UC1
     * ===
     * controleerJaar controleert of de gebruiker niet jonger is dan 6 jaar en niet ouder is dan 100 jaar.
     * @param geboortejaar
     */
    private void controleerJaar(int geboortejaar) 
    {
        if(geboortejaar > (cal.get(Calendar.YEAR) - MINIMUM_LEEFTIJD))
            throw new IllegalArgumentException("TeJong");
        if(geboortejaar > cal.get(Calendar.YEAR) || geboortejaar < (cal.get(Calendar.YEAR) - 100)) 
            //2e argument zodat persoon niet ouder kan zijn dan 100
            throw new IllegalArgumentException("TeOud");
    }
}