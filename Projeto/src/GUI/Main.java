/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import classesFX.Artigo;
import classesFX.Cliente;
import classesFX.Diagnostico;
import classesFX.Fatura;
import classesFX.Fornecedor;
import classesFX.Funcionario;
import classesFX.Manutencao;
import classesFX.Reparacao;
import java.io.IOException;
import classesFX.Subscricao;
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
    public static ObservableList<Diagnostico> diagnosticoList;
    public static ObservableList<Reparacao> reparacaoList;
    public static ObservableList<Fornecedor> fornecedorList;
    public static ObservableList<Fatura> faturaList;
    public static ObservableList<Manutencao> manutencaoList;
    
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
        
        diagnosticoList = FXCollections.observableArrayList();

        for(classes.Diagnostico a : classes.Diagnostico.readAll()){
            Diagnostico temp = new Diagnostico();
            
            temp.setCodigo(a.getCodigo());
            temp.setEquipamento(a.getEquipamento());
            temp.setProblema(a.getProblema());
            temp.setCliente(a.getCliente().getNumContribuinte());

            diagnosticoList.add(temp);
        }
        
        
        reparacaoList = FXCollections.observableArrayList();

        for(classes.Reparacao a : classes.Reparacao.readAll()){
            Reparacao temp = new Reparacao();
            
            temp.setCodigo(a.getCodigo());
            temp.setCusto(a.getCusto());
            temp.setDiagnostico(a.getDiagnostico().getCodigo());
            temp.setCliente(a.getCliente().getNumContribuinte());

            reparacaoList.add(temp);
        }
        
        
        fornecedorList = FXCollections.observableArrayList();

        for(classes.Fornecedor a : classes.Fornecedor.readAll()){
            Fornecedor temp = new Fornecedor();
            
            temp.setCodigo(a.getCodigo());
            temp.setNome(a.getNome());
            temp.setContacto(a.getContacto());
            

            fornecedorList.add(temp);
        }
        
        getFaturas();
        
        manutencaoList = FXCollections.observableArrayList();

        for(classes.Manutencao a : classes.Manutencao.readAll()){
            Manutencao temp = new Manutencao();
            
            temp.setCodigo(a.getCodigo());
            temp.setEquipamento(a.getEquipamento());
            temp.setLocalizacao(a.getLocalizacao());
            temp.setDataAgendada(a.getDataAgendada());
            temp.setSubscricao(a.getSubscricao().getNome());
            

            manutencaoList.add(temp);
        }
        
        
        stage = new Stage(); 
        gotoLogin();
        
        
        
    }
    

    
    public void getFaturas(){
        faturaList = FXCollections.observableArrayList();

        for(classes.Fatura a : classes.Fatura.readAll()){
            Fatura temp = new Fatura();
            
            temp.setCodigo(a.getCodigo());
            temp.setNumContribuinte(a.getNumContribuinte());
            temp.setTotal(a.getTotal());
            temp.setArtigos(a.getArtigos());
            

            faturaList.add(temp);
        }
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
            stage.setMaximized(false);
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
    
     public  void gotoReparador() {
        try {
            
            rootScene = FXMLLoader.load(getClass().getResource("Reparador/PagPrincipal.fxml"));

            
            stage.setTitle("Reparador");

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
