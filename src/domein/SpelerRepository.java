package domein;

import java.util.ArrayList;
import java.util.List;

import exceptions.GebruikersnaamInGebruikException;
import persistentie.SpelerMapper;

public class SpelerRepository 
{
    // MAPPER, LIJSTEN EN OBJECT GEKOZEN SPELER
    private final SpelerMapper mapper;
    private List<Speler> spelers;
    
    // CONSTRUCTOR
    public SpelerRepository() 
    {
        mapper = new SpelerMapper();
        spelers = new ArrayList<>(mapper.geefSpelers());
    }
    
    // ---------------------------------------------------------------
    // UC1
    // SPELER TOEVOEGEN AAN REPOSITORY
    // ---------------------------------------------------------------
    
    /**
     * UC1
     * ===
     * Deze methode zal ervoor zorgen dat we een speler kunnen toevoegen. In de methode controleren we eerst met een hulpmethode
     * of de speler al bestaat (dus als in de mapper staat) als dit niet het geval is kunnen we in de mapper de nieuwe speler toevoegen.
     * @param speler
     */
    public void voegToe(Speler speler) 
    {
       if (bestaatSpeler(speler.getGebruikersnaam()))
            throw new GebruikersnaamInGebruikException("InGebruik");
       
       mapper.voegToe(speler);
       spelers = mapper.geefSpelers();
    }
    
    /**
     * UC1
     * ===
     * In deze methode controleren we of de speler bestaat in de SpelerMapper.
     * @param gebruikersnaam
     * @return
     */
    private boolean bestaatSpeler(String gebruikersnaam) 
    {
        return mapper.geefSpeler(gebruikersnaam) != null;
    }
    
    /**
     * UC1
     * ===
     * In deze methode gaan we de speler gaan nemen uit de mapper. Deze wordt dan ook getoond.
     * @param gebruikersnaam
     * @return
     */
    public Speler geefSpeler(String gebruikersnaam) 
    {
        return mapper.geefSpeler(gebruikersnaam);
    }
    
    // ---------------------------------------------------------------
    // UC2
    // SPELERS OPVRAGEN EN SELECTEREN + KLEUR KOPPELEN
    // ---------------------------------------------------------------
    
    /**
     * UC2
     * ===
     * In deze methode gaan we de spelers die in de mapper zitten allemaal gaan afprinten.
     * @return alle spelers
     */
    public List<Speler> getSpelers() 
    { 
    	spelers = mapper.geefSpelers();
    	return spelers;
    }
    
    /**
     * UC2
     * ===
     * In deze methode gaan we de mapper retourneren.
     * @return
     */
    public SpelerMapper geefMapper()
    {
    	return mapper;
    }
    
    /**
     * UC2
     * ===
     * Deze methode zal ervoor zorgen dat de gebruikersnaam wordt aangepast.
     * @param deelnemers
     */
    public void pasAan(List<Speler> deelnemers) 
    {
    	for (Speler s : deelnemers)
    		mapper.pasAan(s, s.getGebruikersnaam());
    }
}