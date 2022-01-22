package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Account extends JFrame{
    private JPanel mainPanel;
    private JButton updateAccountButton;
    private JButton deleteAccountButton;
    private JButton logOutButton;
    private JButton goBackButton;


    public Account(String s,String e,String p,String a,int pay){
        this.setTitle("Account Options");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        updateAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent update) {
                dispose();
                JFrame updation = new updateAccount(s,e,p,a,pay);
                updation.setVisible(true);
            }
        });

        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent deleteacc) {
                dispose();
                JFrame updation = new deleteAcc(s,e,p,a,pay);
                updation.setVisible(true);
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent loggout) {

                dispose();
                JOptionPane.showMessageDialog(null,"You successfully logged out");
                JFrame updation = new registerGUI();
                updation.setVisible(true);
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent goo) {
                dispose();
                JFrame updation = new AfterLogin(s,e,p,a,pay);
                updation.setVisible(true);
            }
        });
    }
}
