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
import java.util.Date;
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
@Table(name = "REPARACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reparacao.findAll", query = "SELECT r FROM Reparacao r"),
    @NamedQuery(name = "Reparacao.findByCodigo", query = "SELECT r FROM Reparacao r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "Reparacao.findByCusto", query = "SELECT r FROM Reparacao r WHERE r.custo = :custo")})
public class Reparacao implements Serializable {

    @OneToMany(mappedBy = "reparacao")
    private Collection<Pagamento> pagamentoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="SEQ_GEN_REP", sequenceName="REPARACAO_SEQ", allocationSize=1)
        @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN_REP")
    @Column(name = "CODIGO")
    private Integer codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CUSTO")
    private float custo;
    @JoinColumn(name = "CLIENTE", referencedColumnName = "NUMCONTRIBUINTE")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "DIAGNOSTICO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Diagnostico diagnostico;
    
    private static EntityManager em;

    public Reparacao() {
    }

    public Reparacao(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
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
        if (!(object instanceof Reparacao)) {
            return false;
        }
        Reparacao other = (Reparacao) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Reparacao[ codigo=" + codigo + " ]";
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
        em.persist((Reparacao)this);
        em.getTransaction().commit();
        this.read(this.getCodigo());
    }
    
    /**
     * Lê um cliente da BD
     * @param codigo ID do cliente a ler da BD
     */    
    public void read(Integer Codigo){
        
        em = PersistenceManager.getEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();
        Query query = em.createNamedQuery("Reparacao.findByCodigo");
        query.setParameter("codigo", codigo);
        
        
        Reparacao rep = (Reparacao)query.getSingleResult();
        
        em.refresh(rep);
        this.setCodigo(rep.getCodigo());
        this.setCusto(rep.getCusto());
        this.setDiagnostico(rep.getDiagnostico());
        this.setCliente(rep.getCliente());
        System.out.println("ID = " + this.getCodigo());
    }
    
    public static ArrayList<Reparacao> readAll(){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Reparacao.findAll");
        
        Collection<Reparacao> repCollection;
        ArrayList<Reparacao> repList;
        
        repCollection = (Collection<Reparacao>) query.getResultList();
        
        repList = new ArrayList<Reparacao>(repCollection);
        
        
        return repList;
    }
    
    public static void delete(Integer codigo){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Reparacao.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Reparacao rep = (Reparacao)query.getSingleResult();
        
        em.getTransaction().begin();
        em.remove(rep);
        em.getTransaction().commit();
    }
    
    public static void update(Integer codigo, float custo){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Reparacao.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Reparacao rep = (Reparacao)query.getSingleResult();
 
        em.getTransaction().begin();
        rep.setCusto(custo);         
        em.getTransaction().commit();


    }
    
}
