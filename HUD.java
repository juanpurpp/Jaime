package Jaime;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
public class HUD{
	private static final Image vidatxt;
	private static final Image ptstxt;
	private static final Image bueno;
	private static final Image ulti;
    static{
		vidatxt =new Image(HUD.class.getResourceAsStream("img/vidatxt.png"));
		ptstxt = new Image(HUD.class.getResourceAsStream("img/ptstxt.png"));
		bueno = new Image(HUD.class.getResourceAsStream("img/bueno.png"));
		ulti = new Image(HUD.class.getResourceAsStream("img/ulti.png"));
    }
	public static void render(GraphicsContext idea,Jugador jug){
		//dibujando cuadro;
		idea.setGlobalAlpha(0.90);
		idea.setFill(Color.DARKSLATEGRAY);
		idea.fillRect(0,600, 1280,700);
		idea.setFill(Color.BLACK);
		idea.fillRect(0,600, 1280,700);
		idea.setGlobalAlpha(1);
		idea.strokeRect(0,600,1280,700);
		//termino cuadro
		Barra.render(idea,Color.LIME, 50,650, jug.vidaTotal, 10, jug.getVida(), (float)2.5); //dibujando vida
		Barra.render(idea,Color.DARKBLUE, 640-250,650, Jugador.ultiPts, 30, jug.getPts(), (float)1);
		idea.drawImage(vidatxt, 100,560);
		idea.drawImage(ptstxt, 640-60,560);
		if(jug.isUltiOn()) idea.drawImage(ulti, 1100,615,80,80);
		else idea.drawImage(bueno, 1100, 615,80,80);
	}
}