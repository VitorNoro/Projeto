/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import BLL.PersistenceManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v_nor
 */
@Entity
@Table(name = "VENDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v"),
    @NamedQuery(name = "Venda.findByCodigo", query = "SELECT v FROM Venda v WHERE v.codigo = :codigo"),
    @NamedQuery(name = "Venda.findByTotal", query = "SELECT v FROM Venda v WHERE v.total = :total")})
public class Venda implements Serializable {

    @OneToMany(mappedBy = "venda")
    private Collection<Pagamento> pagamentoCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
    private Collection<LinhaArtigo> linhaartigoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="SEQ_GEN_VEN", sequenceName="VENDA_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN_VEN")
    @Column(name = "CODIGO")
    private Integer codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "TOTAL")
    private float total;
    
    private static EntityManager em;

    public Venda() {
    }

    public Venda(Integer codigo) {
        this.codigo = codigo;
    }

    public Venda(Integer codigo, Float total) {
        this.codigo = codigo;
        this.total = total;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
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
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Venda[ codigo=" + codigo + " ]";
    }

    @XmlTransient
    public Collection<LinhaArtigo> getLinhaartigoCollection() {
        return linhaartigoCollection;
    }

    public void setLinhaartigoCollection(Collection<LinhaArtigo> linhaartigoCollection) {
        this.linhaartigoCollection = linhaartigoCollection;
    }

    @XmlTransient
    public Collection<Pagamento> getPagamentoCollection() {
        return pagamentoCollection;
    }

    public void setPagamentoCollection(Collection<Pagamento> pagamentoCollection) {
        this.pagamentoCollection = pagamentoCollection;
    }

    public void createT() {
        em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        em.persist((Venda)this);
        em.getTransaction().commit();
        this.read(this.getCodigo());
    }
    
    /**
     * Lê um cliente da BD
     * @param codigo ID do cliente a ler da BD
     */    
    public void read(Integer codigo){
        em = PersistenceManager.getEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();
        Query query = em.createNamedQuery("Venda.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Venda venda = (Venda)query.getSingleResult();
        em.refresh(venda);
        this.setCodigo(venda.getCodigo());
        this.setTotal(venda.getTotal());
        System.out.println("ID = " + this.getCodigo());
    }
    
    public static ArrayList<Venda> readAll(){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Venda.findAll");
        
        ArrayList<Venda> vendaList;
        Collection<Venda> vendaCollection;
        
        vendaCollection = (Collection<Venda>) query.getResultList();
        
        vendaList = new ArrayList<Venda>(vendaCollection);
        
        return vendaList;
    }
    
    public static void delete(Integer codigo){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Venda.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Venda venda = (Venda)query.getSingleResult();
        
        em.getTransaction().begin();
        em.remove(venda);
        em.getTransaction().commit();
    }
    
}
