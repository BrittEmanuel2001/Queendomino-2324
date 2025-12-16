package testen;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.DominotegelRepository;
import domein.Koninkrijk;
import domein.Spel;
import domein.Speler;
import domein.Vak;
import utils.Kleur;

public class WinnaarTest {
	private Vak[][] arrayKoninkrijk;
	private Spel spel;
	private List<Speler> deelnemendeSpelers;
	private List<Koninkrijk> koninkrijken;

    @BeforeEach
    void setUp() 
    {
        deelnemendeSpelers = new ArrayList<>();
        
        deelnemendeSpelers.add(new Speler("Brittney001", 2001, 0, 0, 0, Kleur.GEEL));
        deelnemendeSpelers.add(new Speler("Alexsken", 2001, 0, 0, 0, Kleur.BLAUW));
        deelnemendeSpelers.add(new Speler("Xaremios", 2001, 0, 0, 0, Kleur.ROOS));
        deelnemendeSpelers.add(new Speler("Xaremij", 2001, 0, 0, 0, Kleur.GROEN));
        
        spel = new Spel(deelnemendeSpelers);
        
        for (int i = 0; i < deelnemendeSpelers.size(); i++) {
            spel.kiesSpelerEnKleur(1, 1);
        }
        
        DominotegelRepository dominotegelRepo = new DominotegelRepository();
        spel.maakSpel(dominotegelRepo.geefTrekstapel());

        koninkrijken = new ArrayList<>();
        koninkrijken.add(new Koninkrijk(Kleur.GEEL));
        koninkrijken.add(new Koninkrijk(Kleur.BLAUW));
        koninkrijken.add(new Koninkrijk(Kleur.ROOS));
        koninkrijken.add(new Koninkrijk(Kleur.GROEN));
    }

