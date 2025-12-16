package main;
import cui.SpelApplicatie;
import domein.DomeinController;

public class StartUp 
{
	public static void main(String[] args) 
	{
		new SpelApplicatie(new DomeinController()).start();
	}
}