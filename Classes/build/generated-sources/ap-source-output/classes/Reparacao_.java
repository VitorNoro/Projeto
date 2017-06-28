package classes;

import classes.Cliente;
import classes.Diagnostico;
import classes.Pagamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T22:47:04")
=======

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T22:45:39")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T22:34:13")

>>>>>>> cd954642b5297bdd1d7e90d60678656e829cadce
>>>>>>> 81e3f438fc30b957a2fe1184bf17c662f349496d
@StaticMetamodel(Reparacao.class)
public class Reparacao_ { 

    public static volatile SingularAttribute<Reparacao, Cliente> cliente;
    public static volatile SingularAttribute<Reparacao, Integer> codigo;
    public static volatile SingularAttribute<Reparacao, Float> custo;
    public static volatile SingularAttribute<Reparacao, Diagnostico> diagnostico;
    public static volatile CollectionAttribute<Reparacao, Pagamento> pagamentoCollection;

}