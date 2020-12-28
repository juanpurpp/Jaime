package Jaime;
import javafx.scene.image.Image;
public class Mira{
	private float x,  y, ancho, alto;
	public Image imgmira;
	public Mira(Image imgmira, float x, float y, float ancho, float alto){
		this.x = x;
		this.y=y;
		this.ancho=ancho;
		this.alto =alto;
		this.imgmira = imgmira;
	}
	public void setPos(float x, float y){
		this.x = x - (ancho/2);
		this.y = y - (alto/2);
	}
	public float getX(){
		return this.x;
	}
	public float getY(){
		return this.y;
	}
	public float getAncho(){
		return this.ancho;
	}
	public float getAlto(){
		return this.alto ;
	}
	public double distancia(float dx, float dy){
		
		return Math.sqrt( Math.pow(dx - (this.x+(this.ancho/2)),2) + Math.pow(dy-(this.y+(this.alto/2)),2 ));
	}
}