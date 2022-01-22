package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Cart extends JFrame{
    private JPanel mainPanel;
    private JTable table1;
    private JButton checkOutButton;
    private JLabel total;
    private JPasswordField passwordField1;
    private JButton goBackButton;
    private JButton emptyCartButton;

    public Cart(String s,String e,String p,String a,int pay){
        this.setTitle("Cart");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        String[][] pop = displayCart(s);
        table1.setModel(new DefaultTableModel(pop,new String[]{"Product Name","Quantity","Price"}));
        total.setText("RM "+Double.toString(totalp(s)));
        this.setVisible(true);


        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent checkOUT) {
                if(insertPaymentPass(pay)){
                    buyNow(s,pay);
                    dispose();
                    JFrame neww = new Customer(s,e ,p,a, pay);
                    neww.setVisible(true);
                }


            }
        });
        emptyCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent we) {
                EmptyCart(s);
                JOptionPane.showMessageDialog(null,"You have emptied your cart");
                dispose();
                JFrame neww = new Customer(s,e ,p,a, pay);
                neww.setVisible(true);
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent goback) {
                dispose();
                JFrame goo = new Customer(s,e ,p,a, pay);
                goo.setVisible(true);
            }
        });
    }



    public String[][] displayCart(String s){
        String[] pronames = new String[10];
        int[] quan = new int[10];
        String[][] data = new String[numbCart(s)][3];

        double totalp = 0,total=0;
        int tity;
        int x = 0;
        try{
            File cart = new File(s+"cart.txt");
            if(cart.length()==0){
                JOptionPane.showMessageDialog(null,"Cart Empty");
                return data;
            }
            Scanner in = new Scanner(new FileInputStream(s+"cart.txt"));
            while(in.hasNextLine()){
                String name = in.nextLine();
                String price = in.nextLine();
                String quantity = in.nextLine();
                data[x][0]=name;
                data[x][2]="RM "+price;
                data[x][1]=quantity;
                total = Double.parseDouble(price);
                tity = Integer.parseInt(quantity);
                totalp += total*tity;
                x++;


            }
            in.close();
        }catch (Exception e){
            System.out.println("Cart Empty");
        }
        return data;
    }

    public int numbCart(String s){
        String[] pronames = new String[10];
        int[] quan = new int[10];
        int m=0;

        double totalp = 0,total=0;
        int tity;
        try{
            File cart = new File(s+"cart.txt");
            if(cart.length()==0){
                System.out.println("Cart is Empty");
                return 0;
            }
            Scanner in = new Scanner(new FileInputStream(s+"cart.txt"));
            while(in.hasNextLine()){
                String name = in.nextLine();
                String price = in.nextLine();
                String quantity = in.nextLine();
                m++;
                total = Double.parseDouble(price);
                tity = Integer.parseInt(quantity);
                totalp += total*tity;


            }
            in.close();
            return m;

        }catch (Exception e){
            System.out.println("Cart Empty");
        }
        return m;
    }

    public double totalp(String s){
        String[] pronames = new String[10];
        int[] quan = new int[10];
        int m=0;

        double totalp = 0,total=0;
        int tity;
        try{
            File cart = new File(s+"cart.txt");
            if(cart.length()==0){
                System.out.println("Cart is Empty");
                return 0;
            }
            Scanner in = new Scanner(new FileInputStream(s+"cart.txt"));
            while(in.hasNextLine()){
                String name = in.nextLine();
                String price = in.nextLine();
                String quantity = in.nextLine();
                m++;
                total = Double.parseDouble(price);
                tity = Integer.parseInt(quantity);
                totalp += total*tity;


            }
            in.close();
            return totalp;

        }catch (Exception e){
            System.out.println("Cart Empty");
        }
        return totalp;
    }

    public void buyNow(String s,int u){
        File delete = new File(s+"cart.txt");
        if(delete.length()==0){
            JOptionPane.showMessageDialog(null,"Cart Empty");
            return;
        }
        double price = totalp(s);
        double before=0;
        int checker = -1,i =0,q=0;
        String lol;
        try{

            Scanner in = new Scanner(new FileInputStream("balance.txt"));
            Scanner in1 = new Scanner(new FileInputStream("balance.txt"));
            Scanner in2 = new Scanner(new FileInputStream(s+"cart.txt"));
            PrintWriter out = new PrintWriter(new FileOutputStream("temp.txt"));
            PrintWriter out1 = new PrintWriter(new FileOutputStream("buytemp.txt"));
            File file = new File("balance.txt");
            File newfile = new File("temp.txt");
            File file1 = new File("buytemp.txt");




            while(in.hasNextLine()){
                lol = in.nextLine();
                if(lol.equals(s)){
                    String o = in.nextLine();
                    before = Double.parseDouble(o);
                    checker = 1;
                    break;
                }
            }



            if(checker==-1){

                JOptionPane.showMessageDialog(null,"User Not Found");
                in.close();
                in1.close();
                in2.close();
                out.close();
                out1.close();
                return ;
            }

            before = before - price;
            if(before < 0){
                JOptionPane.showMessageDialog(null,"Insufficient Balance");
                in.close();
                in1.close();
                in2.close();
                out.close();
                out1.close();
                return ;
            }



            while(in2.hasNextLine()) {
                String name = in2.nextLine();
                String pricee = in2.nextLine();
                String quantity = in2.nextLine();
                int l = Integer.parseInt(quantity);
                updateSales(s,name,l);
                addtransactionlist(s,name,l,price);
                addcreditlist(s,name,l,price);
            }
            while(in1.hasNextLine()){
                lol = in1.nextLine();
                if(lol.equals(s)){
                    String o =in1.nextLine();
                    out.println(lol);
                    out.println(before);
                }else
                    out.println(lol);
            }

            JOptionPane.showMessageDialog(null,"You have successfull purchased\nNew Balance : RM "+before);



            in.close();
            in1.close();
            in2.close();
            out.close();
            out1.close();
            delete.delete();
            file1.renameTo(delete);
            file.delete();
            newfile.renameTo(file);
        }catch (Exception e){
            System.out.println("Error");
        }

    }

    public boolean insertPaymentPass(int k){
        if(!isNumeric(passwordField1.getText())){
            JOptionPane.showMessageDialog(null, "Wrong Input");
            passwordField1.setText("");
            return false;
        }
        int y = Integer.parseInt(passwordField1.getText());

        if(k==y){

            return true;
        }else {
            JOptionPane.showMessageDialog(null, "Wrong Payment Pass");
            passwordField1.setText("");
            return false;
        }
    }

    public void addtransactionlist(String s,String r,int sales,double total) {
        String q = whichSeller(r);
        String sale,tot;
        int x;
        double y,profit=0;

        try{
            PrintWriter out = new PrintWriter(new FileOutputStream(q+"trans.txt",true));
            PrintWriter out1 = new PrintWriter(new FileOutputStream("temp7.txt"));
            Scanner in = new Scanner(new FileInputStream(q+"trans.txt"));
            File file = new File(q+"trans.txt");
            File newfile = new File("temp7.txt");
            if(checkUniqueTrans(q,r)){
                while(in.hasNextLine()){
                    String lol = in.nextLine();
                    if(lol.equals(r)){
                        sale = in.nextLine();
                        tot = in.nextLine();
                        out1.println(lol);
                        x = Integer.parseInt(sale);
                        y = Double.parseDouble(tot);
                        out1.println(x+sales);
                        double tootal = y+total;
                        out1.printf("%.2f\n",tootal);
                    }else
                        out1.println(lol);
                }
                out.close();
                out1.close();
                in.close();
                file.delete();
                newfile.renameTo(file);
            }else{
                out.println(r);
                out.println(sales);
                out.println(total);
            }
            out.close();
            out1.close();
            in.close();

        }catch (Exception e){

        }
    }

    public void addcreditlist(String s,String r,int sales,double total) {
        ;
        String sale,tot;
        int x;
        double y,profit=0;

        try{
            PrintWriter out = new PrintWriter(new FileOutputStream(s+"cred.txt",true));
            PrintWriter out1 = new PrintWriter(new FileOutputStream("temp8.txt"));
            Scanner in = new Scanner(new FileInputStream(s+"cred.txt"));
            File file = new File(s+"cred.txt");
            File newfile = new File("temp8.txt");
            if(checkUniqueCred(s,r)){
                while(in.hasNextLine()){
                    String lol = in.nextLine();
                    if(lol.equals(r)){
                        sale = in.nextLine();
                        tot = in.nextLine();
                        out1.println(lol);
                        x = Integer.parseInt(sale);
                        y = Double.parseDouble(tot);
                        out1.println(x+sales);
                        out1.println(y+total);
                        double tootal = y+total;
                        out1.printf("%.2f\n",tootal);
                    }else
                        out1.println(lol);
                }
                out.close();
                out1.close();
                in.close();
                file.delete();
                newfile.renameTo(file);
            }else{
                out.println(r);
                out.println(sales);
                out.println(total);
            }
            out.close();
            out1.close();
            in.close();

        }catch (Exception e){

        }
    }

    public boolean checkUniqueTrans(String x,String r){

        try {
            Scanner in = new Scanner(new FileInputStream(x+"trans.txt"));
            while(in.hasNextLine()){
                String name = in.nextLine();
                if(r.equals(name)){
                    in.close();
                    return true;
                }
            }
            in.close();
        }catch (Exception e){
            System.out.println("Error");
        }
        return false;
    }

    public boolean checkUniqueCred(String x,String r){

        try {
            Scanner in = new Scanner(new FileInputStream(x+"cred.txt"));
            while(in.hasNextLine()){
                String name = in.nextLine();
                if(r.equals(name)){
                    in.close();
                    return true;
                }
            }
            in.close();
        }catch (Exception e){
            System.out.println("Error");
        }
        return false;
    }

    public void updateSales(String s,String r,int x){
        String lol,temp,temp1,temp2,temp3,temp4,temp5;
        int z,y;
        try {
            Scanner in = new Scanner(new FileInputStream(whichSeller(r) + ".txt"));
            Scanner in1 = new Scanner(new FileInputStream("allproducts.txt"));
            PrintWriter out = new PrintWriter(new FileOutputStream("temp11.txt"));
            PrintWriter out1 = new PrintWriter(new FileOutputStream("temp12.txt"));
            File file = new File(whichSeller(r) + ".txt");
            File newfile = new File("temp11.txt");
            File file1 = new File("allproducts.txt");
            File newfile1 = new File("temp12.txt");


            while(in.hasNextLine()){
                lol = in.nextLine();
                if(lol.equals(r)){
                    temp = in.nextLine();
                    temp1 = in.nextLine();
                    temp2 = in.nextLine();
                    temp3 = in.nextLine();
                    temp4 = in.nextLine();
                    y = Integer.parseInt(temp2);
                    z=Integer.parseInt(temp3);
                    y -= x;
                    z += x;

                    out.println(lol);
                    out.println(temp);
                    out.println(temp1);
                    out.println(y);
                    out.println(z);
                    out.println(temp4);

                }else
                    out.println(lol);

            }

            while(in1.hasNextLine()){
                lol = in1.nextLine();
                if(lol.equals(r)){
                    temp = in1.nextLine();
                    temp1 = in1.nextLine();
                    temp2 = in1.nextLine();
                    temp3 = in1.nextLine();
                    temp4 = in1.nextLine();
                    y = Integer.parseInt(temp2);
                    z=Integer.parseInt(temp3);
                    y -= x;
                    z += x;

                    out1.println(lol);
                    out1.println(temp);
                    out1.println(temp1);
                    out1.println(y);
                    out1.println(z);
                    out1.println(temp4);

                }else
                    out1.println(lol);

            }

            in.close();
            in1.close();
            out.close();
            out1.close();
            file.delete();
            newfile.renameTo(file);
            file1.delete();
            newfile1.renameTo(file1);








        }catch (Exception e){
            System.out.println("Error");
        }
    }

    public String whichSeller(String r){
        String temp,temp1,temp2;
        try{
            Scanner in = new Scanner(new FileInputStream("data.txt"));
            while(in.hasNextLine()){
                temp = in.nextLine();
                temp1 = in.nextLine();
                temp2 = in.nextLine();
                Scanner in1 = new Scanner(new FileInputStream(temp+".txt"));
                while(in1.hasNextLine()){
                    String lol = in1.nextLine();
                    if(lol.equals(r)){
                        in.close();
                        in1.close();
                        return temp;
                    }
                }
                in1.close();
            }

            in.close();
        }catch (Exception e){
            System.out.println("Error");
        }
        return "-1";
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void EmptyCart(String s){
        try {
            PrintWriter out = new PrintWriter(s+"cart.txt");
            out.close();
        }catch (Exception e){
            System.out.println("error");
        }
    }


}
