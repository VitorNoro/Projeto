/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Caixa.MainScene;
import GUI.Caixa.Scene;
import classes.Pagamento;
import classesFX.Artigo;
import classesFX.Cliente;
import classesFX.LinhaArtigo;
import classesFX.Venda;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

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
    @FXML
    private TextField filterField;
    @FXML
    protected TableView<Cliente> clientes;
    @FXML
    protected TableColumn<Cliente, String> clienteContribuinte;
    @FXML
    protected TableColumn<Cliente, String> clienteNome;
    @FXML
    protected TableColumn<Cliente, String> clienteContacto;
    
    private classes.Venda vendaBD = new classes.Venda();
    private Venda vendaFX;
    private ObservableList<LinhaArtigo> linhasVenda;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainScene scene = new MainScene();
        info.setCenter(scene);
        
        linhasVenda = FXCollections.observableArrayList();
        
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
        
        //classes.Venda vendaBD = new classes.Venda();
        vendaBD.createT();
        
        
       
        codArtLinha.setCellValueFactory(cellData -> cellData.getValue().getArtigo().asObject());
        precoLinha.setCellValueFactory(cellData -> cellData.getValue().getTotal().asObject());
        quantLinha.setCellValueFactory(cellData -> cellData.getValue().getQuantidade().asObject());
        nomeArtLinha.setCellValueFactory(cellData -> cellData.getValue().getNomeArtigo());
        
        quantLinha.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter(){
            @Override
            public Integer fromString(String value) {
                try {
                    if(Integer.parseInt(value) > 0)
                        return Integer.parseInt(value);
                    else
                        return -1;
                } catch(NumberFormatException e) {
                    return -1;
                }
            }
        }));
        
        quantLinha.setOnEditCommit(t -> {            
            if(t.getNewValue() < 0) {
                //erro.setText("ERRO");
                t.getRowValue().setQuantidade(t.getOldValue());
            } else {
                t.getRowValue().setQuantidade(t.getNewValue());
                classes.LinhaArtigo.update(t.getRowValue().getCodigo().getValue(), t.getNewValue());
                linhas.refresh();
                sync();
            }
            
            linhas.refresh();
        });
        
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
        
        sync();
        total.setText(vendaFX.getTotal().getValue().toString());
    }
    
    public void adicionar(){
        classes.LinhaArtigo linha = new classes.LinhaArtigo();
        classes.Artigo art = new classes.Artigo();
        
        for(classes.Artigo a: classes.Artigo.readAll()){
            if(a.getCodigo() == artigos.getSelectionModel().getSelectedItem().getCodigo().getValue()){
                art = a;
            }
        }
        
        linha.setArtigo(art);
        linha.setVenda(vendaBD);
        linha.setQuantidade(0);
        linha.createT();
        
        /*LinhaArtigo linha = new LinhaArtigo();
        
        linha.setCodigo(temp.getCodigo());
        linha.setArtigo(temp.getArtigo().getCodigo());
        linha.setNomeArtigo(temp.getArtigo().getNome());
        linha.setQuantidade(temp.getQuantidade());
        linha.setTotal(temp.getTotal());
        linha.setVenda(temp.getVenda().getCodigo());*/
        
        sync();
        
        linhas.refresh();
    }
    
    public void remover(){
        classes.LinhaArtigo.delete(linhas.getSelectionModel().getSelectedItem().getCodigo().getValue());
        
        sync();
        linhas.refresh();
    }
    
    public void removerTudo(){
        for(classes.LinhaArtigo l : classes.LinhaArtigo.readAll()){
            if(l.getVenda().getCodigo() == vendaFX.getCodigo().getValue()){
                classes.LinhaArtigo.delete(l.getCodigo());
            }
        }
        
        sync();
        linhas.refresh();
    }
    
    public void confirmar(){
        if(vendaFX.getTotal().getValue() > 0){
            switchScene("selectClientes");

            clienteContribuinte.setCellValueFactory(cellData -> cellData.getValue().getNumContribuinte());
            clienteNome.setCellValueFactory(cellData -> cellData.getValue().getNome());
            clienteContacto.setCellValueFactory(cellData -> cellData.getValue().getContacto());

            FilteredList<Cliente> filteredData = new FilteredList<>(app.clienteList, p -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(cliente -> {
                    // If filter text is empty, display all persons.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (cliente.getNome().getValue().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches first name.
                    } 

                    return false; // Does not match.
                });
            });

            // 3. Wrap the FilteredList in a SortedList. 
            SortedList sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            sortedData.comparatorProperty().bind(clientes.comparatorProperty());

            clientes.setItems(sortedData);
            clientes.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override 
                public void handle(MouseEvent event) {
                    if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                        Pagamento pag = new Pagamento();        
                        
                        classes.Cliente cli = new classes.Cliente();
                
                        cli.setNome(clientes.getSelectionModel().getSelectedItem().getNome().getValueSafe());
                        cli.setNumContribuinte(clientes.getSelectionModel().getSelectedItem().getNumContribuinte().getValueSafe());
                        cli.setContacto(clientes.getSelectionModel().getSelectedItem().getContacto().getValueSafe());
                        
                        pag.setNumcontribuinte(cli);
                        pag.setVenda(vendaBD);
                        pag.setReparacao(null);
                        pag.setArtigos("");
                        
                        pag.createT();
                        
                        switchScene("opening");
                    }
                }
                });

            clientes.getSelectionModel().selectFirst();

            clienteContribuinte.prefWidthProperty().bind(clientes.widthProperty().divide(4)); // w * 1/4
            clienteNome.prefWidthProperty().bind(clientes.widthProperty().divide(2)); // w * 1/4
            clienteContacto.prefWidthProperty().bind(clientes.widthProperty().divide(4)); // w * 1/4
        }else{
            System.out.println("erro");
                    
        }
    }
    
    
    
    public void endSession(){
        app.gotoLogin();
    }
    
    public void sync(){
        vendaFX = new Venda();
        linhasVenda.removeAll(linhasVenda);        
        
        vendaBD.read(vendaBD.getCodigo());
        vendaFX.setCodigo(vendaBD.getCodigo());
        vendaFX.setTotal(vendaBD.getTotal());
        
        for(classes.LinhaArtigo l : classes.LinhaArtigo.readAll()){
            if(l.getVenda().getCodigo() == vendaFX.getCodigo().getValue()){
                LinhaArtigo temp = new LinhaArtigo();
                
                temp.setCodigo(l.getCodigo());
                temp.setArtigo(l.getArtigo().getCodigo());
                temp.setNomeArtigo(l.getArtigo().getNome());
                temp.setQuantidade(l.getQuantidade());
                temp.setTotal(l.getTotal());
                temp.setVenda(l.getVenda().getCodigo());
                
                
                linhasVenda.add(temp);
            }
        }      

        System.out.println(vendaFX.getTotal().getValue());
        
        total.setText(vendaFX.getTotal().getValue().toString());
    }
    
}
