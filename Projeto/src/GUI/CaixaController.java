/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Caixa.MainScene;
import GUI.Caixa.Scene;
import classesFX.Artigo;
import classesFX.LinhaArtigo;
import classesFX.Venda;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author v_nor
 */
public class CaixaController implements Initializable {
    @FXML
    private BorderPane info;
    private Main app = new Main();
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
    private TextField filterArtField;
    @FXML
    private TableView<LinhaArtigo> linhas;
    @FXML
    private TableColumn<LinhaArtigo, Integer> codArtLinha;
    @FXML
    private TableColumn<LinhaArtigo, Float> precoLinha;
    @FXML
    private TableColumn<LinhaArtigo, Integer> quantLinha;
    @FXML
    private TableColumn<LinhaArtigo, String> nomeArtLinha;
    @FXML
    private Label total;
    @FXML
    private TextField filterLinhaField;
    
    private classes.Venda vendaBD;
    private Venda vendaFX;
    private ObservableList<LinhaArtigo> linhasVenda;
    
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
        
        codArtigos.setCellValueFactory(cellData -> cellData.getValue().getCodigo().asObject());
        precoArtigos.setCellValueFactory(cellData -> cellData.getValue().getPreco().asObject());
        quantArtigos.setCellValueFactory(cellData -> cellData.getValue().getQuantidade().asObject());
        nomeArtigos.setCellValueFactory(cellData -> cellData.getValue().getNome());
        
        FilteredList<Artigo> filteredArtData = new FilteredList<>(app.artigoList, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterArtField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredArtData.setPredicate(artigo -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (artigo.getNome().getValue().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } 
                
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList sortedArtData = new SortedList<>(filteredArtData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedArtData.comparatorProperty().bind(artigos.comparatorProperty());
        
        artigos.setItems(sortedArtData);
        
        
        codArtigos.prefWidthProperty().bind(artigos.widthProperty().divide(5)); // w * 1/4
        precoArtigos.prefWidthProperty().bind(artigos.widthProperty().divide(5)); // w * 1/4
        quantArtigos.prefWidthProperty().bind(artigos.widthProperty().divide(5)); // w * 1/4
        nomeArtigos.prefWidthProperty().bind(artigos.widthProperty().divide(3)); // w * 1/4
        
        classes.Venda vendaBD = new classes.Venda();
        vendaBD.createT();
        
        Venda vendaFX = new Venda();
        sync();
        
        
        linhasVenda = FXCollections.observableArrayList(vendaFX.getLinhaartigoCollection());
        
        codArtLinha.setCellValueFactory(cellData -> cellData.getValue().getArtigo().asObject());
        precoLinha.setCellValueFactory(cellData -> cellData.getValue().getTotal().asObject());
        quantLinha.setCellValueFactory(cellData -> cellData.getValue().getQuantidade().asObject());
        nomeArtLinha.setCellValueFactory(cellData -> cellData.getValue().getNomeArtigo());
        
        FilteredList<LinhaArtigo> filteredLinhaData = new FilteredList<>(linhasVenda, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterLinhaField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredLinhaData.setPredicate(linha -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (linha.getNomeArtigo().getValue().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } 
                
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList sortedLinhaData = new SortedList<>(filteredLinhaData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedLinhaData.comparatorProperty().bind(linhas.comparatorProperty());
        
        linhas.setItems(sortedLinhaData);
        
        codArtLinha.prefWidthProperty().bind(linhas.widthProperty().divide(5)); // w * 1/4
        precoLinha.prefWidthProperty().bind(linhas.widthProperty().divide(5)); // w * 1/4
        quantLinha.prefWidthProperty().bind(linhas.widthProperty().divide(5)); // w * 1/4
        nomeArtLinha.prefWidthProperty().bind(linhas.widthProperty().divide(3)); // w * 1/4
        
        total.setText(vendaFX.getTotal().getValue().toString());
    }
    
    public void adicionar(){
        classes.LinhaArtigo temp = new classes.LinhaArtigo();
        classes.Artigo art = new classes.Artigo();
        
        for(classes.Artigo a: classes.Artigo.readAll()){
            if(a.getCodigo() == linhas.getSelectionModel().getSelectedItem().getArtigo().getValue()){
                art = a;
                break;
            }
        }
        
        temp.setArtigo(art);
        temp.setVenda(vendaBD);
        //temp.setQuantidade();
        
    }
    
    public void remover(){
        app.gotoLogin();
    }
    
    public void removerTudo(){
        app.gotoLogin();
    }
    
    public void endSession(){
        app.gotoLogin();
    }
    
    public void sync(){
        vendaFX.setTotal(vendaBD.getTotal());
        
        for(classes.LinhaArtigo l : classes.LinhaArtigo.readAll()){
            if(l.getVenda().getCodigo() == vendaFX.getCodigo()){
                LinhaArtigo temp = new LinhaArtigo();
                
                temp.setCodigo(l.getCodigo());
                temp.setArtigo(l.getArtigo().getCodigo());
                temp.setNomeArtigo(l.getArtigo().getNome());
                temp.setQuantidade(l.getQuantidade());
                temp.setTotal(l.getTotal());
                temp.setVenda(l.getVenda().getCodigo());
                
                vendaFX.getLinhaartigoCollection().add(temp);
                linhasVenda.add(temp);
            }
        }
     
    }
    
}
