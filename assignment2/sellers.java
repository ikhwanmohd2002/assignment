package ikhwan;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class sellers {

    public String product;

    public void displayall(){
        int a = 0;

        try{

            Scanner in = new Scanner(new FileInputStream("allproducts.txt"));
            while(in.hasNextLine()){
                String lol = in.nextLine();
                if(a == 0){

                }
                else if(a == 1){
                    System.out.println("Product name: "+lol);
                }else if(a == 2){
                    System.out.println("Price: RM"+lol);
                }else if(a == 3){
                    System.out.println("Description: "+lol);
                }else if(a==4){
                    System.out.println("Stock : "+lol);
                }else if(a==5){
                    System.out.println("Sales volume: "+lol);
                }else if (a==6)
                    System.out.println("Latest Review: "+lol);
                else {
                    System.out.println("\nProduct name: "+lol);
                    a = 0;
                }
                a++;


            }
            in.close();
        }catch (Exception e){
            System.out.println("Error");
        }

    }

    public void displayallname(){
        int a = 0,b=1;
        String lol;

        try{

            Scanner in = new Scanner(new FileInputStream("allproducts.txt"));
            while(in.hasNextLine()){
                lol = in.nextLine();
                if(a == 0) {
                }
                else if(a == 1){
                    System.out.println(b+"."+lol);
                    b++;

                }else if(a >= 2 && a <=6) {
                }
                else
                    a = 0;

                a++;


            }
            in.close();
        }catch (Exception e){
            System.out.println("Error");
        }

    }

    public void displayname(String r){

        String price,desc,stock,sale,review;
        int x = -1;
        try{

            Scanner in = new Scanner(new FileInputStream("allproducts.txt"));
            while(in.hasNextLine()){
                String lol = in.nextLine();
                if(r.equals(lol)){
                    price = in.nextLine();
                    desc = in.nextLine();
                    stock =in.nextLine();
                    sale=in.nextLine();
                    review=in.nextLine();
                    System.out.println("Product name: " + lol);
                    System.out.println("Price : " + price);
                    System.out.println("Description : " + desc);
                    System.out.println("Stocks : " + stock);
                    System.out.println("Sales : " + sale);
                    System.out.println("Latest Review : " + review);
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

    public void enternew(String s){
        Scanner scan = new Scanner(System.in);
        String lol ="";

        try {
            System.out.print("Enter product category\nA.Clothes\nB.Electronics\nC.Outdoor & Sports\nD.Food\nE.Home & Living\nF.Games & Hobbies\nG.Miscellaneous\nA-G : ");
            String cat= scan.nextLine();
            System.out.print("Enter a new product name: ");
            String productname = scan.nextLine();
            PrintWriter out = new PrintWriter(new FileOutputStream(s+".txt",true));
            PrintWriter out2 = new PrintWriter(new FileOutputStream("allproducts.txt",true));
            Scanner in = new Scanner("allproducts.txt");
            if(checkUnique(productname)){
                System.out.println("Product name taken");
                out.close();
                out2.close();
                in.close();
                return;
            }

            out.println(cat.toLowerCase());
            out2.println(cat.toLowerCase());
            out.println(productname);
            out2.println(productname);
            System.out.print("Enter product price: ");
            double productprice = scan.nextInt();
            out.println(productprice);
            out2.println(productprice);
            scan.nextLine();
            System.out.print("Enter product description: ");
            String productdesc = scan.nextLine();
            out.println(productdesc);
            out2.println(productdesc);
            System.out.print("Enter amount of stocks: ");
            int productstock = scan.nextInt();
            out.println(productstock);
            out2.println(productstock);
            out.println(0);
            out2.println(0);
            out.println("-");
            out2.println("-");
            in.close();
            out2.close();
            out.close();

        }catch (Exception e){
            System.out.println("Error");
        }
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

    public void updateproduct(String s,String r){
        Scanner scan = new Scanner(System.in);
        String lol,product,price = "",description="",stocks="",sales="",review="",cat="";
        int b = 0,check =-1 ,c,d;

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
            System.out.print("Update\n1.Price\n2.Description\n3.Add Stock\n1-3 : ");
            b = scan.nextInt();
            scan.nextLine();
            if(b==3)
                System.out.print("add :");
            else if (b>3) {
                System.out.println("Wrong Input");
                return;
            }else
            System.out.print("change to : ");
            changeto = scan.nextLine();


            switch(b){
                case 1: {
                    if(!isNumeric(changeto)) {
                        System.out.println("Wrong Input");
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
                    break;
                }
                case 2:{
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


                    break;
                }
                case 3:{
                    if(!isNumeric(changeto)) {
                        System.out.println("Wrong Input");
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

    public void search(String s,int l){

        Scanner scan = new Scanner(System.in);
        customer cust = new customer();
        String name="",price="",desc="",stocks="",sales="",review="";
        String[] ssplit,sssplit;
        System.out.print("Search : ");
        String search = scan.nextLine();
        System.out.print("\n");
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
                            System.out.println(b+". " + name);
                            pronames.add(name);
                            b++;
                        }
                }
            if(pronames.isEmpty()){
                System.out.print("No Product Found");
                in.close();
                return;
            }


            System.out.print("Enter product code (0 to Continue) : ");
            int c = scan.nextInt()-1;
            if(c <= -1 || c >= b-1){
                System.out.println("Wrong Input");
                in.close();
                return;
            }
            String o = pronames.get(c);
            in.close();
            cust.kimi(s,o,l);


        }catch (Exception e){
            System.out.println("Error");
        }

    }

    public void EditDelete(String s,String r){

        Scanner scan = new Scanner(System.in);
        System.out.print("1.Edit Product\n2.Delete Product\n3.Go Back\nEnter 1-3: ");
        int x = scan.nextInt();
        switch(x){
            case 1:{
                updateproduct(s,r);
                break;
            }
            case 2:{
                deleteProduct(s,r);
                break;
            }
            case 3:
                return;
        }
    }

    public void productList(String s){
        Scanner scan = new Scanner(System.in);
        String price,desc,stock,sale,review,name;
        ArrayList<String> pronames = new ArrayList<String>();
        int x = -1,b=1;
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
                    System.out.println(b+". " + name);
                    pronames.add(name);
                    b++;
                    x = 1;

            }
            if(x==-1){
                System.out.println("No Product(s) Found");
                in.close();
                return;
            }
            in.close();
            System.out.print("Enter Product Code: ");
            int c = scan.nextInt()-1;
            String o = pronames.get(c);
            EditDelete(s,o);

        }catch (Exception e){
            System.out.println("Error");
        }

    }

    public void sellerpers(String s){
        int x;
        while(true){
        Scanner scan = new Scanner(System.in);
        System.out.print("1.Enter new product\n2.Update product\n3.Exit\nEnter 1-3: ");
        x = scan.nextInt();
        switch(x){
            case 1:{
                enternew(s);
                break;
            }
            case 2:{
               // updateproduct(s);
                break;
            }
            case 3:
                return;
        }
    }}

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

    public int Transactions(String s){
        Scanner scan = new Scanner(System.in);
        customer cust = new customer();
        System.out.print("A.Display Profit\nB.Display History\nC.Go Back Main\nEnter A-C: ");
        char a = scan.next().toLowerCase().charAt(0);
        switch (a){
            case 'a' :{
               cust.displayProfit(s);
                break;
            }
            case 'b':{
                cust.displayTransaction(s);
                break;
            }
            case 'c':{
                return 0;
            }
        }
return 0;
    }



    public int Products(String s){
        Scanner scan = new Scanner(System.in);
        sellers sell = new sellers();
        while(true){
        System.out.print("A.Enter New Product\nB.Product Listings\nC.Go Back\nEnter A-C: ");
        char a = scan.next().toLowerCase().charAt(0);
        switch (a){
            case 'a' :{
                enternew(s);
                return 1;
            }
            case 'b':{
                productList(s);
                break;
            }
            case 'd':{
                return 1;
            }
        }
        return 0;
        }
    }

    public int Seller(String s){
        customer cust = new customer();
        Scanner scan = new Scanner(System.in);
        sellers sell = new sellers();
        while(true){
        System.out.print("1.Go To Products\n2.Go To Transaction\n3.Go Back Main\nEnter 1-3: ");
        int x = scan.nextInt();
        switch (x){
            case 1 :{
                if(Products(s)==1)
                break;
                else
                    continue;
            }
            case 2:{
                if(Transactions(s)==1)
                break;
                else
                    continue;
            }
            case 3:{
                return 1;
            }
        }
        return 1;
    }}


}



