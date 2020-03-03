package csc1035.project3;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class UpdateDelete implements Update_Delete{

    @Override
    public void deleteStock(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession
                ();
        try {
            session = (Session) HibernateUtil.getSessionFactory().openSession().
                    beginTransaction();
            Stock item = session.get(Stock.class, id);
            int stock = item.getStock();
            if (stock == 1) //Checks if there is only one item remaining in the store.
            {               //If true, then the stock would reach 0, so we delete the whole column instead.
                session.delete(item);
                session.getTransaction().commit();
            }
            stock -= 1;
            item.setStock(stock);
            session.update(item);
        } catch (HibernateException e){
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    @Override
    public void addStock(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession
                ();
        try {
            session = (Session) HibernateUtil.getSessionFactory().openSession().
                    beginTransaction();
            Stock item = session.get(Stock.class, id);
            int stock = item.getStock();
            stock += 1;
            item.setStock(stock);
            session.update(item);
        } catch (HibernateException e){
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    @Override
    public void updatewTrans(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession
                ();
        try {
            session = (Session) HibernateUtil.getSessionFactory().openSession().
                    beginTransaction();
            Stock item = session.get(Stock.class, id);
            deleteStock(id);



        }catch (HibernateException e){
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public void updatewoutTrans(int id, boolean add_del) {
        Session session = HibernateUtil.getSessionFactory().openSession
                ();
        try {
            session = (Session) HibernateUtil.getSessionFactory().openSession().
                    beginTransaction();
            Stock item = session.get(Stock.class, id);

            if (add_del) addStock(id);
            else deleteStock(id);

        }catch (HibernateException e){
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }
}

