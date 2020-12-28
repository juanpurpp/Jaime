package Jaime;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Entorno{
	public int xMax,yMax;
	public Ente[] entes;
	public int entesCant = 0;
	public Image bg;
	public Colision limites;
	public static Boolean colVisual = false;
	public Entorno(int xMax, int yMax, int maxEntes){
		this.xMax = xMax;
		this.yMax = yMax;
		this.entes = new Ente[maxEntes];
		this.limites = new Colision(false);
		this.limites.addCuadros(new Cuadro(0,0,xMax,yMax));
        //this.limites = new Colision(0,0, xMax,yMax);
	}
	public void update(GraphicsContext idea){
        if(this.bg != null) idea.drawImage(this.bg, 0,0,this.xMax,this.yMax);
        for(Ente renderizando : this.entes) {
            if(renderizando !=null) renderizando.render(idea);
			if(renderizando !=null && colVisual) renderizando.col.render(idea);
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
		for(Ente contrario : entes){
			if(detectando.exCol(contrario.col)&&contrario != null && contrario != detectando) return true;
		}
		return false;
	}
	public void setFondo(Image bg){
		this.bg = bg;
	}
}