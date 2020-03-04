package csc1035.project3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
     * Decreases the stock value of an item by n, caused by a transaction.
     *
     * @param id
     * @param n
     */
    @Override
    public void updatewTrans(int id, int n) {
        Session session = HibernateUtil.getSessionFactory().openSession
                ();
        try {
            session.beginTransaction();
            deleteStock(id, n);

        }catch (HibernateException e){
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    /**
     * Decreases/Increases the stock value of an element by 1,
     * without a transaction.
     *
     * @param id
     * @param add_del
     */
    @Override
    public void updatewoutTrans(int id, boolean add_del, int n) {


        Session session = HibernateUtil.getSessionFactory().openSession
                ();
        try {
            session.beginTransaction();

            if (add_del) addStock(id, n);
            else deleteStock(id, n);

        }catch (HibernateException e){
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }
}

