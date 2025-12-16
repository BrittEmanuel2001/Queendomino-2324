package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Vak;
import dto.SpelerDTO;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import utils.Kleur;

public class WinnaarSchermController extends VBox
{
	private DomeinController dc;
	private WelcomeScreenController wsc;
	private ResourceBundle taal;
	private HostServices hs;
	private MediaPlayer mp;
	private int nummer;
	
	// PANE MET ALLE SCORES IN
	@FXML
    private Pane Pane1;

	
	// TERUG NAAR START BUTTON
    @FXML
    private Button btnTerug;

    
    // NAVIGATIEBALK MENUITEMS
    @FXML
    private MenuItem menuItem1;
    @FXML
    private MenuItem menuItem2;
    @FXML
    private MenuItem menuItem3;
    @FXML
    private MenuItem menuItem4;
    
    
    // SPELERS OVERZICHT SCORE
    @FXML
    private Text speler1Naam;
    @FXML
    private Text speler1Score;
    @FXML
    private Text speler1aantal;
    @FXML
    private Pane spelerPane1;

    @FXML
    private Text speler2Naam;
    @FXML
    private Text speler2Score;
    @FXML
    private Text speler2aantal;
    @FXML
    private Pane spelerPane2;

    @FXML
    private Text speler3Naam;
    @FXML
    private Text speler3Score;
    @FXML
    private Text speler3aantal;
    @FXML
    private Pane spelerPane3;

    @FXML
    private Text speler4Naam;
    @FXML
    private Text speler4Score;
    @FXML
    private Text speler4aantal;
    @FXML
    private Pane spelerPane4;

    
    // TITEL OVERZICHT
    @FXML
    private Label txtTitel;

    
    // TEKST WIE GEWONNEN
    @FXML
    private Text winnertxt;
    
    
    // GEGEVENS WINNAAR(S)
    @FXML
    private Text winnaar1Naam;
    @FXML
    private Pane winnaar1Pane;
    @FXML
    private Text winnaar1Score;
    @FXML
    private Text winnaar1aantal;

    @FXML
    private Text winnaar2Naam;
    @FXML
    private Pane winnaar2Pane;
    @FXML
    private Text winnaar2Score;
    @FXML
    private Text winnaar2aantal;
    
    private List<SpelerDTO> winnaars;
    private List<SpelerDTO> deelnemendeSpelers;
    
    
    // OVERZICHT SCHERMPJE VAN KONINKRIJKEN
    @FXML
    private Pane OverzichtPane;
    @FXML
    private GridPane koninkrijk1;
    @FXML
    private GridPane koninkrijk2;
    @FXML
    private GridPane koninkrijk3;
    @FXML
    private GridPane koninkrijk4;

    @FXML
    private Text overzichtKoninkrijk1;
    @FXML
    private Text overzichtKoninkrijk2;
    @FXML
    private Text overzichtKoninkrijk3;
    @FXML
    private Text overzichtKoninkrijk4;
    
    @FXML
    private Pane overzichtInfoSpeler4;
    @FXML
    private Text overzichtScore1;
    @FXML
    private Text overzichtScore2;
    @FXML
    private Text overzichtScore3;
    @FXML
    private Text overzichtScore4;
    @FXML
    private Text overzichtSpeler1;
    @FXML
    private Text overzichtSpeler2;
    @FXML
    private Text overzichtSpeler3;
    @FXML
    private Text overzichtSpeler4;
	
    @FXML
    private Button btnTerugNaarScore;
    @FXML
    private Button btnOverzicht;
    
    
	public WinnaarSchermController(ResourceBundle taal, DomeinController dc, WelcomeScreenController wsc, HostServices hs, MediaPlayer mp, int nummer) 
	{
		this.dc = dc;
		this.wsc = wsc;
		this.taal = taal;
		this.hs = hs;
		this.mp = mp;
		this.nummer = nummer;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("WinnaarScherm.fxml"));
		
		loader.setController(this);
		loader.setRoot(this);
		
