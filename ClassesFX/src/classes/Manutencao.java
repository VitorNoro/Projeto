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
import java.util.Date;
import javafx.beans.property.*;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v_nor
 */
@Entity
@Table(name = "MANUTENCAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Manutencao.findAll", query = "SELECT m FROM Manutencao m"),
    @NamedQuery(name = "Manutencao.findByCodigo", query = "SELECT m FROM Manutencao m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "Manutencao.findByEquipamento", query = "SELECT m FROM Manutencao m WHERE m.equipamento = :equipamento"),
    @NamedQuery(name = "Manutencao.findByLocalizacao", query = "SELECT m FROM Manutencao m WHERE m.localizacao = :localizacao"),
    @NamedQuery(name = "Manutencao.findByDataagendada", query = "SELECT m FROM Manutencao m WHERE m.dataagendada = :dataagendada")})
public class Manutencao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="SEQ_GEN_MAN", sequenceName="MANUTENCAO_SEQ", allocationSize=1)
        @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN_MAN")
    @Column(name = "CODIGO")
    private IntegerProperty codigo;
    @Column(name = "EQUIPAMENTO")
    private StringProperty equipamento;
    @Basic(optional = false)
    @Column(name = "LOCALIZACAO")
    private StringProperty localizacao;
    @Basic(optional = false)
    @Column(name = "DATAAGENDADA")
    @Temporal(TemporalType.TIMESTAMP)
    private ObjectProperty<Date> dataAgendada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subscricao")
    private Collection<Subscricao> subscricaoCollection;
    @JoinColumn(name = "SUBSCRICAO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Subscricao subscricao;

    private static EntityManager em;
    
    public Manutencao() {
    }

    public Manutencao(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public Manutencao(Integer codigo, String localizacao, Date dataAgendada) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.localizacao = new SimpleStringProperty(localizacao);
        this.dataAgendada = new SimpleObjectProperty(dataAgendada);
    }

    public Integer getCodigo() {
        return codigo.getValue();
    }

    public void setCodigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public String getEquipamento() {
        return equipamento.getValue();
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = new SimpleStringProperty(equipamento);
    }

    public String getLocalizacao() {
        return localizacao.getValue();
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = new SimpleStringProperty(localizacao);
    }

    public Date getDataAgendada() {
        return dataAgendada.getValue();
    }

    public void setDataAgendada(Date dataAgendada) {
        this.dataAgendada = new SimpleObjectProperty(dataAgendada);
    }

    @XmlTransient
    public Collection<Subscricao> getManutencaoCollection() {
        return subscricaoCollection;
    }

    public void setSubscricaoCollection(Collection<Subscricao> subscricaoCollection) {
        this.subscricaoCollection = subscricaoCollection;
    }

    public Subscricao getSubscricao() {
        return subscricao;
    }

    public void setSubscricao(Subscricao subscricao) {
        this.subscricao = subscricao;
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
        if (!(object instanceof Manutencao)) {
            return false;
        }
        Manutencao other = (Manutencao) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Manutencao[ codigo=" + codigo + " ]";
    }
    
    public void createT() {
        em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        em.persist((Manutencao)this);
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
        Query query = em.createNamedQuery("Manutencao.findByCodigo");
        query.setParameter("codigo", codigo);
        
        
        Manutencao man = (Manutencao)query.getSingleResult();
        
        em.refresh(man);
        this.setCodigo(man.getCodigo());
        this.setEquipamento(man.getEquipamento());
        this.setLocalizacao(man.getLocalizacao());
        this.setDataAgendada(man.getDataAgendada());
        this.setSubscricao(man.getSubscricao());
        System.out.println("ID = " + this.getCodigo());
    }
    
    public static ArrayList<Manutencao> readAll(){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Manutencao.findAll");
        
        Collection<Manutencao> manCollection;
        ArrayList<Manutencao> manList;
        
        manCollection = (Collection<Manutencao>) query.getResultList();
        
        manList = new ArrayList<Manutencao>(manCollection);
        
        
        return manList;
    }
    
    public static void delete(Integer codigo){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Manutencao.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Manutencao man = (Manutencao)query.getSingleResult();
        
        em.getTransaction().begin();
        em.remove(man);
        em.getTransaction().commit();
    }
    
    public void update(Integer codigo, String equipamento, String localizacao, Date dataAgendada){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Manutencao.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Manutencao man = (Manutencao)query.getSingleResult();
 
        em.getTransaction().begin();
        man.setEquipamento(equipamento);
        man.setLocalizacao(localizacao);
        man.setDataAgendada(dataAgendada);
        em.getTransaction().commit();
        this.read(codigo);

    }
    
}
