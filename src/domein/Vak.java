package domein;

public class Vak 
{    
    // attributen
    private int nummer;
    private String landschapstype;
    private int aantalKronen;
    
    // getters
    public int getNummer() {return nummer;}
    public String getLandschapstype() {return landschapstype;}
    public int getAantalKronen() {return aantalKronen;}

    /**
     * UC2
     * ===
     * constructor die ervoor zorgt dat het vak gemaakt wordt.
     * @param nummer
     * @param landschapstype
     * @param aantalKronen
     */
    public Vak(int nummer, String landschapstype, int aantalKronen) 
    {
        this.nummer = nummer;
        this.landschapstype = landschapstype;
        this.aantalKronen = aantalKronen;
    }
    
    /**
     * UC2
     * ===
     * constructor zonder parameters
     */
    public Vak() 
    {
    	this(0, "", 0);
    }
    
    /**
     * UC2
     * ===
     * Deze methode plaatst een symbool voor elk landschapstype, wat het overzichtelijker maakt in de CUI.
     */
    @Override
    public String toString() 
    {
    	String kronen = "0x👑";
    	String type = "";
    	
    	if(getLandschapstype().equals("bos")) type = "🌲";
    	else if(getLandschapstype().equals("akker")) type = "🌽";
    	else if(getLandschapstype().equals("water")) type = "💦";
    	else if(getLandschapstype().equals("moeras")) type = "👣";
    	else if(getLandschapstype().equals("mijn")) type = "💎";
    	else type = "🍃";
    	
    	if(aantalKronen == 1) kronen = "1x👑";
    	else if(aantalKronen == 2) kronen = "2x👑";
    	else if(aantalKronen == 3) kronen = "3x👑";
    	
        return String.format("%2s%4s", type, kronen);
    }
    
    /**
     * UC2
     * ===
     * Deze methode is voor de GUI. Deze zorgt ervoor dat we in ons arrayKoninkrijk een foto staan hebben waar ons vak leeg is.
     * @return
     */
    public String geefBestandsnaam()
    {
        if (getLandschapstype().isBlank() && getLandschapstype().isEmpty()) 
            return "leeg.png";
        else
            return String.format("%s%d.png", getLandschapstype(), getAantalKronen());
    }
}