<<<<<<< HEAD
package csc1035.project3;

=======
package main.java.csc1035.project3;
>>>>>>> 7e718d9bb89c6bed3c08753bec6222c14401eb9c

import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {

<<<<<<< HEAD
        Stock s1 = new Stock(1,"Gold Ring","Jewellery",true,120.00,4,180.00);
=======
        Stock s1 = new Stock(1,"Gold Ring","Jewellery",false,120.00,4,180.00);
>>>>>>> 7e718d9bb89c6bed3c08753bec6222c14401eb9c

        Session session = csc1035.project3.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(s1);

        session.getTransaction().commit();

        session.close();
    }

}
