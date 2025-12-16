package dto;

import domein.Koning;
import domein.Vak;

public record DominotegelDTO(int nummer, Vak vak1, Vak vak2, Koning koning) 
{
	@Override
    public String toString() 
    {
        return "Nr " + nummer + " - [" + vak1.toString() + "|" + vak2.toString() + "]";
    }
}