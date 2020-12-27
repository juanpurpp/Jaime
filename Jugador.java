package Jaime;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;

public class Jugador extends Ente{
	public Jugador(Image cont, float x, float y, float ancho, float alto, int vel, int atq){
		super(cont,x,y,ancho,alto,vel,atq);
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
}