package cui;

import java.util.ResourceBundle;
import java.util.Scanner;

import domein.DomeinController;
import exceptions.GebruikersnaamInGebruikException;

public class RegistreerSpeler {
    private final DomeinController dc;
    private final Scanner input = new Scanner(System.in);
    private final ResourceBundle taal;

    public RegistreerSpeler(DomeinController dc, ResourceBundle taal) 
    {
        this.dc = dc;
        this.taal = taal;
    }

    public void start() 
    {
        boolean inputOk = false;
        do {
            try {
                String gebruikersnaam = geefNaam();
                int geboortejaar = geefJaar();
                dc.registreerSpeler(gebruikersnaam, geboortejaar);
                inputOk = true;
                System.out.printf(taal.getString("RegistratieBevestiging") + gebruikersnaam, "%n");
            } catch (IllegalArgumentException ex) {
                System.out.println(taal.getString(ex.getMessage()) + "\n");
            } catch (GebruikersnaamInGebruikException ex) {
                System.out.println(taal.getString("InGebruik") + "\n");
            }
        } while (!inputOk);
    }

    private String geefNaam() 
    {
        System.out.print(taal.getString("Gebruikersnaam"));
        return input.nextLine();
    }

    private int geefJaar() 
    {
        int jaar = 0;
        boolean jaarOk;
        do {
            try {
                System.out.print(taal.getString("Geboortejaar"));
                String jaarString = input.nextLine();
                if (jaarString.isBlank() || jaarString.isEmpty()) {
                    throw new IllegalArgumentException(taal.getString("nietLeeg"));
                }
                jaar = Integer.parseInt(jaarString);
                jaarOk = true;
            } catch (NumberFormatException ex) {
                System.out.println(taal.getString("geheelGetal") + "\n");
                jaarOk = false;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage() + "\n");
                jaarOk = false;
            }
        } while (!jaarOk);
        return jaar;
    }
}
