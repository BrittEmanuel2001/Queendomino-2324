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

import domein.Dominotegel;
import domein.DominotegelRepository;
import domein.Koning;
import domein.Koninkrijk;
import domein.Spel;
import domein.Speler;
import domein.Vak;
import utils.Kleur;

public class SpelTest {
	private static Kleur kleur = Kleur.GEEL;
	private Vak[][] arrayKoninkrijk;
	private Spel spel;
	private List<Speler> deelnemendeSpelers;
	private List<Koninkrijk> koninkrijken;
	private Koninkrijk koninkrijk;
	private Vak[][] initialArray;
	private List<Speler> spelers;

	@BeforeEach
	void setUp() {
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
		
		
		
		
////		dc = new DomeinController();
//		
//		spelers = new ArrayList<>();
//		
//		spelers.add(new Speler("Brittney001", 2001, 0, 0, 0, Kleur.GEEL));
//		spelers.add(new Speler("Alexsken", 2001, 0, 0, 0, Kleur.BLAUW));
//		spelers.add(new Speler("Xaremios", 2001, 0, 0, 0, Kleur.ROOS));
//		 
////		 this.spelers = deelnemendeSpelers;
//		
//		
// 		for (int i = 0; i < spelers.size(); i++) {
//			spel.kiesSpelerEnKleur(1, 1);
//		}
//		
//		DominotegelRepository dominotegelRepo = new DominotegelRepository();
//		spel.maakSpel(dominotegelRepo.geefTrekstapel());
//
//		deelnemendeSpelers = spel.geefDeelnemendeSpelers();
//		 
//
////		kleur = deelnemendeSpelers.get(0).getKleur();
//
//		koninkrijk = new Koninkrijk(kleur);
//
//		koninkrijken = new ArrayList<>();
//		koninkrijken.add(new Koninkrijk(Kleur.GEEL));
//		koninkrijken.add(new Koninkrijk(Kleur.BLAUW));
//		koninkrijken.add(new Koninkrijk(Kleur.ROOS));
//
//
////		this.koninkrijk = new Koninkrijk(Kleur.GEEL);
////        initialArray = new Vak[5][5];
////        for (int i = 0; i < initialArray.length; i++) {
////            for (int j = 0; j < initialArray[i].length; j++) {
////            }
////        }
////        koninkrijk.setArrayKoninkrijk(initialArray);
	}
//
//	// Alex en Britt

	@Test
	void berekenScore_SlechtsEenGebiedPerLandschapstypeEnBevatMeerdereKronen_VermeerderdScoreVanSpeler() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

		for(Koninkrijk k : koninkrijken)
    	{
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
				  arrayKoninkrijk[0][0] = new Vak(32,"bos", 0); 
				  arrayKoninkrijk[0][1] = new Vak(32,"water", 1); 
				  arrayKoninkrijk[0][2] = new Vak(42,"water", 0);
				  arrayKoninkrijk[0][3] = new Vak(30,"water", 1); 
				  arrayKoninkrijk[0][4] = new Vak(9,"water", 0);
				  
				  arrayKoninkrijk[1][0] = new Vak(29,"bos", 1); 
				  arrayKoninkrijk[1][1] = new Vak(29,"gras", 0); 
				  arrayKoninkrijk[1][2] = new Vak(42,"gras", 2);
				  arrayKoninkrijk[1][3] = new Vak(30,"akker", 0); 
				  arrayKoninkrijk[1][4] = new Vak(9,"water", 0);
				  
				  arrayKoninkrijk[2][0] = new Vak(27,"bos", 1); 
				  arrayKoninkrijk[2][1] = new Vak(27,"akker", 0); 
				  arrayKoninkrijk[2][3] = new Vak(48,"akker", 0);
				  arrayKoninkrijk[2][4] = new Vak(48,"mijn", 3);
				  
				  arrayKoninkrijk[3][0] = new Vak(20,"bos", 0); 
				  arrayKoninkrijk[3][1] = new Vak(20,"akker", 1); 
				  arrayKoninkrijk[3][2] = new Vak(22,"akker", 1);
				  arrayKoninkrijk[3][3] = new Vak(43,"akker", 0); 
				  arrayKoninkrijk[3][4] = new Vak(47,"mijn", 2);
				  
				  arrayKoninkrijk[4][0] = new Vak(2,"akker", 0); 
				  arrayKoninkrijk[4][1] = new Vak(2,"akker", 0); 
				  arrayKoninkrijk[4][2] = new Vak(22,"moeras", 0);
				  arrayKoninkrijk[4][3] = new Vak(43,"moeras", 2); 
				  arrayKoninkrijk[4][4] = new Vak(47,"moeras", 0);
				  
				  k.breidKoninkrijkUit(arrayKoninkrijk);	
				  break;

			}
    		if(k.getKleur().equals(Kleur.BLAUW))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
    		 arrayKoninkrijk[0][0] = new Vak(32,"bos", 0); 
			  arrayKoninkrijk[0][1] = new Vak(32,"water", 1); 
			  arrayKoninkrijk[0][2] = new Vak(42,"water", 0);
			  arrayKoninkrijk[0][3] = new Vak(30,"water", 1); 
			  arrayKoninkrijk[0][4] = new Vak(9,"water", 0);
			  
			  arrayKoninkrijk[1][0] = new Vak(29,"bos", 1); 
			  arrayKoninkrijk[1][1] = new Vak(29,"gras", 0); 
			  arrayKoninkrijk[1][2] = new Vak(42,"gras", 2);
			  arrayKoninkrijk[1][3] = new Vak(30,"akker", 0); 
			  arrayKoninkrijk[1][4] = new Vak(9,"water", 0);
			  
			  arrayKoninkrijk[2][0] = new Vak(27,"bos", 1); 
			  arrayKoninkrijk[2][1] = new Vak(27,"akker", 0); 
			  arrayKoninkrijk[2][3] = new Vak(48,"akker", 0);
			  arrayKoninkrijk[2][4] = new Vak(48,"mijn", 3);
			  
			  arrayKoninkrijk[3][0] = new Vak(20,"bos", 0); 
			  arrayKoninkrijk[3][1] = new Vak(20,"akker", 1); 
			  arrayKoninkrijk[3][2] = new Vak(22,"akker", 1);
			  arrayKoninkrijk[3][3] = new Vak(43,"akker", 0); 
			  arrayKoninkrijk[3][4] = new Vak(47,"mijn", 2);
			  
