package classes;

import classes.Cliente;
import classes.Reparacao;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T21:12:22")
@StaticMetamodel(Diagnostico.class)
public class Diagnostico_ { 

    public static volatile SingularAttribute<Diagnostico, Cliente> cliente;
    public static volatile CollectionAttribute<Diagnostico, Reparacao> reparacaoCollection;
    public static volatile SingularAttribute<Diagnostico, Integer> codigo;
    public static volatile SingularAttribute<Diagnostico, String> equipamento;
    public static volatile SingularAttribute<Diagnostico, String> problema;

}