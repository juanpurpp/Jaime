package Jaime;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;
import java.util.Random;

public class Enemigo extends Ente{
	private int movs;
	private int dir;
	public Enemigo(Image cont, float x, float y, float ancho, float alto, int vel, int atq, int vida){
		super(cont,x,y,ancho,alto,vel,atq,vida);
	}
	public void accion(Entorno ent){
		if(this.vida > 0){
			Random rand = new Random();
			if(rand.nextInt(10000) < 25){
				for(int i = 0; i<ent.entes.length;i++){
					if(ent.entes[i]!=null && ent.entes[i].getClass() == Jugador.class){
						Random azar = new Random();
						int dano = 5 + azar.nextInt(Math.abs(this.atq-5));
						ent.entes[i].vida -= dano;
					}
				}
			}
			for(int i = 0;i<this.vel;i++){
				float oldx = this.x, oldy = this.y;
				if(dir == 0) this.x-=1;
				if(dir == 1) this.x+=1;
				if(dir == 2) this.y-=1;
				if(dir == 3) this.y+=1;
				if(this.col.tipo == false){
					for(int j = 0; j<this.col.cuadros.length;j++){
						if(this.col.cuadros[j] != null)this.col.cuadros[j].update(this.x - oldx, this.y - oldy);
					}
				}
				else this.col.oval.update(this.x-oldx, this.y-oldy);
				if(colOn && ent.detectCol(this)){
					//System.out.println("Colision xd");
					if(this.col.tipo == true) this.col.oval.update(oldx - this.x, oldy- this.y);
					else{
						for(int j = 0; j<this.col.cuadros.length;j++){
							if(this.col.cuadros[j] != null)this.col.cuadros[j].update(oldx- this.x, oldy- this.y);
						}
					}
					this.x = oldx;
					this.y = oldy;
				}
			}
			movs--;
			if(movs >0)return;
			dir = rand.nextInt(8);
			movs = rand.nextInt(30);
		}
	}
}