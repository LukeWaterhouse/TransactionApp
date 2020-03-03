package main.java.csc1035.project3;

import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {

<<<<<<< HEAD
        Stock s1 = new Stock("Gold Ring","Jewellery",false,120.00,4,180.00);
=======
        Stock s1 = new Stock("Cupboard","Furniture",true,20.00,8,50.00);
>>>>>>> origin/master

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(s1);

        session.getTransaction().commit();

        session.close();
    }
}

