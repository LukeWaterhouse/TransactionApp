package csc1035.project3;


import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {

        Stock s1 = new Stock("Cupboard","Furniture",true,20.00,8,50.00);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(s1);

        session.getTransaction().commit();

        session.close();
    }
}
