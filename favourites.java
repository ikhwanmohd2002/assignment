package gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class favourites extends JFrame{
    private JPanel mainPanel;
    private JList list1;
    private JButton goBackButton;

    public favourites(String s,String e,String p,String a,int pay){
        DefaultListModel<String> model = new DefaultListModel<>();
        this.setTitle("Favourites");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        model.removeAllElements();
        String[] pop = displayFav(s);
        list1.setModel(model);
        for(int i = 0; i < pop.length;i++) {
            model.addElement(pop[i]);
        }
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent dawd) {
                if (list1.getValueIsAdjusting()) return;
                String r = list1.getSelectedValue().toString();
                dispose();
                JFrame goo = new kimi(s,e,p,a,r,pay);
                goo.setVisible(true);

            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dawd) {
                dispose();
                JFrame goo = new Customer(s,e,p,a,pay);
                goo.setVisible(true);
            }
        });
    }

    public String[] displayFav(String s){
        int a =0,x=-1;
        ArrayList<String> pronames = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        String[] data = new String[numbFav(s)];
        try{
            Scanner in = new Scanner(new FileInputStream(s+"fav.txt"));
            while(in.hasNextLine()){
                String lol = in.nextLine();
                System.out.println(a+"."+lol);
                data[a] = lol;
                pronames.add(lol);
                a++;
                x=1;
            }



            in.close();
        }catch (Exception e){
            System.out.println("Error");
        }
        return data;
    }

    public int numbFav(String s){
        int a =0,x=-1;
        ArrayList<String> pronames = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        try{
            Scanner in = new Scanner(new FileInputStream(s+"fav.txt"));
            while(in.hasNextLine()){
                String lol = in.nextLine();
                pronames.add(lol);
                a++;
                x=1;
            }

            if(x==-1){
                JOptionPane.showMessageDialog(null,"No Products Found");
                in.close();
                return a;
            }


        }catch (Exception e){
            System.out.println("Error");
        }
        return a;
    }
}
