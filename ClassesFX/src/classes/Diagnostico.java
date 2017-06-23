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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "DIAGNOSTICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diagnostico.findAll", query = "SELECT d FROM Diagnostico d"),
    @NamedQuery(name = "Diagnostico.findByCodigo", query = "SELECT d FROM Diagnostico d WHERE d.codigo = :codigo"),
    @NamedQuery(name = "Diagnostico.findByEquipamento", query = "SELECT d FROM Diagnostico d WHERE d.equipamento = :equipamento"),
    @NamedQuery(name = "Diagnostico.findByProblema", query = "SELECT d FROM Diagnostico d WHERE d.problema = :problema")})
public class Diagnostico implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diagnostico")
    private Collection<Reparacao> reparacaoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="SEQ_GEN_DIAG", sequenceName="DIAGNOSTICO_SEQ", allocationSize=1)
        @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN_DIAG")
    @Column(name = "CODIGO")
    private IntegerProperty codigo;
    @Basic(optional = false)
    @Column(name = "EQUIPAMENTO")
    private StringProperty equipamento;
    @Column(name = "PROBLEMA")
    private StringProperty problema;
    @JoinColumn(name = "CLIENTE", referencedColumnName = "NUMCONTRIBUINTE")
    @ManyToOne(optional = false)
    private Cliente cliente;
    
    public Diagnostico() {
    }

    public Diagnostico(Integer codigo) {
        this.codigo = new SimpleIntegerProperty();
    }

    public Diagnostico(Integer codigo, String equipamento, String problema, Cliente cliente) {
        this.codigo = new SimpleIntegerProperty();
        this.equipamento = new SimpleStringProperty(equipamento);
        this.problema = new SimpleStringProperty(problema);
        this.cliente = cliente;
    }

    public Integer getCodigo() {
        return codigo.getValue();
    }

    public void setCodigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public String getEquipamento() {
        return equipamento.getValue();
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = new SimpleStringProperty(equipamento);
    }

    public String getProblema() {
        return problema.getValue();
    }

    public void setProblema(String problema) {
        this.problema = new SimpleStringProperty(problema);
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

    @XmlTransient
    public Collection<Reparacao> getReparacaoCollection() {
        return reparacaoCollection;
    }

    public void setReparacaoCollection(Collection<Reparacao> reparacaoCollection) {
        this.reparacaoCollection = reparacaoCollection;
    }
    
}
