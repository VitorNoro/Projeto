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
    
    private Cliente cliente;
    

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

    public Integer getCodigo() {
        return codigo.getValue();
    }

    public void setCodigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public String getNome() {
        return nome.getValue();
    }

    public void setNome(String nome) {
        this.nome = new SimpleStringProperty(nome);
    }

    public Date getFimsubscricao() {
        return fimsubscricao.getValue();
    }

    public void setFimsubscricao(Date fimsubscricao) {
        this.fimsubscricao = new SimpleObjectProperty(fimsubscricao);
    }

    public float getMensalidade() {
        return mensalidade.getValue();
    }

    public void setMensalidade(float mensalidade) {
        this.mensalidade = new SimpleFloatProperty(mensalidade);
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
