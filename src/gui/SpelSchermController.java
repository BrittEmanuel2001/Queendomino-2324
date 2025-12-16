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
import dto.DominotegelDTO;
import dto.SpelerDTO;
import javafx.animation.PauseTransition;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;
import utils.Kleur;

public class SpelSchermController extends VBox
{
	private DomeinController dc;
	private WelcomeScreenController wsc;
	private ResourceBundle taal;
	private HostServices hs;
	private MediaPlayer mp;
	private int nummer;
	
	private SpelerDTO huidigeSpeler;
	private List<DominotegelDTO> startkolom;
	private List<DominotegelDTO> eindkolom;
	private int ronde;
	private int tegelsStartKolom;
	private int beurtteller = 0;
	private int startkolcounter = 0;
	
	// MENU
	@FXML
    private MenuItem menuItem1;
	@FXML
    private MenuItem menuItem2;
    @FXML
    private MenuItem menuItem3;
    @FXML
    private MenuItem menuItem4;
    
    // ALLE KONINKRIJKEN DIE GETOOND MOETEN WORDEN
    @FXML
    private GridPane koninkrijkOverzicht;
    @FXML
    private GridPane koninkrijk1;
    @FXML
    private GridPane koninkrijk2;
    @FXML
    private GridPane koninkrijk3;
    @FXML
    private GridPane koninkrijk4;
    
    // UITBREID PIJLEN    
    @FXML
    private ImageView arrowD;
    @FXML
    private ImageView arrowL;
    @FXML
    private ImageView arrowR;
    @FXML
    private ImageView arrowU;
    
    // TREKSTAPEL, START- EN EINDKOLOM
    @FXML
    private ImageView imgtrekstapel;
    @FXML
    private ImageView startkol1;
    @FXML
    private ImageView startkol2;
    @FXML
    private ImageView startkol3;
    @FXML
    private ImageView startkol4;
    @FXML
    private ImageView eindkol1;
    @FXML
    private ImageView eindkol2;
    @FXML
    private ImageView eindkol3;
    @FXML
    private ImageView eindkol4;
    
    // TEKST IN SPEL
    @FXML
    private Label txtRonde;
    @FXML
    private Text txtWarning;
    @FXML
    private Text txtkleur;
    @FXML
    private Text txtprompt;
    
    // PIONNEN START- EN EINDKOLOM
    @FXML
    private ImageView startkoning1;
    @FXML
    private ImageView startkoning2;
    @FXML
    private ImageView startkoning3;
    @FXML
    private ImageView startkoning4;
    @FXML
    private ImageView eindkoning1;
    @FXML
    private ImageView eindkoning2;
    @FXML
    private ImageView eindkoning3;
    @FXML
    private ImageView eindkoning4;
    
    
    // PLAATS DOMINOTEGEL VENSTER
    private ImageView clickedHoverVak = null;
    @FXML
    private Pane plaatsTegelVenster;
    @FXML
    private ImageView selectedL;
    @FXML
    private ImageView selectedR;
    @FXML
    private ImageView tePlaatsenTegel;
    private ImageView hoverVak;
    @FXML
    private GridPane aanKoninkrijkToevoegen;
    @FXML
    private Button btnPlaatsTegel;
    @FXML
    private Hyperlink cancelPlaatsen;
    @FXML
    private Button btnNietPlaatsen;
    
    
    // STANDAARDWAARDEN MEE TE GEVEN VAKKEN DOMINOTEGEL
 	private int V1X = -1;
    private int V1Y = -1;
    private int V2X = -1;
    private int V2Y = -1;
    
