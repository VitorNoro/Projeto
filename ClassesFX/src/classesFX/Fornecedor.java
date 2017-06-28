/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesFX;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author v_nor
 */

public class Fornecedor implements Serializable {
    private IntegerProperty codigo;
    private StringProperty nome;
    private StringProperty contacto;

    public Fornecedor() {
    }

    public Fornecedor(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public Fornecedor(Integer codigo, String nome, String contacto) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.nome = new SimpleStringProperty(nome);
        this.contacto = new SimpleStringProperty(contacto);
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Fornecedor[ codigo=" + codigo + " ]";
    }
    
    
}
