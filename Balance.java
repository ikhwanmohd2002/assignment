package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Balance extends JFrame{
    private JPanel mainPanel;
    private JButton goBackButton;
    private JButton topUpButton;
    private JTextField top;
    private JLabel bal;

    public Balance(String s,String e,String p,String a,int pay){
        this.setContentPane(mainPanel);
        this.setTitle("Balance");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        bal.setText(displayBal(s));

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent go) {
                dispose();
                JFrame back = new Customer(s,e,p,a,pay);
                back.setVisible(true);
            }
        });
        topUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent topup) {
                balance(s);
                bal.setText(displayBal(s));
                top.setText("");
            }
        });
    }

    public String displayBal(String s){
        String balance="";

        try{
            Scanner in = new Scanner(new FileInputStream("balance.txt"));
            while(in.hasNextLine()){
                String name = in.nextLine();
                if(name.equals(s)){
                   balance = in.nextLine();
                    break;
                }
            }

            in.close();
            return balance;
        }catch (Exception e){
            System.out.println("Balance Error");
        }
        return "";
    }

    public void balance(String s){
        String lol = "",prevbal="";
        Scanner scan = new Scanner(System.in);
        String newbal = top.getText();
        double x,y;
        if(!isNumeric(newbal)){
            JOptionPane.showMessageDialog(null,"Wrong Input");
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
                    in.printf("%.2f\n",(x+y));
                } else
                    in.println(lol);

            }
            pw.close();
            in.close();
            input.close();
            file.delete();
            newfile.renameTo(file);
            JOptionPane.showMessageDialog(null,"You have successfully topped up");

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

}


