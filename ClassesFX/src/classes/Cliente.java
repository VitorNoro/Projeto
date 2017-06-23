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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v_nor
 */
@Entity
@Table(name = "CLIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByNumcontribuinte", query = "SELECT c FROM Cliente c WHERE c.numcontribuinte = :numcontribuinte"),
    @NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cliente.findByContacto", query = "SELECT c FROM Cliente c WHERE c.contacto = :contacto")})
public class Cliente implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numcontribuinte")
    private Collection<Pagamento> pagamentoCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<Reparacao> reparacaoCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<Diagnostico> diagnosticoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<Subscricao> subscricaoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUMCONTRIBUINTE")
    private StringProperty numcontribuinte;
    @Basic(optional = false)
    @Column(name = "NOME")
    private StringProperty nome;
    @Basic(optional = false)
    @Column(name = "CONTACTO")
    private StringProperty contacto;
    

    public Cliente() {
    }

    public Cliente(String numcontribuinte) {
        this.numcontribuinte = new SimpleStringProperty(numcontribuinte);
    }

    public Cliente(String numcontribuinte, String nome, String contacto) {
        this.numcontribuinte = new SimpleStringProperty(numcontribuinte);
        this.nome = new SimpleStringProperty(nome);
        this.contacto = new SimpleStringProperty(contacto);
    }

    public String getNumContribuinte() {
        return numcontribuinte.getValue();
    }

    public void setNumContribuinte(String numcontribuinte) {
        this.numcontribuinte = new SimpleStringProperty(numcontribuinte);
    }

    public String getNome() {
        return nome.getValue();
    }

    public void setNome(String nome) {
        this.nome = new SimpleStringProperty(nome);
    }

    public String getContacto() {
        return contacto.getValue();
    }

    public void setContacto(String contacto) {
        this.contacto = new SimpleStringProperty(contacto);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numcontribuinte != null ? numcontribuinte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.numcontribuinte == null && other.numcontribuinte != null) || (this.numcontribuinte != null && !this.numcontribuinte.equals(other.numcontribuinte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Cliente[ numcontribuinte=" + numcontribuinte + " ]";
    }

    @XmlTransient
    public Collection<Diagnostico> getDiagnosticoCollection() {
        return diagnosticoCollection;
    }

    public void setDiagnosticoCollection(Collection<Diagnostico> diagnosticoCollection) {
        this.diagnosticoCollection = diagnosticoCollection;
    }

    @XmlTransient
    public Collection<Subscricao> getSubscricaoCollection() {
        return subscricaoCollection;
    }

    public void setSubscricaoCollection(Collection<Subscricao> subscricaoCollection) {
        this.subscricaoCollection = subscricaoCollection;
    }

    @XmlTransient
    public Collection<Reparacao> getReparacaoCollection() {
        return reparacaoCollection;
    }

    public void setReparacaoCollection(Collection<Reparacao> reparacaoCollection) {
        this.reparacaoCollection = reparacaoCollection;
    }

    @XmlTransient
    public Collection<Pagamento> getPagamentoCollection() {
        return pagamentoCollection;
    }

    public void setPagamentoCollection(Collection<Pagamento> pagamentoCollection) {
        this.pagamentoCollection = pagamentoCollection;
    }
    
    
    
}
