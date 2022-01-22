package gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class registerGUI extends JFrame {
    private JPanel mainPanel;
    private JButton Register;
    private JButton Login;

    public registerGUI(){
        this.setTitle("Starting Page");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        try {
            File file = new File("Elevator-Music (nearly) 10 minutes (online-audio-converter.com).wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();




        }catch (Exception e){
            System.out.println("Error");
        }


        Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame register = new registration();

            }
        });
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame login = new login();
            }
        });
    }

    public static void main(String[]args){
        JFrame frame = new registerGUI();
        frame.setVisible(true);
    }

}
