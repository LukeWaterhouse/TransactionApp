package csc1035.project3;
import org.hibernate.Session;

public class OneToOneMain {
    public static void main(String[] args) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Transactions t1 = new Transactions(30.00, 10.00);
        Stock s1 = new Stock();

        s.setStock(s1);

        s.persist(s1);
        s.persist(t1);

        s.getTransaction().commit();


    }
}
