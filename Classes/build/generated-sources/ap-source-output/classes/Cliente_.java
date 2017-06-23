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
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-23T19:28:52")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-23T21:05:45")
>>>>>>> a6d71efda95411a0de9573c466ecd76310401a1a
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