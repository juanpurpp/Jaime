package Jaime;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;
import java.util.Random;
public class Jefe extends Enemigo{
	public Jefe(Image cont, float x, float y, float ancho, float alto, int vel, int atq, int vida){
		super(cont,x,y,ancho,alto,vel,atq,vida);
	}

}