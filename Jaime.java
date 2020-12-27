package Jaime;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Group;
import javafx.scene.text.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;

public class Jaime extends Application{
	static Stage window;
	static int vancho=1240,vlargo=720; //ventana
	static int nivel = 0;
	Jugador jug = new Jugador(new Image(getClass().getResourceAsStream("img/jaime.png")),15, vlargo/2, 100,100, 7, 50);
	Enemigo[] enem = new Enemigo[3];
	public static void main(String[] args){
		launch(args);
	}
	public void start(Stage primaryStage){
		jug.col = new Colision(true);
		//jug.col.addCuadros(new Cuadro(jug.x+25,jug.y+0,50,100));
		jug.col.addRadial(jug.x+50, jug.y+50,50);
		window=primaryStage;
		Canvas lienzo = new Canvas(vancho,vlargo);
		Group grupo = new Group(lienzo);
		GraphicsContext idea = lienzo.getGraphicsContext2D();

		Entorno[] ent = new Entorno[3];
		ent[0] = new Entorno(vancho,vlargo,4);
		ent[0].setFondo(new Image(getClass().getResourceAsStream("img/fondo.png")));
		ent[0].add(jug);
		for(int i = 0;i<3;i++){
			enem[i] = new Enemigo(new Image(getClass().getResourceAsStream("img/enemigo1.png")), i*200+200,200,150,150, 5, 50);
			enem[i].col = new Colision(true);
			enem[i].col.addRadial(enem[i].x+75, enem[i].y+75, 90);
			ent[0].add(enem[i]);
		}
		Timeline clock = new Timeline(new KeyFrame(Duration.millis(10), e ->{
			ent[0].update(idea);
			jug.accion(ent[0]);
			for(Enemigo en:enem) en.accion(ent[0]);
		}));
		clock.setCycleCount(Timeline.INDEFINITE);
		Scene escena = new Scene(grupo);
        escena.setOnKeyPressed(e->{
			if(e.getEventType() == KeyEvent.KEY_PRESSED) jug.getAccion(e);

		});
        escena.setOnKeyReleased(e->{
			jug.stop(e);
			if(e.getCode() == KeyCode.F3 && !Entorno.colVisual) Entorno.colVisual = true;
			else if(e.getCode() == KeyCode.F3 && Entorno.colVisual) Entorno.colVisual = false;// Entorno.colVisual = false;	
		});
        lienzo.setOnMouseMoved(e->{
            
        });
        window.setScene(escena);
        window.show();
        clock.play();
	}
}