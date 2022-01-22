package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Seller extends JFrame{
    private JPanel mainPanel;
    private JButton productsButton;
    private JButton transactionHistoryButton;
    private JButton goBackButton;

    public Seller(String s,String e,String p,String a,int pay){
        this.setTitle("Seller Perspective");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent fsf) {
                dispose();
                JFrame backk = new AfterLogin(s,e,p,a,pay);
                backk.setVisible(true);
            }
        });
        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dwad) {
                dispose();
                JFrame backk = new products(s,e,p,a,pay);
                backk.setVisible(true);
            }
        });
        transactionHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dwad) {
                dispose();
                JFrame backk = new Transaction(s,e,p,a,pay);
                backk.setVisible(true);
            }
        });
    }


}