		try {
			loader.load();
			menuItem1.setText(taal.getString("MenuTaal"));
			menuItem2.setText(taal.getString("geluid"));
			menuItem3.setText(taal.getString("MenuItemAfsluiten"));
			menuItem4.setText(taal.getString("MenuSpelregels"));
			
			menuItem3.setAccelerator(KeyCombination.keyCombination("Ctrl + X"));
	        menuItem3.setOnAction(this::quit);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		OverzichtPane.setVisible(false);
		overzichtInfoSpeler4.setVisible(false);
		koninkrijk4.setVisible(false);
		
		// Ophalen koninkrijken	
		geefKoninkrijk(koninkrijk1, dc.geefKoninkrijkHuidigeSpeler(dc.geefDeelnemendeSpelers().get(0).kleur()), dc.geefDeelnemendeSpelers().get(0).kleur());
		geefKoninkrijk(koninkrijk2, dc.geefKoninkrijkHuidigeSpeler(dc.geefDeelnemendeSpelers().get(1).kleur()), dc.geefDeelnemendeSpelers().get(1).kleur());
		geefKoninkrijk(koninkrijk3, dc.geefKoninkrijkHuidigeSpeler(dc.geefDeelnemendeSpelers().get(2).kleur()), dc.geefDeelnemendeSpelers().get(2).kleur());
		
		if (dc.geefDeelnemendeSpelers().size() > 3)
		{
			koninkrijk4.setVisible(true);
			overzichtInfoSpeler4.setVisible(true);
			geefKoninkrijk(koninkrijk4, dc.geefKoninkrijkHuidigeSpeler(dc.geefDeelnemendeSpelers().get(3).kleur()), dc.geefDeelnemendeSpelers().get(3).kleur());
		}
		
		// Tekst instellingen
		txtTitel.setText(taal.getString("OverzichtSpel"));
		winnertxt.setText(dc.geefWinnaars(taal));
		btnTerug.setText(taal.getString("TerugStart"));
		btnTerugNaarScore.setText(taal.getString("TerugScore"));
		btnOverzicht.setText(taal.getString("BekijkKoninkrijken"));

		winnaars = dc.geefWinnaarsLijst();
		deelnemendeSpelers = dc.geefDeelnemendeSpelers();
		
		if(deelnemendeSpelers.size() < 4)
			spelerPane4.setVisible(false);
		else
			spelerPane4.setVisible(true);
		
		
		// INSTELLEN WINNAAR OVERVIEW		
		winnaar1Naam.setText(winnaars.get(0).gebruikersnaam());
		winnaar1Score.setText(String.format("Score: %d", winnaars.get(0).score()));
		winnaar1aantal.setText(String.format("%s: %d | %s: %d", taal.getString("gespeeld"),
				winnaars.get(0).aantalGespeeld(), taal.getString("gewonnen"), 
				winnaars.get(0).aantalGewonnen()));
		
		if(winnaars.size() > 1)
		{
			winnaar2Pane.setVisible(true);
			winnaar2Naam.setText(winnaars.get(1).gebruikersnaam());
			winnaar2Score.setText(String.format("Score: %d", winnaars.get(1).score()));
			winnaar2aantal.setText(String.format("%s: %d | %s: %d", taal.getString("gespeeld"),
					winnaars.get(1).aantalGespeeld(), taal.getString("gewonnen"), 
					winnaars.get(1).aantalGewonnen()));
		}
		else winnaar2Pane.setVisible(false);
		
		
		// INSTELLEN OVERVIEW SPELER 1
		speler1Naam.setText(deelnemendeSpelers.get(0).gebruikersnaam());
		speler1Score.setText(String.format("Score: %d", deelnemendeSpelers.get(0).score()));
		speler1aantal.setText(String.format("%s: %d%n%s: %d", taal.getString("gespeeld"),
				deelnemendeSpelers.get(0).aantalGespeeld(), taal.getString("gewonnen"), 
				deelnemendeSpelers.get(0).aantalGewonnen()));
		overzichtSpeler1.setText(deelnemendeSpelers.get(0).gebruikersnaam());;
		overzichtScore1.setText(String.format("Score: %d", deelnemendeSpelers.get(0).score()));
		overzichtKoninkrijk1.setText(String.format("%s %s", taal.getString("koninkrijk"), 
				taal.getLocale().getLanguage().equals("eng") ? 
						deelnemendeSpelers.get(0).kleur().getEngels() : deelnemendeSpelers.get(0).kleur().getNederlands()));
		
		// INSTELLEN OVERVIEW SPELER 2
		speler2Naam.setText(deelnemendeSpelers.get(1).gebruikersnaam());
		speler2Score.setText(String.format("Score: %d", deelnemendeSpelers.get(1).score()));
		speler2aantal.setText(String.format("%s: %d%n%s: %d", taal.getString("gespeeld"),
				deelnemendeSpelers.get(1).aantalGespeeld(), taal.getString("gewonnen"), 
				deelnemendeSpelers.get(1).aantalGewonnen()));
		overzichtSpeler2.setText(deelnemendeSpelers.get(1).gebruikersnaam());
		overzichtScore2.setText(String.format("Score: %d", deelnemendeSpelers.get(1).score()));
		overzichtKoninkrijk2.setText(String.format("%s %s", taal.getString("koninkrijk"), 
				taal.getLocale().getLanguage().equals("eng") ? 
						deelnemendeSpelers.get(1).kleur().getEngels() : deelnemendeSpelers.get(1).kleur().getNederlands()));
		
		// INSTELLEN OVERVIEW SPELER 3
		speler3Naam.setText(deelnemendeSpelers.get(2).gebruikersnaam());
		speler3Score.setText(String.format("Score: %d", deelnemendeSpelers.get(2).score()));
		speler3aantal.setText(String.format("%s: %d%n%s: %d", taal.getString("gespeeld"),
				deelnemendeSpelers.get(2).aantalGespeeld(), taal.getString("gewonnen"), 
				deelnemendeSpelers.get(2).aantalGewonnen()));
		overzichtSpeler3.setText(deelnemendeSpelers.get(2).gebruikersnaam());
		overzichtScore3.setText(String.format("Score: %d", deelnemendeSpelers.get(2).score()));
		overzichtKoninkrijk3.setText(String.format("%s %s", taal.getString("koninkrijk"), 
				taal.getLocale().getLanguage().equals("eng") ? 
						deelnemendeSpelers.get(2).kleur().getEngels() : deelnemendeSpelers.get(2).kleur().getNederlands()));
		
		// INSTELLEN OVERVIEW SPELER 4
		if(deelnemendeSpelers.size() == 4)
		{
			speler4Naam.setText(deelnemendeSpelers.get(3).gebruikersnaam());
			speler4Score.setText(String.format("Score: %d", deelnemendeSpelers.get(3).score()));
			speler4aantal.setText(String.format("%s: %d%n%s: %d", taal.getString("gespeeld"),
					deelnemendeSpelers.get(3).aantalGespeeld(), taal.getString("gewonnen"), 
					deelnemendeSpelers.get(3).aantalGewonnen()));
			overzichtSpeler4.setText(deelnemendeSpelers.get(3).gebruikersnaam());
			overzichtScore4.setText(String.format("Score: %d", deelnemendeSpelers.get(3).score()));
			overzichtKoninkrijk4.setText(String.format("%s %s", taal.getString("koninkrijk"), 
					taal.getLocale().getLanguage().equals("eng") ? 
							deelnemendeSpelers.get(3).kleur().getEngels() : deelnemendeSpelers.get(3).kleur().getNederlands()));
		}
	}
	
