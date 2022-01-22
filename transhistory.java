package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class transhistory extends JFrame{
    private JPanel mainPanel;
    private JTable table1;
    private JButton goBackButton;

    public transhistory(String s,String e,String p,String a,int pay){
        this.setTitle("Search");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        String[][] pop = displayCreditReview(s);
        table1.setModel(new DefaultTableModel(pop,new String[]{"Product Name","Quantity","Price"}));


        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent dd) {
                super.mouseClicked(dd);

                    JTable jTable= (JTable)dd.getSource();
                    int row = jTable.getSelectedRow();
                    String r = (String)jTable.getValueAt(row, 0);

                giveReview(s,r);

            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dwadw) {
                dispose();
                JFrame lol = new Customer(s,e,p,a,pay);
            }
        });
    }



    public String[][] displayCreditReview(String s){
        ArrayList<String> proname = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        String[][] data = new String[numbCreds(s)][3];
        int b =0;
        try{
            Scanner in = new Scanner(new FileInputStream(s+"cred.txt"));
            while(in.hasNextLine()){
                String name = in.nextLine();
                String quantity = in.nextLine();
                String price = in.nextLine();
                data[b][0] = name;
                data[b][1] = quantity;
                data[b][2] = price;
                proname.add(name);
                b++;
            }


            in.close();

        }catch (Exception e){
            System.out.println("Empty Bought List");
        }
        return data;
    }

    public int numbCreds(String s){
        ArrayList<String> proname = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);

        int b =0;
        try{
            Scanner in = new Scanner(new FileInputStream(s+"cred.txt"));
            while(in.hasNextLine()){
                String name = in.nextLine();
                String quantity = in.nextLine();
                String price = in.nextLine();
                proname.add(name);
                b++;
            }
            in.close();
            return b;


        }catch (Exception e){
            System.out.println("Empty Bought List");
        }
        return b;
    }

    public void giveReview(String s,String r){
        Scanner scan = new Scanner(System.in);
        String lol,product,price = "",description="",stocks="",sales="",review="",cat="";
        int b = 0,check =-1 ,c,d;



        try{

            PrintWriter out = new PrintWriter(new FileOutputStream("temp.txt"));
            PrintWriter out1 = new PrintWriter(new FileOutputStream("temp1.txt"));
            PrintWriter out2 = new PrintWriter(new FileOutputStream("temp2.txt"));
            Scanner in4 = new Scanner(new FileInputStream(s+"cred.txt"));
            Scanner in = new Scanner(new FileInputStream(whichSeller(r)+".txt"));

            Scanner in3 = new Scanner(new FileInputStream("allproducts.txt"));

            File file = new File(whichSeller(r)+".txt");
            File newfile = new File("temp.txt");
            File file1 = new File("allproducts.txt");
            File newfile1 = new File("temp1.txt");




            while(in4.hasNextLine()){
                String me = in4.nextLine();
                if(me.equals(r)){
                    check = 0;
                    break;
                }
                else
                    check = -1;
            }

            if(check == -1) {
                JOptionPane.showMessageDialog(null,"No Product Found");
                in.close();
                in3.close();
                in4.close();
                out.close();
                out1.close();
                out2.close();
                return;
            }

            String changeto = JOptionPane.showInputDialog("Review: ");



            while(in.hasNextLine()){
                lol = in.nextLine();
                if(lol.equals(r)) {
                    price = in.nextLine();
                    description = in.nextLine();
                    stocks = in.nextLine();
                    sales = in.nextLine();
                    review=in.nextLine();
                    out.println(lol);
                    out.println(price);
                    out.println(description);
                    out.println(stocks);
                    out.println(sales);
                    out.println(s+" - "+changeto);
                }else
                    out.println(lol);
            }

            while(in3.hasNextLine()){
                lol = in3.nextLine();
                if(lol.equals(r)) {
                    price = in3.nextLine();
                    description = in3.nextLine();
                    stocks = in3.nextLine();
                    sales = in3.nextLine();
                    review=in3.nextLine();
                    out1.println(lol);
                    out1.println(price);
                    out1.println(description);
                    out1.println(stocks);
                    out1.println(sales);
                    out1.println(s+" - "+changeto);
                }else
                    out1.println(lol);
            }

            JOptionPane.showMessageDialog(null,"You have given a review");

            in.close();
            in3.close();
            in4.close();
            out.close();
            out1.close();
            out2.close();
            file.delete();
            newfile.renameTo(file);
            file1.delete();
            newfile1.renameTo(file1);

        }catch (Exception e){
            System.out.println("No product found");
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
}
