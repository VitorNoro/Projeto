package classes;

import classes.LinhaArtigo;
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
@StaticMetamodel(Venda.class)
public class Venda_ { 

    public static volatile SingularAttribute<Venda, Integer> codigo;
    public static volatile SingularAttribute<Venda, Float> total;
    public static volatile CollectionAttribute<Venda, LinhaArtigo> linhaartigoCollection;
    public static volatile CollectionAttribute<Venda, Pagamento> pagamentoCollection;

}