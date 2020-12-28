package Jaime;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class Barra{
	public static void render(GraphicsContext idea, Color color,float x,float  y,float  w,float  h,float  p, float escala){
		w*=escala;
		h*=escala;
		p*=escala;
		idea.setFill(Color.DIMGREY);
		idea.fillRect(x, y, w,h);

		idea.setGlobalAlpha(0.25);
		idea.setFill(Color.BLACK);
		idea.fillRect(x,y,w,h);
		idea.setGlobalAlpha(1.0);

		idea.setFill(color);
		idea.fillRect(x,y, p,h);
		idea.setStroke(Color.BLACK);
		idea.strokeRect(x, y, w,h);
	}
}