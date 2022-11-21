package HibernateCalculadora.Entity;
import javax.persistence.*;

@Entity
@Table(name = "operations")
public class Operations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double firstValue;
    private double secondValue;
    private String equationName;
    private  double result;

    public Operations() {
    }

    public Operations(double firstValue, double secondValue, String equationName, double result) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.equationName = equationName;
        this.result = result;
    }

    public double getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(double firstValue) {
        this.firstValue = firstValue;
    }

    public double getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(double secondValue) {
        this.secondValue = secondValue;
    }

    public String getEquationName() {
        return equationName;
    }

    public void setEquationName(String equationName) {
        this.equationName = equationName;
    }

    public double getResult() {
        return result;
    }


    @Override
    public String toString() {
        String operator = null;

        if (getEquationName().equalsIgnoreCase(OperationsConst.ADD)){
            operator = "+";
        } else if (getEquationName().equalsIgnoreCase(OperationsConst.SUBTRACT)){
            operator = "-";
        }else if (getEquationName().equalsIgnoreCase(OperationsConst.MULTIPLY)){
            operator = "x";
        }else if (getEquationName().equalsIgnoreCase(OperationsConst.DIVIDE)) {
            operator = "/";
        }else if (getEquationName().equalsIgnoreCase(OperationsConst.PERCENT)) {
            operator = "%";
        } else if (getEquationName().equalsIgnoreCase(OperationsConst.MODULE)) {
            operator = "Mod";
        }

        return "|   " +
                + firstValue +" "+ operator +" "+ secondValue + "   |" +
                " = " + result;
    }

}
