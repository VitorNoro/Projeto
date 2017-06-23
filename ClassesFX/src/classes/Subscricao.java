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
import javafx.beans.property.*;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v_nor
 */
@Entity
@Table(name = "SUBSCRICAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subscricao.findAll", query = "SELECT s FROM Subscricao s"),
    @NamedQuery(name = "Subscricao.findByCodigo", query = "SELECT s FROM Subscricao s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "Subscricao.findByNome", query = "SELECT s FROM Subscricao s WHERE s.nome = :nome"),
    @NamedQuery(name = "Subscricao.findByFimsubscricao", query = "SELECT s FROM Subscricao s WHERE s.fimsubscricao = :fimsubscricao"),
    @NamedQuery(name = "Subscricao.findByMensalidade", query = "SELECT s FROM Subscricao s WHERE s.mensalidade = :mensalidade")})
public class Subscricao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
     @SequenceGenerator(name="SEQ_GEN_SUBS", sequenceName="SUBSCRICAO_SEQ", allocationSize=1)
        @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN_SUBS")
    @Column(name = "CODIGO")
    private IntegerProperty codigo;
    @Basic(optional = false)
    @Column(name = "NOME")
    private StringProperty nome;
    @Basic(optional = false)
    @Column(name = "FIMSUBSCRICAO")
    @Temporal(TemporalType.TIMESTAMP)
    private ObjectProperty<Date> fimsubscricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MENSALIDADE")
    private FloatProperty mensalidade;
    @JoinColumn(name = "CLIENTE", referencedColumnName = "NUMCONTRIBUINTE")
    @ManyToOne(optional = false)
    private Cliente cliente;
    
    private static EntityManager em;

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
    
    public void createT() {
        em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        em.persist((Subscricao)this);
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
        Query query = em.createNamedQuery("Subscricao.findByCodigo");
        query.setParameter("codigo", codigo);
        
        
        Subscricao subs = (Subscricao)query.getSingleResult();
        
        em.refresh(subs);
        this.setCodigo(subs.getCodigo());
        this.setNome(subs.getNome());
        this.setFimsubscricao(subs.getFimsubscricao());
        this.setMensalidade(subs.getMensalidade());
        this.setCliente(subs.getCliente());
        System.out.println("ID = " + this.getCodigo());
    }
    
    public static ArrayList<Subscricao> readAll(){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Subscricao.findAll");
        
        Collection<Subscricao> subsCollection;
        ArrayList<Subscricao> subsList;
        
        subsCollection = (Collection<Subscricao>) query.getResultList();
        
        subsList = new ArrayList<Subscricao>(subsCollection);
        
        
        return subsList;
    }
    
    public static void delete(Integer codigo){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Subscricao.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Subscricao subs = (Subscricao)query.getSingleResult();
        
        em.getTransaction().begin();
        em.remove(subs);
        em.getTransaction().commit();
    }
    
    public void update(Integer codigo, String nome, Date fimsubscricao, float mensalidade){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Subscricao.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Subscricao subs = (Subscricao)query.getSingleResult();
 
        em.getTransaction().begin();
        subs.setNome(nome);
        em.getTransaction().begin();
        subs.setFimsubscricao(fimsubscricao);
        em.getTransaction().begin();
        subs.setMensalidade(mensalidade);
        
        em.getTransaction().commit();
        this.read(codigo);

    }
}
