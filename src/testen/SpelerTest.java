package testen;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Speler;
import utils.Kleur;

class SpelerTest 
{
	private Speler speler;
	private static final int JAAR_GELDIG = 2005;
	private static final int JAAR_TEJONG = 2024;
	private static final int JAAR_TEOUD = 1804;
	private static final int JAAR_NETTEJONG= 2019;
	private static final int JAAR_NETOUDGENOEG= 2017;
	private static final int JAAR_NETTEOUD= 1923;
	private static final int JAAR_KANNOG= 1925;
	private static final String GELDIGE_NAAM = "plopkoek";
	private static final String RANDGEVAL_NAAM = "Do kUn";
	

	@Test
	void maakSpeler_alleGegevensCorrect_maaktObject() 
	{
		speler = new Speler("avatar", 2003, 4, 25, 0, Kleur.GEEN_KLEUR);
		Assertions.assertEquals("avatar", speler.getGebruikersnaam());
		Assertions.assertEquals(2003, speler.getGeboortejaar());
		Assertions.assertEquals(4, speler.getAantalGewonnen());
		Assertions.assertEquals(25, speler.getAantalGespeeld());
	}
	
	@Test
	void maakSpeler_correcteGebruikersnaamGeboortejaar_maaktObject() 
	{
		speler = new Speler("avatar", 2003);
		Assertions.assertEquals("avatar", speler.getGebruikersnaam());
		Assertions.assertEquals(2003, speler.getGeboortejaar());
		Assertions.assertEquals(0, speler.getAantalGewonnen());
		Assertions.assertEquals(0, speler.getAantalGespeeld());
	}
	
	
	@ParameterizedTest
	@ValueSource(ints = {JAAR_TEJONG, JAAR_TEOUD, JAAR_NETTEJONG, JAAR_NETTEOUD})
	void controleerJaar_foutJaar(int geboortejaar)
	{
		assertThrows(IllegalArgumentException.class, () -> new Speler("avatar", geboortejaar, 0, 0, 0, Kleur.GEEN_KLEUR));
	}

	@ParameterizedTest
	@ValueSource(ints = {JAAR_GELDIG, JAAR_NETOUDGENOEG, JAAR_KANNOG})
	void controleerJaar_juistJaar(int geboortejaar)
	{
		assertDoesNotThrow(() -> new Speler("avatar", geboortejaar, 0, 0, 0, Kleur.GEEN_KLEUR));
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {" ", "\t    \n   "})
	void controleerNaam_fouteNaam(String naam)
	{
		assertThrows(IllegalArgumentException.class, () -> new Speler(naam, 2005, 0, 0, 0, Kleur.GEEN_KLEUR));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {GELDIGE_NAAM, RANDGEVAL_NAAM})
	void controleerNaam_juisteNaam(String naam)
	{
		assertDoesNotThrow(() -> new Speler(naam, 2005, 0, 0, 0, Kleur.GEEN_KLEUR));
	}
	
}
