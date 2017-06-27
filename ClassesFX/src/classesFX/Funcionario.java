/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesFX;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javafx.beans.property.*;


/**
 *
 * @author v_nor
 */

public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private IntegerProperty codigo;
  
    private StringProperty nome;
    
    private StringProperty contacto;
 
    private StringProperty morada;
    
    private StringProperty funcao;
 
    private StringProperty username;

    private StringProperty password;

    public Funcionario() {
    }

    public Funcionario(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public Funcionario(Integer codigo, String nome, String contacto, String morada, String funcao, String username, String password) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.nome = new SimpleStringProperty(nome);
        this.contacto = new SimpleStringProperty(contacto);
        this.morada = new SimpleStringProperty(morada);
        this.funcao = new SimpleStringProperty(funcao);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    public IntegerProperty getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public StringProperty getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = new SimpleStringProperty(nome);
    }

    public StringProperty getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = new SimpleStringProperty(contacto);
    }

    public StringProperty getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = new SimpleStringProperty(morada);
    }

    public StringProperty getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = new SimpleStringProperty(funcao);
    }
    
    public StringProperty getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = new SimpleStringProperty(username);
    }
    
    public StringProperty getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new SimpleStringProperty(password);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Funcionario[ codigo=" + codigo + " ]";
    }
    
}
