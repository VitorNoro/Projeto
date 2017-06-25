/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import classes.Funcionario;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author v_nor
 */
public class Main extends Application {
    private Group root = new Group();
    public static Stage stage;
    private Parent rootScene;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        gotoLogin();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    public  void gotoLogin() {
        try {
            rootScene = FXMLLoader.load(getClass().getResource("Login/PagPrincipal.fxml"));
            AnchorPane root = (AnchorPane) FXMLLoader.load(Main.class.getResource("Login/PagPrincipal.fxml"));

            stage = new Stage();
            stage.setTitle("Login");

            Scene scene = new Scene(rootScene);
            stage.setScene(scene);
            stage.setHeight(768);
            stage.setWidth(1024);
            stage.setResizable(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public  void gotoGestor() {
        try {
            
            rootScene = FXMLLoader.load(getClass().getResource("Gestor/PagPrincipal.fxml"));

            
            stage.setTitle("Gestor");

            Scene scene = new Scene(rootScene);
            stage.setScene(scene);
            stage.setHeight(768);
            stage.setWidth(1024);
            stage.setResizable(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean autenticador(String user, String pass){
        boolean existe = false;
        
        for(Funcionario f : Funcionario.readAll()){
            if(user.equals(f.getUsername()) && pass.equals(f.getPassword()))
                existe = true;
        }
        
        return existe;
    }
}
