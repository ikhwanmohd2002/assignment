package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class registration extends JFrame{
    private JPanel mainPanel;
    private JTextField userText;
    private JTextField emailText;
    private JPasswordField passText;
    private JButton registerButton;
    private JLabel success;
    private JButton cancelButton;

    public registration(){
        this.setTitle("Registration Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);


        this.setVisible(true);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner scan = new Scanner(System.in);
                try {
                    PrintWriter out = new PrintWriter(new FileOutputStream("data.txt", true));
                    PrintWriter pw = new PrintWriter(new FileOutputStream("balance.txt", true));
                    PrintWriter pw2 = new PrintWriter(new FileOutputStream("add.txt", true));
                    Scanner in = new Scanner(new FileInputStream("data.txt"));
                    String user = userText.getText();
                    String email = emailText.getText();
                    String pass = passText.getText();

                    if(user.isBlank() || email.isBlank() || pass.isBlank()){
                        JOptionPane.showMessageDialog(null,"Empty Input Present");
                        userText.setText("");
                        emailText.setText("");
                        passText.setText("");
                        in.close();
                        pw2.close();
                        pw.close();
                        out.close();
                        return;
                    }



                    while (in.hasNextLine()) {
                        String a = in.nextLine();
                        if (a.equals(user)) {
                            JOptionPane.showMessageDialog(null,"Username has been taken");
                            userText.setText("");
                            emailText.setText("");
                            passText.setText("");
                            in.close();
                            pw2.close();
                            pw.close();
                            out.close();
                            return;
                        }

                        if(!isValid(emailText.getText())){
                            JOptionPane.showMessageDialog(null,"Invalid Email\nPlease Enter In Format : example@gmail.com");
                            userText.setText("");
                            emailText.setText("");
                            passText.setText("");
                            return;
                        }

                        if (a.equals(email)) {
                            JOptionPane.showMessageDialog(null,"Email has been taken");
                            userText.setText("");
                            emailText.setText("");
                            passText.setText("");
                            in.close();
                            pw2.close();
                            pw.close();
                            out.close();
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

                    PrintWriter out1 = new PrintWriter(new FileOutputStream(user + ".txt", true));
                    PrintWriter out2 = new PrintWriter(new FileOutputStream(user + "cart.txt", true));
                    PrintWriter out3 = new PrintWriter(new FileOutputStream(user + "fav.txt", true));
                    PrintWriter out4 = new PrintWriter(new FileOutputStream(user + "trans.txt", true));
                    PrintWriter out5 = new PrintWriter(new FileOutputStream(user + "cred.txt", true));
                    PrintWriter out6 = new PrintWriter(new FileOutputStream("allproducts.txt", true));
                    out1.close();
                    out2.close();
                    out3.close();
                    out4.close();
                    out5.close();
                    out6.close();

                    JOptionPane.showMessageDialog(null,"You have successfully registered "+user );
                    dispose();
                    JFrame goback = new registerGUI();
                    goback.setVisible(true);
                } catch (Exception f) {
                    System.out.println("Failed");
                }

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent cacel) {
                dispose();
                JFrame cancel = new registerGUI();
                cancel.setVisible(true);
            }
        });
        userText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent daw) {
                emailText.requestFocus();
            }
        });
        emailText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent daw) {
                passText.requestFocus();
            }
        });
        passText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dawd) {
                registerButton.doClick();
            }
        });
    }

    public static boolean isValid(String email)
    {
        String emaill = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emaill);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}


