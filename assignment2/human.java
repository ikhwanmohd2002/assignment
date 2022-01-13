package ikhwan;

import com.sun.jdi.connect.Connector;

import java.util.Locale;
import java.util.Scanner;

public class human {

    public static void main(String[] args){

        register reg = new register();
        sellers sell = new sellers();
        customer cust = new customer();
        Scanner scan = new Scanner(System.in);
        int y = 0,x=1;

        start:
        {
            while (true) {
                if(reg.registerandlogin()==-1)
                return;
                middle:{
                while (true) {

                    System.out.print("A.Account\nB.Customer Perspective\nC.Seller Perspective\nD.Exit\nEnter A-D: ");
                    char a = scan.next().toLowerCase().charAt(0);

                    switch (a) {
                        case 'a': {
                            if(reg.Account()==1)
                            continue ;
                            else
                            break middle;
                        }
                        case 'b':{
                            if(cust.Customer(reg.name, reg.paymentpass)==1)
                                continue ;
                            else
                                break middle;
                        }
                        case 'c':{
                            if(sell.Seller(reg.name)==1)
                                continue ;
                            else
                                break middle;
                        }
                        case 'd': {
                            return;
                        }

                    }
                }
            }}

        }




    }


}
