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

    private static final long serialVersionUID = 1L;
   
    private IntegerProperty codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    private FloatProperty total;
   
    private IntegerProperty quantidade;
  
    private Artigo artigo;
    
    private Venda venda;
    
    public LinhaArtigo() {
    }

    public LinhaArtigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public LinhaArtigo(Integer quantidade, Artigo artigo, Venda venda) {
        this.quantidade = new SimpleIntegerProperty(quantidade);
        this.artigo = artigo;
        this.venda = venda;
    }

    public int getCodigo() {
        return codigo.getValue();
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

    public Artigo getArtigo() {
        return artigo;
    }

    public void setArtigo(Artigo artigo) {
        this.artigo = artigo;
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
