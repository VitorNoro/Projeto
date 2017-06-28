/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import classesFX.Artigo;
import classesFX.Cliente;
import classesFX.Funcionario;
import classesFX.Subscricao;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author v_nor
 */
public class GestorControllerNodes {
     @FXML
    protected BorderPane info;
    @FXML
    protected TableView<Artigo> artigos;
    @FXML
    protected TableColumn<Artigo, Integer> codArtigos;
    @FXML
    protected TableColumn<Artigo, Float> precoArtigos;
    @FXML
    protected TableColumn<Artigo, Integer> quantArtigos;
    @FXML
    protected TableColumn<Artigo, String> nomeArtigos;
    @FXML
    protected TableColumn<Artigo, String> descArtigos;
    @FXML
    protected TableView<Funcionario> funcionarios;
    @FXML
    protected TableColumn<Funcionario, Integer> codFuncionarios;
    @FXML
    protected TableColumn<Funcionario, String> nomeFuncionarios;
    @FXML
    protected TableColumn<Funcionario, String> moradaFuncionarios;
    @FXML
    protected TableColumn<Funcionario, String> contactoFuncionarios;
    @FXML
    protected TableColumn<Funcionario, String> funcaoFuncionarios;
    @FXML
    protected TableColumn<Funcionario, String> userFuncionarios;
    @FXML
    protected TextField filterField;
    @FXML
    protected Label erro;
    @FXML
    protected Spinner<Integer> spinnerStock;
    @FXML
    protected TextField prodNome;
    @FXML
    protected TextArea prodDescricao;
    @FXML
    protected TextField prodPreco;
    @FXML
    protected TextField prodQuantidade;
    
    @FXML
    protected TextField funcNome;
    @FXML
    protected TextField funcMorada;
    @FXML
    protected TextField funcTelefone;
    @FXML
    protected TextField funcFuncao;
    @FXML
    protected TextField funcUser;
    @FXML
    protected TextField funcPass;
    @FXML
    protected ComboBox combo;
    
    @FXML
    protected TableView<Subscricao> subscricoes;
    @FXML
    protected TableColumn<Subscricao, Integer> subCodigo;
    @FXML
    protected TableColumn<Subscricao, String> subNome;
    @FXML
    protected TableColumn<Subscricao, Date> subFim;
    @FXML
    protected TableColumn<Subscricao, Float> subMensalidade;
    @FXML
    protected TableColumn<Subscricao, String> subCliente;
    
    @FXML
    protected TextField newSubNome;
    @FXML
    protected DatePicker newSubFim;
    @FXML
    protected TextField newSubMensalidade;
    
    @FXML
    protected TableView<Cliente> clientes;
    @FXML
    protected TableColumn<Cliente, String> clienteContribuinte;
    @FXML
    protected TableColumn<Cliente, String> clienteNome;
    @FXML
    protected TableColumn<Cliente, String> clienteContacto;
}
