/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesFX;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javafx.beans.property.*;


/**
 *
 * @author v_nor
 */

public class Subscricao implements Serializable {

    private static final long serialVersionUID = 1L;
   
   
    private IntegerProperty codigo;

    private StringProperty nome;

    private ObjectProperty<Date> fimsubscricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    private FloatProperty mensalidade;
    
    private StringProperty cliente;
    

    public Subscricao() {
    }

    public Subscricao(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public Subscricao(Integer codigo, String nome, Date fimsubscricao, float mensalidade) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.nome = new SimpleStringProperty(nome);
        this.fimsubscricao = new SimpleObjectProperty(fimsubscricao);
        this.mensalidade = new SimpleFloatProperty(mensalidade);
    }

    public IntegerProperty getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public StringProperty getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = new SimpleStringProperty(nome);
    }

    public ObjectProperty<Date> getFimsubscricao() {
        return fimsubscricao;
    }

    public void setFimsubscricao(Date fimsubscricao) {
        this.fimsubscricao = new SimpleObjectProperty(fimsubscricao);
    }

    public FloatProperty getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(float mensalidade) {
        this.mensalidade = new SimpleFloatProperty(mensalidade);
    }

    public StringProperty getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = new SimpleStringProperty(cliente);
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
