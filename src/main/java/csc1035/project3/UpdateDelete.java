package csc1035.project3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class UpdateDelete implements Update_Delete{


    /**
     * Decreases the stock value of an element by n.
     *
     * @param id
     * @param n
     */
    @Override
    public void deleteStock(int id, int n) {
        Session session = HibernateUtil.getSessionFactory().openSession
                ();
        try {
            session.beginTransaction();
            Stock item = session.get(Stock.class, id);

            System.out.println(item.getStock());
            int stock = item.getStock();
            if (stock == 1) //Checks if there is only one item remaining in the store.
            {               //If true, then the stock would reach 0, so we delete the whole column instead.
                session.delete(item);
                session.getTransaction().commit();
                session.close();
            }
            stock -= n;
            item.setStock(stock);
            session.update(item);
            session.getTransaction().commit();

        } catch (HibernateException e){
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    /**
     * Increases the stock value of an element by 1.
     *
     * @param id
     * @param n
     */
    @Override
    public void addStock(int id, int n) {
        Session session = HibernateUtil.getSessionFactory().openSession
                ();
        try {
            session.beginTransaction();
            Stock item = session.get(Stock.class, id);
            int stock = item.getStock();
            stock += n;
            item.setStock(stock);
            session.update(item);
            session.getTransaction().commit();

        } catch (HibernateException e){
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }



    /**
     * Decreases/Increases the stock value of an element by n,
     * without a transaction.
     *
     * @param id
     * @param n
     */
    @Override
    public void updatewoutTrans(int id) {

        Scanner in = new Scanner(System.in);
        System.out.println("Would you like to add or delete stock? " +
                " Type 0 to add or 1 to delete.");
        int add_del = in.nextInt();

        while (add_del !=0 && add_del !=1){

            System.out.println("Only 0 and 1 are acceptable.");
            add_del = in.nextInt();
        }
        System.out.println("Please give the amount of stock.");
        int n = in.nextInt();

        if (add_del==0) addStock(id, n);
        else deleteStock(id, n);

    }
}

