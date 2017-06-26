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
    private TextField prodNome;
    @FXML
    private TextArea prodDescricao;
    @FXML
    private TextField prodPreco;
    @FXML
    private TextField prodQuantidade;
    
    @FXML
    private TextField funcNome;
    @FXML
    private TextField funcMorada;
    @FXML
    private TextField funcTelefone;
    @FXML
    private TextField funcFuncao;
    @FXML
    private TextField funcUser;
    @FXML
    private TextField funcPass;
    
    Main app = new Main();
    Artigo updateArtigo;
    
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
    
    public void pop(){
        switchScene("listarProdutos");
        
        codArtigos.setCellValueFactory(cellData -> cellData.getValue().getCodigo().asObject());
        precoArtigos.setCellValueFactory(cellData -> cellData.getValue().getPreco().asObject());
        quantArtigos.setCellValueFactory(cellData -> cellData.getValue().getQuantidade().asObject());
        nomeArtigos.setCellValueFactory(cellData -> cellData.getValue().getNome());
        descArtigos.setCellValueFactory(cellData -> cellData.getValue().getDescricao());
        
        FilteredList<Artigo> filteredData = new FilteredList<>(app.artigoList, p -> true);

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
            for(Artigo a : app.artigoList){
                
                if (a.getCodigo() == artigos.getSelectionModel().getSelectedItem().getCodigo()){
                    classes.Artigo.delete(a.getCodigo().getValue());
                    app.artigoList.remove(a);
                }
            }
        }
    }
    
    public void addStock(){
        if(artigos.getSelectionModel().isEmpty())
            erro.setText("Selecione um artigo");
        else{
            for(Artigo a : app.artigoList){
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
            int quant = Integer.parseInt(prodQuantidade.getText());
            float preco = Float.parseFloat(prodPreco.getText().replaceAll(",", "."));
            boolean existeN = false;
            
            for(classes.Artigo t : classes.Artigo.readAll()){
                if(t.getNome().equals(prodNome.getText())){
                    //erro.setText("Nome em uso");
                    existeN = true;
                }                          
            }
            
            if(!(prodNome.getText().isEmpty() || prodDescricao.getText().isEmpty() || preco <= 0 || quant <= 0) && !existeN){
                art.setNome(prodNome.getText());
                art.setDescricao(prodDescricao.getText());
                art.setPreco(preco);
                art.setQuantidade(quant);

                art.createT();

                Artigo temp = new Artigo();

                temp.setCodigo(art.getCodigo());
                temp.setPreco(art.getPreco());
                temp.setQuantidade(art.getQuantidade());
                temp.setDescricao(art.getDescricao());
                temp.setNome(art.getNome());

                app.artigoList.add(temp);

                pop();
            }
            else
                System.out.println("ERRO");
            
            
        }catch(NumberFormatException ex){
            System.out.println("ERRRO");
        }
    }
    
    public void editArtigo(){
        if(artigos.getSelectionModel().isEmpty())
            erro.setText("Selecione um artigo");
        else{
            updateArtigo = artigos.getSelectionModel().getSelectedItem();
            
            switchScene("editarProduto");
            
            prodNome.setText(updateArtigo.getNome().getValue());
            prodDescricao.setText(updateArtigo.getDescricao().getValue());
            prodPreco.setText(updateArtigo.getPreco().getValue().toString());
        }
    }
    
    public void confirmEditArtigo(){
        try{
            float preco = Float.parseFloat(prodPreco.getText().replaceAll(",", "."));
            boolean existeN = false;
            
            for(classes.Artigo t : classes.Artigo.readAll()){
                if(t.getNome().equals(prodNome.getText())){
                    //erro.setText("Nome em uso");
                    existeN = true;
                }                          
            }

            if(!(prodNome.getText().isEmpty() || prodDescricao.getText().isEmpty() || preco <= 0) && !existeN){
            
                for(Artigo a : app.artigoList){
                    if (a.getCodigo() == updateArtigo.getCodigo()){
                        a.setPreco(preco);
                        a.setDescricao(prodDescricao.getText());
                        a.setNome(prodNome.getText());

                        artigos.refresh();
                        for(classes.Artigo art: classes.Artigo.readAll()){
                            if(a.getCodigo().getValue() == art.getCodigo()){
                                art.update(art.getCodigo(), preco, prodDescricao.getText(), prodNome.getText());
                            }
                        }
                    }
                }
                
                pop(); 
            }
            else
                System.out.println("erro");
                               
        }catch(NumberFormatException ex){
            System.out.println("ERRRO");
        }
    }
    
    public void newFunc(){
        switchScene("inserirFuncionario");
    }
    
    public void newFunc2(){
        switchScene("inserirCredenciaisFuncionario");
    }
    
    public void endSession(){
        app.gotoLogin();
    }
}
