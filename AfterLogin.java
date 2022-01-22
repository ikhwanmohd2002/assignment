package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AfterLogin extends JFrame{
    private JPanel mainPanel;
    private JButton accountButton;
    private JButton customerPerspectiveButton;
    private JButton sellerPerspectiveButton;

    public AfterLogin(String s,String e,String p,String a,int pay){
        this.setTitle("Interface");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent account) {
                dispose();
                JFrame accountt = new Account(s,e,p,a,pay);
                accountt.setVisible(true);
            }
        });
        customerPerspectiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent customer) {
                dispose();
                JFrame accountt = new Customer(s,e,p,a,pay);
                accountt.setVisible(true);

            }
        });
        sellerPerspectiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent seller) {
                dispose();
                JFrame accountt = new Seller(s,e,p,a,pay);
                accountt.setVisible(true);
            }
        });


    }
}
