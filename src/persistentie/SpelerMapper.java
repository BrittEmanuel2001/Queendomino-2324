package persistentie;

import domein.Speler;
import utils.Kleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpelerMapper 
{
    private static final String INSERT_SPELER = "INSERT INTO ID429726_g32.Speler (gebruikersnaam, geboortejaar, aantalGewonnen, aantalGespeeld, kleur)"
            + "VALUES (?, ?, ?, ?, ?)";
    
    public void voegToe(Speler speler) 
    {
    	Connectie ssh = new Connectie();
    	try (Connection conn = DriverManager.getConnection(Connectie.MYSQL_JDBC);
                PreparedStatement query = conn.prepareStatement(INSERT_SPELER)) 
        {
    		String kleur = speler.getKleur().toString();
    		
            query.setString(1, speler.getGebruikersnaam());
            query.setInt(2, speler.getGeboortejaar());
            query.setInt(3, speler.getAantalGewonnen());
            query.setInt(4, speler.getAantalGespeeld());
            query.setString(5, kleur);
            
            // Execute the update statement
            query.executeUpdate();

        }catch (SQLException ex){
        	throw new RuntimeException(ex);
        }
    	ssh.closeConnection();
    }

    public void pasAan(Speler speler, String gebruikersnaam) 
    {
    	Connectie ssh = new Connectie();
    	try (Connection conn = DriverManager.getConnection(Connectie.MYSQL_JDBC);
                PreparedStatement query = conn.prepareStatement("UPDATE ID429726_g32.Speler SET kleur = ?, aantalGespeeld = ?, aantalGewonnen = ? WHERE gebruikersnaam = ?")) 
        {
    		query.setString(1, speler.getKleur().toString());
            query.setInt(2, speler.getAantalGespeeld());
            query.setInt(3, speler.getAantalGewonnen());
            query.setString(4, gebruikersnaam);

            // Execute the update statement
            query.executeUpdate();	
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    	ssh.closeConnection();
    }
    
    public Speler geefSpeler(String gebruikersnaam) 
    {
    	Connectie ssh = new Connectie();
        Speler speler = null;

        try (Connection conn = DriverManager.getConnection(Connectie.MYSQL_JDBC);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID429726_g32.Speler WHERE gebruikersnaam = ?")) 
        {
            query.setString(1, gebruikersnaam);
            try (ResultSet rs = query.executeQuery()) 
            {
                if (rs.next()) 
                {
                    int geboortejaar = rs.getInt("geboortejaar");
                    int aantalGewonnen = rs.getInt("aantalGewonnen");
                    int aantalGespeeld = rs.getInt("aantalGespeeld");
                    String kleur = rs.getString("kleur");
                    
                    Kleur kleurEnum = Kleur.valueOf(kleur);

                    speler = new Speler(gebruikersnaam, geboortejaar, aantalGewonnen, aantalGespeeld, 0, kleurEnum);               
                }
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }

        ssh.closeConnection();
        return speler;
    }
    
    public List<Speler> geefSpelers() 
    {
    	Connectie ssh = new Connectie();
        Speler speler = null;
        List<Speler> spelers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.MYSQL_JDBC);
                PreparedStatement query = conn.prepareStatement("SELECT gebruikersnaam, geboortejaar, aantalGewonnen, aantalGespeeld FROM ID429726_g32.Speler")) 
        {
            try (ResultSet rs = query.executeQuery()) 
            {
                while (rs.next()) 
                {
                	String gebruikersnaam = rs.getString("gebruikersnaam");
                    int geboortejaar = rs.getInt("geboortejaar");
                    int aantalGewonnen = rs.getInt("aantalGewonnen");
                    int aantalGespeeld = rs.getInt("aantalGespeeld");
                    speler = new Speler(gebruikersnaam, geboortejaar, aantalGewonnen, aantalGespeeld);
                    spelers.add(speler);
                }
            }
        }catch (SQLException ex){
           throw new RuntimeException(ex);
        }

        ssh.closeConnection();
        return spelers;
    }
}
