package classes;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-03T16:54:46")
@StaticMetamodel(Fatura.class)
public class Fatura_ { 

    public static volatile SingularAttribute<Fatura, Integer> codigo;
    public static volatile SingularAttribute<Fatura, BigDecimal> total;
    public static volatile SingularAttribute<Fatura, String> numcontribuinte;
    public static volatile SingularAttribute<Fatura, String> artigos;

}