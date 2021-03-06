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

public class Diagnostico implements Serializable {

    
    private Collection<Reparacao> reparacaoCollection;

    private static final long serialVersionUID = 1L;
  
    private IntegerProperty codigo;

    private StringProperty equipamento;
    
    private StringProperty problema;

    private StringProperty cliente;
    
    public Diagnostico() {
    }

    public Diagnostico(Integer codigo) {
        this.codigo = new SimpleIntegerProperty();
    }

    public Diagnostico(Integer codigo, String equipamento, String problema, String cliente) {
        this.codigo = new SimpleIntegerProperty();
        this.equipamento = new SimpleStringProperty(equipamento);
        this.problema = new SimpleStringProperty(problema);
        this.cliente = new SimpleStringProperty(cliente);
    }

    public IntegerProperty getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public StringProperty getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = new SimpleStringProperty(equipamento);
    }

    public StringProperty getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = new SimpleStringProperty(problema);
    }

    public StringProperty getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = new SimpleStringProperty(cliente);
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
        if (!(object instanceof Diagnostico)) {
            return false;
        }
        Diagnostico other = (Diagnostico) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Diagnostico[ codigo=" + codigo + " ]";
    }

  
    public Collection<Reparacao> getReparacaoCollection() {
        return reparacaoCollection;
    }

    public void setReparacaoCollection(Collection<Reparacao> reparacaoCollection) {
        this.reparacaoCollection = reparacaoCollection;
    }
    
}
