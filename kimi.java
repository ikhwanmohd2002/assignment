package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class kimi extends JFrame{
    private JPanel mainPanel;
    private JLabel name;
    private JLabel price;
    private JLabel stocks;
    private JLabel desc;
    private JLabel sales;
    private JLabel review;
    private JButton goBackButton;
    private JButton addToCartButton;
    private JButton addToFavouriteButton;
    private JButton buyNowButton;
    private JLabel pic;


    public kimi(String s,String e,String p,String a,String r,int pay){
        this.setContentPane(mainPanel);
        this.setTitle("KIMI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        displayname(r);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dawd) {
                dispose();
                JFrame backk = new Customer(s,e,p,a,pay);
            }
        });
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent llll) {
                String quanti = JOptionPane.showInputDialog("Quantity");
                if(!isNumeric(quanti)){
                    JOptionPane.showMessageDialog(null,"Wrong Input");
                    return;
                }
                int quantii = Integer.parseInt(quanti);
                addCart(s,r,quantii);
                dispose();
                JFrame backk = new Customer(s,e,p,a,pay);
            }
        });
        addToFavouriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent fiv) {
                addFavourite(s,r);
                dispose();
                JFrame backk = new Customer(s,e,p,a,pay);

            }
        });
        buyNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent hhh) {
                String quanti = JOptionPane.showInputDialog("Quantity");
                if(!isNumeric(quanti)){
                    JOptionPane.showMessageDialog(null,"Wrong Input");
                    return;
                }
                int quantii = Integer.parseInt(quanti);
                String payment = JOptionPane.showInputDialog("Payment Password");
                if(!isNumeric(payment)){
                    JOptionPane.showMessageDialog(null,"Wrong Input");
                    return;
                }
                int pp =  Integer.parseInt(payment);
                if(pp != pay){
                    JOptionPane.showMessageDialog(null,"Wrong Payment Password");
                    return;
                }
                addCart(s,r,quantii);
                buyNow(s,pay);
                dispose();
                JFrame backk = new Customer(s,e,p,a,pay);

            }
        });
    }


    public void displayname(String r){

        String prices,descs,stock,sale,reviews;
        int x = -1;
        try{

            Scanner in = new Scanner(new FileInputStream("allproducts.txt"));
            while(in.hasNextLine()){
                String lol = in.nextLine();
                if(r.equals(lol)){
                    prices = in.nextLine();
                    descs = in.nextLine();
                    stock =in.nextLine();
                    sale=in.nextLine();
                    reviews=in.nextLine();
                    name.setText(":     "+r);
                    price.setText(":     "+prices);
                    desc.setText(":     "+descs);
                    stocks.setText(":     "+stock);
                    sales.setText(":     "+sale);
                    review.setText(":     "+reviews);


                    x = 1;
                }


            }
            if(x==-1){
                JOptionPane.showMessageDialog(null,"Product Not Found");
            }
            in.close();
        }catch (Exception e){
            System.out.println("Error");
        }
    }

    public void addCart(String s,String r,int x){
        Scanner scan = new Scanner(System.in);
        try{
            Scanner in5 = new Scanner(new FileInputStream("allproducts.txt"));
            PrintWriter out = new PrintWriter(new FileOutputStream(s+"cart.txt",true));
            String name="",price="",desc="",stocks="",sales="",review="";
            if(checkUniqueSell(s,r)){
                JOptionPane.showMessageDialog(null,"Own product cannot be bought");
                in5.close();
                out.close();
                return;
            }
            if(checkUnique(s,r)){
                JOptionPane.showMessageDialog(null,"Product Found In Cart");
                in5.close();
                out.close();
                return;
            }



            if(checkEnough(r,x)){

                JOptionPane.showMessageDialog(null,"Not enough stock");
                in5.close();
                out.close();
                return;
            }
            while(in5.hasNextLine()){
                String lol = in5.nextLine();
                if(lol.equals(r)){
                    name = lol;
                    price = in5.nextLine();
                    desc = in5.nextLine();
                    stocks = in5.nextLine();
                    sales = in5.nextLine();
                    review = in5.nextLine();
                    out.println(name);
                    out.println(price);
                    out.println(x);
                    JOptionPane.showMessageDialog(null,name+" added to cart");
                    break;
                }
            }
            in5.close();
            out.close();
        }catch (Exception e){
            System.out.println("dawdda");
        }






    }

    public boolean checkUnique(String x,String r){

        try {
            Scanner in = new Scanner(new FileInputStream(x+"cart.txt"));
            while(in.hasNextLine()){
                String name = in.nextLine();
                String email = in.nextLine();
                String pass = in.nextLine();
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

    public boolean checkUniqueSell(String x,String r){

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(x+".txt",true));
            Scanner in = new Scanner(new FileInputStream(x+".txt"));
            while(in.hasNextLine()){
                String name = in.nextLine();
                if(r.equals(name)){
                    out.close();
                    in.close();
                    return true;
                }
            }
            out.close();
            in.close();
        }catch (Exception e){
            System.out.println("babi");
        }
        return false;
    }

    public boolean checkEnough(String r,int x){
        String temp,temp1,temp2,temp3,temp4;
        int y;
        try {
            Scanner in = new Scanner(new FileInputStream("allproducts.txt"));
            while(in.hasNextLine()){
                String name = in.nextLine();

                if(r.equals(name)){
                    temp = in.nextLine();
                    temp1 = in.nextLine();
                    temp2 = in.nextLine();
                    temp3 = in.nextLine();
                    temp4 = in.nextLine();
                    y = Integer.parseInt(temp2);
                    if(x > y) {
                        in.close();
                        return true;
                    }else
                        in.close();
                    return false;
                }
            }
            in.close();
        }catch (Exception e){
            System.out.println("Error");
        }
        return false;
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

    public boolean checkUniquefav(String x,String r){

        try {
            Scanner in = new Scanner(new FileInputStream(x+"fav.txt"));
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

    public void addFavourite(String s,String r){

        try{
            PrintWriter out = new PrintWriter(new FileOutputStream(s+"fav.txt",true));
            if(checkUniquefav(s,r)){
                JOptionPane.showMessageDialog(null,"Product found in favourite");

                out.close();
                return;
            }
            JOptionPane.showMessageDialog(null,r+" added to favourites");
            out.println(r);
            out.close();

        }catch (Exception e){
            System.out.println("Error");
        }
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
            round(before,2);
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
                    out.printf("%.2f",before);
                }else
                    out.println(lol);
            }

            JOptionPane.showMessageDialog(null,"You successfully Purchased\nNew Balance : RM "+before);



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
                        out1.printf("%.2f",tootal);
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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
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

    public void EmptyCart(String s){
        try {
            PrintWriter out = new PrintWriter(s+"cart.txt");
            out.close();
        }catch (Exception e){
            System.out.println("error");
        }
    }
}
