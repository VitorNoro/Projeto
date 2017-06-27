/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import classesFX.Artigo;
import GUI.Gestor.MainScene;
import GUI.Gestor.Scene;
import classesFX.Funcionario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.FloatStringConverter;

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
    private TableView<Funcionario> funcionarios;
    @FXML
    private TableColumn<Funcionario, Integer> codFuncionarios;
    @FXML
    private TableColumn<Funcionario, String> nomeFuncionarios;
    @FXML
    private TableColumn<Funcionario, String> moradaFuncionarios;
    @FXML
    private TableColumn<Funcionario, String> contactoFuncionarios;
    @FXML
    private TableColumn<Funcionario, String> funcaoFuncionarios;
    @FXML
    private TableColumn<Funcionario, String> userFuncionarios;
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
    @FXML
    private ComboBox combo;
    
    Main app = new Main();
    Artigo updateArtigo;
    classes.Funcionario newFunc;
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
        

        precoArtigos.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter(){
            @Override
            public Float fromString(String value) {
                try {
                    if(Float.parseFloat(value.replaceAll(",", ".")) > 0)
                        return Float.parseFloat(value.replaceAll(",", "."));
                    else
                        return Float.NaN;
                } catch(NumberFormatException e) {
                    return Float.NaN;
                }
            }
        }));
        
        precoArtigos.setOnEditCommit(t -> {            
            if(t.getNewValue().isNaN()) {
                erro.setText("ERRO");
                t.getRowValue().setPreco(t.getOldValue());
            } else {
                t.getRowValue().setPreco(t.getNewValue());
                classes.Artigo.updatePreco(t.getRowValue().getCodigo().getValue(), t.getNewValue());
            }
            artigos.refresh();
        });
        
        
        nomeArtigos.setCellFactory(TextFieldTableCell.forTableColumn());
        nomeArtigos.setOnEditCommit((CellEditEvent<Artigo, String> t) -> {
            if(1 == 2)
                ((Artigo) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setNome(t.getNewValue());
        });

        descArtigos.setCellFactory(TextFieldTableCell.forTableColumn());
        descArtigos.setOnEditCommit((CellEditEvent<Artigo, String> t) -> {
            ((Artigo) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                    ).setNome(t.getNewValue());
        });
        
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
    
    public void funcs(){
        switchScene("listarFuncionarios");
        
        codFuncionarios.setCellValueFactory(cellData -> cellData.getValue().getCodigo().asObject());
        nomeFuncionarios.setCellValueFactory(cellData -> cellData.getValue().getNome());
        moradaFuncionarios.setCellValueFactory(cellData -> cellData.getValue().getMorada());
        contactoFuncionarios.setCellValueFactory(cellData -> cellData.getValue().getContacto());
        funcaoFuncionarios.setCellValueFactory(cellData -> cellData.getValue().getFuncao());
        userFuncionarios.setCellValueFactory(cellData -> cellData.getValue().getUsername());
        
        FilteredList<Funcionario> filteredData = new FilteredList<>(app.funcList, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(funcionario -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (funcionario.getNome().getValue().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } 
                
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(funcionarios.comparatorProperty());
        
        funcionarios.setItems(sortedData);
        
        funcionarios.getSelectionModel().selectFirst();
        
        codFuncionarios.prefWidthProperty().bind(funcionarios.widthProperty().divide(10)); // w * 1/4
        nomeFuncionarios.prefWidthProperty().bind(funcionarios.widthProperty().divide(5)); // w * 1/4
        moradaFuncionarios.prefWidthProperty().bind(funcionarios.widthProperty().divide(4)); // w * 1/4
        contactoFuncionarios.prefWidthProperty().bind(funcionarios.widthProperty().divide(8)); // w * 1/4
        funcaoFuncionarios.prefWidthProperty().bind(funcionarios.widthProperty().divide(8)); // w * 1/4
        userFuncionarios.prefWidthProperty().bind(funcionarios.widthProperty().divide(5)); // w * 1/4
        
    }
    
    public void newFunc(){
        switchScene("inserirFuncionario");
        
        combo.getItems().clear();
        combo.getItems().addAll("Gestor", "Caixa", "Reparação");
    }
    
    public void newFunc2(){
        
        newFunc = new classes.Funcionario();
        
        for(classes.Funcionario f : classes.Funcionario.readAll()){
            if(!(funcNome.getText().isEmpty() || funcMorada.getText().isEmpty() || funcTelefone.getText().isEmpty() || combo.getSelectionModel().isEmpty())){
                newFunc.setNome(funcNome.getText());
                newFunc.setMorada(funcMorada.getText());
                newFunc.setContacto(funcTelefone.getText());
                newFunc.setFuncao(funcFuncao.getText());
         
                switchScene("inserirCredenciaisFuncionario");
            }
            else
                System.out.println("ERRO"); 
        
        }
    }
    
    public void confirmNewFunc(){
        boolean existeU = false;
        
            for(classes.Funcionario f : classes.Funcionario.readAll()){
                if(f.getNome().equals(funcUser.getText())){
                    //erro.setText("Nome em uso");
                    existeU = true;
                }                          
            }
            
            if(!(funcUser.getText().isEmpty() || funcPass.getText().isEmpty()) && !existeU){
                newFunc.setUsername(funcUser.getText());
                newFunc.setPassword(funcPass.getText());

                newFunc.createT();

                Funcionario temp = new Funcionario();

                temp.setCodigo(newFunc.getCodigo());
                temp.setNome(newFunc.getNome());
                temp.setMorada(newFunc.getMorada());
                temp.setContacto(newFunc.getContacto());
                temp.setFuncao(newFunc.getFuncao());
                temp.setUsername(newFunc.getUsername());
                temp.setPassword(newFunc.getPassword());

                app.funcList.add(temp);

                funcs();
            }
            else
                System.out.println("ERRO");
    }
    
    
    
    public void endSession(){
        app.gotoLogin();
    }
}
