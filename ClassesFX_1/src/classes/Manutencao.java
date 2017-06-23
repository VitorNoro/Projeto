/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javafx.beans.property.*;


/**
 *
 * @author v_nor
 */

public class Manutencao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private IntegerProperty codigo;
    
    private StringProperty equipamento;
 
    private StringProperty localizacao;

    private ObjectProperty<Date> dataAgendada;
    
    private Collection<Subscricao> subscricaoCollection;


    private Subscricao subscricao;

    
    public Manutencao() {
    }

    public Manutencao(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public Manutencao(Integer codigo, String localizacao, Date dataAgendada) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.localizacao = new SimpleStringProperty(localizacao);
        this.dataAgendada = new SimpleObjectProperty(dataAgendada);
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

    public String getLocalizacao() {
        return localizacao.getValue();
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = new SimpleStringProperty(localizacao);
    }

    public Date getDataAgendada() {
        return dataAgendada.getValue();
    }

    public void setDataAgendada(Date dataAgendada) {
        this.dataAgendada = new SimpleObjectProperty(dataAgendada);
    }


    public Collection<Subscricao> getManutencaoCollection() {
        return subscricaoCollection;
    }

    public void setSubscricaoCollection(Collection<Subscricao> subscricaoCollection) {
        this.subscricaoCollection = subscricaoCollection;
    }

    public Subscricao getSubscricao() {
        return subscricao;
    }

    public void setSubscricao(Subscricao subscricao) {
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
