package classes;

import classes.LinhaArtigo;
import classes.Pagamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T13:54:19")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T13:52:16")
>>>>>>> 9acc5780695204e4688f8be3b7a2b2d27673202f
@StaticMetamodel(Venda.class)
public class Venda_ { 

    public static volatile SingularAttribute<Venda, Integer> codigo;
    public static volatile SingularAttribute<Venda, Float> total;
    public static volatile CollectionAttribute<Venda, LinhaArtigo> linhaartigoCollection;
    public static volatile CollectionAttribute<Venda, Pagamento> pagamentoCollection;

}