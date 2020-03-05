package main.test;

import csc1035.project3.EPOS;

public class TestMain {

    public static void main(String[] args) {

        //Creating EPOS object

        EPOS newEpos = new EPOS();

//        newEpos.addItem();

        //Getting stock by a valid ID.

        System.out.println(newEpos.getStockById(5));


    }
}
