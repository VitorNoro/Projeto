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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v_nor
 */
@Entity
@Table(name = "FORNECEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f")
    , @NamedQuery(name = "Fornecedor.findByCodigo", query = "SELECT f FROM Fornecedor f WHERE f.codigo = :codigo")
    , @NamedQuery(name = "Fornecedor.findByNome", query = "SELECT f FROM Fornecedor f WHERE f.nome = :nome")
    , @NamedQuery(name = "Fornecedor.findByContacto", query = "SELECT f FROM Fornecedor f WHERE f.contacto = :contacto")})
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="SEQ_GEN_FOR", sequenceName="FORNECEDOR_SEQ", allocationSize=1)
        @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN_FOR")
    @Column(name = "CODIGO")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Basic(optional = false)
    @Column(name = "CONTACTO")
    private String contacto;
    
    private static EntityManager em;

    public Fornecedor() {
    }

    public Fornecedor(Integer codigo) {
        this.codigo = codigo;
    }

    public Fornecedor(Integer codigo, String nome, String contacto) {
        this.codigo = codigo;
        this.nome = nome;
        this.contacto = contacto;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
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
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Fornecedor[ codigo=" + codigo + " ]";
    }
    
    public void createT() {
        em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        em.persist((Fornecedor)this);
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
        Query query = em.createNamedQuery("Artigo.findByCodigo");
        query.setParameter("codigo", codigo);
        
        
        Fornecedor art = (Fornecedor)query.getSingleResult();
        
        em.refresh(art);
        this.setCodigo(art.getCodigo());
        this.setContacto(art.getContacto());
        this.setNome(art.getNome());

    }
    
    public static ArrayList<Fornecedor> readAll(){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Artigo.findAll");
        
        Collection<Fornecedor> artCollection;
        ArrayList<Fornecedor> artList;
        
        artCollection = (Collection<Fornecedor>) query.getResultList();
        
        artList = new ArrayList<Fornecedor>(artCollection);
        
        
        return artList;
    }
    
    public static void delete(Integer codigo){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Artigo.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Fornecedor art = (Fornecedor)query.getSingleResult();
        
        em.getTransaction().begin();
        em.remove(art);
        em.getTransaction().commit();
    }
    
    public static void updatePreco(Integer codigo, String contacto){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Artigo.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Fornecedor art = (Fornecedor)query.getSingleResult();
 
        em.getTransaction().begin();
        art.setContacto(contacto);
        em.getTransaction().commit();
    }
    
    public static void updateNome(Integer codigo, String nome){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Artigo.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Fornecedor art = (Fornecedor)query.getSingleResult();
 
        em.getTransaction().begin();
        art.setNome(nome);
        em.getTransaction().commit();
    }
    
}
