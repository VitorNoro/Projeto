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
public class VendaTest {
    
    public VendaTest() {
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

        result = Venda.readAll().size();
        
        assertEquals(expResult,result);
        
        Venda instance = new Venda();
        instance.createT();
        
        expResult++;
        
        result = Venda.readAll().size();
        
        assertEquals(expResult,result);
        
        int codigo = instance.getCodigo();
        
        Venda teste = new Venda(codigo,instance.getTotal());
        
        assertEquals(teste,instance);
        
        Venda readTeste = new Venda();
        
        readTeste.read(codigo);
        
        assertEquals(teste,readTeste);
        
        assertEquals(Venda.readAll().get(0),instance);
        
        Venda.delete(codigo);
        
        assertTrue(Venda.readAll().isEmpty());
        
    }
    
}
