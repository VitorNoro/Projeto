package classes;

import classes.LinhaArtigo;
import classes.Pagamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T22:47:04")
=======

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T22:34:13")

>>>>>>> cd954642b5297bdd1d7e90d60678656e829cadce
@StaticMetamodel(Venda.class)
public class Venda_ { 

    public static volatile SingularAttribute<Venda, Integer> codigo;
    public static volatile SingularAttribute<Venda, Float> total;
    public static volatile CollectionAttribute<Venda, LinhaArtigo> linhaartigoCollection;
    public static volatile CollectionAttribute<Venda, Pagamento> pagamentoCollection;

}