    private int reedsgespeeld = -1;
    private boolean einde = false;
    
    
    /**
     * Inladen van het spel. In deze methode worden de koninkrijken weergegeven, 
     * tekst prompts geset, alsook de start- en eindkolom werden ingesteld.
     * @param taal
     * @param dc
     * @param wsc
     * @param hs
     * @param mp 
     */
    public SpelSchermController(ResourceBundle taal, DomeinController dc, WelcomeScreenController wsc, HostServices hs, MediaPlayer mp, int nummer) 
	{
		this.dc = dc;
		this.wsc = wsc;
		this.taal = taal;
		this.hs = hs;
		this.mp = mp;
		
		// STARTEN VAN SPEL
		dc.maakSpel();
		dc.startSpel();
		
		// AANMAKEN ALLEREERSTE STARTKOLOM ZONDER RONDE GESPEELD
	    startkolom = dc.geefStartkolom();
	    tegelsStartKolom = startkolom.size();
		
		ronde = 0;
		huidigeSpeler = dc.geefSpelerAanDeBeurt(ronde);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SpelScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// MENU INSTELLINGEN
		menuItem3.setAccelerator(KeyCombination.keyCombination("Ctrl + X"));
		menuItem3.setOnAction(this::quit);

		menuItem1.setText(taal.getString("MenuTaal"));
		menuItem2.setText(taal.getString("geluid"));
		menuItem3.setText(taal.getString("MenuItemAfsluiten"));
		menuItem4.setText(taal.getString("MenuSpelregels"));
		
		btnPlaatsTegel.setText(taal.getString("plaats"));
		cancelPlaatsen.setText(taal.getString("annuleer"));
		
		// TONEN ACTIEF OVERZICHT KONINKRIJK (KONINKRIJK HUIDIGE SPELER)
		geefKoninkrijkGroot(huidigeSpeler.kleur(), koninkrijkOverzicht);
		
		// TONEN ALLE KONINKRIJKEN IN HET KLEIN
		geefKoninkrijk(dc.geefDeelnemendeSpelers().get(0).kleur(), koninkrijk1);
		geefKoninkrijk(dc.geefDeelnemendeSpelers().get(1).kleur(), koninkrijk2);
		geefKoninkrijk(dc.geefDeelnemendeSpelers().get(2).kleur(), koninkrijk3);
		if (dc.geefDeelnemendeSpelers().size() > 3)
			geefKoninkrijk(dc.geefDeelnemendeSpelers().get(3).kleur(), koninkrijk4);
		
		// PLAATS DOMINOTEGEL VENSTER NIET ZICHTBAAR
		plaatsTegelVenster.setVisible(false);
		selectedL.setVisible(false);
		selectedR.setVisible(false);
		
		// HOVER SETTINGS KONINKRIJK (IN BEGIN OOK NIET ZICHTBAAR)
		aanKoninkrijkToevoegen.setVisible(false);
		
		// UPDATEN VAN KONINKRIJK NAARGELANG SPELER AAN DE BEURT
		updatenKoninkrijk();
		
		// TITEL WIJZIGING
		String beurtKleur = String.format("%s", taal.getLocale().getLanguage().equals("eng") ? 
				huidigeSpeler.kleur().getEngels() : huidigeSpeler.kleur().getNederlands()+" ");
		
		txtRonde.setText(taal.getString("titelStart"));
		txtkleur.setText(beurtKleur + taal.getString("beurtIndicator"));
		txtprompt.setText(huidigeSpeler.gebruikersnaam() + taal.getString("KeuzeDominotegelGUI"));
		btnNietPlaatsen.setText(taal.getString("nietPlaatsen"));
		
		// EERSTE IMAGE INSTELLEN TREKSTAPEL
		int nummerTegel = dc.geefTrekstapel().get(0).nummer();
		imgtrekstapel.setImage(createImageDominotegel(nummerTegel, false));
				
		// IMAGES INSTELLEN STARTKOLOM
		stelAfbeeldingenStartkolomIn(false);
			
		// DRAAI DE AFBEELDINGEN VAN DE STARTKOLOM OM NA 1,5 SECONDE
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(event -> {stelAfbeeldingenStartkolomIn(true);});
        pause.play();
        
        if(dc.geefDeelnemendeSpelers().size() < 4)
			startkol4.setVisible(false);
        
        // ONZICHTBAAR ZETTEN ALLE KONINGEN + EINDKOLOM
        startkoning1.setVisible(false);
        startkoning2.setVisible(false);
        startkoning3.setVisible(false);
        startkoning4.setVisible(false);
        eindkol1.setVisible(false);
        eindkol2.setVisible(false);
        eindkol3.setVisible(false);
        eindkol4.setVisible(false);
        eindkoning1.setVisible(false);
        eindkoning2.setVisible(false);
        eindkoning3.setVisible(false);
        eindkoning4.setVisible(false);
        // ONZICHTBAAR ZETTEN PLAATS BUTTONS
        btnPlaatsTegel.setDisable(true);
        cancelPlaatsen.setVisible(false);
        cancelPlaatsen.setBorder(Border.EMPTY);
        cancelPlaatsen.setPadding(new Insets(4, 0, 4, 0));
	}

	
	
	
	
	
	
	
	
	/**
     * Deze methode bepaalt of alle spelers reeds een tegel hebben gekozen in de betreffende kolom.
     * Indien ze allemaal gekozen zijn vult hij de eindkolom, vernieuwt hij de trekstapel, veranderd naar de volgende ronde.
     * @param aantalTegels
     */
    private void gaNaarVolgendeRonde(int aantalTegels) 
    {
    	if(aantalTegels == 0)
        {    
    		ronde++;
    		
    		for(int i = 0; i < startkolom.size(); i++)
    			startkolom.remove(i);
    		dc.bepaalSpelvolgordeStart();
    		
    		// NIEUWE EINDKOLOM WORDT GEVULD
    		// SPELVOLGORDE RONDE WORDT BEPAALD
    		dc.speelRonde();
    	    eindkolom = dc.geefEindkolom();
    	    
    	    // IMAGE INSTELLEN TREKSTAPEL
    		int nrtegel = dc.geefTrekstapel().get(0).nummer();
    		imgtrekstapel.setImage(createImageDominotegel(nrtegel, false));
    	    
    		// TITELWIJZIGING RONDE
    		txtRonde.setText(taal.getString("ronde") + " " + ronde);
    		  
    		// IMAGES INSTELLEN EINDKOLOM
    		eindkol1.setVisible(true);
            eindkol2.setVisible(true);
            eindkol3.setVisible(true);
            eindkol4.setVisible(true);
    		stelAfbeeldingenEindkolomIn(false);
    			
    		// DRAAI DE AFBEELDINGEN VAN DE EINDKOLOM OM NA 1,5 SECONDE
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event -> {
            	stelAfbeeldingenEindkolomIn(true);
            });
            pause.play();
            
            // INDIEN GEEN 4 SPELERS, UITSCHAKELEN 4DE IMAGEVIEW 
            // (ANDERS HANDJE ZICHTBAAR MAAR KAN NIET KLIKKEN)
            if(dc.geefDeelnemendeSpelers().size() < 4)
    			eindkol4.setVisible(false);
            
            // INSTELLEN VOLGENDE SPELER AAN DE BEURT
            huidigeSpeler = dc.geefSpelerAanDeBeurt(beurtteller);
            
            // ... SPELER "KIES EEN TEGEL UIT DE EINDKOLOM"
            txtprompt.setText(huidigeSpeler.gebruikersnaam() + taal.getString("KeuzeDominotegelGUIEind"));
        }
    	else
    	{
    		// ... SPELER "KIES EEN TEGEL UIT DE STARTKOLOM"
    		huidigeSpeler = dc.geefSpelerAanDeBeurt(ronde);
    		txtprompt.setText(huidigeSpeler.gebruikersnaam() + taal.getString("KeuzeDominotegelGUI"));
    	}
    	
    	// AANDUIDEN WIE ER AAN DE BEURT IS BOVEN DIALOOGVENSTER
        String beurtKleur = String.format("%s", taal.getLocale().getLanguage().equals("eng") ? 
    			huidigeSpeler.kleur().getEngels() : huidigeSpeler.kleur().getNederlands()+" ");
        txtkleur.setText(beurtKleur + taal.getString("beurtIndicator"));
        
