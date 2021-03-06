/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import classesFX.Artigo;
import GUI.Gestor.MainScene;
import GUI.Gestor.Scene;
import classesFX.Cliente;
import classesFX.Fornecedor;
import classesFX.Funcionario;
import classesFX.Subscricao;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;

/**
 * FXML Controller class
 *
 * @author v_nor
 */
public class GestorController extends GestorControllerNodes implements Initializable {
    
    private Main app = new Main();
    private Artigo updateArtigo;
    private classes.Funcionario newFunc;
    private Date hoje = new Date();
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
                erro.setText("Insira um valor válido");
                t.getRowValue().setPreco(t.getOldValue());
            } else {
                erro.setText("");
                t.getRowValue().setPreco(t.getNewValue());
                classes.Artigo.updatePreco(t.getRowValue().getCodigo().getValue(), t.getNewValue());
            }
            artigos.refresh();
        });
        
        
        nomeArtigos.setCellFactory(TextFieldTableCell.forTableColumn());
        nomeArtigos.setOnEditCommit((CellEditEvent<Artigo, String> t) -> {
            boolean existe = false;
            
            for(Artigo a : app.artigoList){
                if(a.getNome().getValue().equals(t.getNewValue())){
                    erro.setText("Já existe um artigo com esse nome");
                    existe = true;
                }                          
            }
            
            if(!existe){
                ((Artigo) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setNome(t.getNewValue());
                
                classes.Artigo.updateNome(t.getRowValue().getCodigo().getValue(), t.getNewValue());
                erro.setText("");
            }
        });

        descArtigos.setCellFactory(TextFieldTableCell.forTableColumn());
        descArtigos.setOnEditCommit((CellEditEvent<Artigo, String> t) -> {
            ((Artigo) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                    ).setNome(t.getNewValue());
            
            classes.Artigo.updateDescricao(t.getRowValue().getCodigo().getValue(), t.getNewValue());
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
            erro.setText("");
            for(Artigo a : app.artigoList){
                
                if (a.getCodigo() == artigos.getSelectionModel().getSelectedItem().getCodigo()){
                    classes.Artigo.delete(a.getCodigo().getValue());
                    app.artigoList.remove(a);
                    break;
                }
            }
        }
    }
    
    public void addStock(){
        if(artigos.getSelectionModel().isEmpty())
            erro.setText("Selecione um artigo");
        else{
            erro.setText("");
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
        int quant = -1;
        float preco = -1;
        try{
            quant = Integer.parseInt(prodQuantidade.getText());
            preco = Float.parseFloat(prodPreco.getText().replaceAll(",", "."));
            boolean existeN = false;
            
            erroNome.setText("");
            
            for(classes.Artigo t : classes.Artigo.readAll()){
                if(t.getNome().equals(prodNome.getText())){
                    erroNome.setText("Nome em uso");
                    existeN = true;
                }                          
            }
            
            if(quant < 0)
                erroQuant.setText("Insira um valor válido para a quantidade");
            else
                erroQuant.setText("");
            if(preco <= 0)
                erroPreco.setText("Insira um valor válido para o preço");
            else
                erroPreco.setText("");
            if(prodNome.getText().isEmpty())
                erroNome.setText("Insira um nome");
            else
                erroNome.setText("");
            
            if(!(prodNome.getText().isEmpty() || preco <= 0 || quant < 0) && !existeN){
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
            
        }catch(NumberFormatException ex){
            if(quant == -1)
                erroQuant.setText("Insira um valor válido para a quantidade");
            else
                erroQuant.setText("");
            if(preco == -1)
                erroPreco.setText("Insira um valor válido para o preço");
            else
                erroPreco.setText("");
            if(prodNome.getText().isEmpty())
                erroNome.setText("Insira um nome");
            else
                erroNome.setText("");
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
        
        nomeFuncionarios.setCellFactory(TextFieldTableCell.forTableColumn());
        nomeFuncionarios.setOnEditCommit((CellEditEvent<Funcionario, String> t) -> {
                ((Funcionario) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setNome(t.getNewValue());
                
                classes.Funcionario.updateNome(t.getRowValue().getCodigo().getValue(), t.getNewValue());
        });
        
        moradaFuncionarios.setCellFactory(TextFieldTableCell.forTableColumn());
        moradaFuncionarios.setOnEditCommit((CellEditEvent<Funcionario, String> t) -> {
                ((Funcionario) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setNome(t.getNewValue());
                
                classes.Funcionario.updateMorada(t.getRowValue().getCodigo().getValue(), t.getNewValue());
        });
        
        contactoFuncionarios.setCellFactory(TextFieldTableCell.forTableColumn());
        contactoFuncionarios.setOnEditCommit((CellEditEvent<Funcionario, String> t) -> {
            if(t.getNewValue().isEmpty() || t.getNewValue().length() != 9)
                erro.setText("Insira um número de telefone válido");
            else{
                erro.setText("");
                ((Funcionario) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setNome(t.getNewValue());
                
                classes.Funcionario.updateContacto(t.getRowValue().getCodigo().getValue(), t.getNewValue());
            }
        });
        
        funcaoFuncionarios.setCellFactory((TableColumn<Funcionario, String> p) -> {
                ComboBoxTableCell<Funcionario, String> cell = new ComboBoxTableCell<>("Gestor","Caixa","Reparador");
                cell.setComboBoxEditable(true);
                return cell;
        });
        funcaoFuncionarios.setOnEditCommit((CellEditEvent<Funcionario, String> t) -> {
                ((Funcionario) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setNome(t.getNewValue());
                
                classes.Funcionario.updateFuncao(t.getRowValue().getCodigo().getValue(), t.getNewValue());
        });
        
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
        
        if(funcNome.getText().isEmpty())
            erroNome.setText("Insira um nome");
        else
            erroNome.setText("");
        
        if(funcMorada.getText().isEmpty())
            erroMorada.setText("Insira uma morada");
        else
            erroMorada.setText("");
        
        if(funcTelefone.getText().isEmpty() || funcTelefone.getText().length() != 9)
            erroTelefone.setText("Insira um número de telefone válido");
        else
            erroTelefone.setText("");
        
        if(combo.getSelectionModel().isEmpty())
            erroFuncao.setText("Selecione uma função");
        else
            erroFuncao.setText("");
        
        if(!(funcNome.getText().isEmpty() || funcMorada.getText().isEmpty() || funcTelefone.getText().isEmpty() || combo.getSelectionModel().isEmpty())){
            newFunc.setNome(funcNome.getText());
            newFunc.setMorada(funcMorada.getText());
            newFunc.setContacto(funcTelefone.getText());
            newFunc.setFuncao(combo.getSelectionModel().getSelectedItem().toString());

            switchScene("inserirCredenciaisFuncionario");
        }
        
        
    }
    
    public void confirmNewFunc(){
        boolean existeU = false;
        
            for(classes.Funcionario f : classes.Funcionario.readAll()){
                if(f.getUsername().equals(funcUser.getText())){
                    erroUsername.setText("O nome de utilizador já existe");
                    existeU = true;
                }                          
            }
            
            if(!existeU)
                erroUsername.setText("");
            if(funcUser.getText().isEmpty())
                erroUsername.setText("Insira um nome de utilizador");
            else
                erroUsername.setText("");
            if(funcPass.getText().isEmpty())
                erroPassword.setText("Insira uma palavra passe");
            else
                erroPassword.setText("");
                    
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

    }
    
    public void deleteFuncionario(){
        if(funcionarios.getSelectionModel().isEmpty())
            erro.setText("Selecione um funcionario");
        else{
            for(Funcionario f : app.funcList){
                
                if (f.getCodigo() == funcionarios.getSelectionModel().getSelectedItem().getCodigo()){
                    classes.Funcionario.delete(f.getCodigo().getValue());
                    app.funcList.remove(f);
                    break;
                }
            }
        }
    }
    
    public void subs(){
        switchScene("listarSubscricoes");
        
        Callback<TableColumn<Subscricao, Date>, TableCell<Subscricao, Date>> dateCellFactory
                = (TableColumn<Subscricao, Date> param) -> new SubDateEditingCell();
        
        subCodigo.setCellValueFactory(cellData -> cellData.getValue().getCodigo().asObject());
        subNome.setCellValueFactory(cellData -> cellData.getValue().getNome());
        subFim.setCellValueFactory(cellData -> cellData.getValue().getFimsubscricao());
        subMensalidade.setCellValueFactory(cellData -> cellData.getValue().getMensalidade().asObject());
        subCliente.setCellValueFactory(cellData -> cellData.getValue().getCliente());
        
        
        subNome.setCellFactory(TextFieldTableCell.forTableColumn());
        subNome.setOnEditCommit((CellEditEvent<Subscricao, String> t) -> {
                ((Subscricao) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setNome(t.getNewValue());
                
                classes.Subscricao.update(t.getRowValue().getCodigo().getValue(), t.getNewValue());
        });

        subFim.setCellValueFactory(cellData -> cellData.getValue().getFimsubscricao());
        subFim.setCellFactory(dateCellFactory);
        subFim.setOnEditCommit(
                (TableColumn.CellEditEvent<Subscricao, Date> t) -> {
                    if(t.getNewValue().after(hoje)){
                        erro.setText("");
                        
                        ((Subscricao) t.getTableView().getItems()
                        .get(t.getTablePosition().getRow()))
                        .setFimsubscricao(t.getNewValue());

                        classes.Subscricao.update(t.getRowValue().getCodigo().getValue(), t.getNewValue());
                    }
                    else
                        erro.setText("Insira uma data futura");
                    
                });
        
        subMensalidade.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter(){
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
        
        subMensalidade.setOnEditCommit(t -> {            
            if(t.getNewValue().isNaN()) {
                erro.setText("Insira um valor válido");
                t.getRowValue().setMensalidade(t.getOldValue());
            } else {
                erro.setText("");
                t.getRowValue().setMensalidade(t.getNewValue());
                classes.Subscricao.update(t.getRowValue().getCodigo().getValue(), t.getNewValue());
            }
            artigos.refresh();
        });
        
        FilteredList<Subscricao> filteredData = new FilteredList<>(app.subscricaoList, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(subscricao -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (subscricao.getNome().getValue().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } 
                
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(subscricoes.comparatorProperty());
        
        subscricoes.setItems(sortedData);
        
        subscricoes.getSelectionModel().selectFirst();
        
        subCodigo.prefWidthProperty().bind(subscricoes.widthProperty().divide(8)); // w * 1/4
        subNome.prefWidthProperty().bind(subscricoes.widthProperty().divide(5)); // w * 1/4
        subFim.prefWidthProperty().bind(subscricoes.widthProperty().divide(4)); // w * 1/4
        subMensalidade.prefWidthProperty().bind(subscricoes.widthProperty().divide(5)); // w * 1/4
        subCliente.prefWidthProperty().bind(subscricoes.widthProperty().divide(5)); // w * 1/4
        
    }
    
    public void deleteSubscricao(){
        if(subscricoes.getSelectionModel().isEmpty())
            erro.setText("Selecione uma subscrição");
        else{
            erro.setText("");
            for(Subscricao s : app.subscricaoList){            
                if (s.getCodigo() == subscricoes.getSelectionModel().getSelectedItem().getCodigo()){
                    classes.Subscricao.delete(s.getCodigo().getValue());
                    app.subscricaoList.remove(s);
                    break;
                }
            }
        }
    }
    
    public void addSubscricao(){
        switchScene("CriarSub");
        
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
    
    public void confirmNewSubscricao(){
        classes.Subscricao sub = new classes.Subscricao();
        float mensalidade = -1;
        try{
            mensalidade = Float.parseFloat(newSubMensalidade.getText().replaceAll(",", "."));
            LocalDate data = newSubFim.getValue();
            Date date = java.sql.Date.valueOf(data);            

            boolean existeN = false;
            
            for(classes.Artigo t : classes.Artigo.readAll()){
                if(t.getNome().equals(newSubNome.getText())){
                    existeN = true;
                }                          
            }
            
            if(mensalidade <= 0)
                erroMensalidade.setText("Insira um valor válido para a mensalidade");
            else
                erroQuant.setText("");
            if(existeN)
                erroNome.setText("Já existe uma subscrição com este nome");
            else
                erroPreco.setText("");
            if(newSubNome.getText().isEmpty())
                erroNome.setText("Insira um nome");
            else
                erroNome.setText("");
            if(hoje.after(date))
                erroFim.setText("Insira uma data futura");
            else
                erroFim.setText("");
            if(clientes.getSelectionModel().isEmpty())
                erroCliente.setText("Insira uma data futura");
            else
                erroCliente.setText("");
            
            if(!(newSubNome.getText().isEmpty() || hoje.after(date) || mensalidade <= 0 || clientes.getSelectionModel().isEmpty()) && !existeN){
                sub.setNome(newSubNome.getText());
                sub.setFimsubscricao(date);
                sub.setMensalidade(mensalidade);
                
                classes.Cliente cli = new classes.Cliente();
                
                cli.setNome(clientes.getSelectionModel().getSelectedItem().getNome().getValueSafe());
                cli.setNumContribuinte(clientes.getSelectionModel().getSelectedItem().getNumContribuinte().getValueSafe());
                cli.setContacto(clientes.getSelectionModel().getSelectedItem().getContacto().getValueSafe());
                
                sub.setCliente(cli);

                sub.createT();

                Subscricao temp = new Subscricao();

                temp.setCodigo(sub.getCodigo());
                temp.setNome(sub.getNome());
                temp.setFimsubscricao(sub.getFimsubscricao());
                temp.setMensalidade(sub.getMensalidade());
                temp.setCliente(sub.getCliente().getNumContribuinte());

                app.subscricaoList.add(temp);

                subs();
            }
            else
                System.out.println("ERRO");
            
            
        }catch(NumberFormatException ex){
            if(mensalidade == -1)
                erroMensalidade.setText("Insira um valor válido para a mensalidade");
            else
                erroQuant.setText("");
            if(newSubNome.getText().isEmpty())
                erroNome.setText("Insira um nome");
            else
                erroNome.setText("");
        }
    }
    
    
    public void addFornecedor(){
        switchScene("adicionarFornecedor");
        
    }
     
    public void forn(){
        switchScene("listarFornecedores");
        
        codFornecedor.setCellValueFactory(cellData -> cellData.getValue().getCodigo().asObject());
        fornNome.setCellValueFactory(cellData -> cellData.getValue().getNome());
        fornContacto.setCellValueFactory(cellData -> cellData.getValue().getContacto());
        

        fornContacto.setCellFactory(TextFieldTableCell.forTableColumn());
        fornContacto.setOnEditCommit((CellEditEvent<Fornecedor, String> t) -> {
                ((Fornecedor) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setContacto(t.getNewValue());
                
                classes.Fornecedor.update(t.getRowValue().getCodigo().getValue(), t.getNewValue());
        });
        
        
        
        FilteredList<Fornecedor> filteredData = new FilteredList<>(app.fornecedorList, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(fornecedor -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (fornecedor.getContacto().getValue().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } 
                
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(fornecedores.comparatorProperty());
        
        fornecedores.setItems(sortedData);
        
        fornecedores.getSelectionModel().selectFirst();
        
      
        
        codFornecedor.prefWidthProperty().bind(fornecedores.widthProperty().divide(5)); // w * 1/4
        fornNome.prefWidthProperty().bind(fornecedores.widthProperty().divide(3)); // w * 1/4
        fornContacto.prefWidthProperty().bind(fornecedores.widthProperty().divide(4)); // w * 1/4
        
    }
    
    
    public void deleteFornecedor(){
        if(fornecedores.getSelectionModel().isEmpty())
            erro.setText("Selecione um fornecedor");
        else{
            for(Fornecedor f : app.fornecedorList){            
                if (f.getCodigo() == fornecedores.getSelectionModel().getSelectedItem().getCodigo()){
                    classes.Fornecedor.delete(f.getCodigo().getValue());
                    app.fornecedorList.remove(f);
                    break;
                }
            }
        }
    }
    
    public void confirmNewFornecedor(){
        classes.Fornecedor forn = new classes.Fornecedor();      

            boolean existeN = false;
            
            for(classes.Fornecedor t : classes.Fornecedor.readAll()){
                if(t.getNome().equals(newfornNome.getText())){
                    erroForn.setText("Nome em uso");
                    existeN = true;
                }                          
            }
            
            if(!(newfornNome.getText().isEmpty() || newfornContacto.getText().isEmpty()) && !existeN){
                forn.setNome(newfornNome.getText());
                forn.setContacto(newfornContacto.getText());


                forn.createT();

                Fornecedor temp = new Fornecedor();

                temp.setCodigo(forn.getCodigo());
                temp.setNome(forn.getNome());
                temp.setContacto(forn.getContacto());
                

                app.fornecedorList.add(temp);

                forn();
            }
            else
                System.out.println("ERRO");
           
    }
    public void endSession(){
        app.gotoLogin();
    }
}
