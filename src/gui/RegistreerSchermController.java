package gui;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.ResourceBundle;

import domein.DomeinController;
import exceptions.GebruikersnaamInGebruikException;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;

public class RegistreerSchermController extends VBox
{
	private DomeinController dc;
	private WelcomeScreenController wsc;
	private ResourceBundle taal;
	private HostServices hs;
	private MediaPlayer mp;
	private int nummer;

	@FXML
    private Hyperlink btnannuleer;
    @FXML
    private Label errorGeboortejaar;
    @FXML
    private TextField inputGeboortejaar;
    @FXML
    private TextField inputGebruikersnaam;
    @FXML
    private Label lblGeboortejaar;
    @FXML
    private Label lblGebruikersnaam;
    @FXML
    private MenuItem menuItem1;
    @FXML
    private MenuItem menuItem2;
    @FXML
    private MenuItem menuItem3;
    @FXML
    private MenuItem menuItem4;
    @FXML
    private Button btnregistreer;
    @FXML
    private Label txtregistreer;
    @FXML
    private Tooltip tooltipGeboortejaar;
    @FXML
    private Tooltip tooltipGebruikersnaam;
    @FXML
    private Label txtwelkom;

    @FXML
    void hoverbtn3(MouseEvent event) {
    	btnregistreer.setStyle("-fx-underline:true; -fx-background-color:none");
    }

    @FXML
    void nohoverbtn3(MouseEvent event) {
    	btnregistreer.setStyle("-fx-underline:false; -fx-background-color:none");
    }

    @FXML
    void terugNaarMenu(ActionEvent event) {
    	getScene().setRoot(new KeuzeMenuSchermController(taal, dc, wsc, hs, mp, nummer));
    }
    
    @FXML
    void displayRules(ActionEvent event) {
    	File file = new File("src/images/Spelregels_kingdomino.pdf");
    	hs.showDocument(file.getAbsolutePath());
    }

    @FXML
    void voegGebruikerToe(ActionEvent event) 
    {
    	try {
    		String gebruikersnaam = inputGebruikersnaam.getText();
    		String geboortejaar = inputGeboortejaar.getText();
    		if (gebruikersnaam.isBlank() || gebruikersnaam.isEmpty() || geboortejaar.isBlank() || geboortejaar.isEmpty()) 
                throw new IllegalArgumentException("nietLeeg");
            
    		int geboorte = Integer.parseInt(geboortejaar);
    		dc.registreerSpeler(gebruikersnaam, geboorte);
    		geefFeedback(AlertType.INFORMATION, 
    				taal.getString("RegistratieBevestiging") + inputGebruikersnaam.getText());
    		
    	} catch (NumberFormatException ex) {
    		errorGeboortejaar.setText(taal.getString("geheelGetal"));
    	} catch (IllegalArgumentException ex) {
            errorGeboortejaar.setText(taal.getString(ex.getMessage()));
        } catch (GebruikersnaamInGebruikException ex) {
            errorGeboortejaar.setText(taal.getString("InGebruik"));
        }
    }
	
	private void geefFeedback(AlertType alertType, String message) 
	{
        Alert boodschap = new Alert(alertType);
        boodschap.setTitle(taal.getString("Proficiat"));
        boodschap.setHeaderText(taal.getString("Geslaagd"));
        boodschap.setContentText(message);
        boodschap.showAndWait();
        getScene().setRoot(new KeuzeMenuSchermController(taal, dc, wsc, hs, mp, nummer));
	}

	@FXML
    void geluidAanUit(ActionEvent event)
    {
    	if(mp.getStatus() == MediaPlayer.Status.PLAYING)
    		mp.pause();
    	else
    		mp.play();
    }

	public RegistreerSchermController(ResourceBundle taal, DomeinController dc, WelcomeScreenController wsc, HostServices hs, MediaPlayer mp, int nummer) 
	{
		this.dc = dc;
		this.wsc = wsc;
		this.taal = taal;
		this.hs = hs;
		this.mp = mp;
		this.nummer = nummer;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistreerScherm.fxml"));
		
		loader.setController(this);
		loader.setRoot(this);
		
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		menuItem3.setAccelerator(KeyCombination.keyCombination("Ctrl + X"));
		menuItem3.setOnAction(this::quit);
		

		menuItem1.setText(taal.getString("MenuTaal"));
		menuItem2.setText(taal.getString("geluid"));
		menuItem3.setText(taal.getString("MenuItemAfsluiten"));
		menuItem4.setText(taal.getString("MenuSpelregels"));
		btnannuleer.setText(taal.getString("annuleer"));
		lblGeboortejaar.setText(taal.getString("Geboorte"));
		lblGebruikersnaam.setText(taal.getString("Gebruiker"));
		txtregistreer.setText(taal.getString("RegistreerZin"));
		tooltipGeboortejaar.setText(taal.getString("TeJong"));
		tooltipGebruikersnaam.setText(taal.getString("GebruikersnaamKort"));
		txtwelkom.setText(taal.getString("Reiziger"));
		btnregistreer.setText(taal.getString("Registreer"));
		
		inputGebruikersnaam.setPromptText("MightyKen");
		inputGeboortejaar.setPromptText("2005");
	}

	private void quit(Event event) 
	{
        Alert alert = new Alert(AlertType.CONFIRMATION);
        
        alert.setTitle(taal.getString("MenuItemAfsluiten"));
        alert.setHeaderText(taal.getString("AfsluitenZeker"));
        alert.setContentText(taal.getString("AfsluitenBoodschap"));
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isEmpty() || result.get().equals(ButtonType.CANCEL))
            event.consume();
        else
        	Platform.exit();
    }
}
