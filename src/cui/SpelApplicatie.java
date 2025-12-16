package cui;

import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

import domein.DomeinController;

import utils.Taal;

public class SpelApplicatie 
{
    private DomeinController dc;
    private Scanner input = new Scanner(System.in);

    // TAAL ATTRIBUTEN (standaard ingesteld in NL)
    private String taal = "nl";
    private String land = "BE";
    private ResourceBundle boodschappen;

    public SpelApplicatie(DomeinController dc) 
    {
        this.dc = dc;
        kiesTaal(); 
        Taal taalObject = new Taal(taal, land);
        boodschappen = taalObject.kiesTaal();
    }
    
    /**
     * MENUKEUZE KINGDOMINO
     * ====================
     */
    public void start() 
    {
        boolean invoerOk = false;
        do 
        {
            try 
            {
                int keuze = geefMenuKeuze();
                if (keuze > 3 || keuze < 1)
                    throw new IllegalArgumentException();

                while (keuze != 3) 
                {
                    switch (keuze) 
                    {
                        case 1 -> new RegistreerSpeler(dc, boodschappen).start();
                        case 2 -> new StartSpel(dc, boodschappen).start();
                        default -> System.out.println(boodschappen.getString("notValid"));
                    }
                    System.out.println();
                    keuze = geefMenuKeuze(); // Nieuwe keuze opvragen
                }
                invoerOk = true;
                
                System.out.println(boodschappen.getString("goodbye"));
                
            } catch (InputMismatchException ex) {
                System.out.println(boodschappen.getString("geheelGetal") + "\n");
                input.nextLine();
            } catch (IllegalArgumentException iae) {
            	System.out.println(boodschappen.getString("notValid") + "\n");
                input.nextLine();
            }
        } while (!invoerOk);
    }

    /**
     * TAALKEUZE KINGDOMINO
     * ====================
     */
    private void kiesTaal() 
    {
        int keuze = 0;
        boolean invoerOK = false;
        do 
        {
            try 
            {
                System.out.printf("Welkom bij Kingdomino!!%n" + "Welcome to Kingdomino!!%n"
                        + "Laten we beginnen: in welke taal zou je graag spelen?%n"
                        + "Let's begin: what language would you like to play in?%n"
                        + "-----------------------------------------------------%n"
                        + "1. Nederlands%n"
                        + "2. English%n" 
                        + "Maak jouw keuze/Enter your choice: ");

                String keuzeString = input.nextLine();
                keuze = Integer.parseInt(keuzeString);
                System.out.println();

                if (keuze > 2 || keuze < 1) throw new IllegalArgumentException();

                switch (keuze) 
                {
                    case 1 -> {taal = "nl"; land = "BE";}
                    case 2 -> {taal = "eng"; land = "UK";}
                }
                invoerOK = true;
                
            } catch (NumberFormatException nfe) {
                System.out.println("Geef het bijpassend nummer voor de gewenste taal");
                System.out.println("Please enter the corresponding number with the preferred language\n");
            } catch (IllegalArgumentException ex) {
                System.out.println("Geef een nummer tussen 1 en 2.");
                System.out.println("Enter a number between 1 and 2.");
            }
        } while (!invoerOK);
    }

    /**
     * INHOUD MENU KINGDOMINO
     * ======================
     */
    private int geefMenuKeuze() 
    {
        System.out.printf(boodschappen.getString("MenuTitel") + "%n");
        System.out.println("1. " + boodschappen.getString("MenuRegistreer"));
        System.out.println("2. " + boodschappen.getString("MenuStart"));
        System.out.println("3. " + boodschappen.getString("MenuAfsluiten"));
        System.out.print(boodschappen.getString("MenuGeefKeuze"));
        return input.nextInt();
    }
}