/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v_nor
 */
@Entity
@Table(name = "MANUTENCAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Manutencao.findAll", query = "SELECT m FROM Manutencao m"),
    @NamedQuery(name = "Manutencao.findByCodigo", query = "SELECT m FROM Manutencao m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "Manutencao.findByEquipamento", query = "SELECT m FROM Manutencao m WHERE m.equipamento = :equipamento"),
    @NamedQuery(name = "Manutencao.findByLocalizacao", query = "SELECT m FROM Manutencao m WHERE m.localizacao = :localizacao"),
    @NamedQuery(name = "Manutencao.findByDataagendada", query = "SELECT m FROM Manutencao m WHERE m.dataagendada = :dataagendada")})
public class Manutencao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Integer codigo;
    @Column(name = "EQUIPAMENTO")
    private String equipamento;
    @Basic(optional = false)
    @Column(name = "LOCALIZACAO")
    private String localizacao;
    @Basic(optional = false)
    @Column(name = "DATAAGENDADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataagendada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subscricao")
    private Collection<Manutencao> manutencaoCollection;
    @JoinColumn(name = "SUBSCRICAO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Manutencao subscricao;

    public Manutencao() {
    }

    public Manutencao(Integer codigo) {
        this.codigo = codigo;
    }

    public Manutencao(Integer codigo, String localizacao, Date dataagendada) {
        this.codigo = codigo;
        this.localizacao = localizacao;
        this.dataagendada = dataagendada;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Date getDataagendada() {
        return dataagendada;
    }

    public void setDataagendada(Date dataagendada) {
        this.dataagendada = dataagendada;
    }

    @XmlTransient
    public Collection<Manutencao> getManutencaoCollection() {
        return manutencaoCollection;
    }

    public void setManutencaoCollection(Collection<Manutencao> manutencaoCollection) {
        this.manutencaoCollection = manutencaoCollection;
    }

    public Manutencao getSubscricao() {
        return subscricao;
    }

    public void setSubscricao(Manutencao subscricao) {
        this.subscricao = subscricao;
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
        if (!(object instanceof Manutencao)) {
            return false;
        }
        Manutencao other = (Manutencao) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Manutencao[ codigo=" + codigo + " ]";
    }
    
}
