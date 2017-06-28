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
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Basic(optional = false)
    @Column(name = "CONTACTO")
    private String contacto;
    @Basic(optional = false)
    @Column(name = "MORADA")
    private String morada;
    @Basic(optional = false)
    @Column(name = "FUNCAO")
    private String funcao;
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    
    private static EntityManager em;

    public Funcionario() {
    }

    public Funcionario(Integer codigo) {
        this.codigo = codigo;
    }

    public Funcionario(Integer codigo, String nome, String contacto, String morada, String funcao, String username, String password) {
        this.codigo = codigo;
        this.nome = nome;
        this.contacto = contacto;
        this.morada = morada;
        this.funcao = funcao;
        this.username = username;
        this.password = password;
    }
    public Funcionario(String nome, String contacto, String morada, String funcao, String username, String password) {
        this.nome = nome;
        this.contacto = contacto;
        this.morada = morada;
        this.funcao = funcao;
        this.username = username;
        this.password = password;
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

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        
        if(!(query.getResultList().isEmpty())){
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
        else{
            System.out.println("Vazio");
        }
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
    
    public static void updateNome(Integer codigo, String nome){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Funcionario.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Funcionario func = (Funcionario)query.getSingleResult();
 
        em.getTransaction().begin();
        func.setNome(nome);
        em.getTransaction().commit();

    }
    
    public static void updateMorada(Integer codigo, String morada){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Funcionario.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Funcionario func = (Funcionario)query.getSingleResult();
 
        em.getTransaction().begin();
        func.setMorada(morada);
        em.getTransaction().commit();

    }
    
    public static void updateContacto(Integer codigo, String contacto){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Funcionario.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Funcionario func = (Funcionario)query.getSingleResult();
 
        em.getTransaction().begin();
        func.setContacto(contacto);
        em.getTransaction().commit();

    }
    
    public static void updateFuncao(Integer codigo, String funcao){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Funcionario.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Funcionario func = (Funcionario)query.getSingleResult();
 
        em.getTransaction().begin();
        func.setFuncao(funcao);
        em.getTransaction().commit();

    }
    
    public static void updateAutenticacao(Integer codigo, String username, String password){
        em = PersistenceManager.getEntityManager();
        Query query = em.createNamedQuery("Funcionario.findByCodigo");
        query.setParameter("codigo", codigo);
        
        Funcionario func = (Funcionario)query.getSingleResult();
 
        em.getTransaction().begin();
        func.setUsername(username);
        func.setPassword(password);
        em.getTransaction().commit();

    }
    
}
