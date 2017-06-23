/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import javafx.beans.property.*;


/**
 *
 * @author v_nor
 */

public class Fatura implements Serializable {

    private static final long serialVersionUID = 1L;

    private IntegerProperty codigo;
   
    private StringProperty numcontribuinte;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
   
    private FloatProperty total;
  
    private StringProperty artigos;


    public Integer getCodigo() {
        return codigo.getValue();
    }

    public void setCodigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public String getNumContribuinte() {
        return numcontribuinte.getValue();
    }

    public void setNumContribuinte(String numcontribuinte) {
        this.numcontribuinte = new SimpleStringProperty(numcontribuinte);
    }

    public float getTotal() {
        return total.getValue();
    }

    public void setTotal(float total) {
        this.total = new SimpleFloatProperty(total);
    }

    public String getArtigos() {
        return artigos.getValue();
    }

    public void setArtigos(String artigos) {
        this.artigos = new SimpleStringProperty(artigos);
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
        if (!(object instanceof Fatura)) {
            return false;
        }
        Fatura other = (Fatura) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Fatura[ codigo=" + codigo + " ]";
    }
    
}
