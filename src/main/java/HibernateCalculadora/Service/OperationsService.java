package HibernateCalculadora.Service;
import HibernateCalculadora.Entity.Operations;
import HibernateCalculadora.Entity.OperationsConst;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.*;
import java.util.*;

public class OperationsService implements OperationsInterface <Operations>{

    private EntityManager em;
    private CriteriaBuilder criteria;

    public OperationsService(EntityManager em) {
        this.em = em;
        this.criteria = em.getCriteriaBuilder();
    }

    @Override
    public void Save(Operations operation) {

        try{

            em.getTransaction().begin();
            em.persist(operation);
            em.getTransaction().commit();

        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();

        }
    }


    @Override
    public List<Operations> List() {

        CriteriaQuery<Operations> query= criteria.createQuery(Operations.class);
        Root<Operations> from = query.from(Operations.class);
        query.select(from).orderBy(criteria.desc(from.get("id")));


        return em.createQuery(query).setMaxResults(10).getResultList();

        //return em.createQuery("select c from Operations c where c.id > ((select max(c.id) from Operations c)-10)", Operations.class).getResultList();
    }

    @Override
    public List<Operations> ListByOperation (String operator){

        CriteriaQuery<Operations> query = criteria.createQuery(Operations.class);
        Root<Operations> from = query.from(Operations.class);
        query.select(from).where(criteria.equal(from.get("equationName"), operator)).orderBy(criteria.desc(from.get("id")));

        return em.createQuery(query).setMaxResults(10).getResultList();
    }

    public void Suma (){

        double fistNumber = Double.parseDouble(JOptionPane.showInputDialog("Input the 1st number"));
        double secondNumber = Double.parseDouble(JOptionPane.showInputDialog("Input the 2nd number"));

        Operations sumaOperation = new Operations(fistNumber,secondNumber, OperationsConst.ADD,fistNumber+secondNumber);
        Save(sumaOperation);

        System.out.println(sumaOperation);

    }

