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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v_nor
 */
@Entity
@Table(name = "FATURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fatura.findAll", query = "SELECT f FROM Fatura f"),
    @NamedQuery(name = "Fatura.findByCodigo", query = "SELECT f FROM Fatura f WHERE f.codigo = :codigo"),
    @NamedQuery(name = "Fatura.findByNumcontribuinte", query = "SELECT f FROM Fatura f WHERE f.numcontribuinte = :numcontribuinte"),
    @NamedQuery(name = "Fatura.findByTotal", query = "SELECT f FROM Fatura f WHERE f.total = :total"),
    @NamedQuery(name = "Fatura.findByArtigos", query = "SELECT f FROM Fatura f WHERE f.artigos = :artigos")})
public class Fatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private IntegerProperty codigo;
    @Column(name = "NUMCONTRIBUINTE")
    private StringProperty numcontribuinte;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOTAL")
    private FloatProperty total;
    @Column(name = "ARTIGOS")
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
