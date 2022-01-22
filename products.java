package gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class products extends JFrame{
    private JPanel mainPanel;
    private JTextField name;
    private JTextField price;
    private JTextField desc;
    private JTextField stocks;
    private JTextField sales;
    private JTextField review;
    private JButton enterNewButton;
    private JButton goBackButton;
    private JList list1;
    private JButton deleteProductButton;

    private JComboBox comboBox1;
    private int y =-1;

    public products(String s,String e,String p ,String a,int pay){
        DefaultListModel<String> model = new DefaultListModel<>();
        this.setTitle("Products");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        comboBox1.addItem("Update Item Price");
        comboBox1.addItem("Update Item Description");
        comboBox1.addItem("Update Item Stocks");
        list1.removeAll();
        String[] pop = productList(s);
        list1.setModel(model);
        for(int i = 0; i < pop.length;i++) {
            model.addElement(pop[i]);
        }
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dawd) {
                dispose();
                JFrame daw = new Seller(s,e,p,a,pay);
                daw.setVisible(true);

            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent dawd) {
                if(list1.isSelectionEmpty()){
                    return;
                }
                String r = list1.getSelectedValue().toString();
                y = list1.getSelectedIndex();
                displayname(r);
            }
        });


        name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                price.requestFocus();

            }
        });
        price.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desc.requestFocus();

            }
        });
        desc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stocks.requestFocus();
            }
        });
        enterNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent enterr) {

                model.addElement(enternew(s));
                }

        });
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dawdwa) {
                if(y==-1){
                    JOptionPane.showMessageDialog(null,"No Product Selected");
                    return;
                }
                String r = name.getText();
                deleteProduct(s,r);
                name.setText("");
                price.setText("");
                desc.setText("");
                stocks.setText("");
                sales.setText("");
                review.setText("");
                model.remove(y);

            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent faf) {
                if(y==-1){
                    JOptionPane.showMessageDialog(null,"No Product Selected");
                    return;
                }
                int b = comboBox1.getSelectedIndex();
                String r = name.getText();
                updateproduct(s,r,b);
                displayname(r);
            }
        });
    }

    public String[] productList(String s){
        String[] data = new String[productNumb(s)];
        Scanner scan = new Scanner(System.in);
        String price,desc,stock,sale,review,name;
        ArrayList<String> pronames = new ArrayList<String>();
        int x = -1,b=0;
        try{

            Scanner in = new Scanner(new FileInputStream(s+".txt"));
            while(in.hasNextLine()){
                String lol = in.nextLine();
                name = in.nextLine();
                price = in.nextLine();
                desc = in.nextLine();
                stock =in.nextLine();
                sale=in.nextLine();
                review=in.nextLine();
                pronames.add(name);
                data[b] = name;
                b++;
                x = 1;

            }
            if(x==-1){
                System.out.println("No Product(s) Found");
                in.close();
                return data;
            }
            in.close();



        }catch (Exception e){
            System.out.println("Error");
        }
    return data;
    }

    public int productNumb(String s){
        Scanner scan = new Scanner(System.in);
        String price,desc,stock,sale,review,name;
        ArrayList<String> pronames = new ArrayList<String>();
        int x = -1,b=0;
        try{

            Scanner in = new Scanner(new FileInputStream(s+".txt"));
            while(in.hasNextLine()){
                String lol = in.nextLine();
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
            if(x==-1){
                System.out.println("No Product(s) Found");
                in.close();
                return b;
            }
            in.close();




        }catch (Exception e){
            System.out.println("Error");
        }
    return b;
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
                    name.setText(lol);
                    price.setText(prices);
                    desc.setText(descs);
                    stocks.setText(stock);
                    sales.setText(sale);
                    review.setText(reviews);
                    x = 1;
                }


            }
            if(x==-1){
                System.out.println("Product Not Found");
            }
            in.close();
        }catch (Exception e){
            System.out.println("Error");
        }
    }

    public String enternew(String s){
        Scanner scan = new Scanner(System.in);
        String lol ="";
        String productname = "";

        try {

            String cat = JOptionPane.showInputDialog("Enter product category,A.Clothes,B.Electronics,C.Outdoor & Sports,D.Food,E.Home & Living,F.Games & Hobbies,G.Miscellaneous").toLowerCase(Locale.ROOT);

            if(!cat.equals("a") && !cat.equals("b") && !cat.equals("c")&& !cat.equals("d")&& !cat.equals("e")&& !cat.equals("f")&& !cat.equals("g")){
                JOptionPane.showMessageDialog(null,"Wrong Input");
                name.setText("");
                price.setText("");
                desc.setText("");
                sales.setText("");
                stocks.setText("");
                review.setText("");

                return productname;
            }

            productname = JOptionPane.showInputDialog("Enter Product Name");
            if(checkUnique(productname)){
                JOptionPane.showMessageDialog(null,"Product Name Taken");
                return productname;
            }

            PrintWriter out = new PrintWriter(new FileOutputStream(s+".txt",true));
            PrintWriter out2 = new PrintWriter(new FileOutputStream("allproducts.txt",true));
            Scanner in = new Scanner("allproducts.txt");



            String proice = JOptionPane.showInputDialog("Enter product price");
            if(!isNumeric(proice)){
                JOptionPane.showMessageDialog(null,"Wrong Input");
                return productname;
            }

            double productprice = Double.parseDouble(proice);


            String productdesc = JOptionPane.showInputDialog("Enter product description");


            String stoick = JOptionPane.showInputDialog("Enter product stock");
            if(!isNumeric(stoick)){
                JOptionPane.showMessageDialog(null,"Wrong Input");
                return productname;
            }
            int productstock = Integer.parseInt(stoick);
            out.println(cat.toLowerCase());
            out2.println(cat.toLowerCase());
            out.println(productname);
            out2.println(productname);
            out.println(productprice);
            out2.println(productprice);
            out.println(productdesc);
            out2.println(productdesc);
            out.println(productstock);
            out2.println(productstock);
            out.println(0);
            out2.println(0);
            out.println("-");
            out2.println("-");
            in.close();
            out2.close();
            out.close();
            JOptionPane.showMessageDialog(null,productname+" added to Omazon");
        }catch (Exception e){
            System.out.println("Error");
        }
        return productname;
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

    public boolean checkUnique(String x){

        try {
            Scanner in = new Scanner(new FileInputStream("allproducts.txt"));
            while(in.hasNextLine()){
                String name = in.nextLine();
                if(x.equals(name)){
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

    public void deleteProduct(String s,String r) {
        Scanner scan = new Scanner(System.in);
        String lol, product, price = "", description = "", stocks = "", sales = "", review = "", cat = "";
        int b = 0, check = -1, c, d;

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream("temp.txt"));
            PrintWriter out1 = new PrintWriter(new FileOutputStream("temp1.txt"));
            PrintWriter out2 = new PrintWriter(new FileOutputStream("temp2.txt"));
            PrintWriter out3 = new PrintWriter(new FileOutputStream(s+"cart.txt",true));
            Scanner in = new Scanner(new FileInputStream(s + ".txt"));
            Scanner in2 = new Scanner(new FileInputStream(s + ".txt"));
            Scanner in3 = new Scanner(new FileInputStream("allproducts.txt"));
            Scanner in4 = new Scanner(new FileInputStream(s + "cart.txt"));
            File file = new File(s + ".txt");
            File newfile = new File("temp.txt");
            File file1 = new File("allproducts.txt");
            File newfile1 = new File("temp1.txt");
            File file2 = new File(s + "cart.txt");
            File newfile2 = new File("temp2.txt");

            product = r;

            while (in2.hasNextLine()) {
                String me = in2.nextLine();
                if (me.equals(product)) {
                    check = 0;
                    break;
                } else
                    check = -1;
            }

            if (check == -1) {
                System.out.println("No product found");
                in.close();
                in2.close();
                in3.close();
                in4.close();
                out.close();
                out1.close();
                out2.close();
                out3.close();
                return;
            }


            while (in.hasNextLine()) {
                cat = in.nextLine();
                lol = in.nextLine();
                price = in.nextLine();
                description = in.nextLine();
                stocks = in.nextLine();
                sales = in.nextLine();
                review = in.nextLine();
                if (lol.equals(product)) {
                } else {
                    out.println(cat);
                    out.println(lol);
                    out.println(price);
                    out.println(description);
                    out.println(stocks);
                    out.println(sales);
                    out.println(review);

                }}
            while (in3.hasNextLine()) {
                cat = in3.nextLine();
                lol = in3.nextLine();
                price = in3.nextLine();
                description = in3.nextLine();
                stocks = in3.nextLine();
                sales = in3.nextLine();
                review = in3.nextLine();
                if (lol.equals(product)) {
                } else {
                    out1.println(cat);
                    out1.println(lol);
                    out1.println(price);
                    out1.println(description);
                    out1.println(stocks);
                    out1.println(sales);
                    out1.println(review);

                }
            }

            while (in4.hasNextLine()) {
                cat = in4.nextLine();
                lol = in4.nextLine();
                price = in4.nextLine();
                description = in4.nextLine();
                stocks = in4.nextLine();
                sales = in4.nextLine();
                review = in4.nextLine();
                if (lol.equals(product)) {
                } else {
                    out2.println(cat);
                    out2.println(lol);
                    out2.println(price);
                    out2.println(description);
                    out2.println(stocks);
                    out2.println(sales);
                    out2.println(review);

                }
            }
            JOptionPane.showMessageDialog(null,"Product Succesfully Deleted");
            in.close();
            in2.close();
            in3.close();
            in4.close();
            out.close();
            out1.close();
            out2.close();
            out3.close();
            file.delete();
            newfile.renameTo(file);
            file1.delete();
            newfile1.renameTo(file1);
            file2.delete();
            newfile2.renameTo(file2);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void updateproduct(String s,String r,int b){
        Scanner scan = new Scanner(System.in);
        String lol,product,price = "",description="",stocks="",sales="",review="",cat="";
        int check =-1 ,c,d;

        try{
            PrintWriter out = new PrintWriter(new FileOutputStream("temp.txt"));
            PrintWriter out1 = new PrintWriter(new FileOutputStream("temp1.txt"));
            PrintWriter out2 = new PrintWriter(new FileOutputStream("temp2.txt"));
            Scanner in = new Scanner(new FileInputStream(s+".txt"));
            Scanner in2 = new Scanner(new FileInputStream(s+".txt"));
            Scanner in3 = new Scanner(new FileInputStream("allproducts.txt"));
            Scanner in4 = new Scanner(new FileInputStream(s+"cart.txt"));
            File file = new File(s+".txt");
            File newfile = new File("temp.txt");
            File file1 = new File("allproducts.txt");
            File newfile1 = new File("temp1.txt");
            File file2 = new File(s+"cart.txt");
            File newfile2 = new File("temp2.txt");


            product = r;

            while(in2.hasNextLine()){
                String me = in2.nextLine();
                if(me.equals(product)){
                    check = 0;
                    break;
                }
                else
                    check = -1;
            }

            if(check == -1) {
                System.out.println("No product found");
                return;
            }

            String changeto = "";

            if(b==2){
                changeto = JOptionPane.showInputDialog("Add Stock ");
            }else
            changeto = JOptionPane.showInputDialog("Change To ");


            switch(b){
                case 0: {
                    if(!isNumeric(changeto)) {
                        JOptionPane.showMessageDialog(null,"Wrong Input");
                        in.close();
                        in2.close();
                        in3.close();
                        in4.close();
                        out.close();
                        out1.close();
                        out2.close();
                        return;
                    }
                    while(in.hasNextLine()){
                        lol = in.nextLine();
                        if(lol.equals(product)) {
                            price = in.nextLine();
                            description = in.nextLine();
                            stocks = in.nextLine();
                            sales = in.nextLine();
                            review=in.nextLine();
                            out.println(lol);
                            out.println(changeto);
                            out.println(description);
                            out.println(stocks);
                            out.println(sales);
                            out.println(review);
                        }else
                            out.println(lol);
                    }
                    while(in3.hasNextLine()){
                        lol = in3.nextLine();
                        if(lol.equals(product)) {
                            price = in3.nextLine();
                            description = in3.nextLine();
                            stocks = in3.nextLine();
                            sales = in3.nextLine();
                            review=in3.nextLine();
                            out1.println(lol);
                            out1.println(changeto);
                            out1.println(description);
                            out1.println(stocks);
                            out1.println(sales);
                            out1.println(review);
                        }else
                            out1.println(lol);
                    }

                    while(in4.hasNextLine()){
                        lol = in4.nextLine();
                        if(lol.equals(product)) {
                            price = in4.nextLine();
                            out2.println(lol);
                            out2.println(changeto);
                        }else
                            out2.println(lol);
                    }
                    JOptionPane.showMessageDialog(null,"You have updated product");
                    break;
                }
                case 1:{
                    while(in.hasNextLine()){
                        lol = in.nextLine();
                        if(lol.equals(product)) {
                            price = in.nextLine();
                            description = in.nextLine();
                            stocks = in.nextLine();
                            sales = in.nextLine();
                            review=in.nextLine();
                            out.println(lol);
                            out.println(price);
                            out.println(changeto);
                            out.println(stocks);
                            out.println(sales);
                            out.println(review);
                        }else
                            out.println(lol);
                    }

                    while(in3.hasNextLine()){
                        lol = in3.nextLine();
                        if(lol.equals(product)) {
                            price = in3.nextLine();
                            description = in3.nextLine();
                            stocks = in3.nextLine();
                            sales = in3.nextLine();
                            review=in3.nextLine();
                            out1.println(lol);
                            out1.println(price);
                            out1.println(changeto);
                            out1.println(stocks);
                            out1.println(sales);
                            out1.println(review);
                        }else
                            out1.println(lol);
                    }

                    JOptionPane.showMessageDialog(null,"You have updated product");
                    break;
                }
                case 2:{
                    if(!isNumeric(changeto)) {
                        JOptionPane.showMessageDialog(null,"Wrong Input");
                        return;
                    }
                    while(in.hasNextLine()){
                        lol = in.nextLine();
                        if(lol.equals(product)) {
                            price = in.nextLine();
                            description = in.nextLine();
                            stocks = in.nextLine();
                            sales = in.nextLine();
                            review=in.nextLine();
                            c = Integer.parseInt(stocks);
                            d= Integer.parseInt(changeto);
                            out.println(lol);
                            out.println(price);
                            out.println(description);
                            out.println(c+d);
                            out.println(sales);
                            out.println(review);
                        }else
                            out.println(lol);
                    }
                    while(in3.hasNextLine()){
                        lol = in3.nextLine();
                        if(lol.equals(product)) {
                            price = in3.nextLine();
                            description = in3.nextLine();
                            stocks = in3.nextLine();
                            sales = in3.nextLine();
                            review=in3.nextLine();
                            c = Integer.parseInt(stocks);
                            d= Integer.parseInt(changeto);
                            out1.println(lol);
                            out1.println(price);
                            out1.println(description);
                            out1.println(c+d);
                            out1.println(sales);
                            out1.println(review);
                        }else
                            out1.println(lol);
                    }
                    JOptionPane.showMessageDialog(null,"You have updated product");
                    break;
                }
            }
            in.close();
            in2.close();
            in3.close();
            in4.close();
            out.close();
            out1.close();
            out2.close();
            file.delete();
            newfile.renameTo(file);
            file1.delete();
            newfile1.renameTo(file1);
            file2.delete();
            newfile2.renameTo(file2);
        }catch (Exception e){
            System.out.println("Error");
        }



    }
}
