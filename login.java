package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.Scanner;

public class login extends JFrame{
    private JButton login;
    private JTextField userText;
    private JPasswordField passText;
    private JPanel mainPanel;
    private JLabel success;
    private JButton registerButton;
    public String name;
    public String add;
    public String temp;
    public int paymentpass;
    public double bal;
    public String email;
    public String pass;

    public login(){

        this.setTitle("Login Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] creds = new String[2];
                try {
                    Scanner innit = new Scanner(new FileInputStream("data.txt"));
                    Scanner in = new Scanner(new FileInputStream("balance.txt"));
                    Scanner in2 = new Scanner(new FileInputStream("add.txt"));
                    int a = 0;
                    int b  =3;



                        String n = userText.getText();

                        String p = passText.getText();


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
                                JOptionPane.showMessageDialog(null,"You have successfully logged in "+name);
                                success.setText("");
                                innit.close();
                                in.close();
                                in2.close();
                                dispose();
                                JFrame afterlog = new AfterLogin(name,email,pass,add,paymentpass);
                                afterlog.setVisible(true);


                                return ;
                            }

                        }


                        JOptionPane.showMessageDialog(null,"Wrong Password, Try Again");
                        userText.setText("");
                        passText.setText("");
                        innit.close();
                        in2.close();
                        in.close();
                        return ;



                } catch (Exception f) {
                    System.out.println("Error");
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                dispose();
                JFrame reg = new registration();
                reg.setVisible(true);
            }
        });
        userText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent daw) {
                passText.requestFocus();
            }
        });
        passText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dwad) {
                login.doClick();
            }
        });
    }
}
