package classes;

import classes.LinhaArtigo;
import classes.Pagamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T11:15:30")
=======
<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-27T23:38:40")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-27T20:40:05")
>>>>>>> be2a2f8b7355eb5a6fc0b2446090f7d1e27c4474
>>>>>>> a27824932d31a29b11828853cbf439cd9569089b
@StaticMetamodel(Venda.class)
public class Venda_ { 

    public static volatile SingularAttribute<Venda, Integer> codigo;
    public static volatile SingularAttribute<Venda, Float> total;
    public static volatile CollectionAttribute<Venda, LinhaArtigo> linhaartigoCollection;
    public static volatile CollectionAttribute<Venda, Pagamento> pagamentoCollection;

}