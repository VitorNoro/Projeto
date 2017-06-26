/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author v_nor
 */
public class Main extends Application {
    public static Stage stage;
    private Parent rootScene;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = new Stage();
        
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

            stage.setTitle("Login");

            Scene scene = new Scene(rootScene);
            stage.setScene(scene);
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
            stage.setMaximized(true);
            stage.setResizable(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
