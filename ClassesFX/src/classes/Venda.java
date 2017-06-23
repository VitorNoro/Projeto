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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v_nor
 */
@Entity
@Table(name = "VENDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v"),
    @NamedQuery(name = "Venda.findByCodigo", query = "SELECT v FROM Venda v WHERE v.codigo = :codigo"),
    @NamedQuery(name = "Venda.findByTotal", query = "SELECT v FROM Venda v WHERE v.total = :total")})
public class Venda implements Serializable {

    @OneToMany(mappedBy = "venda")
    private Collection<Pagamento> pagamentoCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
    private Collection<LinhaArtigo> linhaartigoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="SEQ_GEN_VEN", sequenceName="VENDA_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN_VEN")
    @Column(name = "CODIGO")
    private IntegerProperty codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "TOTAL")
    private FloatProperty total;
    

    public Venda() {
    }

    public Venda(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public Venda(Integer codigo, Float total) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.total = new SimpleFloatProperty(total);
    }

    public int getCodigo() {
        return codigo.getValue();
    }

    public void setCodigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public float getTotal() {
        return total.getValue();
    }

    public void setTotal(Float total) {
        this.total = new SimpleFloatProperty(total);
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
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Venda[ codigo=" + codigo + " ]";
    }

    @XmlTransient
    public Collection<LinhaArtigo> getLinhaartigoCollection() {
        return linhaartigoCollection;
    }

    public void setLinhaartigoCollection(Collection<LinhaArtigo> linhaartigoCollection) {
        this.linhaartigoCollection = linhaartigoCollection;
    }

    @XmlTransient
    public Collection<Pagamento> getPagamentoCollection() {
        return pagamentoCollection;
    }

    public void setPagamentoCollection(Collection<Pagamento> pagamentoCollection) {
        this.pagamentoCollection = pagamentoCollection;
    }
    
}
