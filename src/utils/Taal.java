package utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class Taal 
{
    private String taal;
    private String land;
    private Locale currentLocale;
    private ResourceBundle boodschappen;

    public Taal(String taal, String land) 
    {
        this.taal = taal;
        this.land = land;
        currentLocale = new Locale(taal, land);
    }

    public ResourceBundle kiesTaal() 
    {
        boodschappen = ResourceBundle.getBundle("resources/langBundle", currentLocale);
        return boodschappen;
    }
}