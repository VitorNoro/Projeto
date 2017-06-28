package classes;

import classes.Cliente;
import classes.Diagnostico;
import classes.Pagamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T20:18:47")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T19:13:33")
>>>>>>> c8c9ba9790574d061f97c51b40275e0218d5cf6a
@StaticMetamodel(Reparacao.class)
public class Reparacao_ { 

    public static volatile SingularAttribute<Reparacao, Cliente> cliente;
    public static volatile SingularAttribute<Reparacao, Integer> codigo;
    public static volatile SingularAttribute<Reparacao, Float> custo;
    public static volatile SingularAttribute<Reparacao, Diagnostico> diagnostico;
    public static volatile CollectionAttribute<Reparacao, Pagamento> pagamentoCollection;

}