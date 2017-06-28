package classes;

import classes.Artigo;
import classes.Venda;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T20:18:47")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T18:19:31")
>>>>>>> c8c9ba9790574d061f97c51b40275e0218d5cf6a
@StaticMetamodel(LinhaArtigo.class)
public class LinhaArtigo_ { 

    public static volatile SingularAttribute<LinhaArtigo, Integer> codigo;
    public static volatile SingularAttribute<LinhaArtigo, Float> total;
    public static volatile SingularAttribute<LinhaArtigo, Venda> venda;
    public static volatile SingularAttribute<LinhaArtigo, Artigo> artigo;
    public static volatile SingularAttribute<LinhaArtigo, Integer> quantidade;

}