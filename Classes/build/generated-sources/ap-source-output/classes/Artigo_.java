package classes;

import classes.LinhaArtigo;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T23:07:52")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T22:56:03")
>>>>>>> e2dfc7ed621e86a3742010898af63887f0812172
@StaticMetamodel(Artigo.class)
public class Artigo_ { 

    public static volatile SingularAttribute<Artigo, Float> preco;
    public static volatile SingularAttribute<Artigo, Integer> codigo;
    public static volatile CollectionAttribute<Artigo, LinhaArtigo> linhaartigoCollection;
    public static volatile SingularAttribute<Artigo, String> nome;
    public static volatile SingularAttribute<Artigo, Integer> quantidade;
    public static volatile SingularAttribute<Artigo, String> descricao;

}