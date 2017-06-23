/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

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

    public Integer getCodigo() {
        return codigo.getValue();
    }

    public void setCodigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public String getNome() {
        return nome.getValue();
    }

    public void setNome(String nome) {
        this.nome = new SimpleStringProperty(nome);
    }

    public String getContacto() {
        return contacto.getValue();
    }

    public void setContacto(String contacto) {
        this.contacto = new SimpleStringProperty(contacto);
    }

    public String getMorada() {
        return morada.getValue();
    }

    public void setMorada(String morada) {
        this.morada = new SimpleStringProperty(morada);
    }

    public String getFuncao() {
        return funcao.getValue();
    }

    public void setFuncao(String funcao) {
        this.funcao = new SimpleStringProperty(funcao);
    }
    
    public String getUsername() {
        return username.getValue();
    }

    public void setUsername(String username) {
        this.username = new SimpleStringProperty(username);
    }
    
    public String getPassword() {
        return password.getValue();
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
