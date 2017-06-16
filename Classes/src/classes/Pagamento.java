/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @Column(name = "CODIGO")
    private Integer codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "TOTAL")
    private BigDecimal total;
    @Column(name = "ARTIGOS")
    private String artigos;
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
        this.codigo = codigo;
    }

    public Pagamento(Integer codigo, BigDecimal total) {
        this.codigo = codigo;
        this.total = total;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getArtigos() {
        return artigos;
    }

    public void setArtigos(String artigos) {
        this.artigos = artigos;
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
