package Jaime;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;

public class Ente{
	public Image cont;
	protected float x,y,ancho,alto;
	protected int vel, atq;
	protected Boolean ulti;
	public Boolean movimientoA = false;
    public Boolean movimientoD = false;
    public Boolean movimientoW = false;
	public Boolean movimientoS = false;
	public Colision col;
	protected Boolean colOn = true;;
	public Ente(Image cont, float x, float y, float ancho, float alto, int vel, int atq){
        this.cont = cont;
        this.x = x;
        this.y = y;
        this.ancho=ancho;
        this.alto =alto;
		this.vel = vel;
		this.atq = atq;
	}
	public void render(GraphicsContext idea){
		idea.drawImage(cont,x,y,ancho, alto);
	}
	public Boolean inCol(Colision difCol){
		if(this.col.tipo == false){ //colision cuadral esa palabra no existe pero no me importa
			for(Cuadro pCuad : this.col.cuadros){
				for(Cuadro dCuad : difCol.cuadros){
					if(pCuad.intx < dCuad.intx) return true;
					if(pCuad.intx + pCuad.w > dCuad.intx + dCuad.w) return true;
					if(pCuad.inty < dCuad.inty) return true;
					if(pCuad.inty + pCuad.h> dCuad.inty + dCuad.h) return true;
				}
			}
		}
		else{
			for(Cuadro dCuad : difCol.cuadros){
				if(this.distancia(dCuad.intx ,this.col.oval.ycenter) < this.col.oval.radio) return true;
				if(this.distancia(this.col.oval.xcenter, dCuad.inty) < this.col.oval.radio) return true;
				if(this.distancia(dCuad.intx+dCuad.w ,this.col.oval.ycenter) < this.col.oval.radio) return true;
				if(this.distancia(this.col.oval.xcenter, dCuad.inty + dCuad.h) < this.col.oval.radio) return true;
			}
		}
		return false;
	}
	public Boolean exCol(Colision difCol){
		if(this.col.tipo == false){ //colision cuadral esa palabra no existe pero no me importa
			for(Cuadro pCuad : this.col.cuadros){
				if(difCol.tipo == false){
					for(Cuadro dCuad : difCol.cuadros){
						if(pCuad.intx + pCuad.w> dCuad.intx && pCuad.intx < dCuad.intx+dCuad.w){
							if(pCuad.inty + pCuad.h> dCuad.inty && pCuad.inty < dCuad.inty+dCuad.h) return true;	
						}
					}
				}
				if(difCol.tipo == true){
					if(this.distancia(difCol.oval.xcenter, difCol.oval.ycenter) < difCol.oval.radio+(this.ancho/2)) return true;
				}
			}
		}
		else{
			if(difCol.tipo == false){
				for(Cuadro dCuad : difCol.cuadros){
					if( this.distancia(dCuad.intx + (dCuad.w/2), dCuad.inty + (dCuad.h/2)) < this.col.oval.radio) return true;
				}
			}
			if(difCol.tipo == true){
				if(this.distancia( difCol.oval.xcenter, difCol.oval.ycenter) < this.col.oval.radio*2) return true;
			}
		}
		return false;

	}
	public void setCont(Image cont){
		this.cont = cont;
	}	
	public Boolean getColState(){
		return this.colOn;
	}
	public void offCol(){
		this.colOn = false;
	}
	public void onCol(){
		this.colOn = true;
	}
	public double distancia(float dx, float dy){
		
		return Math.sqrt( Math.pow(dx - (this.x+(this.ancho/2)),2) + Math.pow(dy-(this.y+(this.alto/2)),2 ));
	}
}