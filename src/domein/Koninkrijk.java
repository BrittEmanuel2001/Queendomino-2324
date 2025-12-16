package domein;

import java.util.ResourceBundle;

import utils.Kleur;

public class Koninkrijk 
{
    private Vak[][] arrayKoninkrijk;
    private Kleur kleur;
    private Starttegel starttegel;
    
    /**
     * UC2
     * ===
     * constructor die het koninkrijk zal aanmaken.
     * @param kleur
     */
    public Koninkrijk(Kleur kleur) 
    {
    	setKleur(kleur);
    	setArrayKoninkrijk();
    }

    //getters
    public Vak[][] getArrayKoninkrijk() {return this.arrayKoninkrijk;}
   	public Kleur getKleur()	{return kleur;}
	

	/**
   	 * UC5
   	 * ===
   	 * Deze methode doorloopt de arrayKoninkrijk om de positie van de starttegel te vinden. 
   	 * Dit is nodig omdat we ons koninkrijk willen uitbreiden, wat later van invloed zal zijn op het berekenen van de score.
   	 * @return de indexen van waar de starttegel ligt
   	 */
   	public int[] getPositieStarttegel() 
    {
	 	int[] indexen = new int[2];
	 		
	 	for (int i = 0 ; i < arrayKoninkrijk.length; i++)
	 		for(int j = 0 ; j < arrayKoninkrijk.length ; j++)
	 		{
	 			if ( arrayKoninkrijk[i][j] == starttegel)
	 		       	{
	 		        	indexen[0] = i;
	 		        	indexen[1] = j;
	 		        	break;
	 		        }
	 		}
	    		
	 	return indexen;
    }

    public final void setKleur(Kleur kleur) {this.kleur = kleur;}
    
    /**
     * UC2
     * ===
     * In deze methode gaan we het Koninkrijk maken. We overlopen de array en we zoeken naar het midden van de array. In het midden
     * van de array zetten we de starttegel.
     */
    public final void setArrayKoninkrijk() 
    {
        Vak[][] arrayKoninkrijk = new Vak[5][5];
        for (int i = 0 ; i < arrayKoninkrijk.length; i++)
		    for(int j = 0 ; j < arrayKoninkrijk.length ; j++)
		    {
		    	if(i == 2 && j == 2) 
		    	{
		    		starttegel = new Starttegel(kleur);
		            // Wijs de starttegel toe aan het midden
		            arrayKoninkrijk[i][j] = starttegel;
		    	}
		    	else arrayKoninkrijk[i][j] = new Vak();
		    }
        this.arrayKoninkrijk = arrayKoninkrijk;
    }
    
    /**
     * UC5
     * ===
     * In deze methode zeggen we dat arrayKoninkrijk het nieuweKoninkrijk wordt nadat we ons koninkrijk hebben uitgebreid.
     * @param nieuwKoninkrijk
     */
    public final void breidKoninkrijkUit(Vak[][] nieuwKoninkrijk)
    {
        this.arrayKoninkrijk = nieuwKoninkrijk;
    }
    
    /**
     * UC5
     * ===
     * In deze methode gaan we de dominotegel gaan plaatsen. We gaan de rij en de kolom voor vak 1 en vak 2 gaan instellen.
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     * @param vak1
     * @param vak2
     */
    public final void voegToeAanKoninkrijk(int row1, int col1, int row2, int col2, Vak vak1, Vak vak2)
    {
    	arrayKoninkrijk[row1][col1] = vak1;
    	arrayKoninkrijk[row2][col2] = vak2;
    }



    /**
     * UC2
     * ===
     * In de toString gaan we ervoor zorgen dat we het koninkrijk visueel kunnen voorstellen
     * @param taal
     * @param k
     * @return een String van het koninkrijk
     */
    public String toString(ResourceBundle taal, Kleur k)
    {
    	String result = String.format("%s %s%n%n"
                + "%6s%s%6s%s%6s%s%6s%s%6s%s%3s%n", taal.getString("koninkrijk"), 
                taal.getLocale().getLanguage().equals("eng") ?
    					k.getEngels() : k.getNederlands(), "", "1", "", "2", "", "3", "", "4", "", "5", "");

    	int i = 1;
        for (Vak[] rij : arrayKoninkrijk) 
        {
        	result += String.format("%2d ", i);
            for (Vak vak : rij) 
            {
                if (vak.getLandschapstype().isBlank() && vak.getLandschapstype().isEmpty()) 
                	result += String.format(" %2s%2s%2s", "", "🆓", "");
                else 
                    result += String.format(" %6s", vak.toString());
            }
            result += "\n";
            i++;
        }
        result += "\n";
        return result;
    }
}
