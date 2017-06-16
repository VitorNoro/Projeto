package classes;

import classes.Manutencao;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-03T16:54:46")
@StaticMetamodel(Manutencao.class)
public class Manutencao_ { 

    public static volatile SingularAttribute<Manutencao, Manutencao> subscricao;
    public static volatile SingularAttribute<Manutencao, Integer> codigo;
    public static volatile SingularAttribute<Manutencao, String> localizacao;
    public static volatile CollectionAttribute<Manutencao, Manutencao> manutencaoCollection;
    public static volatile SingularAttribute<Manutencao, String> equipamento;
    public static volatile SingularAttribute<Manutencao, Date> dataagendada;

}