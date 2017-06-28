package classes;

import classes.Cliente;
import classes.Reparacao;
import classes.Venda;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T22:30:27")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T21:12:22")
>>>>>>> f8ce1b087b189a84fb90b9ae04986447ee107c20
@StaticMetamodel(Pagamento.class)
public class Pagamento_ { 

    public static volatile SingularAttribute<Pagamento, Integer> codigo;
    public static volatile SingularAttribute<Pagamento, Float> total;
    public static volatile SingularAttribute<Pagamento, Venda> venda;
    public static volatile SingularAttribute<Pagamento, Cliente> numcontribuinte;
    public static volatile SingularAttribute<Pagamento, Reparacao> reparacao;
    public static volatile SingularAttribute<Pagamento, String> artigos;

}