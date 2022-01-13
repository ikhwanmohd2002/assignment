package ikhwan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class register {

    public int a ;
    public String name = "";
    public String email = "";
    public String pass = "";
    public double bal = -1;
    public String temp = "";
    public String add = "";
    public int paymentpass = 0;

    public int  registerandlogin(){

        Scanner scan = new Scanner(System.in);
        int a;

        while(true){
            System.out.print("------------------------------\n~ ~ ~ ~ ~ ~ ~Omazon~ ~ ~ ~ ~ ~\n------------------------------\n");
            System.out.print("1.Register\n2.Login\n3.Exit App\nEnter 1-3: ");

            a = scan.nextInt();

            switch (a){
                case 1:
                    registery();
                    return 0;
                case 2: {
                    if (login() )
                        return 0;
                    else
                        break;
                }
                case 3:{
                    System.out.println("Thank You for using Omazon");
                    return -1;
                }
                default:{
                    System.out.println("Wrong Input");
                    break;
                }
            }
        }
    }

    public void registery(){

        Scanner scan = new Scanner(System.in);
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream("data.txt", true));
            PrintWriter pw = new PrintWriter(new FileOutputStream("balance.txt",true));
            PrintWriter pw2 = new PrintWriter(new FileOutputStream("add.txt",true));
            Scanner in = new Scanner(new FileInputStream("data.txt"));
            System.out.print("Username : ");
            String user = scan.next();

            System.out.print("Email : ");
            String email = scan.next();
            System.out.print("Password : ");
            String pass = scan.next();

            while(in.hasNextLine()){
                String a = in.nextLine();
                if(a.equals(user)){
                    System.out.println("Username has been taken");
                    return;
                }
                if(a.equals(email)){
                    System.out.println("Email has been taken");
                    return;
                }
            }


            out.println(user);
            out.println(email);
            out.println(pass);
            pw2.println(user);
            pw2.println("-");
            pw2.println(0);
            pw.println(user);
            pw.println(0.0);





            in.close();
            pw2.close();
            pw.close();
            out.close();

            loginfirsttime(user,pass);
            PrintWriter out1 = new PrintWriter(new FileOutputStream(user+".txt",true));
            PrintWriter out2 = new PrintWriter(new FileOutputStream(user+"cart.txt",true));
            PrintWriter out3 = new PrintWriter(new FileOutputStream(user+"fav.txt",true));
            PrintWriter out4 = new PrintWriter(new FileOutputStream(user+"trans.txt",true));
            PrintWriter out5 = new PrintWriter(new FileOutputStream(user+"cred.txt",true));
            PrintWriter out6 = new PrintWriter(new FileOutputStream("allproducts.txt"));
            out1.close();
            out2.close();
            out3.close();
            out4.close();
            out5.close();
            out6.close();
            System.out.println("You have successfully registered, Welcome To Omazon "+ user);
        } catch (Exception e) {
            System.out.println("Failed");
        }
    }

    public boolean login(){
        Scanner scan = new Scanner(System.in);

        try {
            Scanner innit = new Scanner(new FileInputStream("data.txt"));
            Scanner in = new Scanner(new FileInputStream("balance.txt"));
            Scanner in2 = new Scanner(new FileInputStream("add.txt"));
            int a = 0;
            int b  =3;

            while (true) {

                System.out.print("Enter name: ");
                String n = scan.next();
                System.out.print("Enter password: ");
                String p = scan.next();


                while (in2.hasNextLine()) {
                    name = in2.nextLine();
                    add = in2.nextLine();
                    temp = in2.nextLine();
                    paymentpass = Integer.parseInt(temp);
                    if (n.equals(name)) {
                        break;
                    }

                }

                while (in.hasNextLine()) {
                    name = in.nextLine();
                    temp = in.nextLine();
                    bal = Double.parseDouble(temp);
                    if (n.equals(name)) {
                        break;
                    }
                }

                while (innit.hasNextLine()) {

                    name = innit.nextLine();
                    email = innit.nextLine();
                    pass = innit.nextLine();

                    if (n.equals(name) && (p.equals(pass))) {

                        System.out.println("You are logged in");
                        innit.close();
                        in.close();
                        in2.close();
                        return true;
                    }

                }


                    System.out.println("Wrong Credentials");
                    innit.close();
                    in2.close();
                    in.close();
                    return false;

            }

        } catch (Exception e) {
            System.out.println("Error");
        }
        return false;
    }

    public int Account(){
        Scanner scan = new Scanner(System.in);
        customer cust = new customer();
        while (true){
        System.out.print("1.Update Account\n2.Update Address/Payment Password\n3.Delete Account\n4.Log Out\n5.Go Back\nEnter 1-5: ");
       int x = scan.nextInt();
            switch (x) {
                case 1: {
                    isWho();
                    changeUserCredentials();
                    break;
                }
                case 2: {
                    isWho();
                    changeAdd();
                    break;
                }
                case 3:{
                    if(deleteacc(name,pass)==1)
                    return 0;
                    else
                        break;
                }
                case 4:{
                    loggout();
                    return 0;
                }
                case 5:{
                    return 1;
                }

            }
        }

    }

    public boolean loginfirsttime(String n,String p){
        Scanner scan = new Scanner(System.in);

        try {
            Scanner innit = new Scanner(new FileInputStream("data.txt"));
            Scanner in = new Scanner(new FileInputStream("balance.txt"));
            Scanner in2 = new Scanner(new FileInputStream("add.txt"));
            int a = 0;
            int b  =3;

            while (true) {



                while (in2.hasNextLine()) {
                    name = in2.nextLine();
                    add = in2.nextLine();
                    temp = in2.nextLine();
                    paymentpass = Integer.parseInt(temp);
                    if (n.equals(name)) {
                        break;
                    }

                }

                while (in.hasNextLine()) {
                    name = in.nextLine();
                    temp = in.nextLine();
                    bal = Double.parseDouble(temp);
                    if (n.equals(name)) {
                        break;
                    }
                }

                while (innit.hasNextLine()) {

                    name = innit.nextLine();
                    email = innit.nextLine();
                    pass = innit.nextLine();

                    if (n.equals(name) && (p.equals(pass))) {


                        innit.close();
                        in.close();
                        in2.close();
                        return true;
                    }

                }
                System.out.println("Wrong credentials ( "+b+" more tries )");
                a++;
                b--;
                if(a == 4){
                    System.out.println("Failed to login");
                    innit.close();
                    in2.close();
                    in.close();
                    return false;
                }
            }

        } catch (Exception e) {
            System.out.println("Error");
        }
        return false;
    }

    public void changeUserCredentials(){
        Scanner scan = new Scanner(System.in);
        String lol,changeto = "",email1,pass1;
        int b ;
        System.out.print("Update\n1.Username\n2.Email\n3.Password\n1-3 : ");
        b = scan.nextInt();
        System.out.print("Change To : ");
        changeto = scan.next();
        try{

            File oldfile = new File(name+".txt");
            File newfilee = new File(changeto+".txt");
            File oldfile1 = new File(name+"cart.txt");
            File newfilee1 = new File(changeto+"cart.txt");
            File oldfile2 = new File(name+"fav.txt");
            File newfilee2 = new File(changeto+"fav.txt");
            File oldfile3 = new File(name+"trans.txt");
            File newfilee3 = new File(changeto+"trans.txt");
            File oldfile4 = new File(name+"cred.txt");
            File newfilee4 = new File(changeto+"cred.txt");
            PrintWriter out = new PrintWriter(new FileOutputStream("temp.txt"));
            PrintWriter out1 = new PrintWriter(new FileOutputStream("temp1.txt"));
            PrintWriter out2 = new PrintWriter(new FileOutputStream("temp2.txt"));
            Scanner in = new Scanner(new FileInputStream("data.txt"));
            Scanner in1 = new Scanner(new FileInputStream("balance.txt"));
            Scanner in2 = new Scanner(new FileInputStream("add.txt"));
            File file = new File("data.txt");
            File newfile = new File("temp.txt");
            File file1 = new File("balance.txt");
            File newfile1 = new File("temp1.txt");
            File file2 = new File("add.txt");
            File newfile2 = new File("temp2.txt");





            switch(b){
                case 1: {
                    if(checkUnique(changeto)) {
                        System.out.println("Credential taken");
                        break;
                    }
                    while (in1.hasNextLine()) {
                        lol = in1.nextLine();
                        if (lol.equals(name))
                            out1.println(changeto);
                        else
                            out1.println(lol);
                    }

                    while (in2.hasNextLine()) {
                        lol = in2.nextLine();
                        if (lol.equals(name))
                            out2.println(changeto);
                        else
                            out2.println(lol);
                    }

                    while (in.hasNextLine()) {
                        lol = in.nextLine();
                        if (lol.equals(name))
                            out.println(changeto);
                        else
                            out.println(lol);
                    }



                    break;
                }
                case 2:{
                    if(checkUnique(changeto)) {
                        System.out.println("Credential taken");
                        break;
                    }


                    while(in.hasNextLine()){
                        lol = in.nextLine();
                        if(lol.equals(email))
                            out.println(changeto);
                        else
                            out.println(lol);
                    }
                    break;
                }
                case 3:{
                    while(in.hasNextLine()){
                        lol = in.nextLine();
                        if(lol.equals(name)) {
                            email1 = in.nextLine();
                            pass1 = in.nextLine();
                            out.println(lol);
                            out.println(email1);
                            out.println(changeto);
                        }
                        else
                            out.println(lol);
                    }
                    break;
                }
                default:{
                    in.close();
                    in1.close();
                    in2.close();
                    out.close();
                    out1.close();
                    out2.close();
                    return;
                }
            }


            in.close();
            in1.close();
            in2.close();
            out.close();
            out1.close();
            out2.close();
            if(checkUnique(changeto))
                return;
            if( b == 1) {

                oldfile.renameTo(newfilee);
                oldfile1.renameTo(newfilee1);
                oldfile2.renameTo(newfilee2);
                oldfile3.renameTo(newfilee3);
                oldfile4.renameTo(newfilee4);
                file1.delete();
                newfile1.renameTo(file1);
                file2.delete();
                newfile2.renameTo(file2);
                file.delete();
                newfile.renameTo(file);
            }else {
                file.delete();
                newfile.renameTo(file);
            }
        }catch (Exception e){
            System.out.println("Error");
        }

    }

    public void isWho(){
        System.out.println("\nUsername : "+name);
        System.out.println("Email    : "+email);
        System.out.print("Password : " );
        for(int i = 0 ; i < pass.length();i++){
            System.out.print("*");
        }
        System.out.println("\nAddress : "+add);
        System.out.println("Payment Password : "+paymentpass);
    }

    public void changeAdd(){
        Scanner scan = new Scanner(System.in);
        String lol;
        int b;
        String changeto ,address="",paypass;
        System.out.print("Update\n1.Address\n2.Payment Pass\n1-2 : ");
        b = scan.nextInt();
        scan.nextLine();
        System.out.print("change to : ");
        changeto = scan.nextLine();
        try{
            PrintWriter out = new PrintWriter(new FileOutputStream("temp.txt"));
            Scanner in = new Scanner(new FileInputStream("add.txt"));
            File file = new File("add.txt");
            File newfile = new File("temp.txt");
            switch(b){
                case 1: {
                    while (in.hasNextLine()) {
                        lol = in.nextLine();
                        if (lol.equals(name)) {
                            address= in.nextLine();
                            paypass=in.nextLine();
                            out.println(lol);
                            out.println(changeto);
                            out.println(paypass);
                            add = changeto;
                        }else
                            out.println(lol);
                    }
                    break;
                }
                case 2:{
                    if(!isNumeric(changeto)){
                        System.out.println("Wrong Input");
                        in.close();
                        out.close();
                        return;
                    }

                    int a = Integer.parseInt(changeto);

                    while(in.hasNextLine()){
                        lol = in.nextLine();
                        if (lol.equals(name)) {
                            address= in.nextLine();
                            paypass=in.nextLine();
                            out.println(lol);
                            out.println(address);
                            out.println(changeto);
                            paymentpass = a;
                        }else
                            out.println(lol);
                    }
                    break;
                }

            }
            in.close();
            out.close();
            file.delete();
            newfile.renameTo(file);


    }catch (Exception e){
            System.out.println("Error");
        }}

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
            Scanner in = new Scanner(new FileInputStream("data.txt"));
            while(in.hasNextLine()){
                String name = in.nextLine();
                String email = in.nextLine();
                String pass = in.nextLine();
                if(x.equals(name) || x.equals(email)){
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

    public void loggout(){

        name = "";
        email = "";
        pass = "";
        System.out.println("You have succesfully logged out");
    }

    public int deleteacc(String s,String p){

        String lol,temp,temp1,temp2,temp3,temp4,temp5,temp6;
        Scanner scan = new Scanner(System.in);

        try{
            PrintWriter de = new PrintWriter(new FileOutputStream(s+"cart.txt"));
            PrintWriter de1 = new PrintWriter(new FileOutputStream(s+"fav.txt"));
            PrintWriter de2 = new PrintWriter(new FileOutputStream(s+"trans.txt"));
            PrintWriter de3 = new PrintWriter(new FileOutputStream(s+"cred.txt"));

            File oldfile1 = new File(s+"cart.txt");
            File oldfile2 = new File(s+"fav.txt");
            File oldfile3 = new File(s+"trans.txt");
            File oldfile4 = new File(s+"cred.txt");

            Scanner in = new Scanner(new FileInputStream("data.txt"));
            Scanner in1 = new Scanner(new FileInputStream("balance.txt"));
            Scanner in2 = new Scanner(new FileInputStream("add.txt"));
            Scanner in3 = new Scanner(new FileInputStream(s+".txt"));
            Scanner in4 = new Scanner(new FileInputStream("allproducts.txt"));
            PrintWriter out = new PrintWriter(new FileOutputStream("temp.txt"));
            PrintWriter out1 = new PrintWriter(new FileOutputStream("temp1.txt"));
            PrintWriter out2 = new PrintWriter(new FileOutputStream("temp2.txt"));
            PrintWriter out3 = new PrintWriter(new FileOutputStream("temp3.txt"));
            File file = new File("data.txt");
            File newfile = new File("temp.txt");
            File file1 = new File("balance.txt");
            File newfile1 = new File("temp1.txt");
            File file2 = new File("add.txt");
            File newfile2 = new File("temp2.txt");
            File file3 = new File("allproducts.txt");
            File newfile3 = new File("temp3.txt");
            File file4 = new File(s+".txt");

            System.out.print("Enter Password To Confirm : ");
            String word = scan.nextLine();
            if(!word.equals(pass)){
                System.out.println("Wrong Password");
                de.close();
                de1.close();
                de2.close();
                de3.close();

                in.close();
                in1.close();
                in2.close();
                in3.close();
                in4.close();
                out.close();
                out1.close();
                out2.close();
                out3.close();
                return 0;
            }


            while(in.hasNextLine()) {
                lol = in.nextLine();
                if (lol.equals(s)) {
                    temp = in.nextLine();
                    temp1 = in.nextLine();
                } else
                    out.println(lol);
            }

            while (in1.hasNextLine()) {
                lol = in1.nextLine();
                if (lol.equals(s))
                    temp = in1.nextLine();
                else
                    out1.println(lol);
            }

            while (in2.hasNextLine()) {
                lol = in2.nextLine();
                if (lol.equals(s)) {
                    temp = in2.nextLine();
                    temp1 = in2.nextLine();
                }
                else
                    out2.println(lol);
            }

            while(in3.hasNextLine()){
                lol = in3.nextLine();
                temp = in3.nextLine();
                temp1 = in3.nextLine();
                temp2 = in3.nextLine();
                temp3 = in3.nextLine();
                temp4 = in3.nextLine();
                temp5 = in3.nextLine();
                while(in4.hasNextLine()){
                    temp6 = in4.nextLine();
                    if(temp6.equals(lol)){
                        temp = in4.nextLine();
                        temp1 = in4.nextLine();
                        temp2 = in4.nextLine();
                        temp3 = in4.nextLine();
                        temp4 = in4.nextLine();
                        temp5 = in4.nextLine();
                        break;
                    }else
                        out3.println(temp6);
                }
            }

            while(in4.hasNextLine()){
                lol = in4.nextLine();
                out3.println(lol);
            }
            de.close();
            de1.close();
            de2.close();
            de3.close();

            in.close();
            in1.close();
            in2.close();
            in3.close();
            in4.close();
            out.close();
            out1.close();
            out2.close();
            out3.close();


            file1.delete();
            newfile1.renameTo(file1);
            file2.delete();
            newfile2.renameTo(file2);
            file.delete();
            newfile.renameTo(file);
            file3.delete();
            newfile3.renameTo(file3);
            file4.delete();
            oldfile1.delete();
            oldfile2.delete();
            oldfile3.delete();
            oldfile4.delete();


        }catch (Exception e){
            System.out.println("Must Have Atleast One Product");
        }
        return 1;
    }
}





