package classes;

import classes.Cliente;
import classes.Reparacao;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T23:07:52")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T22:56:04")
>>>>>>> e2dfc7ed621e86a3742010898af63887f0812172
@StaticMetamodel(Diagnostico.class)
public class Diagnostico_ { 

    public static volatile SingularAttribute<Diagnostico, Cliente> cliente;
    public static volatile CollectionAttribute<Diagnostico, Reparacao> reparacaoCollection;
    public static volatile SingularAttribute<Diagnostico, Integer> codigo;
    public static volatile SingularAttribute<Diagnostico, String> equipamento;
    public static volatile SingularAttribute<Diagnostico, String> problema;

}