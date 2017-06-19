/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author v_nor
 */
public class LoginController implements Initializable {
    private Main application;
    
    ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Option 1",
        "Option 2",
        "Option 3"
    );
    
    @FXML
    final ComboBox<String> combo = new ComboBox<String>(options);
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combo.setItems(options);
    }    
    /*
    @FXML
    private void iniciar(ActionEvent event) {
        if(application.autenticador(userName.getText(), password.getText()))
            for(Utilizador u : Main.utilizadores){
                if(userName.getText().equals(u.getUserName().getValue())){
                    if(u instanceof Administrador){
                        Main.currentAdmin.add((Administrador)u);
                        application.gotoAdmin();             
                    }                       
                
                    else{
                      Main.user.add((Investidor)u);
                      application.gotoInvestidor();
                    }   
                    
                }      
                }                
        else
            label.setText("Nome ou palavra passe errados");
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userName.setPromptText("Nome de utilizador");
        password.setPromptText("Palavra passe");
        
    }   */ 
    
    public void setApp(Main application){
        this.application = application;
    }
    
}
