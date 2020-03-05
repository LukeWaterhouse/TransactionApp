package csc1035.project3;


import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello and welcome to the CSC1035 EPOS System:");
        System.out.println();
        System.out.println("Available to you are these options:");
        System.out.println("(Please enter the corresponding number for the option after the '>>')" + "\n");


        System.out.print("1 - Display current stock table" + "\n" +
                "2 - Get record of item by a given id" + "\n" +
                "3 - Add a new item to stock table" + "\n" +
                "4 - Increase the number of stock for an existing item" + "\n" +
                "5 - Decrease the number of stock for an existing item" + "\n" +
                "6 - Perform a transaction" + "\n");

        while(true){

            System.out.print("\n"+">> ");


            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();

            switch (choice) {

                case "1":

                    System.out.println("You chose option 1");;
                    break;

                case "2":

                    System.out.println("You chose option 2");;
                    break;

                default:

                    System.out.println("This is an invalid input");
                    break;
            }
        }



    }
}
