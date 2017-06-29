/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import classes.Funcionario;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author v_nor
 */
public class LoginController implements Initializable {
    private Main application = new Main();
    private ArrayList<Funcionario> funcs;
    
    @FXML
    private ComboBox combo;
    @FXML 
    private TextField userName;
    
    @FXML
    private Button BtnIniciar;
    
    @FXML
    private PasswordField password;
    @FXML
    private Label erro;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        // get the file name from which we recently created the scene
        String fileName = url.getFile().substring(url.getFile().lastIndexOf('/')+1,url.getFile().length());
        
        if(fileName.equals("PagPrincipal.fxml")){
            fadeTrans(BtnIniciar);    
        }
        
        
      
        //-----------------------------------------------------
        funcs = Funcionario.readAll();
        
        combo.getItems().clear();
        combo.getItems().addAll("Gestor", "Caixa", "Reparação");
        
        userName.setPromptText("Nome de utilizador");
        password.setPromptText("Palavra passe");
    }
    
    

    private void fadeTrans(Node e){
        FadeTransition x = new FadeTransition(new Duration(2000),e);
        x.setFromValue(0);
        x.setToValue(100);
        x.setCycleCount(1);
        x.setInterpolator(Interpolator.LINEAR);
        x.play();
    }
    
    
    @FXML
    private void iniciar(ActionEvent event) {
        if(autenticador(userName.getText(), password.getText()))
            for(Funcionario f : funcs){
                if(userName.getText().equals(f.getUsername())){
                    switch (f.getFuncao()){
                            case "Gestor":
                                switch (combo.getSelectionModel().getSelectedItem().toString()){
                                    case "Gestor":
                                        fadeTrans(BtnIniciar);
                                        application.gotoGestor();
                                        break;
                                    case "Caixa":
                                        application.gotoCaixa();
                                        break;
                                    case "Reparação":
                                        application.gotoReparador();
                                        break;
                                    default:
                                        erro.setText("Selecione uma função a realizar");
                                        break;
                                }
                                break;
                            case "Caixa":
                                switch (combo.getSelectionModel().getSelectedItem().toString()){
                                    case "Gestor":
                                        erro.setText("Não tem permissão para realizar esta função");
                                        break;
                                    case "Caixa":
                                        application.gotoCaixa();
                                        break;
                                    case "Reparação":
                                        erro.setText("Não tem permissão para realizar esta função");
                                        break;
                                    default:
                                        erro.setText("Selecione uma função a realizar");
                                        break;
                                }
                                
                                break;
                            case "Reparador":
                                switch (combo.getSelectionModel().getSelectedItem().toString()){
                                    case "Gestor":
                                        erro.setText("Não tem permissão para realizar esta função");
                                        break;
                                    case "Caixa":
                                        erro.setText("Não tem permissão para realizar esta função");
                                        break;
                                    case "Reparação":
                                        application.gotoReparador();
                                        break;
                                    default:
                                        erro.setText("Selecione uma função a realizar");
                                        break;
                                }
                                break;
                            default:
                                
                                break;
                    }
                }      
                }                
        else{
            erro.setText("Nome ou palavra passe errados");
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
