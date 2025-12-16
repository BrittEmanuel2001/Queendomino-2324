module Kingdomino_g32 {
	exports persistentie;
	exports cui;
	exports utils;
	exports gui;
	exports main;
	exports domein;
	exports testen;
	exports dto;
	exports exceptions;

	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.media;
	requires jsch;
	requires org.junit.jupiter.api;
	requires org.junit.jupiter.params;
	
	opens main to javafx.graphics, javafx.fxml;
	opens gui to java.fx.graphics, javafx.fxml;
}