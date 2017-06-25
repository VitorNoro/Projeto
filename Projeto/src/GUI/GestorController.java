/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import classesFX.Artigo;
import GUI.Gestor.MainScene;
import GUI.Gestor.Scene;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author v_nor
 */
public class GestorController implements Initializable {
    @FXML
    protected BorderPane info;
    @FXML
    private TableView<Artigo> artigos;
    @FXML
    private TableColumn<Artigo, Integer> codArtigos;
    @FXML
    private TableColumn<Artigo, Float> precoArtigos;
    @FXML
    private TableColumn<Artigo, Integer> quantArtigos;
    @FXML
    private TableColumn<Artigo, String> nomeArtigos;
    @FXML
    private TableColumn<Artigo, String> descArtigos;
    
    ObservableList<Artigo> artigo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        artigo = FXCollections.observableArrayList();
        int i = 0;
        for(classes.Artigo a : classes.Artigo.readAll()){
            Artigo temp = new Artigo();
            
            temp.setCodigo(a.getCodigo());
            temp.setPreco(a.getPreco());
            temp.setQuantidade(a.getQuantidade());
            temp.setDescricao(a.getDescricao());
            temp.setNome(a.getNome());
            
            artigo.add(temp);
        }
        
        MainScene scene = new MainScene();
        info.setCenter(scene);
        
    }   
    
    public void switchScene(String scene){
        Scene current = new Scene(this, scene);
        info.setCenter(current);
    }
    
    public void pop(){
        switchScene("listarProdutos");
        
        codArtigos.setCellValueFactory(cellData -> cellData.getValue().getCodigo().asObject());
        precoArtigos.setCellValueFactory(cellData -> cellData.getValue().getPreco().asObject());
        quantArtigos.setCellValueFactory(cellData -> cellData.getValue().getQuantidade().asObject());
        nomeArtigos.setCellValueFactory(cellData -> cellData.getValue().getNome());
        descArtigos.setCellValueFactory(cellData -> cellData.getValue().getDescricao());
        artigos.setItems(artigo);
        
        codArtigos.prefWidthProperty().bind(artigos.widthProperty().divide(10)); // w * 1/4
        precoArtigos.prefWidthProperty().bind(artigos.widthProperty().divide(10)); // w * 1/4
        quantArtigos.prefWidthProperty().bind(artigos.widthProperty().divide(5)); // w * 1/4
        nomeArtigos.prefWidthProperty().bind(artigos.widthProperty().divide(3)); // w * 1/4
        descArtigos.prefWidthProperty().bind(artigos.widthProperty().divide(4)); // w * 1/4
    }
    
}
