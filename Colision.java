package Jaime;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class Colision{
	public Boolean tipo; //FALSO PARA CUADROS, VERDADERO PARA RADIAL;
	public Radial oval;
	public Cuadro[] cuadros;
	public Colision(Boolean tipo){
		this.tipo = tipo;
	}
	public void addRadial(float xcenter, float ycenter,float radio){
		this.oval = new Radial(xcenter,ycenter,radio);
	}
	public void addCuadros(Cuadro...cuadros){
		this.cuadros = new Cuadro[cuadros.length];
		for(int i = 0; i < cuadros.length;i++){
			this.cuadros[0] = cuadros[0];
		}
	}
	public void editCuadros(Cuadro...cuadros){
		if(cuadros.length != this.cuadros.length) System.out.println("Debe actualizar "+this.cuadros.length+" cuadros (actualizó "+ cuadros.length +" cuadros");
		else{
			for(int i = 0; i < cuadros.length;i++){
				this.cuadros[0] = cuadros[0];
			}
		}
	}
	public void render(GraphicsContext idea){
		if(this.tipo == false){
			for(Cuadro quad : cuadros){
				if(quad != null){
					idea.setFill(Color.VIOLET);
					idea.fillRect(quad.intx,quad.inty,quad.w,quad.h);
				}
			}
		}
		else{
			idea.setFill(Color.VIOLET);
			idea.fillOval(this.oval.xcenter-this.oval.radio, this.oval.ycenter-this.oval.radio, this.oval.radio*2,this.oval.radio*2);
		}
	}
}