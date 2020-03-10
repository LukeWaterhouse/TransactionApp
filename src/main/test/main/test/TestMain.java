package main.test;

import csc1035.project3.EPOS;
import csc1035.project3.Stock;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {

        EPOS newEpos = new EPOS();

        List<Stock> singleElementArray = new ArrayList<>();
        singleElementArray.add(newEpos.getStockById());

        newEpos.asciiOut(singleElementArray);


        List<Stock> stockList = newEpos.getStock();

        if (stockList.size() != 0){

            newEpos.asciiOut(stockList);
        }
        else{
            System.out.println("Empty Array");
        }



    }
}
