package classes;

import classes.Cliente;
import classes.Diagnostico;
import classes.Pagamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-23T23:11:35")
=======

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-23T19:28:52")

>>>>>>> 45f4d94e595bb048caac36087de23273f8284bb0
@StaticMetamodel(Reparacao.class)
public class Reparacao_ { 

    public static volatile SingularAttribute<Reparacao, Cliente> cliente;
    public static volatile SingularAttribute<Reparacao, Integer> codigo;
    public static volatile SingularAttribute<Reparacao, Float> custo;
    public static volatile SingularAttribute<Reparacao, Diagnostico> diagnostico;
    public static volatile CollectionAttribute<Reparacao, Pagamento> pagamentoCollection;

}