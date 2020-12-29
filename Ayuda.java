package Jaime;
import java.util.Timer; 
import java.util.TimerTask;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
public abstract class Ayuda extends TimerTask{
	public static final Image ayudaimg;
	public static Boolean isAyuda = false;
	public static float alpha = 1;
    static{
		ayudaimg =new Image(HUD.class.getResourceAsStream("img/ayuda.png"));
	}
	public static void initAyuda(GraphicsContext idea){
		Timer timing = new Timer();
		idea.drawImage(Ayuda.ayudaimg,0,0);
		isAyuda=true;
		timing.schedule(new TimerTask(){
			@Override
			public void run(){
				Ayuda.alpha -= 0.01;
			}
		}, 4000,10);
		if(Ayuda.alpha == 0) timing.cancel();
	}
}