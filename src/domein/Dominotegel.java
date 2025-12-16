package domein;

import java.util.ResourceBundle;

import utils.Kleur;

public class Dominotegel 
{
    // attributen
    private Vak vak1;
    private Vak vak2;
    private Koning koning;
    private int nummer;
    
    // setters
    public final void setKoning(Koning koning) 
    {
        this.koning = koning;
    }
   
    // getters
    public Koning getKoning() {return this.koning;}
    public int getNummer() {return this.nummer;}
    public Vak getVak1() {return this.vak1;}
    public Vak getVak2() {return this.vak2;}
  
    
    /**
     * UC2
     * ===
     * Dit is een constructor die ervoor zorgt dat er dominotegels worden gemaakt.
     * @param nummer
     * @param vak1
     * @param vak2
     * @param koning
     */
    public Dominotegel(int nummer, Vak vak1, Vak vak2, Koning koning) 
    {
        this.nummer = nummer;
        this.vak1 = vak1;
        this.vak2 = vak2;
        setKoning(koning);
    }

 
    /**
     * UC2
     * ===
     * In deze methode gaan we een overzicht maken van de de dominotegels.
     * @param taal
     * @param kleur
     * @return de String van dominotegel
     */
    public String toString(ResourceBundle taal, Kleur kleur) 
    {
    	//methode voor startkolom die altijd een koning met kleur heeft
    	String string = String.format("Nr " + nummer + " - [" + vak1.toString() + "|" + vak2.toString() + "]");
    	
        if(!(koning == null))
            string += String.format("(" + taal.getString("heeft") + koning.toString(taal, kleur) + ")");
        return string;

    }

}