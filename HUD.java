package Jaime;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;

public class HUD{
	public static Boolean visible=true;
	private static final Image vidatxt;
	private static final Image ptstxt;
	private static final Image bueno;
	private static final Image ulti;
	private static final Image ultitxt;
    static{
		vidatxt =new Image(HUD.class.getResourceAsStream("img/vidatxt.png"));
		ptstxt = new Image(HUD.class.getResourceAsStream("img/ptstxt.png"));
		bueno = new Image(HUD.class.getResourceAsStream("img/bueno.png"));
		ulti = new Image(HUD.class.getResourceAsStream("img/ultider.png"));
		ultitxt = new Image(HUD.class.getResourceAsStream("img/ultitxt.png"));
    }
	public static void render(GraphicsContext idea,Jugador jug){
		
		if(visible){
			//dibujando cuadro;
			idea.setGlobalAlpha(0.15);
			if(jug.isUltiOn()){
				idea.setFill(Color.RED);
				idea.fillRect(0,0,idea.getCanvas().getWidth(),idea.getCanvas().getHeight() );
			}
			idea.setGlobalAlpha(0.90);
			idea.setFill(Color.DARKSLATEGRAY);
			idea.fillRect(0,600, 1280,700);
			idea.setFill(Color.BLACK);
			idea.fillRect(0,600, 1280,700);
			idea.setGlobalAlpha(1);
			idea.strokeRect(0,600,1280,700);
			//termino cuadro
			Barra.render(idea,Color.LIME, 50,650, jug.vidaTotal, 10, jug.getVida(), (float)2.5); //dibujando vida
			Barra.render(idea,Color.CYAN, 640-250,650, Jugador.ultiPts, 30, jug.getPts(), (float)1);
			idea.setFill(Color.CYAN);
			idea.setFont(Font.font(20.0));
			idea.fillText(jug.getPts()+"", 640-250, 630,50);
			idea.drawImage(vidatxt, 100,605);
			idea.drawImage(ptstxt, 640-60,605);
			idea.drawImage(ultitxt,  975, 605);
			if(jug.isUltiOn()) idea.drawImage(ulti, 1150,615,80,80);
			else idea.drawImage(bueno, 1150, 615,80,80);	
		}
	}
}