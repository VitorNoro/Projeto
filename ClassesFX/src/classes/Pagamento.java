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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v_nor
 */
@Entity
@Table(name = "PAGAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM Pagamento p"),
    @NamedQuery(name = "Pagamento.findByCodigo", query = "SELECT p FROM Pagamento p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Pagamento.findByTotal", query = "SELECT p FROM Pagamento p WHERE p.total = :total"),
    @NamedQuery(name = "Pagamento.findByArtigos", query = "SELECT p FROM Pagamento p WHERE p.artigos = :artigos")})
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="SEQ_GEN_PAG", sequenceName="PAGAMENTO_SEQ", allocationSize=1)
        @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN_PAG")
    @Column(name = "CODIGO")
    private IntegerProperty codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "TOTAL")
    private FloatProperty total;
    @Column(name = "ARTIGOS")
    private StringProperty artigos;
    @JoinColumn(name = "NUMCONTRIBUINTE", referencedColumnName = "NUMCONTRIBUINTE")
    @ManyToOne(optional = false)
    private Cliente numcontribuinte;
    @JoinColumn(name = "REPARACAO", referencedColumnName = "CODIGO")
    @ManyToOne
    private Reparacao reparacao;
    @JoinColumn(name = "VENDA", referencedColumnName = "CODIGO")
    @ManyToOne
    private Venda venda;
    
  

    public Pagamento() {
    }

    public Pagamento(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public Pagamento(Integer codigo, float total) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.total = new SimpleFloatProperty(total);
    }

    public Integer getCodigo() {
        return codigo.getValue();
    }

    public void setCodigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
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

    public Cliente getNumcontribuinte() {
        return numcontribuinte;
    }

    public void setNumcontribuinte(Cliente numcontribuinte) {
        this.numcontribuinte = numcontribuinte;
    }

    public Reparacao getReparacao() {
        return reparacao;
    }

    public void setReparacao(Reparacao reparacao) {
        this.reparacao = reparacao;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
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
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Pagamento[ codigo=" + codigo + " ]";
    }
    
   
   
    
}
