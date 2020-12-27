package Registro;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight; 
import javafx.scene.text.Text; 

public class Info{
    public static void display(Cliente[] clientes, int cantclientes){
        Stage window = new Stage();
        ScrollPane barra = new ScrollPane();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Información de clientes");
        Button ok = new Button("Cerrar");
        Label titulo = new Label("Información clientes");

        titulo.setFont(Font.font("lucida sans unicode", FontWeight.BOLD, FontPosture.REGULAR, 20));
        Label[] infotxt;
        
        if(cantclientes != 0) infotxt= new Label[cantclientes];
        else infotxt= new Label[1];
        String texto;
        if(cantclientes!= 0){
            for(int i = 0;i<cantclientes;i++){
                String[] productos = new String[clientes[i].getProductos().length];
                productos = clientes[i].getProductos();
                texto = "Nombre: " + clientes[i].getNombre() +"\n";
                texto+= "Rut: " + clientes[i].getRut()+"\n";
                texto+= "Región: " + clientes[i].getRegion()+"\n";
                texto+= "Género: " + clientes[i].getGenero()+"\n"; 
                texto+= "Productos: \n";
                for(String producto : productos) texto+=producto+"\n";
                texto+= "ID: " + clientes[i].getID()+"\n";
                infotxt[i] = new Label(texto);
           }
        }
        else infotxt[0] = new Label("No hay clientes para mostrar");
        VBox panelv = new VBox(20);
        panelv.getChildren().add(titulo);
        for(Label mostrar : infotxt){
            if(mostrar != null) panelv.getChildren().add(mostrar);
        }
        panelv.getChildren().add(ok);
        barra.setContent(panelv);
        ok.setOnAction(e->{
            window.close();
        });
        Scene escena = new Scene(barra, 400,600);
        window.setScene(escena);
        window.show();
    }
}