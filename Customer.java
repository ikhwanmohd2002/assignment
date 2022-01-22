package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Customer extends JFrame{
    private JPanel mainPanel;
    private JButton goToBalanceButton;
    private JButton goToCartButton;
    private JButton searchButton;
    private JButton transactionHistoryButton;
    private JButton displayFavouriteButton;
    private JButton goBackButton;

    public Customer(String s,String e,String p,String a,int pay){
        this.setTitle("Customer Perspective");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        goToBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dawd) {
                dispose();
                JFrame balance = new Balance(s,e,p,a,pay);
                balance.setVisible(true);
            }
        });
        goToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent awdwa) {
                File cart = new File(s+"cart.txt");
                if(cart.length()==0){
                    JOptionPane.showMessageDialog(null,"Cart is Empty");
                }else {
                    dispose();
                    JFrame shees = new Cart(s,e,p,a, pay);
                    shees.setVisible(true);
                    return;
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dawd) {
                dispose();
                JFrame balance = new SearchFunction(s,e,p,a,pay);
                balance.setVisible(true);
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent backk) {
                dispose();
                JFrame balance = new AfterLogin(s,e,p,a,pay);
                balance.setVisible(true);
            }
        });
        transactionHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent wee) {
                dispose();
                JFrame balance = new transhistory(s,e,p,a,pay);
                balance.setVisible(true);
            }
        });
        displayFavouriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dwad) {
                File file = new File(s+"fav.txt");
                if(file.length()==0){
                    JOptionPane.showMessageDialog(null,"No Products Found In Favorites");

                }else {
                    dispose();
                    JFrame balance = new favourites(s, e, p, a, pay);
                    balance.setVisible(true);
                }
            }
        });
    }
}
