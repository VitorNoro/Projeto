/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import classes.Artigo;
import classes.Funcionario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author v_nor
 */
public class LoginController implements Initializable {
    private Main application;
    
    @FXML
    public ComboBox combo;
    @FXML 
    public TextField userName;
    @FXML
    public PasswordField password;
    @FXML 
    public Label notificacao;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combo.getItems().clear();
        combo.getItems().addAll("Gestor", "Caixa", "Reparação");
        
        userName.setPromptText("Nome de utilizador");
        password.setPromptText("Palavra passe");
    }    
    
    @FXML
    private void iniciar(ActionEvent event) {
        if(application.autenticador(userName.getText(), password.getText()))
            for(Funcionario f : Funcionario.readAll()){
                if(userName.getText().equals(f.getUsername())){
                    switch (f.getFuncao()){
                            case "Gestor":
                                notificacao.setText("Gestor");
                                String output = combo.getSelectionModel().getSelectedItem().toString();
                                System.out.println(output);
                                break;
                            case "Caixa":
                                notificacao.setText("Caixa");
                                break;
                            case "Reparador":
                                notificacao.setText("Reparador");
                                break;
                            default:
                                notificacao.setText("Nada");
                                break;
                    }
                }      
                }                
        else
            notificacao.setText("Nome ou palavra passe errados");
    }
    
    public void setApp(Main application){
        this.application = application;
    }
    
}
