/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import java.util.Collection;
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
public class ArtigoTest {
    
    public ArtigoTest() {
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

        result = Artigo.readAll().size();
        
        assertEquals(expResult,result);
        
        float preco = 10;
        Integer quantidade = 10;
        String descricao = "Teste";
        String nome = "Teste";
        
        Artigo instance = new Artigo(preco,quantidade,descricao,nome);
        instance.createT();
        
        expResult++;
        
        result = Artigo.readAll().size();
        
        assertEquals(expResult,result);
        
        int codigo = instance.getCodigo();
        
        Artigo teste = new Artigo(codigo,preco,quantidade,descricao,nome);
        
        assertEquals(teste,instance);
        
        Artigo readTeste = new Artigo();
        
        readTeste.read(codigo);
        
        assertEquals(teste,readTeste);
        
        //instance.update(codigo, 15, 15, "update", "update");
        
        teste = new Artigo(codigo, 15, 15, "update", "update");
        
        assertEquals(teste,instance);
        
        assertEquals(Artigo.readAll().get(0),instance);
        
        Artigo.delete(codigo);
        
        assertTrue(Artigo.readAll().isEmpty());
        
    }

   
}
