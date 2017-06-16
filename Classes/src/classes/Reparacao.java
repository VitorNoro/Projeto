/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v_nor
 */
@Entity
@Table(name = "REPARACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reparacao.findAll", query = "SELECT r FROM Reparacao r"),
    @NamedQuery(name = "Reparacao.findByCodigo", query = "SELECT r FROM Reparacao r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "Reparacao.findByCusto", query = "SELECT r FROM Reparacao r WHERE r.custo = :custo")})
public class Reparacao implements Serializable {

    @OneToMany(mappedBy = "reparacao")
    private Collection<Pagamento> pagamentoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Integer codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CUSTO")
    private BigDecimal custo;
    @JoinColumn(name = "CLIENTE", referencedColumnName = "NUMCONTRIBUINTE")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "DIAGNOSTICO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Diagnostico diagnostico;

    public Reparacao() {
    }

    public Reparacao(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
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
        if (!(object instanceof Reparacao)) {
            return false;
        }
        Reparacao other = (Reparacao) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Reparacao[ codigo=" + codigo + " ]";
    }

    @XmlTransient
    public Collection<Pagamento> getPagamentoCollection() {
        return pagamentoCollection;
    }

    public void setPagamentoCollection(Collection<Pagamento> pagamentoCollection) {
        this.pagamentoCollection = pagamentoCollection;
    }
    
}
