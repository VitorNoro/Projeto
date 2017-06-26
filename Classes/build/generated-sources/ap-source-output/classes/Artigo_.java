package classes;

import classes.LinhaArtigo;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-26T16:21:55")
@StaticMetamodel(Artigo.class)
public class Artigo_ { 

    public static volatile SingularAttribute<Artigo, Float> preco;
    public static volatile SingularAttribute<Artigo, Integer> codigo;
    public static volatile CollectionAttribute<Artigo, LinhaArtigo> linhaartigoCollection;
    public static volatile SingularAttribute<Artigo, String> nome;
    public static volatile SingularAttribute<Artigo, Integer> quantidade;
    public static volatile SingularAttribute<Artigo, String> descricao;

}