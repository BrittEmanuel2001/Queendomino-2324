package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domein.Vak;

public class DominotegelMapper 
{
    public List<Vak> geefVakken()
    {
        Connectie ssh = new Connectie();
        Vak vak = null;
        List<Vak> vakken = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(Connectie.MYSQL_JDBC);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID429726_g32.Vak ORDER BY nummer, plaats")) 
        {
            try (ResultSet rs = query.executeQuery()) 
            {
                while (rs.next()) 
                {
                    int nummer = rs.getInt("nummer");
                    String landschapstype = rs.getString("landschapstype");
                    int aantalKronen = rs.getInt("aantalKronen");
                    
                    vak = new Vak(nummer, landschapstype, aantalKronen);        
                    vakken.add(vak);
                }
            }
        } 
        catch (SQLException ex) 
        {
            throw new RuntimeException(ex);
        }

        ssh.closeConnection();
        return vakken;
    }
}