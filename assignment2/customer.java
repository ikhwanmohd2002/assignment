package ikhwan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class customer {

    public void balance(String s){
        String lol = "",prevbal="";
        Scanner scan = new Scanner(System.in);
        System.out.print("Top up : ");
        String newbal = scan.nextLine();
        double x,y;
        if(!isNumeric(newbal)){
            System.out.println("Wrong Input");
            return;
        }

        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream("balance.txt",true));
            Scanner input = new Scanner(new FileInputStream("balance.txt"));
            PrintWriter in = new PrintWriter(new FileOutputStream("btemp.txt"));
            File file = new File("balance.txt");
            File newfile = new File("btemp.txt");

            while(input.hasNextLine()){
                lol = input.nextLine();
                if(lol.equals(s)){
                    prevbal = input.nextLine();
                    in.println(lol);
                    x = Double.parseDouble(prevbal);
                    y=Double.parseDouble(newbal);
                    in.println(x+y);
                } else
                    in.println(lol);

            }
            pw.close();
            in.close();
            input.close();
            file.delete();
            newfile.renameTo(file);
            System.out.print("New ");
            displayBal(s);

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

    public void addCart(String s,String r){
        Scanner scan = new Scanner(System.in);
        try{
            Scanner in5 = new Scanner(new FileInputStream("allproducts.txt"));
            PrintWriter out = new PrintWriter(new FileOutputStream(s+"cart.txt",true));
            String name="",price="",desc="",stocks="",sales="",review="";
            if(checkUniqueSell(s,r)){
                System.out.println("Own product cannot be bought");
                in5.close();
                out.close();
                return;
            }
            if(checkUnique(s,r)){
                System.out.println("Product found in cart");
                in5.close();
                out.close();
                return;
            }


            System.out.print("Quantity : ");
            int x = scan.nextInt();
            if(checkEnough(r,x)){
                System.out.println("Not enough stock");
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
                    System.out.println(name+" Added to Cart");
                    break;
                }
            }
            in5.close();
            out.close();
        }catch (Exception e){
            System.out.println("dawdda");
        }






    }

    public double displayCart(String s){
        String[] pronames = new String[10];
        int[] quan = new int[10];

        double totalp = 0,total=0;
        int tity;
        try{
            Scanner in = new Scanner(new FileInputStream(s+"cart.txt"));
            System.out.println("Name\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPrice\t\t\t\t\tQuantity");
            while(in.hasNextLine()){
                String name = in.nextLine();
                String price = in.nextLine();
                String quantity = in.nextLine();
                System.out.printf("%-72sRM%-28s%-20s\n",name,price,quantity);
                total = Double.parseDouble(price);
                tity = Integer.parseInt(quantity);
                totalp += total*tity;


            }
            System.out.println("\nTotal Price : RM"+totalp);
            in.close();
        }catch (Exception e){
            System.out.println("Cart Empty");
        }
        return totalp;
    }

    public void buyNow(String s,int u){
        double price = displayCart(s);
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
            File delete = new File(s+"cart.txt");
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

            if(insertPaymentPass(u))
                return;


            if(checker==-1){
                System.out.println("User Not Found");
                return ;
            }

            before = before - price;
            if(before < 0){
                System.out.println("Insufficient balance");
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
            System.out.println("You have succesfully purchased\nBalance : RM"+before);



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

    public void buyNowItem(String s,String r,int k){
        addCart(s,r);
        buyNow(s,k);
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
            System.out.println(file1.delete());
            file1.delete();
            newfile1.renameTo(file1);








        }catch (Exception e){
            System.out.println("Error");
        }
    }

    public void displayBal(String s){

        try{
            Scanner in = new Scanner(new FileInputStream("balance.txt"));
            while(in.hasNextLine()){
                String name = in.nextLine();
                if(name.equals(s)){
                    String balance = in.nextLine();
                    System.out.println("Balance : "+balance);
                }
            }

            in.close();
        }catch (Exception e){
            System.out.println("Balance Error");
        }
    }

    public void combineBal(String s){
        Scanner scan = new Scanner(System.in);
        System.out.print("A.Check Balance\nB.Top Up Balance\nC.Go Back Main\nEnter A-C: ");
        char a = scan.next().toLowerCase().charAt(0);
        switch (a){
            case 'a' :{
                displayBal(s);
                break;
            }
            case 'b':{
                balance(s);
                break;
            }
            case 'c':{
                return;
            }
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

    public boolean checkUniqueSell(String x,String r){

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(x+".txt",true));
            Scanner in = new Scanner(new FileInputStream(x+".txt"));
            while(in.hasNextLine()){
                String name = in.nextLine();
                if(r.equals(name)){
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
                        out1.println(y+total);
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

    public int Customer(String s,int i){
        Scanner scan = new Scanner(System.in);
        while(true) {
            top3(s, i);
            System.out.print("1.Balance\n2.Go to Cart\n3.Search\n4.Transaction\n5.Display Favourite\n6.Go Back\nEnter 1-6 : ");
            int x = scan.nextInt();
            switch (x){
                case 1:{
                    combineBal(s);
                    break;
                }
                case 2:{
                    Cart(s,i);
                    break;
                }
                case 3:{
                    Search(s,i);
                    break;
                }
                case 4:{
                    Transaction(s);
                    break;
                }
                case 5:{
                    displayFav(s,i);
                    break;
                }
                case 6:{
                    return 1;
                }
            }
        }
    }



    public void kimi(String s,String r,int i){
        Scanner scan = new Scanner(System.in);
        sellers sell = new sellers();
        displayname1(r);
        System.out.print("\n1.Add to Cart\n2.Buy Now\n3.Add to Favourite\n4.Main menu\nEnter 1-4 : ");
        int y = scan.nextInt();

        switch(y){
            case 1:{
                addCart(s,r);
                break;
            }
            case 2:{
                buyNowItem(s,r,i);
                break;
            }
            case 3:{
                addFavourite(s,r);
                break;
            }
            case 4:{
                return;
            }
        }
    }

    public void displayname1(String r){

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

    public void addFavourite(String s,String r){

        try{
            PrintWriter out = new PrintWriter(new FileOutputStream(s+"fav.txt",true));
            if(checkUniquefav(s,r)){
                System.out.println("Product found in favourite");
                out.close();
                return;
            }
            System.out.println(r+" added to Favourites");
            out.println(r);
            out.close();

        }catch (Exception e){
            System.out.println("Error");
        }
    }

    public void displayFav(String s,int payment){
        int a =1,x=-1;
        ArrayList<String> pronames = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        try{
            Scanner in = new Scanner(new FileInputStream(s+"fav.txt"));
            while(in.hasNextLine()){
                String lol = in.nextLine();
                System.out.println(a+"."+lol);
                pronames.add(lol);
                a++;
                x=1;
            }

            if(x==-1){
                System.out.println("No Product(s) Found");
                in.close();
                return;
            }

            System.out.print("Enter Product Code: ");
            int c = scan.nextInt()-1;
            if(c <= -1 || c >= a-1){
                System.out.println("Wrong Input");
                in.close();
                return;
            }
            String o = pronames.get(c);
            kimi(s,o,payment);
        }catch (Exception e){
            System.out.println("Error");
        }
    }

    public void displayTransaction(String s){
        String name,quantity,amount;
        double price,total=0;
        int b =1;
        try{
            PrintWriter out = new PrintWriter(new FileOutputStream(s+"cred.txt",true));
            PrintWriter out1 = new PrintWriter(new FileOutputStream(s+"trans.txt",true));
            Scanner in = new Scanner(new FileInputStream(s+"cred.txt"));
            Scanner in1 = new Scanner(new FileInputStream(s+"trans.txt"));
            System.out.println("Name\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tQuantity\t\t\t\t\tPrice");
            while(in1.hasNextLine()){
                name = in1.nextLine();
                quantity = in1.nextLine();
                amount = in1.nextLine();
                System.out.printf("%d. %-69s%-28s+RM%-20s\n",b,name,quantity,amount);
                price = Double.parseDouble(amount);
                total += price;
                b++;
            }
            while(in.hasNextLine()){
                name = in.nextLine();
                quantity = in.nextLine();
                amount = in.nextLine();
                System.out.printf("%d. %-69s%-28s-RM%-20s\n",b,name,quantity,amount);
                price = Double.parseDouble(amount);
                total -= price;
                b++;
            }
            System.out.println("\nTotal amount : RM"+total);
            in.close();
            in1.close();
            out.close();
            out1.close();
        }catch (Exception e){
            System.out.println("Error");
        }
    }

    public void deleteTemp(){
        File del = new File("temp.txt");
        File deel = new File("temp1.txt");
        File dell = new File("temp2.txt");
        File ddel = new File("temp3.txt");
        File ddd = new File("temp4.txt");
        File eee = new File("temp5.txt");
        File eeee = new File("temp6.txt");
        File uu = new File("temp7.txt");
        File uue = new File("temp8.txt");
        del.delete();
        deel.delete();
        dell.delete();
        ddel.delete();
        ddd.delete();
        eee.delete();
        eeee.delete();
        uu.delete();
        uue.delete();

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
                    System.out.println("No product found");
                    in.close();
                    in3.close();
                    in4.close();
                    out.close();
                    out1.close();
                    out2.close();
                    return;
                }

                String changeto = "";
                System.out.print("Give Review\n1.Yes\n2.No\nEnter 1-2 : ");
                b = scan.nextInt();
                if(b!=1) {
                    in.close();
                    in3.close();
                    in4.close();
                    out.close();
                    out1.close();
                    out2.close();
                    return;
                }
                scan.nextLine();

                System.out.print("Review : ");
                changeto = scan.nextLine();


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

    public void displayCat(String s,int payment){
        Scanner scan = new Scanner(System.in);
        String price,desc,stock,sale,review,name;
        System.out.print("Enter product category\nA.Clothes\nB.Electronics\nC.Outdoor & Sports\nD.Food\nE.Home & Living\nF.Games & Hobbies\nG.Miscellaneous\nA-G : ");
        String r = scan.next();
        ArrayList<String> pronames = new ArrayList<String>();

        int x = -1,b=1;
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
                    System.out.println(b+". " + name);
                    pronames.add(name);
                    b++;
                    x = 1;
                }


            }
            if(x==-1){
                System.out.println("No Product(s) Found");
                in.close();
                return;
            }

            System.out.print("Enter Product Code: ");
            int c = scan.nextInt()-1;
            if(c <= -1 || c >= b-1){
                System.out.println("Wrong Input");
                in.close();
                return;
            }
            String o = pronames.get(c);
            kimi(s,o,payment);
        }catch (Exception e){
            System.out.println("Error");
        }

    }

    public boolean insertPaymentPass(int k){
        Scanner scan = new Scanner(System.in);
        System.out.print("Insert Payment Pass : ");
        int y = scan.nextInt();
        if(k!=y){
            System.out.println("Wrong Payment Pass");
            return true;
        }else
            return false;
    }

    public void displayHistory(String s){
        int b =1;
        try{
            Scanner in = new Scanner(new FileInputStream(s+"cred.txt"));
            System.out.println("Name\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tQuantity\t\t\t\t\tPrice");
            while (in.hasNextLine()){
                String name = in.nextLine();
                String quantity = in.nextLine();
                String price = in.nextLine();
                System.out.printf("%d. %-69s%-28sRM%-20s\n",b,name,quantity,price);
                b++;
            }
            in.close();
        }catch (Exception e){
            System.out.println("Empty History");
        }
    }

    public void top3(String s,int u){
        Scanner scan = new Scanner(System.in);
        int m = 0,n=0,temp,y=1;
        String temp1;
        ArrayList<String> pronames = new ArrayList<String>();
        try {
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
            if(sales.length<=3){
                for(int f = m;f >=0;f--){
                    System.out.println(y+". "+name[f-1]);
                    pronames.add(name[f-1]);
                    y++;
                }
            }else
                for(int f = m ; f >= m-2;f--){
                    System.out.println(y+". "+name[f-1]);
                    pronames.add(name[f-1]);
                    y++;
                }
            System.out.print("Enter product code (0 to Continue) : ");
            int c = scan.nextInt()-1;
            if(c <= -1 || c >= 3){
                System.out.println("Wrong Input");
                in.close();
                in1.close();
                return;
            }
            in.close();
            in1.close();
            String o = pronames.get(c);
            kimi(s,o,u);



        }catch (Exception e){
            System.out.println("Wrong Input");
        }

    }

    public void displayCreditReview(String s){
        ArrayList<String> proname = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        int b =1;
        try{
            Scanner in = new Scanner(new FileInputStream(s+"cred.txt"));
            System.out.println("Name\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tQuantity\t\t\t\t\tPrice");
            while(in.hasNextLine()){
                String name = in.nextLine();
                String quantity = in.nextLine();
                String price = in.nextLine();
                proname.add(name);
                System.out.printf("%d. %-69s%-28sRM%-20s\n",b,name,quantity,price);
                b++;
            }
            System.out.print("\nEnter Product Code To Review (0 To Exit): ");
            int c = scan.nextInt()-1;
            if(c==-1) {
                in.close();
                return;
            }
            String r = proname.get(c);
            in.close();
            giveReview(s,r);
        }catch (Exception e){
            System.out.println("Empty Bought List");
        }
    }

    public void displayProfit(String s){
        ArrayList<String> proname = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        int b =1;
        double price,total=0;
        try{
            Scanner in1 = new Scanner(new FileInputStream(s+"trans.txt"));
            System.out.println("Name\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tQuantity\t\t\t\t\tPrice");
            while(in1.hasNextLine()){
                String name = in1.nextLine();
                String quantity = in1.nextLine();
                String amount = in1.nextLine();
                System.out.printf("%d. %-69s%-28s+RM%-20s\n",b,name,quantity,amount);
                price = Double.parseDouble(amount);
                total += price;
                b++;
            }
            System.out.println("Total Profit : RM"+total);

            in1.close();
        }catch (Exception e){
            System.out.println("Empty Bought List");
        }
    }


    public void Search(String s,int u){
        Scanner scan = new Scanner(System.in);
        sellers sell = new sellers();
        System.out.print("A.Search Bar\nB.Search By Category\nC.Go Back Main\nEnter A-C: ");
        char a = scan.next().toLowerCase().charAt(0);
        switch (a){
            case 'a' :{
                sell.search(s,u);
                break;
            }
            case 'b':{
                displayCat(s,u);
                break;
            }
            case 'c':{
                return;
            }
        }

    }

    public void Cart(String s,int u){
        Scanner scan = new Scanner(System.in);
        System.out.print("A.Display Cart\nB.Check Out\nC.Go Back Main\nEnter A-C: ");
        char a = scan.next().toLowerCase().charAt(0);
        switch (a){
            case 'a' :{
                displayCart(s);
                break;
            }
            case 'b':{
                buyNow(s,u);
                break;
            }
            case 'c':{
                return;
            }
        }
    }

    public void Transaction(String s){
        Scanner scan = new Scanner(System.in);
        System.out.print("A.Display Credit Transaction\nB.Go Back Main\nEnter A-B: ");
        char a = scan.next().toLowerCase().charAt(0);
        switch (a){
            case 'a' :{
                displayCreditReview(s);
                break;
            }

            case 'b':{
                return;
            }
        }
    }

    }





