package domein;
import java.util.ResourceBundle;

import utils.Kleur;

public class Koning 
{
	// attributen
    private Kleur kleur;

    // getter
    public Kleur getKleur() {return this.kleur;}
    
    // constructor
    /**
     * Dit is de constructor die ervoor zorgt dat de koning wordt aangemaakt.
     * @param kleur
     */
    public Koning(Kleur kleur) 
    {
        this.kleur = kleur;
    }
    
    /**
     * UC2
     * ===
     * In deze methode gaan we zorgen dat we de koning kunnen afprinten met dit bepaald kleur.
     * @param taal
     * @param k
     * @return de string voor de koning af te drukken
     */
    public String toString(ResourceBundle taal, Kleur k) 
    {
        return String.format(taal.getString("Koning") + " " + (taal.getLocale().getLanguage().equals("eng") ?
				k.getEngels() : k.getNederlands()));
    }
}