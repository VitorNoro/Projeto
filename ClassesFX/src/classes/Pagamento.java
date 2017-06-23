/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import javafx.beans.property.*;


/**
 *
 * @author v_nor
 */

public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private IntegerProperty codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
   
    private FloatProperty total;

    private StringProperty artigos;
 
    private Cliente numcontribuinte;

    private Reparacao reparacao;
 
    private Venda venda;
    
  

    public Pagamento() {
    }

    public Pagamento(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public Pagamento(Integer codigo, float total) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.total = new SimpleFloatProperty(total);
    }

    public Integer getCodigo() {
        return codigo.getValue();
    }

    public void setCodigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public float getTotal() {
        return total.getValue();
    }

    public void setTotal(float total) {
        this.total = new SimpleFloatProperty(total);
    }

    public String getArtigos() {
        return artigos.getValue();
    }

    public void setArtigos(String artigos) {
        this.artigos = new SimpleStringProperty(artigos);
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
