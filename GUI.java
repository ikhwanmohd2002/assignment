package gui;

import javax.swing.*;

public class GUI {


    public static void main(String[] args){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.setSize(500,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Login Form");

        panel.setLayout(null);

        frame.add(panel);
        JLabel label = new JLabel("User ");
        label.setBounds(30,20,80,20);
        panel.add(label);
        JTextField userText = new JTextField(20);
        userText.setBounds(80,20,165,20);
        panel.add(userText);
        JLabel labelPass = new JLabel("Password");
        label.setBounds(30,-10,80,30);
        panel.add(labelPass);
        JPasswordField passText = new JPasswordField(20);
        passText.setBounds(80,-10,165,30);
        panel.add(passText);
        frame.setVisible(true);
    }
}
