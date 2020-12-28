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
import javafx.scene.Cursor;

public class Jaime extends Application{
	static Stage window;
	static int vancho=1280,vlargo=700; //ventana
	static int nivel = 0;
	Jugador jug = new Jugador(new Image(getClass().getResourceAsStream("img/jaime.png")),vancho/2, vlargo/2, 100,100, 7, 25,100);
	Enemigo[] enem1 = new Enemigo[3];
	Enemigo[] enem2 = new Enemigo[5];
	public static void main(String[] args){
		launch(args);
	}
	public void start(Stage primaryStage){
		jug.col = new Colision(true);
		//jug.col.addCuadros(new Cuadro(jug.x+25,jug.y+0,50,100));
		jug.col.addRadial(jug.x+50, jug.y+50,50);
		jug.addMira(new Image(getClass().getResourceAsStream("img/mira.png")),vancho/2, vlargo/2, 25,25);
		window=primaryStage;
		Canvas lienzo = new Canvas(vancho,vlargo);
		Group grupo = new Group(lienzo);
		GraphicsContext idea = lienzo.getGraphicsContext2D();

		Entorno[] ent = new Entorno[3];
		ent[0] = new Entorno(vancho,vlargo,4);
		ent[1] = new Entorno(vancho,vlargo,6);
		for(int i = 0;i<3;i++){
			enem1[i] = new Enemigo(new Image(getClass().getResourceAsStream("img/enemigo1.png")), i*200+200,200,150,150, 5, 10,100);
			enem1[i].col = new Colision(true);
			enem1[i].col.addRadial(enem1[i].x+75, enem1[i].y+75, 90);
			ent[0].add(enem1[i]);
			ent[0].addEnem(enem1[i]);
		}
		for(int i = 0;i<5;i++){
			enem2[i] = new Enemigo(new Image(getClass().getResourceAsStream("img/enemigo1.png")), i*200+200,200,150,150, 5, 20,100);
			enem2[i].col = new Colision(true);
			enem2[i].col.addRadial(enem2[i].x+75, enem2[i].y+75, 90);
			ent[1].add(enem2[i]);
			ent[1].addEnem(enem2[i]);
		}
		for(int i = 0; i<2;i++){
			ent[i].setFondo(new Image(getClass().getResourceAsStream("img/fondo.png")));
			ent[i].add(jug);
			ent[i].addJug(jug);
		}
		Timeline clock = new Timeline(new KeyFrame(Duration.millis(10), e ->{
			ent[nivel].update(idea);
			jug.accion(ent[nivel]);
			for(Enemigo accionando : ent[nivel].enemigos){
				if(accionando != null) accionando.accion(ent[nivel]);
			}
			if(ent[nivel].allDead()) nivel++;
		}));
		clock.setCycleCount(Timeline.INDEFINITE);
		Scene escena = new Scene(grupo);
		escena.setCursor(Cursor.NONE);
        escena.setOnKeyPressed(e->{
			if(e.getEventType() == KeyEvent.KEY_PRESSED) jug.getAccion(e);
		});
        escena.setOnKeyReleased(e->{
			jug.stop(e);
			if(e.getCode() == KeyCode.F3 && !Entorno.colVisual) Entorno.colVisual = true;
			else if(e.getCode() == KeyCode.F3 && Entorno.colVisual) Entorno.colVisual = false;
			if(e.getCode() == KeyCode.F4 && !HUD.visible) HUD.visible = true;
			else if(e.getCode() == KeyCode.F4 &&HUD.visible) HUD.visible = false;// Entorno.colVisual = false;	
			if(e.getCode() == KeyCode.X && !jug.isUltiOn()) jug.alternarUlti();
			if(e.getCode() == KeyCode.K) jug.setPts(501);
		});
        lienzo.setOnMouseMoved(e->{
            jug.mira.setPos((float)e.getX(), (float)e.getY());
		});
		lienzo.setOnMouseClicked(e->{
			jug.atacar(ent[nivel]);
		});
        window.setScene(escena);
        window.show();
        clock.play();
	}
}