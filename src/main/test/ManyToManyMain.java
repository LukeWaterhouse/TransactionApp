import csc1035.project3.HibernateUtil;
import csc1035.project3.Stock;
import csc1035.project3.Transactions;
import org.hibernate.Session;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ManyToManyMain {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Stock s2 = new Stock("Bottle","Home",false,100.00,10,150.00);
        Stock s3 = new Stock("Door","Home",false,100.00,12,350.00);

        Set<Stock> sl = new HashSet<>(Arrays.asList(s2, s3));

        Transactions t1 = new Transactions(190.00, 74 );
        Transactions t2 = new Transactions(45, 2);

        Set<Transactions> tl = new HashSet<>(Arrays.asList(t1, t2));

        session.beginTransaction();

        for(Stock stu : sl) {
            session.saveOrUpdate(stu);
        }

        for(Transactions tra : tl){
            session.saveOrUpdate(tra);
        }


        for (Stock stu: sl ) {

            stu.setTransactions(tl);
            session.persist(stu);
            
        }

        for(Transactions tra : tl){
            tra.setStock(sl);
            session.persist(tra);
        }
        session.getTransaction().commit();
    }

}
