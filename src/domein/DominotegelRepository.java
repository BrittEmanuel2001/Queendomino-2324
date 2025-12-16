package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import persistentie.DominotegelMapper;

public class DominotegelRepository 
{
    // MAPPER EN LIJSTEN
    private final DominotegelMapper mapper;
    private List<Dominotegel> dominotegels;
    private List<Vak> vakken;
    private List<Dominotegel> trekstapel;

    /**
     * UC2
     * ===
     * Dit is een constructor en instantieert alle nodige lijsten en vult de lijst dominotegels op met dominotegels
     * aan de hand van de methode maakDominotegels()
     */
    public DominotegelRepository()
    {
        mapper = new DominotegelMapper();
        dominotegels = new ArrayList<>();
        vakken = new ArrayList<>();
        trekstapel = new ArrayList<>();
        dominotegels = maakDominotegels();
    }
    
    
    // AANMAKEN VAN ALLE DOMINOTEGELS
    // --------------------------------------------
    /**
     * UC2
     * ===
     * maakDominotegels haalt alle nodige informatie uit de DominotegelMapper en maakt alle dominotegels aan.
     * @return alle dominotegels van KingDomino
     */
    public List<Dominotegel> maakDominotegels()
    {
        vakken = mapper.geefVakken();
        
        for (int i = 0; i < vakken.size(); i+=2)
        {
        	Vak vak1 = vakken.get(i);
            Vak vak2 = vakken.get(i+1);
            Dominotegel dominotegel = new Dominotegel(vakken.get(i).getNummer(), vak1, vak2, null);
                
            if(!dominotegels.contains(dominotegel)) 
            	dominotegels.add(dominotegel);
        }
        return dominotegels;
    }
    
    /**
     * UC2
     * ===
     * In de methode geefTrekstapel worden alle dominotegels van het spel aan de trekstapel toegevoegd. 
     * Met andere woorden, de trekstapel bevat alle mogelijke dominotegels van KingDomino. 
     * Vervolgens wordt de trekstapel geschud en geretourneerd. Deze methode wordt aangeroepen in de klasse Spel om de 
     * trekstapel aan te maken aan het begin van elk nieuw spel, afhankelijk van het aantal deelnemende spelers.
     * @return alle dominotegels van KingDomino als geschudde trekstapel
     */
    public List<Dominotegel> geefTrekstapel()
    {
    	trekstapel = dominotegels;
        Collections.shuffle(trekstapel);
        return trekstapel;
    }    
 }