package gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class SearchFunction extends JFrame{
    private JPanel mainPanel;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JList list1;
    private JButton searchButton;
    private JButton displayTopProductsButton;
    private JButton goBackButton;

    public SearchFunction(String s,String e,String p,String a,int pay){
        DefaultListModel<String> model = new DefaultListModel<>();
        this.setTitle("Search");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        textField1.setText("Search Products Here");
        comboBox1.addItem("Clothes");
        comboBox1.addItem("Electronics");
        comboBox1.addItem("Outdoor & Sports");
        comboBox1.addItem("Food");
        comboBox1.addItem("Home & Living");
        comboBox1.addItem("Games & Hobbies");
        comboBox1.addItem("Miscellaneous");
        list1.setModel(model);

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent cat) {



                int x = 8;
                String r="";
                x= comboBox1.getSelectedIndex();
                if(x==0){
                    System.out.println("a");
                    r= "a";
                }else if(x==1){
                    r ="b";
                }
                else if(x==2){
                    r ="c";
                }
                else if(x==3){
                    r ="d";
                }
                else if(x==4){
                    r ="e";
                }
                else if(x==5){
                    r ="f";
                }
                else if(x==6){
                    r ="g";
                }else{
                    return;
                }
                model.removeAllElements();
                String[] pop = displayCat(r.toLowerCase(Locale.ROOT));
                for(int i = 0; i < pop.length;i++) {
                    model.addElement(pop[i]);
                }
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent dad) {
                if (list1.getValueIsAdjusting()) return;
                String r = list1.getSelectedValue().toString();
                dispose();
                JFrame goo = new kimi(s,e,p,a,r,pay);
                goo.setVisible(true);


            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent seeach) {
                String r = textField1.getText();
                model.removeAllElements();
                String[] pop = search(r);
                for(int i = 0; i < pop.length;i++) {
                    model.addElement(pop[i]);
                }

            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dwa) {
                dispose();
                JFrame wow = new Customer(s,e,p,a,pay);
                wow.setVisible(true);
            }
        });
        displayTopProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dwad) {
                model.removeAllElements();
                String[] pop = topProducts(s);
                for(int i = pop.length-1; i >= pop.length-10;i--) {
                    model.addElement(pop[i]);
                }
            }
        });
    }



    public String[] displayCat(String r){
        Scanner scan = new Scanner(System.in);
        String[] data = new String[numbCat(r)];
        String price,desc,stock,sale,review,name;
        ArrayList<String> pronames = new ArrayList<String>();


        int x = -1,b=0;
        try{

            Scanner in = new Scanner(new FileInputStream("allproducts.txt"));
            while(in.hasNextLine()){
                String lol = in.nextLine();
                if(r.equalsIgnoreCase(lol)){
                    name = in.nextLine();
                    price = in.nextLine();
                    desc = in.nextLine();
                    stock =in.nextLine();
                    sale=in.nextLine();
                    review=in.nextLine();
                    data[b] = name;
                    b++;
                    x = 1;
                }
            }
            if(x==-1){
                System.out.println("No Product(s) Found");
                in.close();

            }

            in.close();
            return data;
        }catch (Exception e){
            System.out.println("Error");
        }
        return data;
    }

    public int numbCat(String r){
        Scanner scan = new Scanner(System.in);
        String price,desc,stock,sale,review,name;
        ArrayList<String> pronames = new ArrayList<String>();

        int x = -1,b=0;
        try{

            Scanner in = new Scanner(new FileInputStream("allproducts.txt"));
            while(in.hasNextLine()){
                String lol = in.nextLine();
                if(r.equalsIgnoreCase(lol)){
                    name = in.nextLine();
                    price = in.nextLine();
                    desc = in.nextLine();
                    stock =in.nextLine();
                    sale=in.nextLine();
                    review=in.nextLine();
                    pronames.add(name);
                    b++;
                    x = 1;
                }
            }
            if(x==-1){
                System.out.println("No Product(s) Found");
                in.close();

            }

            in.close();
            return b;
        }catch (Exception e){
            System.out.println("Error");
        }
        return 0;
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

    public String[] search(String search){
        String[] empty = {""};
        Scanner scan = new Scanner(System.in);
        String name="",price="",desc="",stocks="",sales="",review="";
        String[] ssplit,sssplit;
        ssplit = search.split(" ");
        int b =1;
        ArrayList<String> pronames = new ArrayList<String>();
        try{
            Scanner in = new Scanner(new FileInputStream("allproducts.txt"));



            while(in.hasNextLine()) {
                String lol = in.nextLine();
                sssplit = lol.split(" ");


                if (checkKey(ssplit, sssplit)) {
                    name = lol;
                    price = in.nextLine();
                    desc = in.nextLine();
                    stocks = in.nextLine();
                    sales = in.nextLine();
                    review = in.nextLine();
                    pronames.add(name);
                    b++;
                }
            }
            String[] data = new String[pronames.size()];


            if(pronames.isEmpty()){
                JOptionPane.showMessageDialog(null,"No product found");
                in.close();
                return data;
            }

            for(int i = 0;i<data.length;i++){
                data[i]=pronames.get(i);
            }


            in.close();

            return data;
        }catch (Exception e){
            System.out.println("Error");
        }
    return empty;
    }

    public boolean checkKey(String[] x,String[] y){
        for (int i = 0; i < x.length; i++)
        {
            for (int j = 0; j < y.length; j++)
            {
                if(x[i].equalsIgnoreCase(y[j]))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public String[] topProducts(String s){
        Scanner scan = new Scanner(System.in);
        String[] dad = {""} ;
        int m = 0,n=0,temp,y=1;
        String temp1;
        ArrayList<String> pronames = new ArrayList<String>();
        try {
            File pro = new File("allproducts.txt");


            Scanner in = new Scanner(new FileInputStream("allproducts.txt"));
            Scanner in1 = new Scanner(new FileInputStream("allproducts.txt"));
            while(in.hasNextLine()) {
                String lol = in.nextLine();
                String lol1 = in.nextLine();
                String lol2 = in.nextLine();
                String lol3 = in.nextLine();
                String lol4 = in.nextLine();
                String lol5 = in.nextLine();
                String lol6 = in.nextLine();
                m++;
            }

            String[] name = new String[m];
            int[] sales = new int[m];
            String[] data = new String[m];
            while(in1.hasNextLine()) {
                String lol = in1.nextLine();
                String lol1 = in1.nextLine();
                String lol2 = in1.nextLine();
                String lol3 = in1.nextLine();
                String lol4 = in1.nextLine();
                String lol5 = in1.nextLine();
                String lol6 = in1.nextLine();
                name[n] = lol1;
                sales[n] = Integer.parseInt(lol5);
                n++;
            }
            for (int i = 0; i <sales.length; i++) {
                for (int j = i+1; j <sales.length; j++) {
                    if(sales[i] >sales[j]) {
                        temp = sales[i];
                        temp1 = name[i];
                        name[i] = name[j];
                        name[j]=temp1;
                        sales[i] = sales[j];
                        sales[j] = temp;
                    }
                }
            }



            in.close();
            in1.close();
            return name;





        }catch (Exception e){
            System.out.println("Not Enough Products In Store");
        }
        return dad;
    }
}
