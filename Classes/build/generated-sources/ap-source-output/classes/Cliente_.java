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
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T22:45:39")
=======
<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T22:47:04")
=======

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T22:34:13")

>>>>>>> cd954642b5297bdd1d7e90d60678656e829cadce
>>>>>>> 81e3f438fc30b957a2fe1184bf17c662f349496d
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