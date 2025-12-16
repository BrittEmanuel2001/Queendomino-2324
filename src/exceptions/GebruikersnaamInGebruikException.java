package exceptions;

import java.util.ResourceBundle;

public class GebruikersnaamInGebruikException extends RuntimeException 
{
	public GebruikersnaamInGebruikException() 
	{
		super("Gebruikersnaam reeds in gebruik.");
	}

	public GebruikersnaamInGebruikException(String message) 
	{
		super(message);
		
	}
}
