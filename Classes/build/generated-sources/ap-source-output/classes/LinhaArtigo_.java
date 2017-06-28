package classes;

import classes.Artigo;
import classes.Venda;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-27T23:38:40")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-27T20:40:05")
>>>>>>> be2a2f8b7355eb5a6fc0b2446090f7d1e27c4474
@StaticMetamodel(LinhaArtigo.class)
public class LinhaArtigo_ { 

    public static volatile SingularAttribute<LinhaArtigo, Integer> codigo;
    public static volatile SingularAttribute<LinhaArtigo, Float> total;
    public static volatile SingularAttribute<LinhaArtigo, Venda> venda;
    public static volatile SingularAttribute<LinhaArtigo, Artigo> artigo;
    public static volatile SingularAttribute<LinhaArtigo, Integer> quantidade;

}