package utils;

import java.util.ArrayList;
import java.util.List;

public enum Kleur 
{
	// MOGELIJKE WAARDEN
	// --------------------------------------
	GEEL("geel", "yellow"), 
	ROOS("roos", "pink"), 
	BLAUW("blauw", "blue"), 
	GROEN("groen", "green"), 
	GEEN_KLEUR("geen kleur", "no color");

	// CONSTRUCTOR
	// --------------------------------------
	Kleur(String nederlands, String engels)
	{
		this.nederlands = nederlands;
		this.engels = engels;
	}
	
	// TAAL OPTIES
	// --------------------------------------
	protected String nederlands;
	protected String engels;
	
	public String getNederlands()
	{
		return this.nederlands;
	}
	
	public String getEngels()
	{
		return this.engels;
	}
	
	// METHODE OM ALLE UIT TE KIEZEN KLEUREN OP TE ROEPEN
	// --------------------------------------------------
	public static List<Kleur> geefBeschikbareKleuren()
    {
        Kleur[] alleKleuren = Kleur.values();
        List<Kleur> beschikbareKleuren = new ArrayList<>();
        
        for(Kleur k : alleKleuren)
        {
            if(k != Kleur.GEEN_KLEUR)
                beschikbareKleuren.add(k);
        }
        return beschikbareKleuren;
    }
}
