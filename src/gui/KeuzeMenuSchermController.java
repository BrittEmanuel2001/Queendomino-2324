package gui;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

import domein.DomeinController;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class KeuzeMenuSchermController extends VBox
{
	private DomeinController dc;
	private WelcomeScreenController wsc;
	private ResourceBundle taal;
	private HostServices hs;
	private MediaPlayer mp;
	private int nummerKeuze;
	
	@FXML
    private ImageView changeMusicIcon;
	
	@FXML
    private Button btnregister;
    @FXML
    private Button btnstart;
    @FXML
    private Button btnstop;
    @FXML
    private MenuItem menuItem1;
    @FXML
    private MenuItem menuItem2;
    @FXML
    private MenuItem menuItem3;
    @FXML
    private MenuItem menuItem4;

    @FXML
    void hoverbtn1(MouseEvent event) {btnregister.setStyle("-fx-underline:true; -fx-background-color:none");}
    @FXML
    void hoverbtn2(MouseEvent event) {btnstart.setStyle("-fx-underline:true; -fx-background-color:none");}
    @FXML
    void hoverbtn3(MouseEvent event) {btnstop.setStyle("-fx-underline:true; -fx-background-color:none");}
    @FXML
    void nohoverbtn1(MouseEvent event) {btnregister.setStyle("-fx-underline:false; -fx-background-color:none");}
    @FXML
    void nohoverbtn2(MouseEvent event) {btnstart.setStyle("-fx-underline:false; -fx-background-color:none");}
    @FXML
    void nohoverbtn3(MouseEvent event) {btnstop.setStyle("-fx-underline:false; -fx-background-color:none");}

    @FXML
    void registreerKlik(MouseEvent event) 
    {getScene().setRoot(new RegistreerSchermController(taal, dc, wsc, hs, mp, nummerKeuze));}

    @FXML
    void startKlik(MouseEvent event) {getScene().setRoot(new SelecteerSpelersController(taal, dc, wsc, hs, mp, nummerKeuze));}
    
    @FXML
    void sluitKlik(MouseEvent event) {quit(event);}
    
    @FXML
    void naarStartScherm(ActionEvent event) {getScene().setRoot(new KeuzeMenuSchermController(taal, dc, wsc, hs, mp, nummerKeuze));}
    @FXML
    void naarTaalScherm(ActionEvent event) {getScene().setRoot(new WelcomeScreenController(dc, hs, mp, nummerKeuze));}
    
    @FXML
    void displayRules(ActionEvent event) {
    	File file = new File("src/images/Spelregels_kingdomino.pdf");
    	hs.showDocument(file.getAbsolutePath());
    }
    
    @FXML
    void geluidAanUit(ActionEvent event)
    {
    	if(mp.getStatus() == MediaPlayer.Status.PLAYING)
    		mp.pause();
    	else
    		mp.play();
    }
    
	public KeuzeMenuSchermController(ResourceBundle taal, DomeinController dc, WelcomeScreenController wsc, HostServices hs, MediaPlayer mp, int nummer)
	{
		this.dc = dc;
		this.wsc = wsc;
		this.taal = taal;
		this.hs = hs;
		this.mp = mp;
		this.nummerKeuze = nummer;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("KeuzeMenuScherm.fxml"));
		
		loader.setController(this);
		loader.setRoot(this);
		
		try {
			loader.load();
			btnregister.setText(taal.getString("MenuRegistreer"));
			btnstart.setText(taal.getString("MenuStart"));
			btnstop.setText(taal.getString("MenuAfsluiten"));
			menuItem1.setText(taal.getString("MenuTaal"));
			menuItem2.setText(taal.getString("geluid"));
			menuItem3.setText(taal.getString("MenuItemAfsluiten"));
			menuItem4.setText(taal.getString("MenuSpelregels"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		menuItem3.setAccelerator(KeyCombination.keyCombination("Ctrl + X"));
		menuItem3.setOnAction(this::quit);
		
		if(dc.geefAantalSpelersInDatabank() < 3)
			btnstart.setDisable(true);
		else
			btnstart.setDisable(false);
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
	
	@FXML
    void btnChangeMusic(MouseEvent event) 
	{
		if (nummerKeuze == 3)
			nummerKeuze = 0;
		else
			nummerKeuze++;
		
		mp.stop();
		
		File audioFile = new File("src/images/musicQueendomino" + nummerKeuze + ".mp3");
        Media media = new Media(audioFile.toURI().toString());
        mp = new MediaPlayer(media);

        // MEDIAPLAYER NON STOP LATEN SPELEN (IN LOOP)
        mp.setCycleCount(MediaPlayer.INDEFINITE);
        mp.play();
    }
}
