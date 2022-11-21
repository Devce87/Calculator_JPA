package HibernateCalculadora.Calculator;
import HibernateCalculadora.Entity.Operations;
import HibernateCalculadora.Entity.OperationsConst;
import HibernateCalculadora.Service.OperationsService;
import HibernateCalculadora.Util.JPAutil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnScreenCalculator extends JFrame implements ActionListener {

    EntityManager em = JPAutil.getEntityManager();
    OperationsService opService = new OperationsService(em);

    JFrame f;
    Operations registro=null;
    JTextField result;
    String Jresult= null;
    String operator=null;
    double temp1;
    double temp2;

    public void calculate() {

        //OnScreenCalculator c = new OnScreenCalculator();

        f =  new JFrame("Coding-Sunday Calculator");
        result = new JTextField(Jresult);
        f.getContentPane().setBackground(Color.DARK_GRAY);

        JButton suma = new JButton("+");
        JButton resta = new JButton("-");
        JButton mult = new JButton("x");
        JButton div = new JButton("/");
        JButton clear = new JButton("C");
        JButton period = new JButton(".");
        JButton igual = new JButton("=");
        JButton percent = new JButton("%");
        JButton n1 = new JButton("1");
        JButton n2 = new JButton("2");
        JButton n3 = new JButton("3");
        JButton n4 = new JButton("4");
        JButton n5 = new JButton("5");
        JButton n6 = new JButton("6");
        JButton n7 = new JButton("7");
        JButton n8 = new JButton("8");
        JButton n9 = new JButton("9");
        JButton n0 = new JButton("0");
        JLabel separate = new JLabel("---------------------------------------------------------");
        JButton reg10 = new JButton("REGx10");


        clear.setBounds(40,25,50,30);
        clear.setBackground(Color.PINK);
        result.setBounds(100,25,170,30);
        suma.setBackground(Color.GREEN);
        suma.setBounds(40,65,50,30);
        resta.setBackground(Color.GREEN);
        resta.setBounds(40,100,50,30);
        mult.setBackground(Color.GREEN);
        mult.setBounds(40,135,50,30);
        div.setBackground(Color.GREEN);
        div.setBounds(40,170,50,30);
        n1.setBounds(100,65,50,30);
        n2.setBounds(160,65,50,30);
        n3.setBounds(220,65,50,30);
        n4.setBounds(100,100,50,30);
        n5.setBounds(160,100,50,30);
        n6.setBounds(220,100,50,30);
        n7.setBounds(100,135,50,30);
        n8.setBounds(160,135,50,30);
        n9.setBounds(220,135,50,30);
        percent.setBounds(100,170,50,30);
        percent.setBackground(Color.GREEN);
        n0.setBounds(160,170,50,30);
        period.setBounds(220,170,50,30);
        igual.setBounds(40,210,230,30);
        igual.setBackground(Color.PINK);
        separate.setBounds(40,230,230,30);
        separate.setForeground(Color.WHITE);
        reg10.setBounds(40,255,230,30);


        n0.addActionListener(this);
        n1.addActionListener(this);
        n2.addActionListener(this);
        n3.addActionListener(this);
        n4.addActionListener(this);
        n5.addActionListener(this);
        n6.addActionListener(this);
        n7.addActionListener(this);
        n8.addActionListener(this);
        n9.addActionListener(this);
        suma.addActionListener(this);
        resta.addActionListener(this);
        mult.addActionListener(this);
        div.addActionListener(this);
        clear.addActionListener(this);
        igual.addActionListener(this);
        percent.addActionListener(this);
        period.addActionListener(this);
        reg10.addActionListener(this);

        f.add(clear);
        f.add(result);
        f.add(suma);
        f.add(resta);
        f.add(mult);
        f.add(div);
        f.add(n1);
        f.add(n2);
        f.add(n3);
        f.add(n4);
        f.add(n5);
        f.add(n6);
        f.add(n7);
        f.add(n8);
        f.add(n9);
        f.add(n0);
        f.add(percent);
        f.add(period);
        f.add(igual);
        f.add(separate);
        f.add(reg10);

        f.setSize(326,355);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();
        temp1 = 0;
        temp2 = 0;

        if ((input.charAt(0)>='0' && input.charAt(0)<='9') || input.charAt(0)=='.'){
            if(Jresult==null){
               Jresult=input;
            } else {
                Jresult = Jresult+input;
            }
            result.setText(Jresult);

        } else if ((input.charAt(0) == '+' || input.charAt(0) == '-' || input.charAt(0) == 'x' || input.charAt(0) == '/' || input.charAt(0) == '%') && Jresult.charAt(0)!='R'){
            Jresult = Jresult+input;
            result.setText(Jresult);
            operator = input;

        } else if ((input.charAt(0)=='=' && Jresult.charAt(0)!='R')){
            Operations registroTemp;
            int operatorIndex = Jresult.indexOf(operator);
            temp1=Double.parseDouble(Jresult.substring(0,operatorIndex));
            temp2=(Double.parseDouble(Jresult.substring(operatorIndex+1)));

            if(operator.charAt(0)=='+'){
                registroTemp = new Operations(temp1,temp2, OperationsConst.ADD,(temp1+temp2));
                registro = registroTemp;
                opService.Save(registro);
                Jresult = String.valueOf(registroTemp.getResult());
                result.setText(Jresult);


            }else if(operator.charAt(0)=='-') {
                registroTemp = new Operations(temp1,temp2, OperationsConst.SUBTRACT,(temp1-temp2));
                registro = registroTemp;
                opService.Save(registro);
                Jresult = String.valueOf(registroTemp.getResult());
                result.setText(Jresult);

            }else if(operator.charAt(0)=='x') {
                registroTemp = new Operations(temp1,temp2, OperationsConst.MULTIPLY,(temp1*temp2));
                registro = registroTemp;
                opService.Save(registro);
                Jresult = String.valueOf(registroTemp.getResult());
                result.setText(Jresult);

            }else if(operator.charAt(0)=='/') {
                registroTemp = new Operations(temp1,temp2, OperationsConst.DIVIDE,(temp1/temp2));
                registro = registroTemp;
                opService.Save(registro);
                Jresult = String.valueOf(registroTemp.getResult());
                result.setText(Jresult);

            }else if(operator.charAt(0)=='%') {
                registroTemp = new Operations(temp1,temp2, OperationsConst.PERCENT,((temp1/temp2)*100));
                registro = registroTemp;
                opService.Save(registro);
                Jresult = String.valueOf(registroTemp.getResult());
                result.setText(Jresult);
            }

        } else if (input.charAt(0)=='C'){
            Jresult=null;
            result.setText(null);


        } else if(input.equalsIgnoreCase("REGx10") || Jresult.charAt(0)=='R'){

            if (Jresult==null){
                Jresult = input;

            } else if(Jresult.equalsIgnoreCase("REGx10") && !input.equalsIgnoreCase("REGx10")){
                Jresult = Jresult+input;

            }

            if (Jresult.charAt(Jresult.length()-1)=='='){
                opService.List().forEach(System.out::println);
                Jresult=null;

            } else if (Jresult.charAt(Jresult.length()-1)=='+'){
                opService.ListByOperation("Add").forEach(System.out::println);
                Jresult=null;

            } else if (Jresult.charAt(Jresult.length()-1)=='-') {
                opService.ListByOperation("Subtract").forEach(System.out::println);
                Jresult=null;

            } else if (Jresult.charAt(Jresult.length()-1)=='x') {
                opService.ListByOperation("Multiply").forEach(System.out::println);
                Jresult=null;

            } else if (Jresult.charAt(Jresult.length()-1)=='/') {
                opService.ListByOperation("Divide").forEach(System.out::println);
                Jresult=null;

            } else if (Jresult.charAt(Jresult.length()-1)=='%') {
                opService.ListByOperation("Percent").forEach(System.out::println);
                Jresult=null;

            }

        }
    }
}
