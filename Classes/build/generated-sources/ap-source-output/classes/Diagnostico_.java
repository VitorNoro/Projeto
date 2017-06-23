package classes;

import classes.Cliente;
import classes.Reparacao;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-23T19:28:52")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-23T21:05:45")
>>>>>>> a6d71efda95411a0de9573c466ecd76310401a1a
@StaticMetamodel(Diagnostico.class)
public class Diagnostico_ { 

    public static volatile SingularAttribute<Diagnostico, Cliente> cliente;
    public static volatile CollectionAttribute<Diagnostico, Reparacao> reparacaoCollection;
    public static volatile SingularAttribute<Diagnostico, Integer> codigo;
    public static volatile SingularAttribute<Diagnostico, String> equipamento;
    public static volatile SingularAttribute<Diagnostico, String> problema;

}