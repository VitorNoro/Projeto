/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import classesFX.Artigo;
import classesFX.Funcionario;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public static ObservableList<Artigo> artigoList;
    public static ObservableList<Funcionario> funcList;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        artigoList = FXCollections.observableArrayList();

        for(classes.Artigo a : classes.Artigo.readAll()){
            Artigo temp = new Artigo();
            
            temp.setCodigo(a.getCodigo());
            temp.setPreco(a.getPreco());
            temp.setQuantidade(a.getQuantidade());
            temp.setDescricao(a.getDescricao());
            temp.setNome(a.getNome());
            
            artigoList.add(temp);
        }
        
        funcList = FXCollections.observableArrayList();
        
        for(classes.Funcionario f : classes.Funcionario.readAll()){
            Funcionario temp = new Funcionario();
            
            temp.setCodigo(f.getCodigo());
            temp.setNome(f.getNome());
            temp.setMorada(f.getMorada());
            temp.setContacto(f.getContacto());
            temp.setFuncao(f.getFuncao());
            temp.setUsername(f.getUsername());
            temp.setPassword(f.getPassword());
            
            funcList.add(temp);
        }
        
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
    
    public  void gotoCaixa() {
        try {
            
            rootScene = FXMLLoader.load(getClass().getResource("Caixa/PagPrincipal.fxml"));

            
            stage.setTitle("Caixa");

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
