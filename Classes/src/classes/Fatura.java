/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import BLL.PersistenceManager;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v_nor
 */
@Entity
@Table(name = "FATURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fatura.findAll", query = "SELECT f FROM Fatura f"),
    @NamedQuery(name = "Fatura.findByCodigo", query = "SELECT f FROM Fatura f WHERE f.codigo = :codigo"),
    @NamedQuery(name = "Fatura.findByNumcontribuinte", query = "SELECT f FROM Fatura f WHERE f.numcontribuinte = :numcontribuinte"),
    @NamedQuery(name = "Fatura.findByTotal", query = "SELECT f FROM Fatura f WHERE f.total = :total"),
    @NamedQuery(name = "Fatura.findByArtigos", query = "SELECT f FROM Fatura f WHERE f.artigos = :artigos")})
public class Fatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Integer codigo;
    @Column(name = "NUMCONTRIBUINTE")
    private String numcontribuinte;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOTAL")
    private BigDecimal total;
    @Column(name = "ARTIGOS")
    private String artigos;

    private static EntityManager em;
    
    public Fatura() {
    }

    public Fatura(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNumContribuinte() {
        return numcontribuinte;
    }

    public void setNumContribuinte(String numcontribuinte) {
        this.numcontribuinte = numcontribuinte;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getArtigos() {
        return artigos;
    }

    public void setArtigos(String artigos) {
        this.artigos = artigos;
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
        if (!(object instanceof Fatura)) {
            return false;
        }
        Fatura other = (Fatura) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Fatura[ codigo=" + codigo + " ]";
    }
    
     public void read(Integer Codigo){
        
        em = PersistenceManager.getEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();
        Query query = em.createNamedQuery("Fatura.findByCodigo");
        query.setParameter("codigo", codigo);
        
        
        Fatura fat = (Fatura)query.getSingleResult();
        
        em.refresh(fat);
        this.setCodigo(fat.getCodigo());
        this.setNumContribuinte(fat.getNumContribuinte());
        this.setTotal(fat.getTotal());
        this.setArtigos(fat.getArtigos());
        System.out.println("ID = " + this.getCodigo());
    }
    
    public static ArrayList<Fatura> readAll(){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Fatura.findAll");
        
        Collection<Fatura> fatCollection;
        ArrayList<Fatura> fatList;
        
        fatCollection = (Collection<Fatura>) query.getResultList();
        
        fatList = new ArrayList<Fatura>(fatCollection);
        
        
        return fatList;
    }
    
    public static void delete(Integer codigo){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Fatura.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Fatura fat = (Fatura)query.getSingleResult();
        
        em.getTransaction().begin();
        em.remove(fat);
        em.getTransaction().commit();
    }
    
}
