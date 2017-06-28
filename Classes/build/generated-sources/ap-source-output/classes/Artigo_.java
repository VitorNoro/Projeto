package classes;

import classes.LinhaArtigo;
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
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-27T21:30:13")
>>>>>>> be2a2f8b7355eb5a6fc0b2446090f7d1e27c4474
>>>>>>> a27824932d31a29b11828853cbf439cd9569089b
@StaticMetamodel(Artigo.class)
public class Artigo_ { 

    public static volatile SingularAttribute<Artigo, Float> preco;
    public static volatile SingularAttribute<Artigo, Integer> codigo;
    public static volatile CollectionAttribute<Artigo, LinhaArtigo> linhaartigoCollection;
    public static volatile SingularAttribute<Artigo, String> nome;
    public static volatile SingularAttribute<Artigo, Integer> quantidade;
    public static volatile SingularAttribute<Artigo, String> descricao;

}