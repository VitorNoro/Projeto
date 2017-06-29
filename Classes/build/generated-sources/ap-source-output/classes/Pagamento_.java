package classes;

import classes.Cliente;
import classes.Reparacao;
import classes.Venda;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-29T22:04:54")
@StaticMetamodel(Pagamento.class)
public class Pagamento_ { 

    public static volatile SingularAttribute<Pagamento, Integer> codigo;
    public static volatile SingularAttribute<Pagamento, Float> total;
    public static volatile SingularAttribute<Pagamento, Venda> venda;
    public static volatile SingularAttribute<Pagamento, Cliente> numcontribuinte;
    public static volatile SingularAttribute<Pagamento, Reparacao> reparacao;
    public static volatile SingularAttribute<Pagamento, String> artigos;

}