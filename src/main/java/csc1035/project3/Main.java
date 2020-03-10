package csc1035.project3;


import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        EPOS epos = new EPOS();

        System.out.println("Hello and welcome to the CSC1035 EPOS System:");
        System.out.println();
        System.out.println("Available to you are these options:");
        System.out.println("(Please enter the corresponding number for the option after the '>>')" + "\n");


        while(true){

            System.out.print("1 - Display current stock table" + "\n" +
                    "2 - Get record of item by a given id" + "\n" +
                    "3 - Add a new item to stock table" + "\n" +
                    "4 - Update stock of an item" + "\n" +
                    "5 - Perform a transaction" + "\n");
            System.out.print("\n"+">> ");


            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();

            switch (choice) {

                case "1": //Display current stock table

                    System.out.println("You chose option 1");;
                    epos.asciiOut(epos.getStock());
                    break;

                case "2": //Get a record of an item by a given id

                    System.out.println("You chose option 2");
                    List<Stock> singleElementArray = new ArrayList<>();
                    singleElementArray.add(epos.getStockById());
                    if (singleElementArray.get(0) != null) {
                        epos.asciiOut(singleElementArray);
                    }
                    else{
                        System.out.println("This record does not exist in the Stock table"+"\n");
                    }
                    break;

                case "3": //Add a new item to stock table

                    System.out.println("You chose option 3");;
                    epos.addItem();
                    break;

                case "4": //Add a new item to stock table

                    System.out.println("You chose option 4");;
                    epos.updateStock();
                    break;

                case "5": //Perform a transaction

                    System.out.println("You chose option 5");
                    epos.addTransaction();

                default: //Default case if an invalid input occurs.

                    System.out.println("This is an invalid input");
                    break;
            }
        }



    }
}
