/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author v_nor
 */
public class LinhaArtigoTest {
    
    public LinhaArtigoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void test() {
        int expResult = 0;
        int result;

        result = LinhaArtigo.readAll().size();
        
        assertEquals(expResult,result);
        
        float preco = 10;
        Integer quantidade = 10;
        String descricao = "Teste";
        String nome = "Teste";
        
        Artigo artigo = new Artigo(preco,quantidade,descricao,nome);
        artigo.createT();
        
        Venda venda = new Venda();
        venda.createT();
        
        LinhaArtigo instance = new LinhaArtigo(2,artigo,venda);
        instance.createT();
        
        
        
        System.out.println(instance.getTotal());
        
        venda.read(venda.getCodigo());
        
        System.out.println(venda.getTotal());
        
        expResult++;
        
        result = LinhaArtigo.readAll().size();
        
        assertEquals(expResult,result);
        
        int codigo = instance.getCodigo();
        float total = 2 * preco;
        
        LinhaArtigo teste = new LinhaArtigo(quantidade,artigo,venda);
        
        teste.setCodigo(codigo);
        teste.setTotal(total);
        
        assertEquals(teste,instance);
        
        LinhaArtigo readTeste = new LinhaArtigo();
        
        readTeste.read(codigo);
        
        assertEquals(teste,readTeste);
        
        instance.update(codigo, 3);
        
        venda.read(venda.getCodigo());
        
        System.out.println(venda.getTotal());
        
        total = 3 * preco;
        
        teste = new LinhaArtigo(3, artigo, venda);
        
        teste.setCodigo(codigo);
        teste.setTotal(total);
        
        assertEquals(teste,instance);
        
        assertEquals(LinhaArtigo.readAll().get(0),instance);
        
        LinhaArtigo.delete(codigo);
        Artigo.delete(artigo.getCodigo());
        Venda.delete(venda.getCodigo());
        
        assertTrue(LinhaArtigo.readAll().isEmpty());
        
    }
    
}
