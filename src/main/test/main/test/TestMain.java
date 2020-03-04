package main.test;

import csc1035.project3.*;
import org.hibernate.Session;
import javax.persistence.*;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {

        //Creating EPOS object

        EPOS newEpos = new EPOS();

//        newEpos.addItem();

        //Getting stock by an invalid ID.

        System.out.println(newEpos.getStockById(5));


    }
}
