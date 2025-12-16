package domein;

import utils.Kleur;

public class Starttegel extends Vak
{   
    private Kleur kleur;
    
    /**
     * UC2
     * ===
     * Dit is de constructor. Deze zorgt ervoor dat de starttegel gemaakt wordt.
     * @param kleur
     */
    public Starttegel(Kleur kleur) 
    {
    	super(0, "Starttegel", 0);
    	this.kleur = kleur;
    }
    
    public Kleur getKleur() {return kleur;}
    
    /**
     * UC2
     * ===
     * In deze methode retourneren we een symbool dat ervoor zorgt dat we een kasteel op de starttegel hebben staan.
     */
    @Override
    public String toString() {return ">>🏰<<";}
    
    /**
     * UC2
     * ===
     * Deze methode is voor de GUI. Deze zorgt ervoor dat we in ons arrayKoninkrijk een foto staan hebben in het midden 
     * waar ons starttegel staat.
     */
    @Override
    public String geefBestandsnaam()
    {
        return "starttegel.png";
    }
}