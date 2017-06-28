/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import classesFX.Artigo;
import classesFX.Cliente;
import classesFX.Funcionario;
<<<<<<< HEAD
import java.io.IOException;
=======
import classesFX.Subscricao;
>>>>>>> be2a2f8b7355eb5a6fc0b2446090f7d1e27c4474
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
    public static ObservableList<Subscricao> subscricaoList;
    public static ObservableList<Cliente> clienteList;
    
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
        
        subscricaoList = FXCollections.observableArrayList();

        for(classes.Subscricao a : classes.Subscricao.readAll()){
            Subscricao temp = new Subscricao();
            
            temp.setCodigo(a.getCodigo());
            temp.setNome(a.getNome());
            temp.setFimsubscricao(a.getFimsubscricao());
            temp.setMensalidade(a.getMensalidade());
            temp.setCliente(a.getCliente().getNumContribuinte());
            
            subscricaoList.add(temp);
        }
        
        clienteList = FXCollections.observableArrayList();

        for(classes.Cliente a : classes.Cliente.readAll()){
            Cliente temp = new Cliente();
            
            temp.setNumContribuinte(a.getNumContribuinte());
            temp.setNome(a.getNome());
            temp.setContacto(a.getContacto());

            clienteList.add(temp);
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
