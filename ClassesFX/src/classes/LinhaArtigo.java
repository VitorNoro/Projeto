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
@Table(name = "LINHAARTIGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LinhaArtigo.findAll", query = "SELECT l FROM LinhaArtigo l"),
    @NamedQuery(name = "LinhaArtigo.findByCodigo", query = "SELECT l FROM LinhaArtigo l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "LinhaArtigo.findByTotal", query = "SELECT l FROM LinhaArtigo l WHERE l.total = :total"),
    @NamedQuery(name = "LinhaArtigo.findByQuantidade", query = "SELECT l FROM LinhaArtigo l WHERE l.quantidade = :quantidade")})
public class LinhaArtigo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="SEQ_GEN_LIN", sequenceName="LINHAARTIGO_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN_LIN")
    @Column(name = "CODIGO")
    private IntegerProperty codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "TOTAL")
    private FloatProperty total;
    @Basic(optional = false)
    @Column(name = "QUANTIDADE")
    private IntegerProperty quantidade;
    @JoinColumn(name = "ARTIGO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Artigo artigo;
    @JoinColumn(name = "VENDA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Venda venda;
    
    public LinhaArtigo() {
    }

    public LinhaArtigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public LinhaArtigo(Integer quantidade, Artigo artigo, Venda venda) {
        this.quantidade = new SimpleIntegerProperty(quantidade);
        this.artigo = artigo;
        this.venda = venda;
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

    public void setTotal(float total) {
        this.total = new SimpleFloatProperty(total);
    }

    public Integer getQuantidade() {
        return quantidade.getValue();
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = new SimpleIntegerProperty(quantidade);
    }

    public Artigo getArtigo() {
        return artigo;
    }

    public void setArtigo(Artigo artigo) {
        this.artigo = artigo;
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
        if (!(object instanceof LinhaArtigo)) {
            return false;
        }
        LinhaArtigo other = (LinhaArtigo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.LinhaArtigo[ codigo=" + codigo + " ]";
    }

   
}
