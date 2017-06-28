package classes;

import classes.Diagnostico;
import classes.Pagamento;
import classes.Reparacao;
import classes.Subscricao;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T20:18:47")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T18:19:31")
>>>>>>> c8c9ba9790574d061f97c51b40275e0218d5cf6a
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile CollectionAttribute<Cliente, Reparacao> reparacaoCollection;
    public static volatile SingularAttribute<Cliente, String> contacto;
    public static volatile CollectionAttribute<Cliente, Subscricao> subscricaoCollection;
    public static volatile SingularAttribute<Cliente, String> numcontribuinte;
    public static volatile CollectionAttribute<Cliente, Diagnostico> diagnosticoCollection;
    public static volatile SingularAttribute<Cliente, String> nome;
    public static volatile CollectionAttribute<Cliente, Pagamento> pagamentoCollection;

}