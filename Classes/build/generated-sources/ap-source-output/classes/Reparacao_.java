package classes;

import classes.Cliente;
import classes.Diagnostico;
import classes.Pagamento;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-03T16:54:46")
@StaticMetamodel(Reparacao.class)
public class Reparacao_ { 

    public static volatile SingularAttribute<Reparacao, Cliente> cliente;
    public static volatile SingularAttribute<Reparacao, Integer> codigo;
    public static volatile SingularAttribute<Reparacao, BigDecimal> custo;
    public static volatile SingularAttribute<Reparacao, Diagnostico> diagnostico;
    public static volatile CollectionAttribute<Reparacao, Pagamento> pagamentoCollection;

}