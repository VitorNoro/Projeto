/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Caixa.MainScene;
import GUI.Caixa.Scene;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author v_nor
 */
public class CaixaController implements Initializable {
    @FXML
    private AnchorPane pane;
    @FXML
    private VBox box;
    @FXML
    private BorderPane info;
    private Main app = new Main();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainScene scene = new MainScene();
        info.setCenter(scene);
        
    }    
    
    public void switchScene(String scene){
        Scene current = new Scene(this, scene);
        info.setCenter(current);
    }
    
    public void change(){
        switchScene("listarProdutos");
        
        box.setMinWidth(pane.getWidth()/2);
    }
    
    public void endSession(){
        app.gotoLogin();
    }
    
}
