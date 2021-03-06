package Jaime;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.lang.Math;
import java.util.Random;
public class Jugador extends Ente{
	public Mira mira;
	private int pts = 0;
	private Boolean ulti = false;
	public static int ultiPts =500;
	private int usos = 0;//new Image(getClass().getResourceAsStream("img/jaimeizq.png")),new Image(getClass().getResourceAsStream("img/jaimeder.png")),
	public Image normald=new Image(getClass().getResourceAsStream("img/jaimeder.png")), normali=new Image(getClass().getResourceAsStream("img/jaimeizq.png"));
	public Image ultid=new Image(getClass().getResourceAsStream("img/ultider.png")), ultii=new Image(getClass().getResourceAsStream("img/ultiizq.png"));
	public Jugador(Image cont, float x, float y, float ancho, float alto, int vel, int atq,int vida){
		super(cont,x,y,ancho,alto,vel,atq,vida);
	}
	public void accion(Entorno ent){
		for(int i = 0; i < this.vel; i++){
            float oldx = this.x, oldy = this.y;
			if(this.movimientoA){
				this.x -=1;
				if(!this.ulti) this.cont = normali;
				else this.cont = ultii;
			}
            if(this.movimientoD){
				this.x +=1;
				if(!this.ulti) this.cont = normald;
				else this.cont = ultid;
			}
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
	public void atacar(Entorno ent){
		Random rand = new Random();
		int dano = 10 + new Random().nextInt(Math.abs(this.atq-10)); 
		if(this.ulti){
			dano = 50 + new Random().nextInt(Math.abs(this.atq*5-50));
			this.usos++;
			if(this.usos >= 3){
				this.usos = 0;
				this.ulti = false;

			}
		}
		for(Ente enemigo:ent.entes){
			if(enemigo != null && enemigo != this && enemigo.vida >= 0){
				if(this.mira.distancia(enemigo.x+(enemigo.ancho/2),enemigo.y+(enemigo.alto/2))<enemigo.col.oval.radio){
					enemigo.vida -= dano;
					this.pts+=dano;
					if(enemigo.vida <= 0){
						this.pts+=50;
					}
					if(this.pts >500) this.pts = 500;
				}
			}
		}
	}
	public void hudVisible(Boolean opcion){
		HUD.visible = opcion;
	}
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
	public void alternarUlti(){
		if(!this.ulti){
			if(this.pts >= 500){
				this.ulti = true;
				this.vida = 100;
				this.pts = 0;
			}
		}
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
	@Override
	public void render(GraphicsContext idea){
		idea.drawImage(cont,x,y,ancho, alto);
		idea.drawImage(this.mira.imgmira,this.mira.getX(),this.mira.getY(),this.mira.getAncho(),this.mira.getAlto());
		HUD.render(idea, this);
	}
}