	@FXML
    void displayRules(ActionEvent event) 
    {
		File file = new File("src/images/Spelregels_kingdomino.pdf");
    	hs.showDocument(file.getAbsolutePath());
    }
	
	// TERUG BUTTON INSTELLEN
    @FXML
    void hoverBtnTerug(MouseEvent event) {btnTerug.setStyle("-fx-underline:true; -fx-background-color:none");}
    @FXML
    void nohoverBtnTerug(MouseEvent event) {btnTerug.setStyle("-fx-underline:false; -fx-background-color:none");}
    @FXML
    void terugNaarStart(MouseEvent event) {getScene().setRoot(new KeuzeMenuSchermController(taal, dc, wsc, hs, mp, nummer));}
    
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
    void geluidAanUit(ActionEvent event)
    {
    	if(mp.getStatus() == MediaPlayer.Status.PLAYING)
    		mp.pause();
    	else
    		mp.play();
    }
    
    @FXML
    void hoverBtnTerugNaarWinnaar(MouseEvent event) {
    	btnTerugNaarScore.setStyle("-fx-underline:true; -fx-background-color:none");
    }

    @FXML
    void nohoverBtnTerugNaarWinnaar(MouseEvent event) {
    	btnTerugNaarScore.setStyle("-fx-underline:false; -fx-background-color:none");
    }
    
    @FXML
    void nohoverBtnOverzicht(MouseEvent event) {
    	btnOverzicht.setStyle("-fx-underline:false; -fx-background-color:none");
    }
    
    @FXML
    void hoverBtnOverzicht(MouseEvent event) {
    	btnOverzicht.setStyle("-fx-underline:true; -fx-background-color:none");
    }
    
    @FXML
    void terugNaarScore(MouseEvent event) {
    	OverzichtPane.setVisible(false);
    }
    
    @FXML
    void naarOverzicht(MouseEvent event) {
    	OverzichtPane.setVisible(true);
    }
    
    private void geefKoninkrijk(GridPane koninkrijk, Vak[][] koninkrijkgrid, Kleur kleur)
	{
		Vak[][] kleinKoninkrijk = koninkrijkgrid;
		for (int x = 0; x < kleinKoninkrijk.length; x++)
	    {
	        for (int y = 0; y < kleinKoninkrijk[x].length; y++)
	        {
	        	Image image = createImage(kleur, kleinKoninkrijk, x, y);
	        	ImageView imageView = new ImageView(image);
	        	
	        	imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                koninkrijk.add(imageView, y, x);
	        }
	    }
	}

	private Image createImage(Kleur kleur, Vak[][] huidigKoninkrijk, int x, int y) 
    {
        String pad = "";
        if(!huidigKoninkrijk[x][y].geefBestandsnaam().equals("leeg.png") 
        	&& !huidigKoninkrijk[x][y].geefBestandsnaam().equals("starttegel.png"))
            pad = String.format("src/images/vakken/%s", huidigKoninkrijk[x][y].geefBestandsnaam()); 
        else 
            pad = String.format("src/images/%s/%s", kleur.getNederlands(), huidigKoninkrijk[x][y].geefBestandsnaam());
        return maakImage(pad);
    }
	
	private Image maakImage(String pad)
	{
		try {
            FileInputStream inputstream = new FileInputStream(pad);
            Image image = new Image(inputstream);
            return image;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } return null;
	}
}
