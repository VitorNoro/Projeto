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

public class LinhaArtigo implements Serializable {
   
    private IntegerProperty codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    private FloatProperty total;
   
    private IntegerProperty quantidade;
  
    private IntegerProperty artigo;
    
    private IntegerProperty venda;
    private StringProperty nomeArtigo;
    
    public LinhaArtigo() {
    }

    public LinhaArtigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public LinhaArtigo(Integer quantidade, Integer artigo, Integer venda) {
        this.quantidade = new SimpleIntegerProperty(quantidade);
        this.artigo = new SimpleIntegerProperty(artigo);
        this.venda = new SimpleIntegerProperty(venda);
    }

    public IntegerProperty getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public FloatProperty getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = new SimpleFloatProperty(total);
    }

    public IntegerProperty getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = new SimpleIntegerProperty(quantidade);
    }

    public IntegerProperty getArtigo() {
        return artigo;
    }

    public void setArtigo(Integer artigo) {
        this.artigo = new SimpleIntegerProperty(artigo);
    }

    public IntegerProperty getVenda() {
        return venda;
    }

    public void setVenda(Integer venda) {
        this.venda = new SimpleIntegerProperty(venda);
    }

    public StringProperty getNomeArtigo() {
        return nomeArtigo;
    }

    public void setNomeArtigo(String nomeArtigo) {
        this.nomeArtigo = new SimpleStringProperty(nomeArtigo);
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
        if (!(object instanceof LinhaArtigo)) {
            return false;
        }
        LinhaArtigo other = (LinhaArtigo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.LinhaArtigo[ codigo=" + codigo + " ]";
    }

   
}
