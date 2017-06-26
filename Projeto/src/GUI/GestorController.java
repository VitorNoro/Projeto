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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    @FXML
    private TextField filterField;
    @FXML
    private Label erro;
    @FXML
    private Spinner<Integer> spinnerStock;
    @FXML
    private TextField addNome;
    @FXML
    private TextArea addDescricao;
    @FXML
    private TextField addPreco;
    @FXML
    private TextField addQuantidade;
    
    Main app = new Main();
    ObservableList<Artigo> artigoList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        artigoList = FXCollections.observableArrayList();
        int i = 0;
        for(classes.Artigo a : classes.Artigo.readAll()){
            Artigo temp = new Artigo();
            
            temp.setCodigo(a.getCodigo());
            temp.setPreco(a.getPreco());
            temp.setQuantidade(a.getQuantidade());
            temp.setDescricao(a.getDescricao());
            temp.setNome(a.getNome());
            
            artigoList.add(temp);
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
        
        FilteredList<Artigo> filteredData = new FilteredList<>(artigoList, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(artigo -> {
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
        SortedList sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(artigos.comparatorProperty());
        
        artigos.setItems(sortedData);
        
        artigos.getSelectionModel().selectFirst();
        
        codArtigos.prefWidthProperty().bind(artigos.widthProperty().divide(10)); // w * 1/4
        precoArtigos.prefWidthProperty().bind(artigos.widthProperty().divide(10)); // w * 1/4
        quantArtigos.prefWidthProperty().bind(artigos.widthProperty().divide(5)); // w * 1/4
        nomeArtigos.prefWidthProperty().bind(artigos.widthProperty().divide(3)); // w * 1/4
        descArtigos.prefWidthProperty().bind(artigos.widthProperty().divide(4)); // w * 1/4
    }
    
    public void deleteProduto(){
        if(artigos.getSelectionModel().isEmpty())
            erro.setText("Selecione um artigo");
        else{
            for(Artigo a : artigoList){
                
                if (a.getCodigo() == artigos.getSelectionModel().getSelectedItem().getCodigo()){
                    classes.Artigo.delete(a.getCodigo().getValue());
                    artigoList.remove(a);
                }
            }
        }
    }
    
    public void addStock(){
        if(artigos.getSelectionModel().isEmpty())
            erro.setText("Selecione um artigo");
        else{
            for(Artigo a : artigoList){
                if (a.getCodigo() == artigos.getSelectionModel().getSelectedItem().getCodigo()){
                    a.setQuantidade(a.getQuantidade().getValue() + spinnerStock.getValue());
                    
                    artigos.refresh();
                    for(classes.Artigo art: classes.Artigo.readAll()){
                        if(a.getCodigo().getValue() == art.getCodigo()){
                            art.addStock(a.getQuantidade().getValue());
                        }
                    }
                }
            }
        }
    }
    
    public void addArtigo(){
        switchScene("inserirProduto");
    }
    
    public void confirmNewArtigo(){
        classes.Artigo art = new classes.Artigo();
        try{
            int quant = Integer.parseInt(addQuantidade.getText());
            float preco = Float.parseFloat(addPreco.getText().replaceAll(",", "."));

            art.setNome(addNome.getText());
            art.setDescricao(addDescricao.getText());
            art.setPreco(preco);
            art.setQuantidade(quant);
            
            art.createT();
            
            Artigo temp = new Artigo();
            
            temp.setCodigo(art.getCodigo());
            temp.setPreco(art.getPreco());
            temp.setQuantidade(art.getQuantidade());
            temp.setDescricao(art.getDescricao());
            temp.setNome(art.getNome());
            
            artigoList.add(temp);
            
            pop();
            
            
        }catch(NumberFormatException ex){
            System.out.println("ERRRO");
        }
    }
    
    public void endSession(){
        app.gotoLogin();
    }
}
