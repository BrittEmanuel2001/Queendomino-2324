package gui;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

import domein.DomeinController;
import dto.SpelerDTO;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import utils.Kleur;

public class SelecteerSpelersController extends VBox
{
	private DomeinController dc;
	private WelcomeScreenController wsc;
	private ResourceBundle taal;
	private HostServices hs;
	private MediaPlayer mp;
	private int nummer;
	
	@FXML
    private Pane Pane1;
    @FXML
    private Pane Pane2;
    @FXML
    private Pane Pane3;
    @FXML
    private Pane Pane4;

	
	@FXML
    private ImageView arrow;
    @FXML
    private Button btnstart;
    @FXML
    private Button btnvoegtoe1;
    @FXML
    private Button btnvoegtoe2;
    @FXML
    private Button btnvoegtoe3;
    @FXML
    private Button btnvoegtoe4;

    @FXML
    private ComboBox<String> cmbbKleur;
    @FXML
    private ComboBox<String> cmbbKleur2;
    @FXML
    private ComboBox<String> cmbbKleur3;
    @FXML
    private ComboBox<String> cmbbKleur4;
    @FXML
    private ComboBox<String> cmbbP1;
    @FXML
    private ComboBox<String> cmbbP2;
    @FXML
    private ComboBox<String> cmbbP3;
    @FXML
    private ComboBox<String> cmbbP4;

    @FXML
    private MenuItem menuItem1;
    @FXML
    private MenuItem menuItem2;
    @FXML
    private MenuItem menuItem3;
    @FXML
    private MenuItem menuItem4;

    @FXML
    private Label txtaantalSpelers;
    @FXML
    private Label txtselecteer;
    @FXML
    private Label txtwelkom;


    @FXML
    void hoverBtn1(MouseEvent event) {btnvoegtoe1.setStyle("-fx-underline:true; -fx-background-color:none");}
    @FXML
    void hoverBtn2(MouseEvent event) {btnvoegtoe2.setStyle("-fx-underline:true; -fx-background-color:none");}
    @FXML
    void hoverBtn3(MouseEvent event) {btnvoegtoe3.setStyle("-fx-underline:true; -fx-background-color:none");}
    @FXML
    void hoverBtn4(MouseEvent event) {btnvoegtoe4.setStyle("-fx-underline:true; -fx-background-color:none");}
    @FXML
    void hoverBtnStart(MouseEvent event) {btnstart.setStyle("-fx-underline:true; -fx-background-color:none");}
    @FXML
    void nohoverBtn1(MouseEvent event) {btnvoegtoe1.setStyle("-fx-underline:false; -fx-background-color:none");}
    @FXML
    void nohoverBtn2(MouseEvent event) {btnvoegtoe2.setStyle("-fx-underline:false; -fx-background-color:none");}
    @FXML
    void nohoverBtn3(MouseEvent event) {btnvoegtoe3.setStyle("-fx-underline:false; -fx-background-color:none");}
    @FXML
    void nohoverBtn4(MouseEvent event) {btnvoegtoe4.setStyle("-fx-underline:false; -fx-background-color:none");}
    @FXML
    void nohoverBtnStart(MouseEvent event) {btnstart.setStyle("-fx-underline:false; -fx-background-color:none");}
    
    @FXML
    void terugNaarStart(MouseEvent event) {
    	getScene().setRoot(new KeuzeMenuSchermController(taal, dc, wsc, hs, mp, nummer));
    }

