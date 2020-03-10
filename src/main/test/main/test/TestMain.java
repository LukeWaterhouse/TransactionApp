package main.test;

import csc1035.project3.*;
import org.hibernate.Session;

public class TestMain {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String query = "FIND Stock";

        //session.
    }
}