			  arrayKoninkrijk[4][0] = new Vak(2,"akker", 0); 
			  arrayKoninkrijk[4][1] = new Vak(2,"akker", 0); 
			  arrayKoninkrijk[4][2] = new Vak(22,"moeras", 0);
			  arrayKoninkrijk[4][3] = new Vak(43,"moeras", 2); 
			  arrayKoninkrijk[4][4] = new Vak(47,"moeras", 0);
			  k.breidKoninkrijkUit(arrayKoninkrijk);	
			  break;
		}
    		if(k.getKleur().equals(Kleur.ROOS))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
    		 arrayKoninkrijk[0][0] = new Vak(32,"bos", 0); 
			  arrayKoninkrijk[0][1] = new Vak(32,"water", 1); 
			  arrayKoninkrijk[0][2] = new Vak(42,"water", 0);
			  arrayKoninkrijk[0][3] = new Vak(30,"water", 1); 
			  arrayKoninkrijk[0][4] = new Vak(9,"water", 0);
			  
			  arrayKoninkrijk[1][0] = new Vak(29,"bos", 1); 
			  arrayKoninkrijk[1][1] = new Vak(29,"gras", 0); 
			  arrayKoninkrijk[1][2] = new Vak(42,"gras", 2);
			  arrayKoninkrijk[1][3] = new Vak(30,"akker", 0); 
			  arrayKoninkrijk[1][4] = new Vak(9,"water", 0);
			  
			  arrayKoninkrijk[2][0] = new Vak(27,"bos", 1); 
			  arrayKoninkrijk[2][1] = new Vak(27,"akker", 0); 
			  arrayKoninkrijk[2][3] = new Vak(48,"akker", 0);
			  arrayKoninkrijk[2][4] = new Vak(48,"mijn", 3);
			  
			  arrayKoninkrijk[3][0] = new Vak(20,"bos", 0); 
			  arrayKoninkrijk[3][1] = new Vak(20,"akker", 1); 
			  arrayKoninkrijk[3][2] = new Vak(22,"akker", 1);
			  arrayKoninkrijk[3][3] = new Vak(43,"akker", 0); 
			  arrayKoninkrijk[3][4] = new Vak(47,"mijn", 2);
			  
			  arrayKoninkrijk[4][0] = new Vak(2,"akker", 0); 
			  arrayKoninkrijk[4][1] = new Vak(2,"akker", 0); 
			  arrayKoninkrijk[4][2] = new Vak(22,"moeras", 0);
			  arrayKoninkrijk[4][3] = new Vak(43,"moeras", 2); 
			  arrayKoninkrijk[4][4] = new Vak(47,"moeras", 0);
			  k.breidKoninkrijkUit(arrayKoninkrijk);	
			  break;
		}
    	
		// Act
		Spel testSpel = new Spel(deelnemendeSpelers);
		testSpel.berekenScores();
		System.out.println(deelnemendeSpelers);

		// Assert
		assertEquals(54,deelnemendeSpelers.get(0).getScore());
    	}
	}

	@Test
	void berekenScore_SlechtsEenGebiedPerLandschapstypeEnBevatSteedsSlechtsEenKroon__VermeerderdScoreVanSpeler() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

		for(Koninkrijk k : koninkrijken)
    	{
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();

				arrayKoninkrijk[0][0] = new Vak(35, "bos", 0);
				arrayKoninkrijk[0][1] = new Vak(35, "water", 1);
				arrayKoninkrijk[0][2] = new Vak(37, "water", 0);
				arrayKoninkrijk[0][3] = new Vak(14, "water", 0);
				arrayKoninkrijk[0][4] = new Vak(9, "water", 0);

				arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
				arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);
				arrayKoninkrijk[1][2] = new Vak(37, "gras", 1);
				arrayKoninkrijk[1][3] = new Vak(14, "akker", 0);
				arrayKoninkrijk[1][4] = new Vak(9, "water", 0);

				arrayKoninkrijk[2][0] = new Vak(24, "bos", 1);
				arrayKoninkrijk[2][1] = new Vak(24, "akker", 0);
				arrayKoninkrijk[2][3] = new Vak(23, "akker", 1);
				arrayKoninkrijk[2][4] = new Vak(23, "mijn", 0);

				arrayKoninkrijk[4][0] = new Vak(13, "bos", 0);
				arrayKoninkrijk[4][1] = new Vak(13, "akker", 0);
				arrayKoninkrijk[4][2] = new Vak(38, "akker", 0);
				arrayKoninkrijk[4][3] = new Vak(40, "akker", 0);
				arrayKoninkrijk[4][4] = new Vak(40, "mijn", 1);

				arrayKoninkrijk[3][0] = new Vak(2, "akker", 0);
				arrayKoninkrijk[3][1] = new Vak(2, "akker", 0);
				arrayKoninkrijk[3][2] = new Vak(38, "moeras", 1);
				arrayKoninkrijk[3][3] = new Vak(12, "moeras", 0);
				arrayKoninkrijk[3][4] = new Vak(12, "moeras", 0);

				k.breidKoninkrijkUit(arrayKoninkrijk);
				// array wordt ingesteld naar gewenste situatie

				break;
			}
    		if(k.getKleur().equals(Kleur.BLAUW))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();

				arrayKoninkrijk[0][0] = new Vak(35, "bos", 0);
				arrayKoninkrijk[0][1] = new Vak(35, "water", 1);
				arrayKoninkrijk[0][2] = new Vak(37, "water", 0);
				arrayKoninkrijk[0][3] = new Vak(14, "water", 0);
				arrayKoninkrijk[0][4] = new Vak(9, "water", 0);

				arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
				arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);
				arrayKoninkrijk[1][2] = new Vak(37, "gras", 1);
				arrayKoninkrijk[1][3] = new Vak(14, "akker", 0);
				arrayKoninkrijk[1][4] = new Vak(9, "water", 0);

				arrayKoninkrijk[2][0] = new Vak(24, "bos", 1);
				arrayKoninkrijk[2][1] = new Vak(24, "akker", 0);
				arrayKoninkrijk[2][3] = new Vak(23, "akker", 1);
				arrayKoninkrijk[2][4] = new Vak(23, "mijn", 0);

				arrayKoninkrijk[4][0] = new Vak(13, "bos", 0);
				arrayKoninkrijk[4][1] = new Vak(13, "akker", 0);
				arrayKoninkrijk[4][2] = new Vak(38, "akker", 0);
				arrayKoninkrijk[4][3] = new Vak(40, "akker", 0);
				arrayKoninkrijk[4][4] = new Vak(40, "mijn", 1);

				arrayKoninkrijk[3][0] = new Vak(2, "akker", 0);
				arrayKoninkrijk[3][1] = new Vak(2, "akker", 0);
				arrayKoninkrijk[3][2] = new Vak(38, "moeras", 1);
				arrayKoninkrijk[3][3] = new Vak(12, "moeras", 0);
				arrayKoninkrijk[3][4] = new Vak(12, "moeras", 0);

				k.breidKoninkrijkUit(arrayKoninkrijk);
				// array wordt ingesteld naar gewenste situatie

				break;
			}
    		if(k.getKleur().equals(Kleur.ROOS))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();

				arrayKoninkrijk[0][0] = new Vak(35, "bos", 0);
				arrayKoninkrijk[0][1] = new Vak(35, "water", 1);
				arrayKoninkrijk[0][2] = new Vak(37, "water", 0);
				arrayKoninkrijk[0][3] = new Vak(14, "water", 0);
				arrayKoninkrijk[0][4] = new Vak(9, "water", 0);

				arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
				arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);
				arrayKoninkrijk[1][2] = new Vak(37, "gras", 1);
				arrayKoninkrijk[1][3] = new Vak(14, "akker", 0);
				arrayKoninkrijk[1][4] = new Vak(9, "water", 0);

				arrayKoninkrijk[2][0] = new Vak(24, "bos", 1);
				arrayKoninkrijk[2][1] = new Vak(24, "akker", 0);
				arrayKoninkrijk[2][3] = new Vak(23, "akker", 1);
				arrayKoninkrijk[2][4] = new Vak(23, "mijn", 0);

				arrayKoninkrijk[4][0] = new Vak(13, "bos", 0);
				arrayKoninkrijk[4][1] = new Vak(13, "akker", 0);
				arrayKoninkrijk[4][2] = new Vak(38, "akker", 0);
				arrayKoninkrijk[4][3] = new Vak(40, "akker", 0);
				arrayKoninkrijk[4][4] = new Vak(40, "mijn", 1);

				arrayKoninkrijk[3][0] = new Vak(2, "akker", 0);
				arrayKoninkrijk[3][1] = new Vak(2, "akker", 0);
				arrayKoninkrijk[3][2] = new Vak(38, "moeras", 1);
				arrayKoninkrijk[3][3] = new Vak(12, "moeras", 0);
				arrayKoninkrijk[3][4] = new Vak(12, "moeras", 0);

				k.breidKoninkrijkUit(arrayKoninkrijk);
				// array wordt ingesteld naar gewenste situatie

				break;
			}
		
    		// Act
    		Spel testSpel = new Spel(deelnemendeSpelers);
    		testSpel.berekenScores();
    		System.out.println(deelnemendeSpelers);


		// Assert
		assertEquals(24, deelnemendeSpelers.get(0).getScore());
		}
	}
	

	@Test
	void berekenScore_SlechtsEenGebiedPerLandschapstypeEnBevatGeenKronen_VermeerderdScoreVanSpeler() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

		for(Koninkrijk k : koninkrijken)
    	{
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();

				arrayKoninkrijk[0][0] = new Vak(3, "bos", 0);
				arrayKoninkrijk[0][1] = new Vak(3, "bos", 0);
				arrayKoninkrijk[0][2] = new Vak(11, "gras", 0);
				arrayKoninkrijk[0][3] = new Vak(7, "water", 0);
				arrayKoninkrijk[0][4] = new Vak(8, "water", 0);

				arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
				arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);
				arrayKoninkrijk[1][2] = new Vak(11, "gras", 0);
				arrayKoninkrijk[1][3] = new Vak(7, "water", 0);
				arrayKoninkrijk[1][4] = new Vak(8, "water", 0);

				arrayKoninkrijk[2][0] = new Vak(5, "bos", 0);
				arrayKoninkrijk[2][1] = new Vak(5, "bos", 0);
				arrayKoninkrijk[2][3] = new Vak(14, "akker", 0);
				arrayKoninkrijk[2][4] = new Vak(14, "water", 0);

				arrayKoninkrijk[3][0] = new Vak(13, "bos", 0);
				arrayKoninkrijk[3][1] = new Vak(13, "akker", 0);
				arrayKoninkrijk[3][2] = new Vak(16, "akker", 0);
				arrayKoninkrijk[3][3] = new Vak(2, "akker", 0);
				arrayKoninkrijk[3][4] = new Vak(2, "akker", 0);

				arrayKoninkrijk[4][0] = new Vak(1, "akker", 0);
				arrayKoninkrijk[4][1] = new Vak(1, "akker", 0);
				arrayKoninkrijk[4][2] = new Vak(16, "moeras", 0);
				arrayKoninkrijk[4][3] = new Vak(12, "moeras", 0);
				arrayKoninkrijk[4][4] = new Vak(12, "moeras", 0);

				k.breidKoninkrijkUit(arrayKoninkrijk);
				// array wordt ingesteld naar gewenste situatie

				break;
			}
    		if(k.getKleur().equals(Kleur.BLAUW))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();

				arrayKoninkrijk[0][0] = new Vak(3, "bos", 0);
				arrayKoninkrijk[0][1] = new Vak(3, "bos", 0);
				arrayKoninkrijk[0][2] = new Vak(11, "gras", 0);
				arrayKoninkrijk[0][3] = new Vak(7, "water", 0);
				arrayKoninkrijk[0][4] = new Vak(8, "water", 0);

				arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
				arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);
				arrayKoninkrijk[1][2] = new Vak(11, "gras", 0);
				arrayKoninkrijk[1][3] = new Vak(7, "water", 0);
				arrayKoninkrijk[1][4] = new Vak(8, "water", 0);

				arrayKoninkrijk[2][0] = new Vak(5, "bos", 0);
				arrayKoninkrijk[2][1] = new Vak(5, "bos", 0);
				arrayKoninkrijk[2][3] = new Vak(14, "akker", 0);
				arrayKoninkrijk[2][4] = new Vak(14, "water", 0);

				arrayKoninkrijk[3][0] = new Vak(13, "bos", 0);
				arrayKoninkrijk[3][1] = new Vak(13, "akker", 0);
				arrayKoninkrijk[3][2] = new Vak(16, "akker", 0);
				arrayKoninkrijk[3][3] = new Vak(2, "akker", 0);
				arrayKoninkrijk[3][4] = new Vak(2, "akker", 0);

				arrayKoninkrijk[4][0] = new Vak(1, "akker", 0);
				arrayKoninkrijk[4][1] = new Vak(1, "akker", 0);
				arrayKoninkrijk[4][2] = new Vak(16, "moeras", 0);
				arrayKoninkrijk[4][3] = new Vak(12, "moeras", 0);
				arrayKoninkrijk[4][4] = new Vak(12, "moeras", 0);

				k.breidKoninkrijkUit(arrayKoninkrijk);
				// array wordt ingesteld naar gewenste situatie

				break;
			}
    		if(k.getKleur().equals(Kleur.ROOS))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();

				arrayKoninkrijk[0][0] = new Vak(3, "bos", 0);
				arrayKoninkrijk[0][1] = new Vak(3, "bos", 0);
				arrayKoninkrijk[0][2] = new Vak(11, "gras", 0);
				arrayKoninkrijk[0][3] = new Vak(7, "water", 0);
				arrayKoninkrijk[0][4] = new Vak(8, "water", 0);

				arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
				arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);
				arrayKoninkrijk[1][2] = new Vak(11, "gras", 0);
				arrayKoninkrijk[1][3] = new Vak(7, "water", 0);
				arrayKoninkrijk[1][4] = new Vak(8, "water", 0);

				arrayKoninkrijk[2][0] = new Vak(5, "bos", 0);
				arrayKoninkrijk[2][1] = new Vak(5, "bos", 0);
				arrayKoninkrijk[2][3] = new Vak(14, "akker", 0);
				arrayKoninkrijk[2][4] = new Vak(14, "water", 0);

				arrayKoninkrijk[3][0] = new Vak(13, "bos", 0);
				arrayKoninkrijk[3][1] = new Vak(13, "akker", 0);
				arrayKoninkrijk[3][2] = new Vak(16, "akker", 0);
				arrayKoninkrijk[3][3] = new Vak(2, "akker", 0);
				arrayKoninkrijk[3][4] = new Vak(2, "akker", 0);

				arrayKoninkrijk[4][0] = new Vak(1, "akker", 0);
				arrayKoninkrijk[4][1] = new Vak(1, "akker", 0);
				arrayKoninkrijk[4][2] = new Vak(16, "moeras", 0);
				arrayKoninkrijk[4][3] = new Vak(12, "moeras", 0);
				arrayKoninkrijk[4][4] = new Vak(12, "moeras", 0);

				k.breidKoninkrijkUit(arrayKoninkrijk);
				// array wordt ingesteld naar gewenste situatie

				break;
			}

    		// Act
    		Spel testSpel = new Spel(deelnemendeSpelers);
    		testSpel.berekenScores();
    		System.out.println(deelnemendeSpelers);

		// Assert
		assertEquals(0, deelnemendeSpelers.get(0).getScore());
    	}
	}

	@Test
	void berekenScore_SlechtsEenGebiedPerLandschapstypeEnBevatSomsEenKroon_VermeerderdScoreVanSpeler() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

		for(Koninkrijk k : koninkrijken)
    	{
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();

				arrayKoninkrijk[0][0] = new Vak(3, "bos", 0);
				arrayKoninkrijk[0][1] = new Vak(3, "bos", 0);
				arrayKoninkrijk[0][2] = new Vak(11, "water", 0);
				arrayKoninkrijk[0][3] = new Vak(7, "water", 0);
				arrayKoninkrijk[0][4] = new Vak(8, "water", 0);

				arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
				arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);
				arrayKoninkrijk[1][2] = new Vak(11, "gras", 0);
				arrayKoninkrijk[1][3] = new Vak(7, "water", 0);
				arrayKoninkrijk[1][4] = new Vak(8, "water", 0);

				arrayKoninkrijk[2][0] = new Vak(20, "bos", 0);
				arrayKoninkrijk[2][1] = new Vak(20, "akker", 1);
				arrayKoninkrijk[2][3] = new Vak(14, "akker", 0);
				arrayKoninkrijk[2][4] = new Vak(14, "water", 0);

				arrayKoninkrijk[3][0] = new Vak(13, "bos", 0);
				arrayKoninkrijk[3][1] = new Vak(13, "akker", 0);
				arrayKoninkrijk[3][2] = new Vak(16, "akker", 0);
				arrayKoninkrijk[3][3] = new Vak(2, "akker", 0);
				arrayKoninkrijk[3][4] = new Vak(2, "akker", 0);

				arrayKoninkrijk[4][0] = new Vak(24, "bos", 1);
				arrayKoninkrijk[4][1] = new Vak(24, "akker", 0);
				arrayKoninkrijk[4][2] = new Vak(16, "moeras", 0);
				arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
				arrayKoninkrijk[4][4] = new Vak(43, "akker", 0);

				k.breidKoninkrijkUit(arrayKoninkrijk);
				// array wordt ingesteld naar gewenste situatie
				break;
			}
    		if(k.getKleur().equals(Kleur.BLAUW))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();

				arrayKoninkrijk[0][0] = new Vak(3, "bos", 0);
				arrayKoninkrijk[0][1] = new Vak(3, "bos", 0);
				arrayKoninkrijk[0][2] = new Vak(11, "water", 0);
				arrayKoninkrijk[0][3] = new Vak(7, "water", 0);
				arrayKoninkrijk[0][4] = new Vak(8, "water", 0);

				arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
				arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);
				arrayKoninkrijk[1][2] = new Vak(11, "gras", 0);
				arrayKoninkrijk[1][3] = new Vak(7, "water", 0);
				arrayKoninkrijk[1][4] = new Vak(8, "water", 0);

				arrayKoninkrijk[2][0] = new Vak(20, "bos", 0);
				arrayKoninkrijk[2][1] = new Vak(20, "akker", 1);
				arrayKoninkrijk[2][3] = new Vak(14, "akker", 0);
				arrayKoninkrijk[2][4] = new Vak(14, "water", 0);

				arrayKoninkrijk[3][0] = new Vak(13, "bos", 0);
				arrayKoninkrijk[3][1] = new Vak(13, "akker", 0);
				arrayKoninkrijk[3][2] = new Vak(16, "akker", 0);
				arrayKoninkrijk[3][3] = new Vak(2, "akker", 0);
				arrayKoninkrijk[3][4] = new Vak(2, "akker", 0);

				arrayKoninkrijk[4][0] = new Vak(24, "bos", 1);
				arrayKoninkrijk[4][1] = new Vak(24, "akker", 0);
				arrayKoninkrijk[4][2] = new Vak(16, "moeras", 0);
				arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
				arrayKoninkrijk[4][4] = new Vak(43, "akker", 0);

				k.breidKoninkrijkUit(arrayKoninkrijk);
				// array wordt ingesteld naar gewenste situatie
				break;
			}
    		if(k.getKleur().equals(Kleur.ROOS))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();

				arrayKoninkrijk[0][0] = new Vak(3, "bos", 0);
				arrayKoninkrijk[0][1] = new Vak(3, "bos", 0);
				arrayKoninkrijk[0][2] = new Vak(11, "water", 0);
				arrayKoninkrijk[0][3] = new Vak(7, "water", 0);
				arrayKoninkrijk[0][4] = new Vak(8, "water", 0);

				arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
				arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);
				arrayKoninkrijk[1][2] = new Vak(11, "gras", 0);
				arrayKoninkrijk[1][3] = new Vak(7, "water", 0);
				arrayKoninkrijk[1][4] = new Vak(8, "water", 0);

				arrayKoninkrijk[2][0] = new Vak(20, "bos", 0);
				arrayKoninkrijk[2][1] = new Vak(20, "akker", 1);
				arrayKoninkrijk[2][3] = new Vak(14, "akker", 0);
				arrayKoninkrijk[2][4] = new Vak(14, "water", 0);

				arrayKoninkrijk[3][0] = new Vak(13, "bos", 0);
				arrayKoninkrijk[3][1] = new Vak(13, "akker", 0);
				arrayKoninkrijk[3][2] = new Vak(16, "akker", 0);
				arrayKoninkrijk[3][3] = new Vak(2, "akker", 0);
				arrayKoninkrijk[3][4] = new Vak(2, "akker", 0);

				arrayKoninkrijk[4][0] = new Vak(24, "bos", 1);
				arrayKoninkrijk[4][1] = new Vak(24, "akker", 0);
				arrayKoninkrijk[4][2] = new Vak(16, "moeras", 0);
				arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
				arrayKoninkrijk[4][4] = new Vak(43, "akker", 0);

				k.breidKoninkrijkUit(arrayKoninkrijk);
				// array wordt ingesteld naar gewenste situatie
				break;
			}
		

    		// Act
    		Spel testSpel = new Spel(deelnemendeSpelers);
    		testSpel.berekenScores();
    		System.out.println(deelnemendeSpelers);

		// Assert
		assertEquals(18, deelnemendeSpelers.get(0).getScore());
    	}
	}

	@Test
	void berekenScore_MeerdereGebiedenPerLandschapstypeEnBevatMeerdereKronen_VermeerderdScoreVanSpeler() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

		for(Koninkrijk k : koninkrijken)
    	{
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
    	arrayKoninkrijk[0][0] = new Vak(3, "bos", 0);
		arrayKoninkrijk[0][1] = new Vak(3, "bos", 0);
		arrayKoninkrijk[0][2] = new Vak(11, "water", 0);
		arrayKoninkrijk[0][3] = new Vak(7, "water", 0);
		arrayKoninkrijk[0][4] = new Vak(8, "water", 0);

		arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
		arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);
		arrayKoninkrijk[1][2] = new Vak(11, "gras", 0);
		arrayKoninkrijk[1][3] = new Vak(7, "water", 0);
		arrayKoninkrijk[1][4] = new Vak(8, "water", 0);

		arrayKoninkrijk[2][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[2][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[2][3] = new Vak(14, "akker", 0);
		arrayKoninkrijk[2][4] = new Vak(14, "water", 0);

		arrayKoninkrijk[3][0] = new Vak(13, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(13, "akker", 0);
		arrayKoninkrijk[3][2] = new Vak(16, "akker", 0);
		arrayKoninkrijk[3][3] = new Vak(2, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(2, "akker", 0);

		arrayKoninkrijk[4][0] = new Vak(24, "bos", 1);
		arrayKoninkrijk[4][1] = new Vak(24, "akker", 0);
		arrayKoninkrijk[4][2] = new Vak(16, "moeras", 0);
		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(43, "akker", 0);
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
    		}
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
    	arrayKoninkrijk[0][0] = new Vak(3, "bos", 0);
		arrayKoninkrijk[0][1] = new Vak(3, "bos", 0);
		arrayKoninkrijk[0][2] = new Vak(11, "water", 0);
		arrayKoninkrijk[0][3] = new Vak(7, "water", 0);
		arrayKoninkrijk[0][4] = new Vak(8, "water", 0);

		arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
		arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);
		arrayKoninkrijk[1][2] = new Vak(11, "gras", 0);
		arrayKoninkrijk[1][3] = new Vak(7, "water", 0);
		arrayKoninkrijk[1][4] = new Vak(8, "water", 0);

		arrayKoninkrijk[2][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[2][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[2][3] = new Vak(14, "akker", 0);
		arrayKoninkrijk[2][4] = new Vak(14, "water", 0);

		arrayKoninkrijk[3][0] = new Vak(13, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(13, "akker", 0);
		arrayKoninkrijk[3][2] = new Vak(16, "akker", 0);
		arrayKoninkrijk[3][3] = new Vak(2, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(2, "akker", 0);

		arrayKoninkrijk[4][0] = new Vak(24, "bos", 1);
		arrayKoninkrijk[4][1] = new Vak(24, "akker", 0);
		arrayKoninkrijk[4][2] = new Vak(16, "moeras", 0);
		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(43, "akker", 0);
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
    		}
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
    	arrayKoninkrijk[0][0] = new Vak(3, "bos", 0);
		arrayKoninkrijk[0][1] = new Vak(3, "bos", 0);
		arrayKoninkrijk[0][2] = new Vak(11, "water", 0);
		arrayKoninkrijk[0][3] = new Vak(7, "water", 0);
		arrayKoninkrijk[0][4] = new Vak(8, "water", 0);

		arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
		arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);
		arrayKoninkrijk[1][2] = new Vak(11, "gras", 0);
		arrayKoninkrijk[1][3] = new Vak(7, "water", 0);
		arrayKoninkrijk[1][4] = new Vak(8, "water", 0);

		arrayKoninkrijk[2][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[2][1] = new Vak(20, "akker", 2);
		arrayKoninkrijk[2][3] = new Vak(14, "akker", 0);
		arrayKoninkrijk[2][4] = new Vak(14, "water", 0);

		arrayKoninkrijk[3][0] = new Vak(13, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(13, "akker", 0);
		arrayKoninkrijk[3][2] = new Vak(16, "akker", 0);
		arrayKoninkrijk[3][3] = new Vak(2, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(2, "akker", 0);

		arrayKoninkrijk[4][0] = new Vak(24, "bos", 2);
		arrayKoninkrijk[4][1] = new Vak(24, "akker", 0);
		arrayKoninkrijk[4][2] = new Vak(16, "moeras", 0);
		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(43, "akker", 0);
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
    		}
    		

    		// Act
    		Spel testSpel = new Spel(deelnemendeSpelers);
    		testSpel.berekenScores();
    		System.out.println(deelnemendeSpelers);

		// Assert
		assertEquals(36, deelnemendeSpelers.get(0).getScore());
    	}
	}
	
	@Test
	void berekenScore_KoninkrijkHeeftMeerdereLegeVakken_VermeerderdScoreVanSpeler() {
	List<Speler> deelnemendeSpelers = new ArrayList<>();
    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

	for(Koninkrijk k : koninkrijken)
	{
		if(k.getKleur().equals(Kleur.GEEL))
		{
	arrayKoninkrijk = k.getArrayKoninkrijk();
	arrayKoninkrijk[0][0] = new Vak(3, "bos", 0);
	arrayKoninkrijk[0][1] = new Vak(3, "bos", 0);


	arrayKoninkrijk[0][4] = new Vak(8, "water", 0);

	arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
	arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);


	arrayKoninkrijk[1][4] = new Vak(8, "water", 0);

	arrayKoninkrijk[2][0] = new Vak(20, "bos", 0);
	arrayKoninkrijk[2][1] = new Vak(20, "akker", 1);
	arrayKoninkrijk[2][3] = new Vak(14, "akker", 0);
	arrayKoninkrijk[2][4] = new Vak(14, "water", 0);

	arrayKoninkrijk[3][0] = new Vak(13, "bos", 0);
	arrayKoninkrijk[3][1] = new Vak(13, "akker", 0);
	arrayKoninkrijk[3][2] = new Vak(16, "akker", 0);
	arrayKoninkrijk[3][3] = new Vak(2, "akker", 0);
	arrayKoninkrijk[3][4] = new Vak(2, "akker", 0);

	arrayKoninkrijk[4][0] = new Vak(24, "bos", 1);
	arrayKoninkrijk[4][1] = new Vak(24, "akker", 0);
	arrayKoninkrijk[4][2] = new Vak(16, "moeras", 0);
	arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
	arrayKoninkrijk[4][4] = new Vak(43, "akker", 0);
	k.breidKoninkrijkUit(arrayKoninkrijk);
	// array wordt ingesteld naar gewenste situatie
	break;
		}
		if(k.getKleur().equals(Kleur.GEEL))
		{
	arrayKoninkrijk = k.getArrayKoninkrijk();
	arrayKoninkrijk[0][0] = new Vak(3, "bos", 0);
	arrayKoninkrijk[0][1] = new Vak(3, "bos", 0);


	arrayKoninkrijk[0][4] = new Vak(8, "water", 0);

	arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
	arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);


	arrayKoninkrijk[1][4] = new Vak(8, "water", 0);

	arrayKoninkrijk[2][0] = new Vak(20, "bos", 0);
	arrayKoninkrijk[2][1] = new Vak(20, "akker", 1);
	arrayKoninkrijk[2][3] = new Vak(14, "akker", 0);
	arrayKoninkrijk[2][4] = new Vak(14, "water", 0);

	arrayKoninkrijk[3][0] = new Vak(13, "bos", 0);
	arrayKoninkrijk[3][1] = new Vak(13, "akker", 0);
	arrayKoninkrijk[3][2] = new Vak(16, "akker", 0);
	arrayKoninkrijk[3][3] = new Vak(2, "akker", 0);
	arrayKoninkrijk[3][4] = new Vak(2, "akker", 0);

	arrayKoninkrijk[4][0] = new Vak(24, "bos", 1);
	arrayKoninkrijk[4][1] = new Vak(24, "akker", 0);
	arrayKoninkrijk[4][2] = new Vak(16, "moeras", 0);
	arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
	arrayKoninkrijk[4][4] = new Vak(43, "akker", 0);
	k.breidKoninkrijkUit(arrayKoninkrijk);
	// array wordt ingesteld naar gewenste situatie
	break;
		}
		if(k.getKleur().equals(Kleur.GEEL))
		{
	arrayKoninkrijk = k.getArrayKoninkrijk();
	arrayKoninkrijk[0][0] = new Vak(3, "bos", 0);
	arrayKoninkrijk[0][1] = new Vak(3, "bos", 0);


	arrayKoninkrijk[0][4] = new Vak(8, "water", 0);

	arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
	arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);


	arrayKoninkrijk[1][4] = new Vak(8, "water", 0);

	arrayKoninkrijk[2][0] = new Vak(20, "bos", 0);
	arrayKoninkrijk[2][1] = new Vak(20, "akker", 1);
	arrayKoninkrijk[2][3] = new Vak(14, "akker", 0);
	arrayKoninkrijk[2][4] = new Vak(14, "water", 0);

	arrayKoninkrijk[3][0] = new Vak(13, "bos", 0);
	arrayKoninkrijk[3][1] = new Vak(13, "akker", 0);
	arrayKoninkrijk[3][2] = new Vak(16, "akker", 0);
	arrayKoninkrijk[3][3] = new Vak(2, "akker", 0);
	arrayKoninkrijk[3][4] = new Vak(2, "akker", 0);

	arrayKoninkrijk[4][0] = new Vak(24, "bos", 1);
	arrayKoninkrijk[4][1] = new Vak(24, "akker", 0);
	arrayKoninkrijk[4][2] = new Vak(16, "moeras", 0);
	arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
	arrayKoninkrijk[4][4] = new Vak(43, "akker", 0);
	k.breidKoninkrijkUit(arrayKoninkrijk);
	// array wordt ingesteld naar gewenste situatie
	break;
		}
		

		// Act
		Spel testSpel = new Spel(deelnemendeSpelers);
		testSpel.berekenScores();
		System.out.println(deelnemendeSpelers);

	// Assert
	assertEquals(18, deelnemendeSpelers.get(0).getScore());
	}
	}

	@Test
	void berekenScore_KoninkrijkHeeftTweeLegeVakken_VermeerderdScoreVanSpeler() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

		for(Koninkrijk k : koninkrijken)
		{
			if(k.getKleur().equals(Kleur.GEEL))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[0][0] = new Vak(3, "bos", 0);
		arrayKoninkrijk[0][1] = new Vak(3, "bos", 0);

		arrayKoninkrijk[0][3] = new Vak(7, "water", 0);
		arrayKoninkrijk[0][4] = new Vak(8, "water", 0);

		arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
		arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);

		arrayKoninkrijk[1][3] = new Vak(7, "water", 0);
		arrayKoninkrijk[1][4] = new Vak(8, "water", 0);

		arrayKoninkrijk[2][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[2][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[2][3] = new Vak(14, "akker", 0);
		arrayKoninkrijk[2][4] = new Vak(14, "water", 0);

		arrayKoninkrijk[3][0] = new Vak(13, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(13, "akker", 0);
		arrayKoninkrijk[3][2] = new Vak(16, "akker", 0);
		arrayKoninkrijk[3][3] = new Vak(2, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(2, "akker", 0);

		arrayKoninkrijk[4][0] = new Vak(24, "bos", 1);
		arrayKoninkrijk[4][1] = new Vak(24, "akker", 0);
		arrayKoninkrijk[4][2] = new Vak(16, "moeras", 0);
		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(43, "akker", 0);
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.GEEL))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[0][0] = new Vak(3, "bos", 0);
		arrayKoninkrijk[0][1] = new Vak(3, "bos", 0);

		arrayKoninkrijk[0][3] = new Vak(7, "water", 0);
		arrayKoninkrijk[0][4] = new Vak(8, "water", 0);

		arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
		arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);

		arrayKoninkrijk[1][3] = new Vak(7, "water", 0);
		arrayKoninkrijk[1][4] = new Vak(8, "water", 0);

		arrayKoninkrijk[2][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[2][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[2][3] = new Vak(14, "akker", 0);
		arrayKoninkrijk[2][4] = new Vak(14, "water", 0);

		arrayKoninkrijk[3][0] = new Vak(13, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(13, "akker", 0);
		arrayKoninkrijk[3][2] = new Vak(16, "akker", 0);
		arrayKoninkrijk[3][3] = new Vak(2, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(2, "akker", 0);

		arrayKoninkrijk[4][0] = new Vak(24, "bos", 1);
		arrayKoninkrijk[4][1] = new Vak(24, "akker", 0);
		arrayKoninkrijk[4][2] = new Vak(16, "moeras", 0);
		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(43, "akker", 0);
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.GEEL))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[0][0] = new Vak(3, "bos", 0);
		arrayKoninkrijk[0][1] = new Vak(3, "bos", 0);

		arrayKoninkrijk[0][3] = new Vak(7, "water", 0);
		arrayKoninkrijk[0][4] = new Vak(8, "water", 0);

		arrayKoninkrijk[1][0] = new Vak(18, "bos", 0);
		arrayKoninkrijk[1][1] = new Vak(18, "gras", 0);

		arrayKoninkrijk[1][3] = new Vak(7, "water", 0);
		arrayKoninkrijk[1][4] = new Vak(8, "water", 0);

		arrayKoninkrijk[2][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[2][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[2][3] = new Vak(14, "akker", 0);
		arrayKoninkrijk[2][4] = new Vak(14, "water", 0);

		arrayKoninkrijk[3][0] = new Vak(13, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(13, "akker", 0);
		arrayKoninkrijk[3][2] = new Vak(16, "akker", 0);
		arrayKoninkrijk[3][3] = new Vak(2, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(2, "akker", 0);

		arrayKoninkrijk[4][0] = new Vak(24, "bos", 1);
		arrayKoninkrijk[4][1] = new Vak(24, "akker", 0);
		arrayKoninkrijk[4][2] = new Vak(16, "moeras", 0);
		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(43, "akker", 0);
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			

			// Act
			Spel testSpel = new Spel(deelnemendeSpelers);
			testSpel.berekenScores();
			System.out.println(deelnemendeSpelers);

		// Assert
		assertEquals(18, deelnemendeSpelers.get(0).getScore());
		}
		}


	// Maxe

	@Test
	void plaatsDominotegel_EersteTegelNietAanStarttegel_throwsEersteTegel() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));
	   
	    for(Koninkrijk k : koninkrijken)
		{
			if(k.getKleur().equals(Kleur.GEEL))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.BLAUW))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.ROOS))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
		    Spel testSpel = new Spel(deelnemendeSpelers);
		    assertThrows(IllegalArgumentException.class, () ->testSpel.plaatsDominotegel(kleur, 1, 1, 1, 2));
		}

	}

	@Test
	void plaatsDominotegel_vakkenNietNaastElkaarGeplaatst_throwsNietNaastElkaar() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));
	   
	    for(Koninkrijk k : koninkrijken)
		{
			if(k.getKleur().equals(Kleur.GEEL))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.BLAUW))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.ROOS))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
		    Spel testSpel = new Spel(deelnemendeSpelers);
		assertThrows(IllegalArgumentException.class, () -> testSpel.plaatsDominotegel(kleur, 3, 2, 5, 5));
		}
	}

	@Test
	void plaatsDominotegel_vakNietLeegWaarGeplaatstWordt_throwsAlTegelOpPlaats() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));
	   
	    for(Koninkrijk k : koninkrijken)
		{
			if(k.getKleur().equals(Kleur.GEEL))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.BLAUW))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.ROOS))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
		    Spel testSpel = new Spel(deelnemendeSpelers);
		assertThrows(IllegalArgumentException.class, () -> testSpel.plaatsDominotegel(kleur, 3, 2, 2, 2));
		}
	}

	@Test
	void plaatsDominotegel_vakjesOpDezelfdePlaatsPlaatsen_throwsZelfdeVak() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));
	   
	    for(Koninkrijk k : koninkrijken)
		{
			if(k.getKleur().equals(Kleur.GEEL))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.BLAUW))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.ROOS))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
		    Spel testSpel = new Spel(deelnemendeSpelers);
		assertThrows(IllegalArgumentException.class, () -> testSpel.plaatsDominotegel(kleur, 2, 2, 2, 2));
		}
	}

	@Test
	void plaatsDominotegel_vakjesDiagonaalPlaatsen_throwsNietDiagonaal() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));
	   
	    for(Koninkrijk k : koninkrijken)
		{
			if(k.getKleur().equals(Kleur.GEEL))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.BLAUW))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.ROOS))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
		    Spel testSpel = new Spel(deelnemendeSpelers);
		assertThrows(IllegalArgumentException.class, () -> testSpel.plaatsDominotegel(kleur, 3, 2, 2, 4));
		}
	}

	@Test
	void plaatsDominotegel_nietAangrenzendAanAndereTegel_throwsAangrenzendeTypes() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));
	   
	    for(Koninkrijk k : koninkrijken)
		{
			if(k.getKleur().equals(Kleur.GEEL))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.BLAUW))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.ROOS))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
		    Spel testSpel = new Spel(deelnemendeSpelers);
		assertThrows(IllegalArgumentException.class, () -> testSpel.plaatsDominotegel(kleur, 1, 1, 1, 2));
		}
	}

	@Test
	void plaatsDominotegel_AangrenzendAanTegelMaarNietLandschapstype_throwsAangrenzendeTypes() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));
	   
	    for(Koninkrijk k : koninkrijken)
		{
			if(k.getKleur().equals(Kleur.GEEL))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.BLAUW))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.ROOS))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
		    Spel testSpel = new Spel(deelnemendeSpelers);
		assertThrows(IllegalArgumentException.class, () -> testSpel.plaatsDominotegel(kleur, 0, 0, 1, 0));
		}
	}

	@Test
	void zijnErNogMogelijkeZetten_geenPlaatsVolGrid_returntFalse() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

		for(Koninkrijk k : koninkrijken)
    	{
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[0][0] = new Vak(32, "bos", 0);
		arrayKoninkrijk[0][1] = new Vak(32, "water", 1);
		arrayKoninkrijk[0][2] = new Vak(42, "water", 0);
		arrayKoninkrijk[0][3] = new Vak(30, "water", 1);
		arrayKoninkrijk[0][4] = new Vak(9, "water", 0);

		arrayKoninkrijk[1][0] = new Vak(29, "bos", 1);
		arrayKoninkrijk[1][1] = new Vak(29, "gras", 0);
		arrayKoninkrijk[1][2] = new Vak(42, "gras", 2);
		arrayKoninkrijk[1][3] = new Vak(30, "akker", 0);
		arrayKoninkrijk[1][4] = new Vak(9, "water", 0);

		arrayKoninkrijk[2][0] = new Vak(27, "bos", 1);
		arrayKoninkrijk[2][1] = new Vak(27, "akker", 0);
		arrayKoninkrijk[2][3] = new Vak(48, "akker", 0);
		arrayKoninkrijk[2][4] = new Vak(48, "mijn", 3);

		arrayKoninkrijk[3][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[3][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[4][0] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][2] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(47, "moeras", 0);
		// returnt false
		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
    		if(k.getKleur().equals(Kleur.BLAUW))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[0][0] = new Vak(32, "bos", 0);
		arrayKoninkrijk[0][1] = new Vak(32, "water", 1);
		arrayKoninkrijk[0][2] = new Vak(42, "water", 0);
		arrayKoninkrijk[0][3] = new Vak(30, "water", 1);
		arrayKoninkrijk[0][4] = new Vak(9, "water", 0);

		arrayKoninkrijk[1][0] = new Vak(29, "bos", 1);
		arrayKoninkrijk[1][1] = new Vak(29, "gras", 0);
		arrayKoninkrijk[1][2] = new Vak(42, "gras", 2);
		arrayKoninkrijk[1][3] = new Vak(30, "akker", 0);
		arrayKoninkrijk[1][4] = new Vak(9, "water", 0);

		arrayKoninkrijk[2][0] = new Vak(27, "bos", 1);
		arrayKoninkrijk[2][1] = new Vak(27, "akker", 0);
		arrayKoninkrijk[2][3] = new Vak(48, "akker", 0);
		arrayKoninkrijk[2][4] = new Vak(48, "mijn", 3);

		arrayKoninkrijk[3][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[3][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[4][0] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][2] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(47, "moeras", 0);
		// returnt false
		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
    		if(k.getKleur().equals(Kleur.ROOS))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[0][0] = new Vak(32, "bos", 0);
		arrayKoninkrijk[0][1] = new Vak(32, "water", 1);
		arrayKoninkrijk[0][2] = new Vak(42, "water", 0);
		arrayKoninkrijk[0][3] = new Vak(30, "water", 1);
		arrayKoninkrijk[0][4] = new Vak(9, "water", 0);

		arrayKoninkrijk[1][0] = new Vak(29, "bos", 1);
		arrayKoninkrijk[1][1] = new Vak(29, "gras", 0);
		arrayKoninkrijk[1][2] = new Vak(42, "gras", 2);
		arrayKoninkrijk[1][3] = new Vak(30, "akker", 0);
		arrayKoninkrijk[1][4] = new Vak(9, "water", 0);

		arrayKoninkrijk[2][0] = new Vak(27, "bos", 1);
		arrayKoninkrijk[2][1] = new Vak(27, "akker", 0);
		arrayKoninkrijk[2][3] = new Vak(48, "akker", 0);
		arrayKoninkrijk[2][4] = new Vak(48, "mijn", 3);

		arrayKoninkrijk[3][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[3][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[4][0] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][2] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(47, "moeras", 0);
		// returnt false
		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
    		Spel testSpel = new Spel(deelnemendeSpelers);
		Assertions.assertFalse(testSpel.zijnErNogMogelijkeZetten(kleur));
    	}
	}

	@Test
	void zijnErNogMogelijkeZetten_geenPlaatsVakjesDieNietNaastElkaarLiggen_returntFalse() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

		for(Koninkrijk k : koninkrijken)
    	{
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[0][0] = new Vak(32, "bos", 0);
		arrayKoninkrijk[0][1] = new Vak(32, "water", 1);

		arrayKoninkrijk[0][3] = new Vak(30, "water", 1);
		arrayKoninkrijk[0][4] = new Vak(9, "water", 0);

		arrayKoninkrijk[1][0] = new Vak(29, "bos", 1);
		arrayKoninkrijk[1][1] = new Vak(29, "gras", 0);
		arrayKoninkrijk[1][2] = new Vak(42, "gras", 2);
		arrayKoninkrijk[1][3] = new Vak(30, "akker", 0);
		arrayKoninkrijk[1][4] = new Vak(9, "water", 0);

		arrayKoninkrijk[2][0] = new Vak(27, "bos", 1);
		arrayKoninkrijk[2][1] = new Vak(27, "akker", 0);
		arrayKoninkrijk[2][3] = new Vak(48, "akker", 0);
		arrayKoninkrijk[2][4] = new Vak(48, "mijn", 3);

		arrayKoninkrijk[3][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[3][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[4][0] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][1] = new Vak(2, "akker", 0);

		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(47, "moeras", 0);
		// returnt false
		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
    		if(k.getKleur().equals(Kleur.BLAUW))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[0][0] = new Vak(32, "bos", 0);
		arrayKoninkrijk[0][1] = new Vak(32, "water", 1);

		arrayKoninkrijk[0][3] = new Vak(30, "water", 1);
		arrayKoninkrijk[0][4] = new Vak(9, "water", 0);

		arrayKoninkrijk[1][0] = new Vak(29, "bos", 1);
		arrayKoninkrijk[1][1] = new Vak(29, "gras", 0);
		arrayKoninkrijk[1][2] = new Vak(42, "gras", 2);
		arrayKoninkrijk[1][3] = new Vak(30, "akker", 0);
		arrayKoninkrijk[1][4] = new Vak(9, "water", 0);

		arrayKoninkrijk[2][0] = new Vak(27, "bos", 1);
		arrayKoninkrijk[2][1] = new Vak(27, "akker", 0);
		arrayKoninkrijk[2][3] = new Vak(48, "akker", 0);
		arrayKoninkrijk[2][4] = new Vak(48, "mijn", 3);

		arrayKoninkrijk[3][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[3][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[4][0] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][1] = new Vak(2, "akker", 0);

		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(47, "moeras", 0);
		// returnt false
		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
    		if(k.getKleur().equals(Kleur.ROOS))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[0][0] = new Vak(32, "bos", 0);
		arrayKoninkrijk[0][1] = new Vak(32, "water", 1);
		
		arrayKoninkrijk[0][3] = new Vak(30, "water", 1);
		arrayKoninkrijk[0][4] = new Vak(9, "water", 0);

		arrayKoninkrijk[1][0] = new Vak(29, "bos", 1);
		arrayKoninkrijk[1][1] = new Vak(29, "gras", 0);
		arrayKoninkrijk[1][2] = new Vak(42, "gras", 2);
		arrayKoninkrijk[1][3] = new Vak(30, "akker", 0);
		arrayKoninkrijk[1][4] = new Vak(9, "water", 0);

		arrayKoninkrijk[2][0] = new Vak(27, "bos", 1);
		arrayKoninkrijk[2][1] = new Vak(27, "akker", 0);
		arrayKoninkrijk[2][3] = new Vak(48, "akker", 0);
		arrayKoninkrijk[2][4] = new Vak(48, "mijn", 3);

		arrayKoninkrijk[3][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[3][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[4][0] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][1] = new Vak(2, "akker", 0);
		
		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(47, "moeras", 0);
		// returnt false
		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
    		Spel testSpel = new Spel(deelnemendeSpelers);
		Assertions.assertFalse(testSpel.zijnErNogMogelijkeZetten(kleur));
    	}
	}

	@Test
	void plaatsDominotegel_AangrenzendAanStarttegel_plaatst() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));
	   
	    for(Koninkrijk k : koninkrijken)
		{
			if(k.getKleur().equals(Kleur.GEEL))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.BLAUW))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.ROOS))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
		    Spel testSpel = new Spel(deelnemendeSpelers);
		assertDoesNotThrow(() -> testSpel.plaatsDominotegel(kleur, 3, 2, 2, 2));
		}
	}

	@Test
	void plaatsDominotegel_AangrenzendAanJuistLandschapstype_plaatst() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));
	   
	    for(Koninkrijk k : koninkrijken)
		{
			if(k.getKleur().equals(Kleur.GEEL))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.BLAUW))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
			if(k.getKleur().equals(Kleur.ROOS))
			{
		arrayKoninkrijk = k.getArrayKoninkrijk();
		k.breidKoninkrijkUit(arrayKoninkrijk);
		// array wordt ingesteld naar gewenste situatie
		break;
			}
		    Spel testSpel = new Spel(deelnemendeSpelers);
		assertDoesNotThrow(() -> testSpel.plaatsDominotegel(kleur, 0, 0, 1, 0));
		}

	}

	@Test
	void breidBovenUit_OndersteRijIsLeeg_UitbreidingNaarBovenIsMogelijk() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

		for(Koninkrijk k : koninkrijken)
    	{
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[3][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[3][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(47, "mijn", 2);


		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
		
    		if(k.getKleur().equals(Kleur.BLAUW))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[3][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[3][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(47, "mijn", 2);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
    		
    		if(k.getKleur().equals(Kleur.ROOS))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[3][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[3][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(47, "mijn", 2);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
		
		Spel testSpel = new Spel(deelnemendeSpelers);
	
	    assertThrows(IllegalArgumentException.class, () -> {
	        testSpel.breidKoninkrijkUit(kleur, 1);}
	    );
		};
    }
	
	void breidRechtsUit_LinkerRijIsLeeg_UitbreidingNaarRechtsIsMogelijk() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

		for(Koninkrijk k : koninkrijken)
    	{
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
     			arrayKoninkrijk[0][1] = new Vak(2, "akker", 0);
    			arrayKoninkrijk[1][1] = new Vak(2, "akker", 0);
    			arrayKoninkrijk[2][1] = new Vak(22, "moeras", 0);
    			arrayKoninkrijk[3][1] = new Vak(43, "moeras", 2);
    			arrayKoninkrijk[4][1] = new Vak(47, "moeras", 0);


		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
		
    		if(k.getKleur().equals(Kleur.BLAUW))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
    	arrayKoninkrijk[0][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[1][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[2][1] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[3][1] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][1] = new Vak(47, "moeras", 0);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
    		
    		if(k.getKleur().equals(Kleur.ROOS))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
    	arrayKoninkrijk[0][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[1][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[2][1] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[3][1] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][1] = new Vak(47, "moeras", 0);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
		
		Spel testSpel = new Spel(deelnemendeSpelers);
	
	    assertThrows(IllegalArgumentException.class, () -> {
	        testSpel.breidKoninkrijkUit(kleur, 2);}
	    );
		};
    }
	void breidOnderUit_BovensteRijIsLeeg_UitbreidingNaarOnderIsMogelijk() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

		for(Koninkrijk k : koninkrijken)
    	{
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[1][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[1][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[1][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[1][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[1][4] = new Vak(47, "mijn", 2);


		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
		
    		if(k.getKleur().equals(Kleur.BLAUW))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
    	arrayKoninkrijk[1][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[1][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[1][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[1][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[1][4] = new Vak(47, "mijn", 2);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
    		
    		if(k.getKleur().equals(Kleur.ROOS))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
    	arrayKoninkrijk[1][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[1][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[1][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[1][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[1][4] = new Vak(47, "mijn", 2);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
		
		Spel testSpel = new Spel(deelnemendeSpelers);
	
	    assertThrows(IllegalArgumentException.class, () -> {
	        testSpel.breidKoninkrijkUit(kleur, 3);}
	    );
		};
    }
	void breidLinksUit_RechterRijIsLeeg_UitbreidingNaarLinksIsMogelijk() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

		for(Koninkrijk k : koninkrijken)
    	{
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
    	arrayKoninkrijk[0][3] = new Vak(2, "akker", 0);
		arrayKoninkrijk[1][3] = new Vak(2, "akker", 0);
		arrayKoninkrijk[2][3] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[3][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][3] = new Vak(47, "moeras", 0);


		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
		
    		if(k.getKleur().equals(Kleur.BLAUW))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
    	arrayKoninkrijk[0][3] = new Vak(2, "akker", 0);
		arrayKoninkrijk[1][3] = new Vak(2, "akker", 0);
		arrayKoninkrijk[2][3] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[3][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][3] = new Vak(47, "moeras", 0);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
    		
    		if(k.getKleur().equals(Kleur.ROOS))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
    	arrayKoninkrijk[0][3] = new Vak(2, "akker", 0);
		arrayKoninkrijk[1][3] = new Vak(2, "akker", 0);
		arrayKoninkrijk[2][3] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[3][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][3] = new Vak(47, "moeras", 0);


		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
		
		Spel testSpel = new Spel(deelnemendeSpelers);
	
	    assertThrows(IllegalArgumentException.class, () -> {
	        testSpel.breidKoninkrijkUit(kleur, 4);}
	    );
		};
    }

	@Test
	void breidBovenUit_OndersteRijIsNietLeeg_UitbreidingNaarBovenIsNietMogelijk() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

		for(Koninkrijk k : koninkrijken)
    	{
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[3][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[3][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[4][0] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][2] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(47, "moeras", 0);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
		
    		if(k.getKleur().equals(Kleur.BLAUW))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[3][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[3][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[4][0] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][2] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(47, "moeras", 0);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
    		
    		if(k.getKleur().equals(Kleur.ROOS))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[3][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[3][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[4][0] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][2] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(47, "moeras", 0);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
		
		Spel testSpel = new Spel(deelnemendeSpelers);
	
	    assertThrows(IllegalArgumentException.class, () -> {
	        testSpel.breidKoninkrijkUit(kleur, 1);}
	    );
		};
    }
	@Test
	void breidRechtsUit_LinkseRijIsNietLeeg_UitbreidingNaarRechtsIsNietMogelijk() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

		for(Koninkrijk k : koninkrijken)
    	{
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[0][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[1][0] = new Vak(20, "akker", 1);
		arrayKoninkrijk[2][0] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][0] = new Vak(43, "akker", 0);
		arrayKoninkrijk[4][0] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[0][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[1][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[2][1] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[3][1] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][1] = new Vak(47, "moeras", 0);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
		
    		if(k.getKleur().equals(Kleur.BLAUW))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[0][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[1][0] = new Vak(20, "akker", 1);
		arrayKoninkrijk[2][0] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][0] = new Vak(43, "akker", 0);
		arrayKoninkrijk[4][0] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[0][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[1][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[2][1] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[3][1] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][1] = new Vak(47, "moeras", 0);
		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
    		
    		if(k.getKleur().equals(Kleur.ROOS))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[0][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[1][0] = new Vak(20, "akker", 1);
		arrayKoninkrijk[2][0] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][0] = new Vak(43, "akker", 0);
		arrayKoninkrijk[4][0] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[0][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[1][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[2][1] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[3][1] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][1] = new Vak(47, "moeras", 0);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
		
		Spel testSpel = new Spel(deelnemendeSpelers);
	
	    assertThrows(IllegalArgumentException.class, () -> {
	        testSpel.breidKoninkrijkUit(kleur, 2);}
	    );
		};
    }
	@Test
	void breidOnderUit_BovensteRijIsNietLeeg_UitbreidingNaarOnderIsNietMogelijk() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

		for(Koninkrijk k : koninkrijken)
    	{
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[0][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[0][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[0][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[0][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[0][4] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[1][0] = new Vak(2, "akker", 0);
		arrayKoninkrijk[1][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[1][2] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[1][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[1][4] = new Vak(47, "moeras", 0);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
		
    		if(k.getKleur().equals(Kleur.BLAUW))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
    	arrayKoninkrijk[0][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[0][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[0][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[0][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[0][4] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[1][0] = new Vak(2, "akker", 0);
		arrayKoninkrijk[1][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[1][2] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[1][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[1][4] = new Vak(47, "moeras", 0);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
    		
    		if(k.getKleur().equals(Kleur.ROOS))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
    	arrayKoninkrijk[0][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[0][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[0][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[0][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[0][4] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[1][0] = new Vak(2, "akker", 0);
		arrayKoninkrijk[1][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[1][2] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[1][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[1][4] = new Vak(47, "moeras", 0);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
		
		Spel testSpel = new Spel(deelnemendeSpelers);
	
	    assertThrows(IllegalArgumentException.class, () -> {
	        testSpel.breidKoninkrijkUit(kleur, 3);}
	    );
		};
    }
	@Test
	void breidBLinksUit_RechterRijIsNietLeeg_UitbreidingNaarLinksIsNietMogelijk() {
		List<Speler> deelnemendeSpelers = new ArrayList<>();
	    deelnemendeSpelers.add(new Speler("Speler1", 2000, 0, 0, 0, Kleur.GEEL));
	    deelnemendeSpelers.add(new Speler("Speler2", 2000, 0, 0, 0, Kleur.BLAUW));
	    deelnemendeSpelers.add(new Speler("Speler3", 2000, 0, 0, 0, Kleur.ROOS));

		for(Koninkrijk k : koninkrijken)
    	{
    		if(k.getKleur().equals(Kleur.GEEL))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[0][3] = new Vak(20, "bos", 0);
		arrayKoninkrijk[1][3] = new Vak(20, "akker", 1);
		arrayKoninkrijk[2][3] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[4][3] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[0][4] = new Vak(2, "akker", 0);
		arrayKoninkrijk[1][4] = new Vak(2, "akker", 0);
		arrayKoninkrijk[2][4] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[3][4] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(47, "moeras", 0);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
		
    		if(k.getKleur().equals(Kleur.BLAUW))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[3][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[3][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[4][0] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][2] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(47, "moeras", 0);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
    		
    		if(k.getKleur().equals(Kleur.ROOS))
    		{
    	arrayKoninkrijk = k.getArrayKoninkrijk();
		arrayKoninkrijk[3][0] = new Vak(20, "bos", 0);
		arrayKoninkrijk[3][1] = new Vak(20, "akker", 1);
		arrayKoninkrijk[3][2] = new Vak(22, "akker", 1);
		arrayKoninkrijk[3][3] = new Vak(43, "akker", 0);
		arrayKoninkrijk[3][4] = new Vak(47, "mijn", 2);

		arrayKoninkrijk[4][0] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][1] = new Vak(2, "akker", 0);
		arrayKoninkrijk[4][2] = new Vak(22, "moeras", 0);
		arrayKoninkrijk[4][3] = new Vak(43, "moeras", 2);
		arrayKoninkrijk[4][4] = new Vak(47, "moeras", 0);

		k.breidKoninkrijkUit(arrayKoninkrijk);
		break;
    		}
		
		Spel testSpel = new Spel(deelnemendeSpelers);
	
	    assertThrows(IllegalArgumentException.class, () -> {
	        testSpel.breidKoninkrijkUit(kleur, 4);}
	    );
		};
    }
}
	