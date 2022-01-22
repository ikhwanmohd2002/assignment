package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Transaction extends JFrame{
    private JPanel mainPanel;
    private JTable table1;
    private JButton goBackButton;
    private JButton displayTotalHistoryButton;
    private JButton displayProfitButton;
    private JTextField total;
    private JLabel label;

    public Transaction(String s,String e,String p,String a,int pay){
        this.setTitle("Transaction List");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        String[][] pop = displayProfit(s);
        table1.setModel(new DefaultTableModel(pop,new String[]{"Product Name","Quantity","Price"}));
        total.setText("RM "+totalProfit(s));
        displayTotalHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dawd) {
                String[][] pop = displayTransaction(s);
                table1.setModel(new DefaultTableModel(pop,new String[]{"Product Name","Quantity","Price"}));
                total.setText("RM "+totalTransaction(s));
            }
        });
        displayProfitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[][] pop = displayProfit(s);
                table1.setModel(new DefaultTableModel(pop,new String[]{"Product Name","Quantity","Price"}));
                total.setText("RM "+totalProfit(s));
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dawd) {
                dispose();
                JFrame daw = new Seller(s,e,p,a,pay);
                daw.setVisible(true);
            }
        });
    }

    public String[][] displayProfit(String s){
        ArrayList<String> proname = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        String[][] data = new String[numbProfit(s)][3];
        int b =0;
        double price,total=0;
        try{
            Scanner in1 = new Scanner(new FileInputStream(s+"trans.txt"));

            while(in1.hasNextLine()){
                String name = in1.nextLine();
                String quantity = in1.nextLine();
                String amount = in1.nextLine();
                data[b][0] = name;
                data[b][1] = quantity;
                data[b][2] = "RM"+amount;
                price = Double.parseDouble(amount);
                total += price;
                b++;
            }


            in1.close();
        }catch (Exception e){
            System.out.println("Empty Bought List");
        }
        return data;
    }

    public int numbProfit(String s){
        ArrayList<String> proname = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        int b =0;
        double price,total=0;
        try{
            Scanner in1 = new Scanner(new FileInputStream(s+"trans.txt"));
            while(in1.hasNextLine()){
                String name = in1.nextLine();
                String quantity = in1.nextLine();
                String amount = in1.nextLine();
                price = Double.parseDouble(amount);
                total += price;
                b++;
            }


            in1.close();
        }catch (Exception e){
            System.out.println("Empty Bought List");
        }
        return b;
    }

    public double totalProfit(String s){
        ArrayList<String> proname = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        int b =0;
        double price,total=0;
        try{
            Scanner in1 = new Scanner(new FileInputStream(s+"trans.txt"));
            while(in1.hasNextLine()){
                String name = in1.nextLine();
                String quantity = in1.nextLine();
                String amount = in1.nextLine();
                price = Double.parseDouble(amount);
                total += price;
                b++;
            }


            in1.close();
        }catch (Exception e){
            System.out.println("Empty Bought List");
        }
        return total;
    }

    public String[][] displayTransaction(String s){
        String name,quantity,amount;
        double price,total=0;
        int b =0;
        String[][] data = new String[numbTransaction(s)][3];
        try{
            PrintWriter out = new PrintWriter(new FileOutputStream(s+"cred.txt",true));
            PrintWriter out1 = new PrintWriter(new FileOutputStream(s+"trans.txt",true));
            Scanner in = new Scanner(new FileInputStream(s+"cred.txt"));
            Scanner in1 = new Scanner(new FileInputStream(s+"trans.txt"));
            while(in1.hasNextLine()){
                name = in1.nextLine();
                quantity = in1.nextLine();
                amount = in1.nextLine();
                data[b][0] = name;
                data[b][1] = quantity;
                data[b][2] = "RM"+amount;
                price = Double.parseDouble(amount);
                total += price;
                b++;
            }
            while(in.hasNextLine()){
                name = in.nextLine();
                quantity = in.nextLine();
                amount = in.nextLine();
                data[b][0] = name;
                data[b][1] = quantity;
                data[b][2] = "RM"+amount;
                price = Double.parseDouble(amount);
                total -= price;
                b++;
            }
            in.close();
            in1.close();
            out.close();
            out1.close();
        }catch (Exception e){
            System.out.println("Error");
        }
        return data;
    }

    public int numbTransaction(String s){
        String name,quantity,amount;
        double price,total=0;
        int b =0;
        try{
            PrintWriter out = new PrintWriter(new FileOutputStream(s+"cred.txt",true));
            PrintWriter out1 = new PrintWriter(new FileOutputStream(s+"trans.txt",true));
            Scanner in = new Scanner(new FileInputStream(s+"cred.txt"));
            Scanner in1 = new Scanner(new FileInputStream(s+"trans.txt"));
            while(in1.hasNextLine()){
                name = in1.nextLine();
                quantity = in1.nextLine();
                amount = in1.nextLine();
                price = Double.parseDouble(amount);
                total += price;
                b++;
            }
            while(in.hasNextLine()){
                name = in.nextLine();
                quantity = in.nextLine();
                amount = in.nextLine();
                price = Double.parseDouble(amount);
                total -= price;
                b++;
            }
            in.close();
            in1.close();
            out.close();
            out1.close();
        }catch (Exception e){
            System.out.println("Error");
        }
        return b;
    }

    public double totalTransaction(String s){
        String name,quantity,amount;
        double price,total=0;
        int b =1;
        try{
            PrintWriter out = new PrintWriter(new FileOutputStream(s+"cred.txt",true));
            PrintWriter out1 = new PrintWriter(new FileOutputStream(s+"trans.txt",true));
            Scanner in = new Scanner(new FileInputStream(s+"cred.txt"));
            Scanner in1 = new Scanner(new FileInputStream(s+"trans.txt"));
            while(in1.hasNextLine()){
                name = in1.nextLine();
                quantity = in1.nextLine();
                amount = in1.nextLine();

                price = Double.parseDouble(amount);
                total += price;
                b++;
            }
            while(in.hasNextLine()){
                name = in.nextLine();
                quantity = in.nextLine();
                amount = in.nextLine();
                price = Double.parseDouble(amount);
                total -= price;
                b++;
            }

            in.close();
            in1.close();
            out.close();
            out1.close();
        }catch (Exception e){
            System.out.println("Error");
        }
        return total;
    }
}