    @FXML
    void voegSpelerToe(MouseEvent event) 
    {
    	dc.kiesSpelerEnKleur(cmbbP1.getSelectionModel().getSelectedIndex()+1, cmbbKleur.getSelectionModel().getSelectedIndex()+1);
    	Pane2.setDisable(false);
    	Pane2.setOpacity(1);
    	txtaantalSpelers.setText(String.format("%d %s", dc.geefDeelnemendeSpelers().size(), taal.getString("geselecteerdeSpelers")));
    	
    	Pane1.setDisable(true);
    	btnvoegtoe1.setText(String.format("%s %s", dc.geefDeelnemendeSpelers().get(0).gebruikersnaam(), taal.getString("doetMee")));
    	btnvoegtoe1.setOpacity(1);
    	btnvoegtoe1.setGraphic(null);
    	
    	// aanvullen volgend lijsten
    	for (Kleur k : dc.geefResterendeKleuren())
    		cmbbKleur2.getItems().add(taal.getLocale().getLanguage().equals("eng") ? k.getEngels() : k.getNederlands());
    			
    	for (SpelerDTO p : dc.geefResterendeSpelers())
    		cmbbP2.getItems().add(p.gebruikersnaam());
    	
    	// Button pas enablen wanneer P2 beide ingevuld
    	btnvoegtoe2.setDisable(true);
    	cmbbP2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		updateButtonState(cmbbP2, cmbbKleur2, btnvoegtoe2);
    	});

    	cmbbKleur2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		updateButtonState(cmbbP2, cmbbKleur2, btnvoegtoe2);
    	});
    }
    
    @FXML
    void voegSpelerToe2(MouseEvent event) 
    {
    	dc.kiesSpelerEnKleur(cmbbP2.getSelectionModel().getSelectedIndex()+1, cmbbKleur2.getSelectionModel().getSelectedIndex()+1);
    	Pane3.setDisable(false);
    	Pane3.setOpacity(1);
    	txtaantalSpelers.setText(String.format("%d %s", dc.geefDeelnemendeSpelers().size(), taal.getString("geselecteerdeSpelers")));
    	
    	Pane2.setDisable(true);
    	btnvoegtoe2.setText(String.format("%s %s", dc.geefDeelnemendeSpelers().get(1).gebruikersnaam(), taal.getString("doetMee")));
    	btnvoegtoe2.setOpacity(1);
    	btnvoegtoe2.setGraphic(null);
    	
    	// aanvullen volgend lijsten
    	for (Kleur k : dc.geefResterendeKleuren())
    		cmbbKleur3.getItems().add(taal.getLocale().getLanguage().equals("eng") ? k.getEngels() : k.getNederlands());
    			
    	for (SpelerDTO p : dc.geefResterendeSpelers())
    		cmbbP3.getItems().add(p.gebruikersnaam());
    	
    	// Button pas enablen wanneer P3 beide ingevuld
    	btnvoegtoe3.setDisable(true);
    	cmbbP3.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		updateButtonState(cmbbP3, cmbbKleur3, btnvoegtoe3);
    	});

    	cmbbKleur3.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		updateButtonState(cmbbP3, cmbbKleur3, btnvoegtoe3);
    	});
    }
    
    @FXML
    void voegSpelerToe3(MouseEvent event) 
    {
    	dc.kiesSpelerEnKleur(cmbbP3.getSelectionModel().getSelectedIndex()+1, cmbbKleur3.getSelectionModel().getSelectedIndex()+1);
    	Pane4.setDisable(false);
    	Pane4.setOpacity(1);
    	txtaantalSpelers.setText(String.format("%d %s", dc.geefDeelnemendeSpelers().size(), taal.getString("geselecteerdeSpelers")));
    	
    	Pane3.setDisable(true);
    	btnvoegtoe3.setText(String.format("%s %s", dc.geefDeelnemendeSpelers().get(2).gebruikersnaam(), taal.getString("doetMee")));
    	btnvoegtoe3.setOpacity(1);
    	btnvoegtoe3.setGraphic(null);
    	
    	// aanvullen volgend lijsten
    	for (Kleur k : dc.geefResterendeKleuren())
    		cmbbKleur4.getItems().add(taal.getLocale().getLanguage().equals("eng") ? k.getEngels() : k.getNederlands());
    			
    	for (SpelerDTO p : dc.geefResterendeSpelers())
    		cmbbP4.getItems().add(p.gebruikersnaam());
    	
    	btnstart.setDisable(false);
    	
    	// Button pas enablen wanneer P3 beide ingevuld
    	btnvoegtoe4.setDisable(true);
    	cmbbP4.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		updateButtonState(cmbbP4, cmbbKleur4, btnvoegtoe4);
    	});

    	cmbbKleur4.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		updateButtonState(cmbbP4, cmbbKleur4, btnvoegtoe4);
    	});
    }
    
    @FXML
    void voegSpelerToe4(MouseEvent event) 
    {
    	dc.kiesSpelerEnKleur(cmbbP4.getSelectionModel().getSelectedIndex()+1, cmbbKleur4.getSelectionModel().getSelectedIndex()+1);
    	txtaantalSpelers.setText(String.format("%d %s", dc.geefDeelnemendeSpelers().size(), taal.getString("geselecteerdeSpelers")));
    	
    	Pane4.setDisable(true);
    	btnvoegtoe4.setText(String.format("%s %s", dc.geefDeelnemendeSpelers().get(3).gebruikersnaam(), taal.getString("doetMee")));
    	btnvoegtoe4.setOpacity(1);
    	btnvoegtoe4.setGraphic(null);
    }

    @FXML
    void startSpel(MouseEvent event) {
    	getScene().setRoot(new SpelSchermController(taal, dc, wsc, hs, mp, nummer));
    }

    
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
    
	public SelecteerSpelersController(ResourceBundle taal, DomeinController dc, WelcomeScreenController wsc, HostServices hs, MediaPlayer mp, int nummer) 
	{
		this.dc = dc;
		this.wsc = wsc;
		this.taal = taal;
		this.hs = hs;
		this.mp = mp;
		this.nummer = nummer;
		
		dc.startKingdomino();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SelecteerSpelers.fxml"));
		
		loader.setController(this);
		loader.setRoot(this);
		
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// VERTALINGEN
		
		menuItem3.setAccelerator(KeyCombination.keyCombination("Ctrl + X"));
		menuItem3.setOnAction(this::quit);

		menuItem1.setText(taal.getString("MenuTaal"));
		menuItem2.setText(taal.getString("geluid"));
		menuItem3.setText(taal.getString("MenuItemAfsluiten"));
		menuItem4.setText(taal.getString("MenuSpelregels"));
		
		cmbbKleur.setPromptText(taal.getString("KiesKleur"));
		cmbbKleur2.setPromptText(taal.getString("KiesKleur"));
		cmbbKleur3.setPromptText(taal.getString("KiesKleur"));
		cmbbKleur4.setPromptText(taal.getString("KiesKleur"));
		
		cmbbP1.setPromptText(taal.getString("KiesSpeler"));
		cmbbP2.setPromptText(taal.getString("KiesSpeler"));
		cmbbP3.setPromptText(taal.getString("KiesSpeler"));
		cmbbP4.setPromptText(taal.getString("KiesSpeler"));
		
		btnvoegtoe1.setText(taal.getString("VoegToe"));
		btnvoegtoe2.setText(taal.getString("VoegToe"));
		btnvoegtoe3.setText(taal.getString("VoegToe"));
		btnvoegtoe4.setText(taal.getString("VoegToe"));
		
		txtaantalSpelers.setText(String.format("%d %s", dc.geefDeelnemendeSpelers().size(), taal.getString("geselecteerdeSpelers")));
		txtselecteer.setText(taal.getString("Selecteer"));
		txtwelkom.setText(taal.getString("Strijd"));
		
		// pane verwijderen van speler 4 wanneer niet genoeg geregistreerde spelers
        if(dc.geefAantalSpelersInDatabank() < 4)
            Pane4.setVisible(false);
        
		// aanvullen lijsten
		for (Kleur k : dc.geefResterendeKleuren())
			cmbbKleur.getItems().add(taal.getLocale().getLanguage().equals("eng") ? k.getEngels() : k.getNederlands());
		
		for (SpelerDTO p : dc.geefResterendeSpelers())
			cmbbP1.getItems().add(p.gebruikersnaam());
		
		
		// Button enablen wanneer P1 beide ingevuld
		cmbbP1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateButtonState(cmbbP1, cmbbKleur, btnvoegtoe1);
        });

        cmbbKleur.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateButtonState(cmbbP1, cmbbKleur, btnvoegtoe1);
        });
	}
	
	private void updateButtonState(ComboBox<String> comboBoxPlayer, ComboBox<String> comboBoxColor, Button button) 
	{
        if (comboBoxPlayer.getValue() != null && comboBoxColor.getValue() != null) {
            button.setDisable(false); // Enable the button if both ComboBoxes have selections
        } else {
            button.setDisable(true); // Disable the button if any ComboBox doesn't have a selection
        }
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