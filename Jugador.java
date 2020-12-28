package Jaime;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Jugador extends Ente{
	public Mira mira;
	private int pts = 0;
	private Boolean ulti = true;
	public static int ultiPts =500;
	public Jugador(Image cont, float x, float y, float ancho, float alto, int vel, int atq,int vida){
		super(cont,x,y,ancho,alto,vel,atq,vida);
	}
	public void accion(Entorno ent){
		for(int i = 0; i < this.vel; i++){
            float oldx = this.x, oldy = this.y;
            if(this.movimientoA) this.x -=1;
            if(this.movimientoD) this.x +=1;
            if(this.movimientoW) this.y -= 1;
			if(this.movimientoS) this.y += 1;
			if(this.col.tipo == false){
				for(int j = 0; j<this.col.cuadros.length;j++){
					if(this.col.cuadros[j] != null)this.col.cuadros[j].update(this.x - oldx, this.y - oldy);
				}
			}
			else this.col.oval.update(this.x-oldx, this.y-oldy);
            if(colOn && ent.detectCol(this)){
				//System.out.println("Colision xd");
				if(this.col.tipo == false){
					for(int j = 0; j<this.col.cuadros.length;j++){
						if(this.col.cuadros[j] != null)this.col.cuadros[j].update(oldx- this.x, oldy- this.y);
					}
				}
				else this.col.oval.update(oldx - this.x, oldy- this.y);
                this.x = oldx;
				this.y = oldy;
			}
        }
	}
	public void addMira(Image imgmira, float x, float y, float ancho, float largo){
		this.mira = new Mira(imgmira,x,y,ancho,largo);
	}
	
	/*public void hudVisible(Boolean opcion){
		hud.visible = opcion;
	}*/
    public void getAccion(KeyEvent e){
        if(e.getCode() == KeyCode.A ) this.movimientoA = true;
        if(e.getCode() == KeyCode.D ) this.movimientoD = true;
        if(e.getCode() == KeyCode.W ) this.movimientoW = true;
        if(e.getCode() == KeyCode.S ) this.movimientoS = true;
	}
	public void stop(KeyEvent e){
        if(e.getCode() == KeyCode.A) this.movimientoA = false;
        if(e.getCode() == KeyCode.D) this.movimientoD = false;
        if(e.getCode() == KeyCode.W) this.movimientoW = false;
        if(e.getCode() == KeyCode.S) this.movimientoS = false;
	}
	@Override
	public void render(GraphicsContext idea){
		idea.drawImage(cont,x,y,ancho, alto);
		idea.drawImage(this.mira.imgmira,this.mira.getX(),this.mira.getY(),this.mira.getAncho(),this.mira.getAlto());
		HUD.render(idea, this);
	}
	public int getVida(){
		return this.vida;
	}
	public int getPts(){
		return this.pts;
	}
	public void setPts(int pts){
		this.pts = pts;
	}
	public Boolean isUltiOn(){
		return this.ulti;
	}
	public void setUlti(Boolean ulti){
		this.ulti = ulti;
	}
}