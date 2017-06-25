/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesFX;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javafx.beans.property.*;


/**
 *
 * @author v_nor
 */

public class Cliente implements Serializable {

 
    private Collection<Pagamento> pagamentoCollection;

    
    private Collection<Reparacao> reparacaoCollection;

  
    private Collection<Diagnostico> diagnosticoCollection;

    private Collection<Subscricao> subscricaoCollection;

    private static final long serialVersionUID = 1L;

    private StringProperty numcontribuinte;

    private StringProperty nome;

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


    public Collection<Diagnostico> getDiagnosticoCollection() {
        return diagnosticoCollection;
    }

    public void setDiagnosticoCollection(Collection<Diagnostico> diagnosticoCollection) {
        this.diagnosticoCollection = diagnosticoCollection;
    }


    public Collection<Subscricao> getSubscricaoCollection() {
        return subscricaoCollection;
    }

    public void setSubscricaoCollection(Collection<Subscricao> subscricaoCollection) {
        this.subscricaoCollection = subscricaoCollection;
    }

 
    public Collection<Reparacao> getReparacaoCollection() {
        return reparacaoCollection;
    }

    public void setReparacaoCollection(Collection<Reparacao> reparacaoCollection) {
        this.reparacaoCollection = reparacaoCollection;
    }


    public Collection<Pagamento> getPagamentoCollection() {
        return pagamentoCollection;
    }

    public void setPagamentoCollection(Collection<Pagamento> pagamentoCollection) {
        this.pagamentoCollection = pagamentoCollection;
    }
    
    
    
}
