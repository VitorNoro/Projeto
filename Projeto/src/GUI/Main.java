/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
        primaryStage.setHeight(500);
        primaryStage.setWidth(800);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
        
    }
    
    public Parent createContent() {
        gotoLogin();
        return root;
     
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    /*
    public  void gotoLogin() {
        try {
            //rootScene = FXMLLoader.load(getClass().getResource("Login/PagPrincipal.fxml"));
            AnchorPane root = (AnchorPane) FXMLLoader.load(Main.class.getResource("Login/PagPrincipal.fxml"));

            stage = new Stage();
            stage.setTitle("Login");

            Scene scene = new Scene(rootScene);
            stage.setScene(scene);
            stage.setHeight(600);
            stage.setWidth(900);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
    public void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("Login/PagPrincipal.fxml");
            login.setApp(this);
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        root.getChildren().removeAll();
        root.getChildren().addAll(page);
        return (Initializable) loader.getController();
    }
    
    
}
