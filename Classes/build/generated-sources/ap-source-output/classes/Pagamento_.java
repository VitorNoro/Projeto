package classes;

import classes.Cliente;
import classes.Reparacao;
import classes.Venda;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-03T16:54:46")
@StaticMetamodel(Pagamento.class)
public class Pagamento_ { 

    public static volatile SingularAttribute<Pagamento, Integer> codigo;
    public static volatile SingularAttribute<Pagamento, BigDecimal> total;
    public static volatile SingularAttribute<Pagamento, Venda> venda;
    public static volatile SingularAttribute<Pagamento, Cliente> numcontribuinte;
    public static volatile SingularAttribute<Pagamento, Reparacao> reparacao;
    public static volatile SingularAttribute<Pagamento, String> artigos;

}