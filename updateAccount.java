package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class updateAccount extends JFrame{
    private JPanel mainPanel;
    private JLabel email;
    private JLabel pass;
    private JLabel add;
    private JLabel paymentpass;
    private JLabel name;
    private JComboBox comboBox;
    private JTextField changetoo;
    private JButton updateAccountButton;
    private JButton goBackButton;
    private int c = 5;

    public updateAccount(String s,String e,String p,String a,int pay){
        this.setTitle("Update");
        String[] combo = {"Change Username","Change Email","Change Password","Change Address","Change Payment Password"};
        comboBox.addItem(combo[0]);
        comboBox.addItem(combo[1]);
        comboBox.addItem(combo[2]);
        comboBox.addItem(combo[3]);
        comboBox.addItem(combo[4]);



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        isWho(s,e,p,a,pay);


        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              c = comboBox.getSelectedIndex() ;


            }
        });
        updateAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent opdate) {
                if(c==5) {
                    JOptionPane.showMessageDialog(null, "Please Reselect Option");
                    return;
                }
                String changer = changetoo.getText();
                if(checkUnique(changer)) {
                    JOptionPane.showMessageDialog(null,"Credential Taken");
                    return;
                }
                if(c >= 0 && c <=2) {
                    changeUserCredentials(s, e, c);
                }else {
                    if(c==4){
                        if(!isNumeric(changer)){
                            JOptionPane.showMessageDialog(null,"Wrong Input");
                            return;
                        }else
                            changeAdd(s, a, pay, c);
                    }else
                        changeAdd(s, a, pay, c);
                }

                if(changer.isBlank()){
                    JOptionPane.showMessageDialog(null,"Blank Input");
                    return;
                }
                dispose();
                if(c==0) {
                    JFrame redisplay = new Account(changer, e, p, a, pay);
                    redisplay.setVisible(true);
                }else if(c==1){
                    JFrame redisplay = new Account(s, changer, p, a, pay);
                    redisplay.setVisible(true);
                }else if(c==2){
                    JFrame redisplay = new Account(s, e, changer, a, pay);
                    redisplay.setVisible(true);
                }else if(c==3){
                    JFrame redisplay = new Account(s, e, p, changer, pay);
                    redisplay.setVisible(true);
                }else {
                    int x = Integer.parseInt(changer);
                    JFrame redisplay = new Account(s, e, p, a, x);
                    redisplay.setVisible(true);
                }

            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dwad) {
                dispose();
                JFrame dad = new Account(s,e,p,a,pay);
                dad.setVisible(true);
            }
        });
    }


    public void isWho(String name1,String email1,String pass1,String add1,int paymentpass1){

        name.setText(name1);
        email.setText(email1);
        pass.setText(pass1);
        add.setText(add1);
        String pay = Integer.toString(paymentpass1);
        paymentpass.setText(pay);
        pass.setText(pass1);

    }

    public void changeUserCredentials(String name,String email,int b){
        Scanner scan = new Scanner(System.in);
        String lol,changeto = "",email1,pass1;

        changeto = changetoo.getText();
        if(changeto.isBlank()){
            return;
        }
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
                case 0: {
                    if(checkUnique(changeto)) {
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
                case 1:{
                    if(checkUnique(changeto)) {
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
                case 2:{
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
                    JOptionPane.showMessageDialog(null,"Failed to Update Account");
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
            if( b == 0) {

                JOptionPane.showMessageDialog(null,"You have updated account");
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
                JOptionPane.showMessageDialog(null,"You have updated account");
                file.delete();
                newfile.renameTo(file);
            }
        }catch (Exception e){
            System.out.println("Error");
        }

    }

    public void changeAdd(String name,String add,int paymentpass,int b){
        Scanner scan = new Scanner(System.in);
        String lol;
        String changeto ,address="",paypass;

        changeto = changetoo.getText();
        try{
            PrintWriter out = new PrintWriter(new FileOutputStream("temp.txt"));
            Scanner in = new Scanner(new FileInputStream("add.txt"));
            File file = new File("add.txt");
            File newfile = new File("temp.txt");
            switch(b){
                case 3: {
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
                    JOptionPane.showMessageDialog(null,"You have updated address");
                    break;
                }
                case 4:{
                    if(!isNumeric(changeto)){
                        JOptionPane.showMessageDialog(null,"Wrong Input");
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
                    JOptionPane.showMessageDialog(null,"You have updated payment password");
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


}
