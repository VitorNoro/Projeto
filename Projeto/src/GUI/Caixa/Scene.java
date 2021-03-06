/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Caixa;


import GUI.CaixaController;
import GUI.Gestor.*;
import GUI.GestorController;
import GUI.ReparadorController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author v_nor
 */
public class Scene extends AnchorPane{
    
    public Scene(CaixaController control, String scene){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(scene + ".fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(control);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
