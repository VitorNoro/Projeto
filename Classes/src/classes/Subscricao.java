/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v_nor
 */
@Entity
@Table(name = "SUBSCRICAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subscricao.findAll", query = "SELECT s FROM Subscricao s"),
    @NamedQuery(name = "Subscricao.findByCodigo", query = "SELECT s FROM Subscricao s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "Subscricao.findByNome", query = "SELECT s FROM Subscricao s WHERE s.nome = :nome"),
    @NamedQuery(name = "Subscricao.findByFimsubscricao", query = "SELECT s FROM Subscricao s WHERE s.fimsubscricao = :fimsubscricao"),
    @NamedQuery(name = "Subscricao.findByMensalidade", query = "SELECT s FROM Subscricao s WHERE s.mensalidade = :mensalidade")})
public class Subscricao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Basic(optional = false)
    @Column(name = "FIMSUBSCRICAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fimsubscricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MENSALIDADE")
    private BigDecimal mensalidade;
    @JoinColumn(name = "CLIENTE", referencedColumnName = "NUMCONTRIBUINTE")
    @ManyToOne(optional = false)
    private Cliente cliente;

    public Subscricao() {
    }

    public Subscricao(Integer codigo) {
        this.codigo = codigo;
    }

    public Subscricao(Integer codigo, String nome, Date fimsubscricao, BigDecimal mensalidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.fimsubscricao = fimsubscricao;
        this.mensalidade = mensalidade;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getFimsubscricao() {
        return fimsubscricao;
    }

    public void setFimsubscricao(Date fimsubscricao) {
        this.fimsubscricao = fimsubscricao;
    }

    public BigDecimal getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(BigDecimal mensalidade) {
        this.mensalidade = mensalidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        if (!(object instanceof Subscricao)) {
            return false;
        }
        Subscricao other = (Subscricao) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Subscricao[ codigo=" + codigo + " ]";
    }
    
}
