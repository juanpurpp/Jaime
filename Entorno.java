package Jaime;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Entorno{
	public int xMax,yMax;
	public Ente[] entes;
	public Jugador jug;
	public Enemigo[] enemigos;
	public int enemCant = 0;
	public int entesCant = 0;
	public Image bg;
	public Colision limites;
	public static Boolean colVisual = false;
	public Entorno(int xMax, int yMax, int maxEntes){
		this.xMax = xMax;
		this.yMax = yMax;
		this.entes = new Ente[maxEntes];
		this.enemigos=new Enemigo[maxEntes-1];
		this.limites = new Colision(false);
		this.limites.addCuadros(new Cuadro(0,0,xMax,yMax));

        //this.limites = new Colision(0,0, xMax,yMax);
	}
	public void update(GraphicsContext idea){
        if(this.bg != null) idea.drawImage(this.bg, 0,0,this.xMax,this.yMax);
        for(Ente renderizando : this.entes) {
            if(renderizando !=null){
				if(renderizando.vida >0) renderizando.render(idea);
			}
			if(renderizando !=null && colVisual){
				if(renderizando.vida >0) renderizando.col.render(idea);
			}
		}
        //for(Obj renderizando : objeto) renderizando.render();
	}
	public void add(Ente ... entrantes){
		for(Ente adding : entrantes){
			entes[entesCant] = adding;
			entesCant++;
		}
	}
	public Boolean detectCol(Ente detectando){
		if(detectando.inCol(this.limites)) return true;
		for(Ente contrario : this.entes){
			if(contrario != null && contrario.vida > 0){
				if(contrario != detectando&&detectando.exCol(contrario.col)) return true;
			}
		}
		return false;
	}
	public void addJug(Jugador jug){
		this.jug = jug;
	}
	public void addEnem(Enemigo... entrantes){
		for(Enemigo adding:entrantes){
			enemigos[enemCant] = adding;
			enemCant++;
		}
	}
	public void setFondo(Image bg){
		this.bg = bg;
	}
	public Boolean allDead(){
		for(Enemigo enemigo: this.enemigos){
			if(enemigo.vida>0)return false;
		}
		return true;
	}
}