/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import GUI.Reparador.MainScene;
import GUI.Reparador.Scene;
import classesFX.Cliente;
import classesFX.Diagnostico;
import classesFX.Reparacao;
import static java.awt.SystemColor.info;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;

/**
 * FXML Controller class
 *
 * @author v_nor
 */
public class ReparadorController implements Initializable {
    private Main app = new Main();
    private classes.Diagnostico newDiag;
    
    @FXML
    protected BorderPane info;
     
    @FXML
    protected TableView<Reparacao> reparacoes;
    @FXML
    protected TableColumn<Reparacao, Integer> repCodigo;
    @FXML
    protected TableColumn<Reparacao, Integer> repDiagnostico;
    @FXML
    protected TableColumn<Reparacao, Float> repCusto;
   
    
   
    
    @FXML
    protected TextArea newDiagProblema;

    @FXML
    protected TextField newDiagEquipamento;
    
    
    
    
    
    
    
    @FXML
    protected TableView<Diagnostico> diagnosticos;
    @FXML
    protected TableColumn<Diagnostico, Integer> diagCodigo;
    @FXML
    protected TableColumn<Diagnostico, String> diagCliente;
    @FXML
    protected TableColumn<Diagnostico, String> diagEquipamento;
    @FXML
    protected TableColumn<Diagnostico, String> diagProblema;
     
    @FXML
    protected TextField filterField;
    @FXML
    protected Label erro;
    
    
    
    
    @FXML
    protected TableView<Cliente> clientes;
    @FXML
    protected TableColumn<Cliente, String> clienteContribuinte;
    @FXML
    protected TableColumn<Cliente, String> clienteNome;
    @FXML
    protected TableColumn<Cliente, String> clienteContacto;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MainScene scene = new MainScene();
        info.setCenter(scene);
    }    
    
    public void switchScene(String scene){
        Scene current = new Scene(this, scene);
        info.setCenter(current);
    }
    
    public void diags(){
        switchScene("listarDiagnostico");
        
        
        diagCodigo.setCellValueFactory(cellData -> cellData.getValue().getCodigo().asObject());
        diagCliente.setCellValueFactory(cellData -> cellData.getValue().getCliente());
        diagEquipamento.setCellValueFactory(cellData -> cellData.getValue().getEquipamento());
        diagProblema.setCellValueFactory(cellData -> cellData.getValue().getProblema());
        
        
        diagProblema.setCellFactory(TextFieldTableCell.forTableColumn());
        diagProblema.setOnEditCommit((TableColumn.CellEditEvent<Diagnostico, String> t) -> {
                ((Diagnostico) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setProblema(t.getNewValue());
                
                classes.Diagnostico.update(t.getRowValue().getCodigo().getValue(), t.getNewValue());
        });

        
        
       
        
        FilteredList<Diagnostico> filteredData = new FilteredList<>(app.diagnosticoList, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(diagnostico -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (diagnostico.getCliente().getValue().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } 
                
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(diagnosticos.comparatorProperty());
        
        diagnosticos.setItems(sortedData);
        
        diagnosticos.getSelectionModel().selectFirst();
        
      
        
        diagCodigo.prefWidthProperty().bind(diagnosticos.widthProperty().divide(5)); // w * 1/4
        diagCliente.prefWidthProperty().bind(diagnosticos.widthProperty().divide(5)); // w * 1/4
        diagEquipamento.prefWidthProperty().bind(diagnosticos.widthProperty().divide(4)); // w * 1/4
        diagProblema.prefWidthProperty().bind(diagnosticos.widthProperty().divide(3)); // w * 1/4
        
        
    }
    
    public void deleteDiagnostico(){
        if(diagnosticos.getSelectionModel().isEmpty())
            erro.setText("Selecione uma subscrição");
        else{
            for(Diagnostico d : app.diagnosticoList){            
                if (d.getCodigo() == diagnosticos.getSelectionModel().getSelectedItem().getCodigo()){
                    classes.Diagnostico.delete(d.getCodigo().getValue());
                    app.diagnosticoList.remove(d);
                    break;
                }
            }
        }
    }
    
    public void addDiagnostico(){
        switchScene("adicionarDiagnostico");
        
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
        
        clientes.getSelectionModel().selectFirst();
        
        clienteContribuinte.prefWidthProperty().bind(clientes.widthProperty().divide(4)); // w * 1/4
        clienteNome.prefWidthProperty().bind(clientes.widthProperty().divide(2)); // w * 1/4
        clienteContacto.prefWidthProperty().bind(clientes.widthProperty().divide(4)); // w * 1/4


    }
    
    public void confirmNewDiagnostico(){
        classes.Diagnostico diag = new classes.Diagnostico();
        try{

            boolean existeN = false;
            
            for(classes.Diagnostico t : classes.Diagnostico.readAll()){
                if(t.getProblema().equals(newDiagProblema.getText())){
                    //erro.setText("Esses detalhes já foram escritos antes");
                    existeN = true;
                }                          
            }
            
            if(!( newDiagProblema.getText().isEmpty()) && !existeN){
                diag.setEquipamento(newDiagEquipamento.getText());
                diag.setProblema(newDiagProblema.getText());
               
                classes.Cliente cli = new classes.Cliente();
                
                cli.setNome(clientes.getSelectionModel().getSelectedItem().getNome().getValueSafe());
                cli.setNumContribuinte(clientes.getSelectionModel().getSelectedItem().getNumContribuinte().getValueSafe());
                cli.setContacto(clientes.getSelectionModel().getSelectedItem().getContacto().getValueSafe());
                
                diag.setCliente(cli);
                
                
               

                diag.createT();

                Diagnostico temp = new Diagnostico();

                temp.setCodigo(diag.getCodigo());
                temp.setProblema(diag.getProblema());
                temp.setEquipamento(diag.getEquipamento());
                temp.setCliente(diag.getCliente().getNumContribuinte());

                app.diagnosticoList.add(temp);

                diags();
            }
            else
                System.out.println("ERRO");
            
            
        }catch(NumberFormatException ex){
            System.out.println("ERRRO");
        }
    }
    
    public void endSession(){
        app.gotoLogin();
    }
    
}