    /*public void Calculate (){

        int chooseOption = 0;

        Map<String, Integer> operationOptions = new HashMap<>();
        operationOptions.put("1- Add", 1);
        operationOptions.put("2- Subtract", 2);
        operationOptions.put("3- Multiply", 3);
        operationOptions.put("4- Divide", 4);
        operationOptions.put("5- Percent", 5);
        operationOptions.put("6- Module", 6);
        operationOptions.put("7- Go back", 7);

        Object[] arrayOptions = operationOptions.keySet().toArray(new String[0]);
        Object [] arraySorted =  Arrays.stream(arrayOptions).sorted().toArray();

        do {
            Object opcion = JOptionPane.showInputDialog(null,
                    "Choose an option",
                    "Calculator Operations",
                    JOptionPane.INFORMATION_MESSAGE, null, arraySorted, arraySorted[0]);

            if (opcion == null) {
                JOptionPane.showMessageDialog(null, "You must select an option");

            } else {
                chooseOption = operationOptions.get(opcion.toString());

                switch (chooseOption) {

                    case 1:
                        Suma().
                        break;

                    case 2:
                        Operations subtOperation = new Operations();
                        number1 = Integer.parseInt(JOptionPane.showInputDialog("Input the 1st number"));
                        number2 = Integer.parseInt(JOptionPane.showInputDialog("Input the 2nd number"));

                        subtOperation.setResult((number1)-(number2));
                        subtOperation.setEquation(number1 +" - "+number2+" = ");

                        Save(subtOperation);
                        //Delete();
                        System.out.println(subtOperation.getEquation() + (int) subtOperation.getResult());
                        break;


                    case 3:
                        Operations multOperation = new Operations();
                        number1 = Integer.parseInt(JOptionPane.showInputDialog("Input the 1st number"));
                        number2 = Integer.parseInt(JOptionPane.showInputDialog("Input the 2nd number"));

                        multOperation.setResult((number1)*(number2));
                        multOperation.setEquation(number1 +" x "+number2+" = ");

                        Save(multOperation);
                        //Delete();
                        System.out.println(multOperation.getEquation() + (int) multOperation.getResult());
                        break;

                    case 4:
                        Operations divOperation = new Operations();
                        number1 = Integer.parseInt(JOptionPane.showInputDialog("Input the 1st number"));

                        number2 = Integer.parseInt(JOptionPane.showInputDialog("Input the 2nd number"));
                        while (number2 ==0){
                            JOptionPane.showMessageDialog(null,"2nd number has to be different to Zero");
                            number2 = Integer.parseInt(JOptionPane.showInputDialog("Input the 2nd number"));
                        }

                        divOperation.setResult((number1) / (number2));
                        divOperation.setEquation(number1 +" / "+number2+" = ");

                        Save(divOperation);
                        //Delete();
                        System.out.println(divOperation.getEquation() + (int) divOperation.getResult());
                        break;

                    case 5:
                        Operations percentOperation = new Operations();
                        dNumber1 = Double.parseDouble(JOptionPane.showInputDialog("Input the 1st number"));
                        dNumber2 = Double.parseDouble(JOptionPane.showInputDialog("Input the 2nd number"));

                        while (dNumber2 ==0){
                            JOptionPane.showMessageDialog(null,"2nd number has to be different to Zero");
                            dNumber2 = Double.parseDouble(JOptionPane.showInputDialog("Input the 2nd number"));
                        }

                        percentOperation.setResult((dNumber1) / (dNumber2) * 100);
                        percentOperation.setEquation((int) dNumber1 +" % "+ (int) dNumber2+" = ");

                        Save(percentOperation);
                        //Delete();
                        System.out.println(percentOperation.getEquation() + (int) percentOperation.getResult());
                        break;

                    case 6:
                        Operations modOperation = new Operations();
                        number1 = Integer.parseInt(JOptionPane.showInputDialog("Input the 1st number"));

                        number2 = Integer.parseInt(JOptionPane.showInputDialog("Input the 2nd number"));
                        while (number2 ==0){
                            JOptionPane.showMessageDialog(null,"2nd number has to be different to Zero");
                            number2 = Integer.parseInt(JOptionPane.showInputDialog("Input the 2nd number"));
                        }

                        modOperation.setResult((number1) % (number2));
                        modOperation.setEquation(number1 +" MOD "+number2+" = ");
                        Save(modOperation);
                        //Delete();
                        System.out.println(modOperation.getEquation() + (int) modOperation.getResult());
                        break;

                    case 7:
                        JOptionPane.showMessageDialog(null,"Redirecting to Calculator Main Menu");
                }
            }
        } while (chooseOption!=7);
    }

    public void CalculatorMenu(){

        int chooseOption = 0;

        Map<String, Integer> menuOptions = new HashMap<>();
        menuOptions.put("1- Perform an operation", 1);
        menuOptions.put("2- See operations history", 2);
        menuOptions.put("3- Exit", 3);

        Object[] arrayOptions = menuOptions.keySet().toArray(new String[0]);
        Object [] arraySorted =  Arrays.stream(arrayOptions).sorted().toArray();

        do {
            Object opcion = JOptionPane.showInputDialog(null,
                    "Choose an option",
                    "Calculator Menu",
                    JOptionPane.INFORMATION_MESSAGE, null, arraySorted, arraySorted[0]);

            if (opcion == null) {
                JOptionPane.showMessageDialog(null, "You must select an option");

            } else {
                chooseOption = menuOptions.get(opcion.toString());

                switch (chooseOption) {

                    case 1:


                        break;

                    case 2:
                        //List().forEach(System.out::println);
                        Stack<Operations> opStack = Stack();
                        for (int i = 1;i<=10;i++){
                            System.out.println(opStack.pop());
                        }


                        break;


                    case 3:
                        JOptionPane.showMessageDialog(null,"See you next time!");
                        break;
                }
            }
        } while (chooseOption!=3);

    }*/



}
