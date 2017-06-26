/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import classes.Funcionario;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author v_nor
 */
public class LoginController implements Initializable {
    private Main application = new Main();
    private ArrayList<Funcionario> funcs;
    
    @FXML
    public ComboBox combo;
    @FXML 
    public TextField userName;
    
    @FXML
    public PasswordField password;
    
    @FXML
    AnchorPane imgPane = new AnchorPane();
    @FXML
    ImageView img = new ImageView();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        img.fitWidthProperty().bind(imgPane.widthProperty()); 
        img.fitHeightProperty().bind(imgPane.heightProperty()); 
        
        funcs = Funcionario.readAll();
        
        combo.getItems().clear();
        combo.getItems().addAll("Gestor", "Caixa", "Reparação");
        
        userName.setPromptText("Nome de utilizador");
        password.setPromptText("Palavra passe");
    }    
    
    @FXML
    private void iniciar(ActionEvent event) {
        if(autenticador(userName.getText(), password.getText()))
            for(Funcionario f : funcs){
                if(userName.getText().equals(f.getUsername())){
                    switch (f.getFuncao()){
                            case "Gestor":
                                //String output = combo.getSelectionModel().getSelectedItem().toString();
                                //System.out.println(output);
                                application.gotoGestor();
                                
                                break;
                            case "Caixa":
                                
                                break;
                            case "Reparador":
                                
                                break;
                            default:
                                
                                break;
                    }
                }      
                }                
        else{
            //notificacao.setText("Nome ou palavra passe errados");
        }
    }
    
    public void setApp(Main application){
        this.application = application;
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