        // NIEUW GROOT KONINKRIJK SCHERM 
        // + WANNEER TEGEL GEPLAATST IS 
        // + NIEUW KONINKRIJKJE WORDT TRANSPARANTER GEZET
    	updatenKoninkrijk();
    	geefKoninkrijkGroot(huidigeSpeler.kleur(), koninkrijkOverzicht);
	}	

	
	
	
	
	
	
	
	
	private void stelAfbeeldingenStartkolomIn(boolean omgedraaid) 
	{
		if(ronde < 2)
		{
			if (!omgedraaid)
			{
				startkol1.setDisable(true);
				startkol2.setDisable(true);
				startkol3.setDisable(true);
				startkol4.setDisable(true);
			}
			else
			{
				startkol1.setDisable(false);
				startkol2.setDisable(false);
				startkol3.setDisable(false);
				startkol4.setDisable(false);
			}
		}
		
		for(int i = 0; i < dc.geefDeelnemendeSpelers().size(); i++)
		{
			int nummer = startkolom.get(i).nummer();
			switch(i)
			{
				case 0 -> startkol1.setImage(createImageDominotegel(nummer, omgedraaid));
				case 1 -> startkol2.setImage(createImageDominotegel(nummer, omgedraaid));
				case 2 -> startkol3.setImage(createImageDominotegel(nummer, omgedraaid));
				case 3 -> startkol4.setImage(createImageDominotegel(nummer, omgedraaid));
			}
		}
	}
	
	private void stelAfbeeldingenEindkolomIn(boolean omgedraaid) 
	{
		if (!omgedraaid)
		{
			eindkol1.setDisable(true);
			eindkol2.setDisable(true);
			eindkol3.setDisable(true);
			eindkol4.setDisable(true);
		}
		else
		{
			eindkol1.setDisable(false);
			eindkol2.setDisable(false);
			eindkol3.setDisable(false);
			eindkol4.setDisable(false);
		}
		
		if(dc.geefRonde() < 13)
		{
			for(int i = 0; i < dc.geefDeelnemendeSpelers().size(); i++)
			{
				int nummer = eindkolom.get(i).nummer();
				switch(i)
				{
					case 0 -> eindkol1.setImage(createImageDominotegel(nummer, omgedraaid));
					case 1 -> eindkol2.setImage(createImageDominotegel(nummer, omgedraaid));
					case 2 -> eindkol3.setImage(createImageDominotegel(nummer, omgedraaid));
					case 3 -> eindkol4.setImage(createImageDominotegel(nummer, omgedraaid));
				}
			}
		}
	}

	
	
	
	
	
	
	
	
	/**
	 * Instellen juiste afbeeldingen voor betreffende koninkrijken.
	 * @param nummer
	 * @param omdraaien
	 * @return
	 */
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
	
	/**
	 * Instellen juiste afbeeldingen voor betreffende dominotegels.
	 * @param nummer
	 * @param omdraaien
	 * @return
	 */
	private Image createImageDominotegel(int nummer, boolean omdraaien)
	{
		String pad = "";
		if(!omdraaien)
			pad = String.format("src/images/dominotegel/%dachterkant.png", nummer);
		else
			pad = String.format("src/images/dominotegel/%dvoorkant.png", nummer);
		return maakImage(pad);
	}
	
	/**
	 * Instellen juiste afbeeldingen voor betreffende koningen.
	 * @param nummer
	 * @param omdraaien
	 * @return
	 */
	private Image createImageKing(Kleur kleur) 
    {
		String pad = String.format("src/images/spelers/%s.png", kleur.getNederlands());		
        return maakImage(pad);       
    }
	
	/**
	 * Afhandeling van try & catch om image aan te maken die kan worden doorgegeven aan een Imageview.
	 * @param pad
	 * @return
	 */
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

	
	
	
	
	
	
	
	
	/**
	 * Alle koninkrijken worden weergegeven op het speelgebied.
	 * @param kleur
	 * @param koninkrijk
	 */
	private void geefKoninkrijk(Kleur kleur, GridPane koninkrijk)
	{
		Vak[][] kleinKoninkrijk = dc.geefKoninkrijkHuidigeSpeler(kleur);
		for (int x = 0; x < kleinKoninkrijk.length; x++)
	    {
	        for (int y = 0; y < kleinKoninkrijk[x].length; y++)
	        {
	        	Image image = createImage(kleur, kleinKoninkrijk, x, y);
	        	ImageView imageView = new ImageView(image);
	        	
	        	imageView.setFitWidth(20);
                imageView.setFitHeight(20);
                koninkrijk.add(imageView, y, x);
	        }
	    }
	}
	
	/**
	 * Alle koninkrijken worden weergegeven op het speelgebied.
	 * @param kleur
	 * @param koninkrijk
	 */
	private void geefKoninkrijkGroot(Kleur kleur, GridPane koninkrijk)
	{
		Vak[][] kleinKoninkrijk = dc.geefKoninkrijkHuidigeSpeler(kleur);
		for (int x = 0; x < kleinKoninkrijk.length; x++)
	    {
	        for (int y = 0; y < kleinKoninkrijk[x].length; y++)
	        {
	        	Image image = createImage(kleur, kleinKoninkrijk, x, y);
	        	ImageView imageView = new ImageView(image);
	        	
	        	imageView.setFitWidth(60);
	        	imageView.setFitHeight(60);
	        	
                koninkrijk.add(imageView, y, x);
                
                kanUitbreidenControle(dc.kanUitbreidenRechts(kleur), arrowL);
                kanUitbreidenControle(dc.kanUitbreidenBoven(kleur), arrowD);
                kanUitbreidenControle(dc.kanUitbreidenLinks(kleur), arrowR);
                kanUitbreidenControle(dc.kanUitbreidenOnder(kleur), arrowU);
	        }
	    }
	}

	/**
	 * Updaten van koninkrijken indien er uitgebreid wordt 
	 * of als er een nieuwe tegel wordt geplaatst.
	 */
	private void updatenKoninkrijk()
    {
    	koninkrijk1.setOpacity(1);
    	koninkrijk2.setOpacity(1);
    	koninkrijk3.setOpacity(1);
    	koninkrijk4.setOpacity(1);
        	
    	if(huidigeSpeler.kleur().equals(dc.geefDeelnemendeSpelers().get(0).kleur()))
    	{
    		geefKoninkrijk(dc.geefDeelnemendeSpelers().get(0).kleur(), koninkrijk1);
    		koninkrijk1.setOpacity(0.4);
    	}
    	
    	else if(huidigeSpeler.kleur().equals(dc.geefDeelnemendeSpelers().get(1).kleur()))
    	{
    		geefKoninkrijk(dc.geefDeelnemendeSpelers().get(1).kleur(), koninkrijk2);
    		koninkrijk2.setOpacity(0.4);
    	}
    	
    	else if(huidigeSpeler.kleur().equals(dc.geefDeelnemendeSpelers().get(2).kleur()))
    	{
    		geefKoninkrijk(dc.geefDeelnemendeSpelers().get(2).kleur(), koninkrijk3);
    		koninkrijk3.setOpacity(0.4);
    	}
    	
    	else if(huidigeSpeler.kleur().equals(dc.geefDeelnemendeSpelers().get(3).kleur()))
    	{
    		geefKoninkrijk(dc.geefDeelnemendeSpelers().get(3).kleur(), koninkrijk4);
    		koninkrijk4.setOpacity(0.4);
    	}
    }
	
	/**
	 * Disablen en enablen van pijlen indien mogelijkheid om uit te breiden.
	 * @param mogelijk
	 * @param arrow
	 */
	private void kanUitbreidenControle(boolean mogelijk, ImageView arrow) 
	{
		if(mogelijk) {
			arrow.setDisable(false);
			arrow.setOpacity(1);
    	} else {
    		arrow.setDisable(true);
    		arrow.setOpacity(0);
    	}
	}
    
	/**
	 * Uitbreiden langs de onderkant (kasteel beweegt naar boven).
	 * @param event
	 */
    @FXML
    void naarBoven(MouseEvent event) 
    {
    	dc.breidKoninkrijkUit(huidigeSpeler.kleur(), 3);
    	geefKoninkrijkGroot(huidigeSpeler.kleur(), koninkrijkOverzicht);
    	updatenKoninkrijk();
    }
    
    /**
	 * Uitbreiden langs de rechterkant (kasteel beweegt naar links).
	 * @param event
	 */
    @FXML
    void naarLinks(MouseEvent event) 
    {
    	dc.breidKoninkrijkUit(huidigeSpeler.kleur(), 2);
    	geefKoninkrijkGroot(huidigeSpeler.kleur(), koninkrijkOverzicht);
    	updatenKoninkrijk();
    }
    
    /**
	 * Uitbreiden langs de bovenkant (kasteel beweegt naar onder).
	 * @param event
	 */
    @FXML
    void naarOnder(MouseEvent event) 
    {   
    	dc.breidKoninkrijkUit(huidigeSpeler.kleur(), 1);
    	geefKoninkrijkGroot(huidigeSpeler.kleur(), koninkrijkOverzicht);
    	updatenKoninkrijk();
    }
    
    /**
	 * Uitbreiden langs de linkerkant (kasteel beweegt naar rechts).
	 * @param event
	 */
    @FXML
    void naarRechts(MouseEvent event) 
    {
    	dc.breidKoninkrijkUit(huidigeSpeler.kleur(), 4);
    	geefKoninkrijkGroot(huidigeSpeler.kleur(), koninkrijkOverzicht);
    	updatenKoninkrijk();
    }

	
	
	
	
	
	
	
	
    /**
     * Plaatsen van de koningen op startkolom
     * @param event
     */
	@FXML
    void plaatskoning1(MouseEvent event) 
	{
		if(dc.geefRonde() == 13)
        {
			dc.geefVolgendeTegelUitStartkolom();
			reedsgespeeld++;
			dc.kiesDominotegel(startkolom.get(0), startkolom);
			dc.kiesDominotegel(startkolom.get(1), startkolom);
			dc.kiesDominotegel(startkolom.get(2), startkolom);
			
			if(dc.geefDeelnemendeSpelers().size() > 3)
				dc.kiesDominotegel(startkolom.get(3), startkolom);
			//DominotegelDTO tegelDTOStart = dc.geefDominotegelDTOGelinktAanKoning(huidigeSpeler.kleur());
			startkol1.setDisable(true);
			startkol2.setDisable(false);
			startkol3.setDisable(true);
			startkol4.setDisable(true);
			
			plaatsTegelVenster(startkolom.get(0));
			
			// WEERGEVEN SPELER AAN DE BEURT
		    huidigeSpeler = dc.geefSpelerAanDeBeurt(beurtteller);
	        
	        String beurtKleur = String.format("%s", taal.getLocale().getLanguage().equals("eng") ? 
	    			huidigeSpeler.kleur().getEngels() : huidigeSpeler.kleur().getNederlands()+" ");
	        
	        txtkleur.setText(beurtKleur + taal.getString("beurtIndicator"));
	    	txtprompt.setText(huidigeSpeler.gebruikersnaam() + taal.getString("KeuzeDominotegelGUILaatsteRonde"));
	    	
	    	updatenKoninkrijk();
	    	geefKoninkrijkGroot(huidigeSpeler.kleur(), koninkrijkOverzicht);
	    	//dc.geefVolgendeTegelUitStartkolom();
	    	
	    	startkol1.setVisible(false);
	    	startkoning1.setVisible(false);
        }
		else plaatsKoningen(startkoning1, startkol1, 0);
	}
    @FXML
    void plaatskoning2(MouseEvent event) 
    {
    	if(dc.geefRonde() == 13)
        {

	    	dc.geefVolgendeTegelUitStartkolom();
    		reedsgespeeld++;
    		// DominotegelDTO tegelDTOStart = dc.geefDominotegelDTOGelinktAanKoning(huidigeSpeler.kleur());
			startkol1.setDisable(true);
			startkol2.setDisable(true);
			startkol3.setDisable(false);
			startkol4.setDisable(true);
			
			plaatsTegelVenster(startkolom.get(1));
			
			// WEERGEVEN SPELER AAN DE BEURT
		    huidigeSpeler = dc.geefSpelerAanDeBeurt(beurtteller);
	        
	        String beurtKleur = String.format("%s", taal.getLocale().getLanguage().equals("eng") ? 
	    			huidigeSpeler.kleur().getEngels() : huidigeSpeler.kleur().getNederlands()+" ");
	        
	        txtkleur.setText(beurtKleur + taal.getString("beurtIndicator"));
	    	txtprompt.setText(huidigeSpeler.gebruikersnaam() + taal.getString("KeuzeDominotegelGUILaatsteRonde"));
	    	
	    	updatenKoninkrijk();
	    	geefKoninkrijkGroot(huidigeSpeler.kleur(), koninkrijkOverzicht);
	    	//dc.geefVolgendeTegelUitStartkolom();
	    	
	    	startkol2.setVisible(false);
	    	startkoning2.setVisible(false);
        }
		else plaatsKoningen(startkoning2, startkol2, 1);
    }
	@FXML
    void plaatskoning3(MouseEvent event) 
	{
		if(dc.geefRonde() == 13)
        {

	    	dc.geefVolgendeTegelUitStartkolom();
			reedsgespeeld++;
			//DominotegelDTO tegelDTOStart = dc.geefDominotegelDTOGelinktAanKoning(huidigeSpeler.kleur());
			startkol1.setDisable(true);
			startkol2.setDisable(true);
			startkol3.setDisable(true);
			startkol4.setDisable(false);
			
			if(dc.geefDeelnemendeSpelers().size() == 3)
				einde = true;
			
			startkol3.setVisible(false);
			startkoning3.setVisible(false);
			
			plaatsTegelVenster(startkolom.get(2));
			
			if(dc.geefDeelnemendeSpelers().size() == 4)
			{
				// WEERGEVEN SPELER AAN DE BEURT
				huidigeSpeler = dc.geefSpelerAanDeBeurt(beurtteller);
			        
			    String beurtKleur = String.format("%s", taal.getLocale().getLanguage().equals("eng") ? 
			    	huidigeSpeler.kleur().getEngels() : huidigeSpeler.kleur().getNederlands()+" ");
			        
			    txtkleur.setText(beurtKleur + taal.getString("beurtIndicator"));
			    txtprompt.setText(huidigeSpeler.gebruikersnaam() + taal.getString("KeuzeDominotegelGUILaatsteRonde"));
			    	
			    updatenKoninkrijk();
			    geefKoninkrijkGroot(huidigeSpeler.kleur(), koninkrijkOverzicht);
			    //dc.geefVolgendeTegelUitStartkolom();
			}
        }
		else plaatsKoningen(startkoning3, startkol3, 2);
	}
    @FXML
    void plaatskoning4(MouseEvent event) 
    {
    	if(dc.geefRonde() == 13)
        {
    		dc.geefVolgendeTegelUitStartkolom();
    		reedsgespeeld++;
    		//DominotegelDTO tegelDTOStart = dc.geefDominotegelDTOGelinktAanKoning(huidigeSpeler.kleur());
			startkol1.setDisable(true);
			startkol2.setDisable(true);
			startkol3.setDisable(true);
			startkol4.setDisable(true);
			
			einde = true;
			startkol4.setVisible(false);
			startkoning4.setVisible(false);
			plaatsTegelVenster(startkolom.get(3));
        }
		else plaatsKoningen(startkoning4, startkol4, 3);
    }
    
    private void plaatsKoningen(ImageView startkoning, ImageView startkol, int nr)
    {
    	startkoning.setVisible(true);
    	dc.kiesDominotegel(startkolom.get(nr), startkolom);
    	
    	startkoning.setImage(createImageKing(huidigeSpeler.kleur()));
    	startkol.setDisable(true);
            
        tegelsStartKolom--;
        gaNaarVolgendeRonde(tegelsStartKolom);   
    }
    
    /**
     * 
     */
    private void updatenKoningenStartkolom()
    {
    	if(dc.geefStartkolom().get(0).koning() == null)    
            startkoning1.setVisible(false);
        if(dc.geefStartkolom().get(1).koning() == null)    
            startkoning2.setVisible(false);
        if(dc.geefStartkolom().get(2).koning() == null)    
            startkoning3.setVisible(false);
        if(dc.geefDeelnemendeSpelers().size() > 3 && dc.geefStartkolom().get(3).koning() == null)    
            startkoning4.setVisible(false);
    }

    /**
     * Plaatsen van de koningen op eindkolom + plaatsen van tegels
     * @param event
     */
    @FXML
    void eindplaatskoning1(MouseEvent event) {eindplaatsKoningen(eindkoning1, eindkol1, 0);}
    @FXML
    void eindplaatskoning2(MouseEvent event) {eindplaatsKoningen(eindkoning2, eindkol2, 1);}
    @FXML
    void eindplaatskoning3(MouseEvent event) {eindplaatsKoningen(eindkoning3, eindkol3, 2);}
    @FXML
    void eindplaatskoning4(MouseEvent event) {eindplaatsKoningen(eindkoning4, eindkol4, 3);}
    
    private void eindplaatsKoningen(ImageView koning, ImageView eindkol, int index)
    {
    	dc.geefVolgendeTegelUitStartkolom();
    	koning.setVisible(true);
    	
    	// VERPLAATSEN KONING
    	DominotegelDTO tegelDTOStart = dc.geefDominotegelDTOGelinktAanKoning(huidigeSpeler.kleur());
    	dc.speelBeurt(eindkolom.get(index), eindkolom, huidigeSpeler.kleur());
		updatenKoningenStartkolom();
		
		koning.setImage(createImageKing(huidigeSpeler.kleur()));
        eindkol.setDisable(true);
        
        switch(startkolcounter)
        {
        	case 0 -> {
        		startkol1.setVisible(false);
            	startkoning1.setVisible(false);
        	}
        	
        	case 1 -> {
        		startkol2.setVisible(false);
            	startkoning2.setVisible(false);
        	}
        	
        	case 2 -> {
        		startkol3.setVisible(false);
            	startkoning3.setVisible(false);
        	}
        	
        	case 3 -> {
        		startkol4.setVisible(false);
            	startkoning4.setVisible(false);
        	}
        }
    	startkolcounter++;
      
        plaatsTegelVenster(tegelDTOStart);
    }

	
	private void plaatsTegelVenster(DominotegelDTO tegelDTOStart)
	{
		if(startkolcounter == dc.geefDeelnemendeSpelers().size())
		{
			startkolcounter = 0;
			
			startkol1.setVisible(true);
        	startkoning1.setVisible(true);
        	
        	startkol2.setVisible(true);
        	startkoning2.setVisible(true);
        	
        	startkol3.setVisible(true);
        	startkoning3.setVisible(true);
        	
        	startkol4.setVisible(true);
        	startkoning4.setVisible(true);
		}
		
		// TONEN WELKE TEGEL TE PLAATSEN
        plaatsTegelVenster.setVisible(true);
        selectedL.setVisible(true);
        tePlaatsenTegel.setImage(createImageDominotegel(tegelDTOStart.nummer(), true));
        
        // TEGEL PLAATSEN
        txtprompt.setText(huidigeSpeler.gebruikersnaam() + taal.getString("plaatsJeTegel1GUI"));
        aanKoninkrijkToevoegen.setVisible(true);
 
        Vak[][] tijdelijkKoninkrijk = dc.geefKoninkrijkHuidigeSpeler(huidigeSpeler.kleur());
        ImageView[][] hoverVakken = new ImageView[tijdelijkKoninkrijk.length][tijdelijkKoninkrijk[0].length];
        
        // HOVER GRIDPANE LEEGMAKEN VOOR NIEUWE BIJPASSENDE HOVERVAKKEN
        if(dc.geefRonde() > 2 || beurtteller != 0)
        {
        	for (int i = 0; i < 25; i++)
        		aanKoninkrijkToevoegen.getChildren().remove(0);
        }
        
        // HOVER GRID OPBOUWEN EN COORDINATEN WANNEER ON CLICK MEEGEVEN
		for (int x = 0; x < tijdelijkKoninkrijk.length; x++)
	        for (int y = 0; y < tijdelijkKoninkrijk[x].length; y++)
	        {
	        	// GRID OPVULLEN MET HOVERVAKKEN MET BIJPASSENDE KLEUR
	        	String lokaalPad = String.format("src/images/%s/plus.png", huidigeSpeler.kleur().getNederlands());
	            hoverVak = hoverVakken[x][y];
	    		hoverVak = new ImageView();
	    		hoverVak.setImage(maakImage(lokaalPad));
	    		hoverVak.setFitWidth(60);
	    		hoverVak.setFitHeight(60);
	    		hoverVak.setOpacity(0);
	    		
	    		int coordinaatX = x;
                int coordinaatY = y;
	    		aanKoninkrijkToevoegen.add(hoverVak, y, x);
	    		
	    		// RESETTEN ERROR TEXT
                txtWarning.setText("");
                
                // HOVER EN NO HOVER INSTELLEN
                hoverVak.setOnMouseEntered(e -> {
                	aanKoninkrijkToevoegen.getChildren().get(coordinaatX * tijdelijkKoninkrijk.length + coordinaatY).setOpacity(0.75);
                	aanKoninkrijkToevoegen.getChildren().get(coordinaatX * tijdelijkKoninkrijk.length + coordinaatY).setStyle("-fx-cursor:hand");});
                hoverVak.setOnMouseExited(e -> { 
                    aanKoninkrijkToevoegen.getChildren().get(coordinaatX * tijdelijkKoninkrijk.length + coordinaatY).setOpacity(0);
                    aanKoninkrijkToevoegen.getChildren().get(coordinaatX * tijdelijkKoninkrijk.length + coordinaatY).setStyle("-fx-cursor:default");});
                
                // ON CLICKED COORDINATEN MEEGEVEN
                hoverVak.setOnMouseClicked(e -> 
                {
                	// EERSTE VAK KIEZEN
                	if (V1X == -1 && V1Y == -1 && V2X == -1 && V2Y == -1) 
                    {
                        this.V1X = coordinaatX;
                        this.V1Y = coordinaatY;
                        
                        clickedHoverVak = (ImageView) e.getSource();
                        selectedL.setVisible(false);
                        selectedR.setVisible(true);
                        cancelPlaatsen.setVisible(true);

                        // HOVER VOOR DIT VAK VERANDERD NAAR "SELECTED" IMAGE
                        String extraPad = String.format("src/images/%s/select.png", huidigeSpeler.kleur().getNederlands());
                        clickedHoverVak.setImage(maakImage(extraPad));
                        clickedHoverVak.setOnMouseEntered(null);
                        clickedHoverVak.setOnMouseExited(null);

                        // VOLGENDE TEKST PROMPT
                        txtprompt.setText(huidigeSpeler.gebruikersnaam() + taal.getString("plaatsJeTegel2GUI"));
                    } 
                	
                	// TWEEDE VAK KIEZEN
                    else if (V1X != -1 && V1Y != -1 && V2X == -1 && V2Y == -1) 
                    {
                        this.V2X = coordinaatX;
                        this.V2Y = coordinaatY;
                        clickedHoverVak = (ImageView) e.getSource();
                        
                        selectedL.setVisible(false);
                        selectedR.setVisible(false);
                        
                        // HOVER VOOR DIT VAK VERANDERD NAAR "SELECTED" IMAGE
                        String extraPad = String.format("src/images/%s/select.png", huidigeSpeler.kleur().getNederlands());
                        clickedHoverVak.setImage(maakImage(extraPad));
                        clickedHoverVak.setOnMouseEntered(null);
                        clickedHoverVak.setOnMouseExited(null);
                        
                        btnPlaatsTegel.setDisable(false);
                    }
                });   	
	        }
	}
	
	
	
	
	
	
    /**
     * Deze methode is de actie wanneer men klikt op de "plaats je tegel" knop.
     * Indien beide coordinaten correct zijn ingegeven wordt de dominotegel in je koninkrijk geplaatst.
     * Indien niet, geeft hij een warning in je tekst box.
     * @param event
     */
    @FXML
    void plaatsTegel(MouseEvent event) 
    {
    	String lokaalPad = String.format("src/images/%s/plus.png", huidigeSpeler.kleur().getNederlands());
    	boolean gelukt = false;
    	
    	try 
    	{
			if (V1X != -1 && V1Y != -1 && V2X != -1 && V2Y != -1)
			{
				dc.plaatsDominoTegel(huidigeSpeler.kleur(), this.V1X, this.V1Y, this.V2X, this.V2Y);
				updatenKoninkrijk();
				geefKoninkrijkGroot(huidigeSpeler.kleur(), koninkrijkOverzicht);
				
				gelukt = true;
			}
		} catch (IllegalArgumentException iae) {
			txtWarning.setText(taal.getString(iae.getMessage()));
			resetWanneerNietGelukt(lokaalPad);
        }
    	
    	// INDIEN GELUKT WORDT HET HOVER GRID VOLLEDIG GERESET EN ONZICHTBAAR GEMAAKT, KLAAR VOOR DE VOLGENDE SPELER.
    	if(gelukt)
    	{
    		if(einde)
    		{
    			dc.berekenScores();
    			dc.isEindeSpel();
    			getScene().setRoot(new WinnaarSchermController(taal, dc, wsc, hs, mp, nummer));
    		}
    		else
    			resetVakPlaatsen(lokaalPad);
    	}
    }
    
    private void resetWanneerNietGelukt(String lokaalPad)
    {
        V1X = -1; V1Y = -1; V2X = -1; V2Y = -1;

        cancelPlaatsen.setVisible(false);
        btnPlaatsTegel.setDisable(true);
        hoverVak.setOnMouseEntered(null);
        hoverVak.setOnMouseExited(null);
        hoverVak.setImage(null);
        txtprompt.setText(huidigeSpeler.gebruikersnaam() + taal.getString("plaatsJeTegel1GUI"));
        
        selectedR.setVisible(false);
        selectedL.setVisible(true);
        
        for (Node node : aanKoninkrijkToevoegen.getChildren())
            if (node instanceof ImageView) 
            {
                ImageView imageView = (ImageView) node;
                imageView.setImage(null);
                imageView.setImage(maakImage(lokaalPad)); // Stel de afbeelding in op "plus.png"
                node.setOpacity(0);
                node.setOnMouseEntered(hover -> {imageView.setOpacity(0.75); imageView.setStyle("-fx-cursor:hand");});
                node.setOnMouseExited(nohover -> {imageView.setOpacity(0); imageView.setStyle("-fx-cursor:default");});
            }
    }

	private void resetVakPlaatsen(String lokaalPad) 
	{		
		btnPlaatsTegel.setDisable(true);
		selectedR.setVisible(false);
		selectedL.setVisible(true);
		
		plaatsTegelVenster.setVisible(false);
        aanKoninkrijkToevoegen.setVisible(false);
        txtWarning.setText("");
         
        for (Node node : aanKoninkrijkToevoegen.getChildren())
        	if (node instanceof ImageView) 
            {
        		ImageView imageView = (ImageView) node;
                imageView.setImage(null);
                imageView.setImage(maakImage(lokaalPad)); // Stel de afbeelding in op "plus.png"
                node.setOpacity(0);
                node.setOnMouseEntered(hover -> {imageView.setOpacity(0.75); imageView.setStyle("-fx-cursor:hand");});
                node.setOnMouseExited(nohover -> {imageView.setOpacity(0); imageView.setStyle("-fx-cursor:default");});
            }
         
        V1X = -1; V1Y = -1; V2X = -1; V2Y = -1;
         
        if(!dc.isEindeRonde() || reedsgespeeld > -1)
        {        	
        	beurtteller++;
            huidigeSpeler = dc.geefSpelerAanDeBeurt(beurtteller);
            
            String beurtKleur = String.format("%s", taal.getLocale().getLanguage().equals("eng") ? 
        			huidigeSpeler.kleur().getEngels() : huidigeSpeler.kleur().getNederlands()+" ");
            
            txtkleur.setText(beurtKleur + taal.getString("beurtIndicator"));
        	txtprompt.setText(huidigeSpeler.gebruikersnaam() + taal.getString("KeuzeDominotegelGUIEind"));
        	
        	updatenKoninkrijk();
        	geefKoninkrijkGroot(huidigeSpeler.kleur(), koninkrijkOverzicht);
        }
        else
        {
        	if(dc.geefRonde() <= 11)
        	{
        		beurtteller = 0;
            	ronde++;
        		dc.resetStartkolom();
        		
        		// TITELWIJZIGING RONDE
        		txtRonde.setText(taal.getString("ronde") + " " + ronde);
        		
        		// NIEUWE EINDKOLOM WORDT GEVULD
        		// SPELVOLGORDE RONDE WORDT BEPAALD
        		dc.speelRonde();
        		startkolom = dc.geefStartkolom();
        	    eindkolom = dc.geefEindkolom();
        	    
        	    // STARTKOLOM WORDT GEDISABLED
        	    startkol1.setDisable(true);
        	    startkol2.setDisable(true);
        	    startkol3.setDisable(true);
        	    startkol4.setDisable(true);
        	    
        	    // KONINGEN VERPLAATSEN
        	    startkoning1.setVisible(true);
        	    startkoning1.setImage(createImageKing(dc.geefSpelvolgorde().get(0).kleur()));
        	    startkoning2.setVisible(true);
        	    startkoning2.setImage(createImageKing(dc.geefSpelvolgorde().get(1).kleur()));
        	    startkoning3.setVisible(true);
        	    startkoning3.setImage(createImageKing(dc.geefSpelvolgorde().get(2).kleur()));
        	    
        	    if(dc.geefDeelnemendeSpelers().size() > 3)
        	    {
        	    	startkoning4.setVisible(true);
            	    startkoning4.setImage(createImageKing(dc.geefSpelvolgorde().get(3).kleur()));
            	    eindkoning4.setVisible(false);
        	    }
        	    eindkoning1.setVisible(false);
        	    eindkoning2.setVisible(false);
        	    eindkoning3.setVisible(false);
        	    
        	    // UPDATEN GUI START- EN EINDKOLOM
        	    stelAfbeeldingenStartkolomIn(true);
        	    stelAfbeeldingenEindkolomIn(false);
        	    
        	    // DRAAI DE AFBEELDINGEN VAN DE EINDKOLOM OM NA 1,5 SECONDE
                PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
                pause.setOnFinished(event -> {
                	stelAfbeeldingenEindkolomIn(true);
                });
                pause.play();
                
                // EERSTE IMAGE INSTELLEN TREKSTAPEL
                if(dc.geefTrekstapel().size() != 0)
                {
                	int nummerTegel = dc.geefTrekstapel().get(0).nummer();
                	imgtrekstapel.setImage(createImageDominotegel(nummerTegel, false));
                }
                else
                	imgtrekstapel.setVisible(false);
        	    
        		// WEERGEVEN SPELER AAN DE BEURT
        	    huidigeSpeler = dc.geefSpelerAanDeBeurt(beurtteller);
                
                String beurtKleur = String.format("%s", taal.getLocale().getLanguage().equals("eng") ? 
            			huidigeSpeler.kleur().getEngels() : huidigeSpeler.kleur().getNederlands()+" ");
                
                txtkleur.setText(beurtKleur + taal.getString("beurtIndicator"));
            	txtprompt.setText(huidigeSpeler.gebruikersnaam() + taal.getString("KeuzeDominotegelGUIEind"));
            	
            	updatenKoninkrijk();
            	geefKoninkrijkGroot(huidigeSpeler.kleur(), koninkrijkOverzicht);
        	}
        	else
        	{
        		reedsgespeeld = 0;
        		beurtteller = 0;
            	ronde++;
        		dc.resetStartkolom();
        		startkolom = dc.geefStartkolom();
        		
        		// TITELWIJZIGING RONDE
        		txtRonde.setText(taal.getString("laatsteRonde"));
        		
        		// NIEUWE EINDKOLOM WORDT GEVULD
        		// SPELVOLGORDE RONDE WORDT BEPAALD
        		dc.speelRonde();
        		
        		// EINDKOLOM ONZICHTBAAR ZETTEN
        		eindkol1.setVisible(false);
        		eindkol2.setVisible(false);
        		eindkol3.setVisible(false);
        		eindkol4.setVisible(false);
        	    
        	    // KONINGEN VERPLAATSEN
        	    startkoning1.setVisible(true);
        	    startkoning1.setImage(createImageKing(dc.geefSpelvolgorde().get(0).kleur()));
        	    startkoning2.setVisible(true);
        	    startkoning2.setImage(createImageKing(dc.geefSpelvolgorde().get(1).kleur()));
        	    startkoning3.setVisible(true);
        	    startkoning3.setImage(createImageKing(dc.geefSpelvolgorde().get(2).kleur()));
        	    
        	    if(dc.geefDeelnemendeSpelers().size() > 3)
        	    {
        	    	startkoning4.setVisible(true);
            	    startkoning4.setImage(createImageKing(dc.geefSpelvolgorde().get(3).kleur()));
            	    eindkoning4.setVisible(false);
        	    }
        	    eindkoning1.setVisible(false);
        	    eindkoning2.setVisible(false);
        	    eindkoning3.setVisible(false);
        	    
        	    // UPDATEN GUI START- EN EINDKOLOM
        	    stelAfbeeldingenStartkolomIn(true);
        	    
        		// WEERGEVEN SPELER AAN DE BEURT
        	    huidigeSpeler = dc.geefSpelerAanDeBeurt(beurtteller);
                
                String beurtKleur = String.format("%s", taal.getLocale().getLanguage().equals("eng") ? 
            			huidigeSpeler.kleur().getEngels() : huidigeSpeler.kleur().getNederlands()+" ");
                
                txtkleur.setText(beurtKleur + taal.getString("beurtIndicator"));
            	txtprompt.setText(huidigeSpeler.gebruikersnaam() + taal.getString("KeuzeDominotegelGUILaatsteRonde"));
            	
            	// STARTKOLOM WORDT GEDISABLED
        	    startkol1.setDisable(false);
        	    startkol2.setDisable(true);
        	    startkol3.setDisable(true);
        	    startkol4.setDisable(true);
            	
            	updatenKoninkrijk();
            	geefKoninkrijkGroot(huidigeSpeler.kleur(), koninkrijkOverzicht);
        	}
        	
        }
	}

	
	
	
	
	
	
	
	
    // ANDERE
	
    @FXML
    void displayRules(ActionEvent event) 
    {
    	File file = new File("src/images/Spelregels_kingdomino.pdf");
    	hs.showDocument(file.getAbsolutePath());
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
    void cancelPlaatsen(MouseEvent event) 
	{
		String lokaalPad = String.format("src/images/%s/plus.png", huidigeSpeler.kleur().getNederlands());
		resetWanneerNietGelukt(lokaalPad);
    }
	
	@FXML
    void onmogelijkClick(MouseEvent event) 
	{
		if(einde)
		{
			dc.berekenScores();
			dc.isEindeSpel();
			getScene().setRoot(new WinnaarSchermController(taal, dc, wsc, hs, mp, nummer));
		}
		else if(!dc.zijnErNogMogelijkeZetten(huidigeSpeler.kleur()))
			resetVakPlaatsen(String.format("src/images/%s/plus.png", huidigeSpeler.kleur().getNederlands()));
		else
			txtWarning.setText(taal.getString("beterKijken"));
    }
	
	@FXML
    void plaatsNoHover(MouseEvent event) {btnPlaatsTegel.setStyle("-fx-underline:false; -fx-background-color:none");}
    @FXML
    void plaatsHover(MouseEvent event) {btnPlaatsTegel.setStyle("-fx-underline:true; -fx-background-color:none");}
    @FXML
    void onmogelijkHover(MouseEvent event) {btnNietPlaatsen.setStyle("-fx-underline:true; -fx-background-color:#3b322e; -fx-background-radius:10px");}
    @FXML
    void onmogelijkNoHover(MouseEvent event) {btnNietPlaatsen.setStyle("-fx-underline:false; -fx-background-color:#3b322e; -fx-background-radius:10px");}
    
    @FXML
    void geluidAanUit(ActionEvent event)
    {
    	if(mp.getStatus() == MediaPlayer.Status.PLAYING)
    		mp.pause();
    	else
    		mp.play();
    }
}