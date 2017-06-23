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



/**
 *
 * @author v_nor
 */


public class Artigo implements Serializable {

   
    private Collection<LinhaArtigo> linhaartigoCollection;

    private static final long serialVersionUID = 1L;
  
    private IntegerProperty codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
   
    private FloatProperty preco;
   
    private IntegerProperty quantidade;
  
    private StringProperty descricao;
  
    private StringProperty nome;
    
    public Artigo() {
    }

    public Artigo(float preco, Integer quantidade, String descricao, String nome) {
        this.preco = new SimpleFloatProperty(preco);
        this.quantidade = new SimpleIntegerProperty(quantidade);
        this.descricao = new SimpleStringProperty(descricao);
        this.nome = new SimpleStringProperty(nome);
    }
    
    public Artigo(Integer codigo, float preco, Integer quantidade, String descricao, String nome) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.preco = new SimpleFloatProperty(preco);
        this.quantidade = new SimpleIntegerProperty(quantidade);
        this.descricao = new SimpleStringProperty(descricao);
        this.nome = new SimpleStringProperty(nome);
    }

    public int getCodigo() {
        return codigo.getValue();
    }

    public void setCodigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);;
    }

    public Float getPreco() {
        return preco.getValue();
    }

    public void setPreco(Float preco) {
        this.preco = new SimpleFloatProperty(preco);;
    }

    public Integer getQuantidade() {
        return quantidade.getValue();
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = new SimpleIntegerProperty(quantidade);
    }

    public String getDescricao() {
        return descricao.getValue();
    }

    public void setDescricao(String descricao) {
        this.descricao = new SimpleStringProperty(descricao);;
    }

    public String getNome() {
        return nome.getValue();
    }

    public void setNome(String nome) {
        this.nome = new SimpleStringProperty(nome);;
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
        if (!(object instanceof Artigo)) {
            return false;
        }
        Artigo other = (Artigo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Artigo[ codigo=" + codigo + " ]";
    }

  
    public Collection<LinhaArtigo> getLinhaartigoCollection() {
        return linhaartigoCollection;
    }

    public void setLinhaartigoCollection(Collection<LinhaArtigo> linhaartigoCollection) {
        this.linhaartigoCollection = linhaartigoCollection;
    }

    
}
