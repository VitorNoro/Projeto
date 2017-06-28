/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesFX;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javafx.beans.property.*;


/**
 *
 * @author v_nor
 */

public class Reparacao implements Serializable {

    
    private Collection<Pagamento> pagamentoCollection;

    private static final long serialVersionUID = 1L;
   
    private IntegerProperty codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    private FloatProperty custo;

    private StringProperty cliente;
    
    private IntegerProperty diagnostico;
    

    public Reparacao() {
    }

    public Reparacao(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public IntegerProperty getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public FloatProperty getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = new SimpleFloatProperty(custo);
    }

    public StringProperty getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = new SimpleStringProperty(cliente);
    }

    public IntegerProperty getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Integer diagnostico) {
        this.diagnostico = new SimpleIntegerProperty(diagnostico);
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


    public Collection<Pagamento> getPagamentoCollection() {
        return pagamentoCollection;
    }

    public void setPagamentoCollection(Collection<Pagamento> pagamentoCollection) {
        this.pagamentoCollection = pagamentoCollection;
    }
    
    
}
