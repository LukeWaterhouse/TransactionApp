package main.java.csc1035.project3;

import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {

        Stock s1 = new Stock("Gold Ring","Jewellery",false,120.00,4,180.00);

        Session session = csc1035.project3.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(s1);

        session.getTransaction().commit();

        session.close();
    }

}
