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
import javafx.beans.property.*;
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
@Table(name = "FUNCIONARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f"),
    @NamedQuery(name = "Funcionario.findByCodigo", query = "SELECT f FROM Funcionario f WHERE f.codigo = :codigo"),
    @NamedQuery(name = "Funcionario.findByNome", query = "SELECT f FROM Funcionario f WHERE f.nome = :nome"),
    @NamedQuery(name = "Funcionario.findByContacto", query = "SELECT f FROM Funcionario f WHERE f.contacto = :contacto"),
    @NamedQuery(name = "Funcionario.findByMorada", query = "SELECT f FROM Funcionario f WHERE f.morada = :morada"),
    @NamedQuery(name = "Funcionario.findByFuncao", query = "SELECT f FROM Funcionario f WHERE f.funcao = :funcao"),
    @NamedQuery(name = "Funcionario.findByUsername", query = "SELECT f FROM Funcionario f WHERE f.username = :username"),
    @NamedQuery(name = "Funcionario.findByPassword", query = "SELECT f FROM Funcionario f WHERE f.password = :password")})
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="SEQ_GEN_FUNC", sequenceName="FUNCIONARIO_SEQ", allocationSize=1)
        @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN_FUNC")
    @Column(name = "CODIGO")
    private IntegerProperty codigo;
    @Basic(optional = false)
    @Column(name = "NOME")
    private StringProperty nome;
    @Basic(optional = false)
    @Column(name = "CONTACTO")
    private StringProperty contacto;
    @Basic(optional = false)
    @Column(name = "MORADA")
    private StringProperty morada;
    @Basic(optional = false)
    @Column(name = "FUNCAO")
    private StringProperty funcao;
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private StringProperty username;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private StringProperty password;
    
    private static EntityManager em;

    public Funcionario() {
    }

    public Funcionario(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public Funcionario(Integer codigo, String nome, String contacto, String morada, String funcao, String username, String password) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.nome = new SimpleStringProperty(nome);
        this.contacto = new SimpleStringProperty(contacto);
        this.morada = new SimpleStringProperty(morada);
        this.funcao = new SimpleStringProperty(funcao);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
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

    public String getContacto() {
        return contacto.getValue();
    }

    public void setContacto(String contacto) {
        this.contacto = new SimpleStringProperty(contacto);
    }

    public String getMorada() {
        return morada.getValue();
    }

    public void setMorada(String morada) {
        this.morada = new SimpleStringProperty(morada);
    }

    public String getFuncao() {
        return funcao.getValue();
    }

    public void setFuncao(String funcao) {
        this.funcao = new SimpleStringProperty(funcao);
    }
    
    public String getUsername() {
        return username.getValue();
    }

    public void setUsername(String username) {
        this.username = new SimpleStringProperty(username);
    }
    
    public String getPassword() {
        return password.getValue();
    }

    public void setPassword(String password) {
        this.password = new SimpleStringProperty(password);
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
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Funcionario[ codigo=" + codigo + " ]";
    }
    
    public void createT() {
        em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        em.persist((Funcionario)this);
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
        Query query = em.createNamedQuery("Funcionario.findByCodigo");
        query.setParameter("codigo", codigo);
        
        
        Funcionario func = (Funcionario)query.getSingleResult();
        
        em.refresh(func);
        this.setCodigo(func.getCodigo());
        this.setNome(func.getNome());
        this.setMorada(func.getMorada());
        this.setContacto(func.getContacto());
        this.setFuncao(func.getFuncao());
        this.setUsername(func.getUsername());
        this.setPassword(func.getPassword());
        System.out.println("ID = " + this.getCodigo());
    }
    
    public static ArrayList<Funcionario> readAll(){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Funcionario.findAll");
        
        Collection<Funcionario> funcCollection;
        ArrayList<Funcionario> funcList;
        
        funcCollection = (Collection<Funcionario>) query.getResultList();
        
        funcList = new ArrayList<Funcionario>(funcCollection);
        
        
        return funcList;
    }
    
    public static void delete(Integer codigo){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Funcionario.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Funcionario func = (Funcionario)query.getSingleResult();
        
        em.getTransaction().begin();
        em.remove(func);
        em.getTransaction().commit();
    }
    
    public void updateDados(Integer codigo, String morada, String contacto, String funcao){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Funcionario.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Funcionario func = (Funcionario)query.getSingleResult();
 
        em.getTransaction().begin();
        func.setMorada(morada);
        func.setContacto(contacto);
        func.setFuncao(funcao);
        em.getTransaction().commit();
        this.read(codigo);

    }
    
    public void updateAutenticacao(Integer codigo, String username, String password){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Funcionario.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Funcionario func = (Funcionario)query.getSingleResult();
 
        em.getTransaction().begin();
        func.setUsername(username);
        func.setPassword(password);
        em.getTransaction().commit();
        this.read(codigo);

    }
    
}
