package Jaime;
import java.util.Timer; 
import java.util.TimerTask;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class Cinematica{
	public  final Image img;
	public  Boolean mostrando;
	public  float alpha = 1;
	public Cinematica(Image img, Boolean mostrando){
		this.img = img;
		this.mostrando = mostrando;
	}

	public void mostrar(GraphicsContext idea){
		idea.drawImage(img,0,0);
	}
}