	@Test
	void geefWinnaars_gelijkeScores_assertTrue() 
	{		
        String taal = "nl";
		String land = "BE";
		Locale currentLocale = new Locale(taal, land);
		
	    List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));
	    
	   	for(Koninkrijk k : koninkrijken)
    	{
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    			arrayKoninkrijk = k.getArrayKoninkrijk();
    			
    			arrayKoninkrijk[0][0] = new Vak(3,"bos", 0);
    	    	arrayKoninkrijk[0][1] = new Vak(3,"gras", 1);
    	    	arrayKoninkrijk[0][2] = new Vak(11,"water", 1);
    	    	arrayKoninkrijk[0][3] = new Vak(7,"zand", 0);
    	    	arrayKoninkrijk[0][4] = new Vak(8,"moeras", 1);
    	    	
    	    	arrayKoninkrijk[1][0] = new Vak(18,"water", 1);
    	    	arrayKoninkrijk[1][1] = new Vak(18,"water", 0);
    	    	arrayKoninkrijk[1][2] = new Vak(11,"mijn", 2);
    	    	arrayKoninkrijk[1][3] = new Vak(7,"zand", 0);
    	    	arrayKoninkrijk[1][4] = new Vak(8,"zand", 0);
    	    	
    	    	arrayKoninkrijk[2][0] = new Vak(20,"water", 0);
    	    	arrayKoninkrijk[2][1] = new Vak(20,"water", 0);
    	    	arrayKoninkrijk[2][3] = new Vak(14,"gras", 0);
    	    	arrayKoninkrijk[2][4] = new Vak(14,"gras", 0);
    	    	
    	    	arrayKoninkrijk[3][0] = new Vak(13,"water", 1);
    	    	arrayKoninkrijk[3][1] = new Vak(13,"bos", 0);
    	    	arrayKoninkrijk[3][2] = new Vak(16,"zand", 0);
    	    	arrayKoninkrijk[3][3] = new Vak(2,"gras", 0);
    	    	arrayKoninkrijk[3][4] = new Vak(2,"gras", 0);
    	    	
    	    	arrayKoninkrijk[4][0] = new Vak(24,"moeras", 0);
    	    	arrayKoninkrijk[4][1] = new Vak(24,"moeras", 0);
    	    	arrayKoninkrijk[4][2] = new Vak(16,"moeras", 0);
    	    	arrayKoninkrijk[4][3] = new Vak(43,"zand", 0);
    	    	arrayKoninkrijk[4][4] = new Vak(43,"gras", 2);
    	    	
    	    	k.breidKoninkrijkUit(arrayKoninkrijk);
    	    	// array wordt ingesteld naar gewenste situatie
    	    	
    			break;
    		}
    		
    		if(k.getKleur().equals(Kleur.BLAUW))
    		{
    			arrayKoninkrijk = k.getArrayKoninkrijk();
    			
    			arrayKoninkrijk[0][0] = new Vak(3,"bos", 0);
    	    	arrayKoninkrijk[0][1] = new Vak(3,"zand", 1);
    	    	arrayKoninkrijk[0][2] = new Vak(11,"moeras", 0);
    	    	arrayKoninkrijk[0][3] = new Vak(7,"bos", 1);
    	    	arrayKoninkrijk[0][4] = new Vak(8,"zand", 0);
    	    	
    	    	arrayKoninkrijk[1][0] = new Vak(18,"gras", 0);
    	    	arrayKoninkrijk[1][1] = new Vak(18,"zand", 0);
    	    	arrayKoninkrijk[1][2] = new Vak(11,"zand", 1);
    	    	arrayKoninkrijk[1][3] = new Vak(7,"moeras", 2);
    	    	arrayKoninkrijk[1][4] = new Vak(8,"zand", 0);
    	    	
    	    	arrayKoninkrijk[2][0] = new Vak(20,"water", 0);
    	    	arrayKoninkrijk[2][1] = new Vak(20,"zand", 0);
    	    	arrayKoninkrijk[2][3] = new Vak(14,"moeras", 1);
    	    	arrayKoninkrijk[2][4] = new Vak(14,"gras", 0);
    	    	
    	    	arrayKoninkrijk[3][0] = new Vak(13,"zand", 0);
    	    	arrayKoninkrijk[3][1] = new Vak(13,"zand", 0);
    	    	arrayKoninkrijk[3][2] = new Vak(16,"bos", 0);
    	    	arrayKoninkrijk[3][3] = new Vak(2,"moeras", 0);
    	    	arrayKoninkrijk[3][4] = new Vak(2,"mijn", 2);
    	    	
    	    	arrayKoninkrijk[4][0] = new Vak(24,"water", 1);
    	    	arrayKoninkrijk[4][1] = new Vak(24,"bos", 0);
    	    	arrayKoninkrijk[4][2] = new Vak(16,"bos", 0);
    	    	arrayKoninkrijk[4][3] = new Vak(43,"bos", 0);
    	    	arrayKoninkrijk[4][4] = new Vak(43,"bos", 0);
    	    	
    	    	k.breidKoninkrijkUit(arrayKoninkrijk);
    	    	// array wordt ingesteld naar gewenste situatie
    	    	
    			break;
    		}
    		
    		if(k.getKleur().equals(Kleur.ROOS))
    		{
    			arrayKoninkrijk = k.getArrayKoninkrijk();
    			
    			arrayKoninkrijk[0][0] = new Vak(3,"bos", 1);
    	    	arrayKoninkrijk[0][1] = new Vak(3,"", 0);
    	    	arrayKoninkrijk[0][2] = new Vak(11,"", 0);
    	    	arrayKoninkrijk[0][3] = new Vak(7,"gras", 0);
    	    	arrayKoninkrijk[0][4] = new Vak(8,"zand", 1);
    	    	
    	    	arrayKoninkrijk[1][0] = new Vak(18,"water", 0);
    	    	arrayKoninkrijk[1][1] = new Vak(18,"bos", 1);
    	    	arrayKoninkrijk[1][2] = new Vak(11,"gras", 0);
    	    	arrayKoninkrijk[1][3] = new Vak(7,"gras", 2);
    	    	arrayKoninkrijk[1][4] = new Vak(8,"water", 0);
    	    	
    	    	arrayKoninkrijk[2][0] = new Vak(20,"water", 0);
    	    	arrayKoninkrijk[2][1] = new Vak(20,"bos", 0);
    	    	arrayKoninkrijk[2][3] = new Vak(14,"gras", 1);
    	    	arrayKoninkrijk[2][4] = new Vak(14,"zand", 0);
    	    	
    	    	arrayKoninkrijk[3][0] = new Vak(13,"zand", 0);
    	    	arrayKoninkrijk[3][1] = new Vak(13,"bos", 1);
    	    	arrayKoninkrijk[3][2] = new Vak(16,"moeras", 0);
    	    	arrayKoninkrijk[3][3] = new Vak(2,"water", 1);
    	    	arrayKoninkrijk[3][4] = new Vak(2,"zand", 0);
    	    	
    	    	arrayKoninkrijk[4][0] = new Vak(24,"zand", 0);
    	    	arrayKoninkrijk[4][1] = new Vak(24,"zand", 0);
    	    	arrayKoninkrijk[4][2] = new Vak(16,"mijn", 2);
    	    	arrayKoninkrijk[4][3] = new Vak(43,"gras", 0);
    	    	arrayKoninkrijk[4][4] = new Vak(43,"gras", 1);
    	    	
    	    	k.breidKoninkrijkUit(arrayKoninkrijk);
    	    	// array wordt ingesteld naar gewenste situatie
    	    	
    			break;
    		}
    	}
	   	
	    Spel testSpel = new Spel(deelnemendeSpelers);

        ResourceBundle taal2 = ResourceBundle.getBundle("resources/langBundle", currentLocale);

        testSpel.berekenScores();
        
	    String verwachteUitvoer = "De winnaars van het spel zijn \n";
	    assertEquals(verwachteUitvoer.trim(), testSpel.geefWinnaars(taal2).trim());

	}


}