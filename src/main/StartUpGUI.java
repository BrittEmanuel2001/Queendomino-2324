package main;

import java.io.File;
import java.security.SecureRandom;
import java.util.Optional;

import domein.DomeinController;
import gui.WelcomeScreenController;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Alert.AlertType;

import javafx.stage.Stage;

public class StartUpGUI extends Application
{
	private MediaPlayer mp;
	private DomeinController dc;
	private SecureRandom sr = new SecureRandom();
	private int nummer;

	@Override
	public void start(Stage startPaneel) 
	{
		try 
		{
			dc = new DomeinController();
			HostServices hostServices = getHostServices();
			music();
			WelcomeScreenController root = new WelcomeScreenController(dc, hostServices, mp, nummer);
			Scene scene = new Scene(root);
			startPaneel.setScene(scene);
			startPaneel.setResizable(false);
			startPaneel.setOnCloseRequest(event -> {
				Alert alert = new Alert(AlertType.CONFIRMATION);
		        alert.setTitle("Exit/Afsluiten");
		        alert.setHeaderText("Do you want to exit Queendomino?\n"
		        		+ "Wil je Queendomino verlaten?");
		        alert.setContentText("We hope you had a blast. Press OK to exit.\n"
		        		+ "Hopelijk had je veel plezier. Druk op OK om te stoppen.");
		        
		        Optional<ButtonType> result = alert.showAndWait();
		        if(result.isEmpty() || result.get().equals(ButtonType.CANCEL))
		            event.consume();
		        else Platform.exit();
            });
			startPaneel.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void music()
	{
		nummer = sr.nextInt(4);
		File audioFile = new File("src/images/musicQueendomino" + nummer + ".mp3");
        Media media = new Media(audioFile.toURI().toString());
        mp = new MediaPlayer(media);

        // MEDIAPLAYER NON STOP LATEN SPELEN (IN LOOP)
        mp.setCycleCount(MediaPlayer.INDEFINITE);
        mp.play();
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}