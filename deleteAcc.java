package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class deleteAcc extends JFrame{
    private JPasswordField passfield;
    private JButton confirmButton;
    private JButton goBackButton;
    private JPanel mainPanel;

    public deleteAcc(String s,String e,String p,String a,int pay){
        this.setTitle("Account");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent back) {
                dispose();
                JFrame backk = new Account(s,e,p,a,pay);
                backk.setVisible(true);
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent confirm) {
                String password = passfield.getText();
                if(password.equals(p)){
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
                            JOptionPane.showMessageDialog(null,"You have deleted account");



                        }catch (Exception e){
                            System.out.println("Must Have Atleast One Product");
                        }
                        dispose();
                        JFrame main = new registerGUI();
                        main.setVisible(true);



                }else{
                    passfield.setText("");
                    JOptionPane.showMessageDialog(null,"Wrong Password");
                    return;
                }
            }
        });
    }
}
