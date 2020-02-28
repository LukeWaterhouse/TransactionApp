package csc1035.project3;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class UpdateDelete implements Update_Delete{

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession
                ();
        try {
            session = (Session) HibernateUtil.getSessionFactory().openSession().
                    beginTransaction();
            Stock item = session.get(Stock.class, id);
            int stock = item.getStock();
            if (stock == 1) {
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
    public void update(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession
                ();
        try {
            session = (Session) HibernateUtil.getSessionFactory().openSession().
                    beginTransaction();
            Stock item = session.get(Stock.class, id);
            delete(id);


        }catch (HibernateException e){
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}

