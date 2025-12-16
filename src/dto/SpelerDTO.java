package dto;

import utils.Kleur;

public record SpelerDTO(String gebruikersnaam, int geboortejaar, int aantalGewonnen, int aantalGespeeld, int score, Kleur kleur) 
{
	
}