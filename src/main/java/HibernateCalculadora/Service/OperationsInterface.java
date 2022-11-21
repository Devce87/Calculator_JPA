package HibernateCalculadora.Service;

import java.util.List;
import java.util.Stack;

public interface OperationsInterface <T>{

    void Save(T t);
    List<T> List();
    List<T> ListByOperation (String operator);

}
