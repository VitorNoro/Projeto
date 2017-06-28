package classes;

import classes.Subscricao;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T15:48:07")
@StaticMetamodel(Manutencao.class)
public class Manutencao_ { 

    public static volatile SingularAttribute<Manutencao, Subscricao> subscricao;
    public static volatile SingularAttribute<Manutencao, Integer> codigo;
    public static volatile SingularAttribute<Manutencao, String> localizacao;
    public static volatile SingularAttribute<Manutencao, Date> dataAgendada;
    public static volatile SingularAttribute<Manutencao, String> equipamento;

}