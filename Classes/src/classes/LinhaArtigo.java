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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "LINHAARTIGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LinhaArtigo.findAll", query = "SELECT l FROM LinhaArtigo l"),
    @NamedQuery(name = "LinhaArtigo.findByCodigo", query = "SELECT l FROM LinhaArtigo l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "LinhaArtigo.findByTotal", query = "SELECT l FROM LinhaArtigo l WHERE l.total = :total"),
    @NamedQuery(name = "LinhaArtigo.findByQuantidade", query = "SELECT l FROM LinhaArtigo l WHERE l.quantidade = :quantidade")})
public class LinhaArtigo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="SEQ_GEN_LIN", sequenceName="LINHAARTIGO_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN_LIN")
    @Column(name = "CODIGO")
    private Integer codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "TOTAL")
    private float total;
    @Basic(optional = false)
    @Column(name = "QUANTIDADE")
    private Integer quantidade;
    @JoinColumn(name = "ARTIGO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Artigo artigo;
    @JoinColumn(name = "VENDA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Venda venda;

    private static EntityManager em;
    
    public LinhaArtigo() {
    }

    public LinhaArtigo(Integer codigo) {
        this.codigo = codigo;
    }

    public LinhaArtigo(Integer quantidade, Artigo artigo, Venda venda) {
        this.quantidade = quantidade;
        this.artigo = artigo;
        this.venda = venda;
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

    public void setTotal(float total) {
        this.total = total;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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

    public void createT() {
        em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        em.persist((LinhaArtigo)this);
        em.getTransaction().commit();
        this.read(this.getCodigo());
    }
    
    /**
     * Lê um cliente da BD
     * @param codigo ID do cliente a ler da BD
     */    
    public void read(Integer codigo){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("LinhaArtigo.findByCodigo");
        query.setParameter("codigo", codigo);
        
        LinhaArtigo linha = (LinhaArtigo)query.getSingleResult();
        
        em.refresh(linha);
        this.setCodigo(linha.getCodigo());
        System.out.println(linha.getTotal());
        this.setTotal(linha.getTotal());
        this.setQuantidade(linha.getQuantidade());
        this.setArtigo(linha.getArtigo());
        this.setVenda(linha.getVenda());
        System.out.println("ID = " + this.getCodigo());
    }
    
    public static ArrayList<LinhaArtigo> readAll(){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("LinhaArtigo.findAll");
        
        ArrayList<LinhaArtigo> linhaList;
        Collection<LinhaArtigo> linhaCollection;
        
        linhaCollection = (Collection<LinhaArtigo>) query.getResultList();
        
        linhaList = new ArrayList<LinhaArtigo>(linhaCollection);
        
        
        return linhaList;
    }
    
    public static void delete(Integer codigo){
        em = PersistenceManager.getEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();
        Query query = em.createNamedQuery("LinhaArtigo.findByCodigo");
        query.setParameter("codigo", codigo);
        
        LinhaArtigo linha = (LinhaArtigo)query.getSingleResult();
        
        em.getTransaction().begin();
        em.remove(linha);
        em.getTransaction().commit();
    }
    
    public void update(Integer codigo, Integer quantidade){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("LinhaArtigo.findByCodigo");
        query.setParameter("codigo", codigo);
        
        LinhaArtigo art = (LinhaArtigo)query.getSingleResult();
 
        em.getTransaction().begin();
        art.setQuantidade(quantidade);
        em.getTransaction().commit();
        this.read(codigo);
    }
    
}
