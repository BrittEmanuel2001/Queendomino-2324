package gui;

import java.io.IOException;
import java.util.ResourceBundle;

import domein.DomeinController;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import utils.Taal;

public class WelcomeScreenController extends VBox
{	
	private DomeinController dc;
	private String taal = "nl";
    private String land = "BE";
    private ResourceBundle boodschappen;
    private HostServices hs;
    private MediaPlayer mp;
    private int nummer;
    
    @FXML
    private ImageView NLVlag;
    @FXML
    private ImageView QueendominoLogo;
    @FXML
    private ImageView UKVlag;
    
	public WelcomeScreenController(DomeinController dc, HostServices hs, MediaPlayer mp, int nummer) {
		this.dc = dc;
		this.hs = hs;
		this.mp = mp;
		this.nummer = nummer;
		bouwScherm();
	}

    @FXML
    void keuzeEngels(MouseEvent event) 
    {
    	taal = "eng"; land = "UK";
    	Taal taalObject = new Taal(taal, land);
        boodschappen = taalObject.kiesTaal();
    	gaNaarKeuzeMenuScherm();
    }

    @FXML
    void keuzeNederlands(MouseEvent event) 
    {
    	taal = "nl"; land = "BE";  
    	Taal taalObject = new Taal(taal, land);
        boodschappen = taalObject.kiesTaal();
    	gaNaarKeuzeMenuScherm();
    }
	
	private void bouwScherm()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeScreen.fxml"));
		
		loader.setController(this);
		loader.setRoot(this);
		
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void gaNaarKeuzeMenuScherm() 
    {
        getScene().setRoot(new KeuzeMenuSchermController(boodschappen, dc, this, hs, mp, nummer));
    }
}

