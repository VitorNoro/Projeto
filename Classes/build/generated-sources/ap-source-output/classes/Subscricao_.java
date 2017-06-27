package classes;

import classes.Cliente;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-27T09:59:54")
@StaticMetamodel(Subscricao.class)
public class Subscricao_ { 

    public static volatile SingularAttribute<Subscricao, Cliente> cliente;
    public static volatile SingularAttribute<Subscricao, Integer> codigo;
    public static volatile SingularAttribute<Subscricao, Float> mensalidade;
    public static volatile SingularAttribute<Subscricao, Date> fimsubscricao;
    public static volatile SingularAttribute<Subscricao, String> nome;

}