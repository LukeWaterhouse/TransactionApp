package csc1035.project3;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class EPOS implements Interface {

    public static EPOS X = new EPOS();


    @Override
    public void addItem(){

        System.out.println("input name");
        Scanner myObj2 = new Scanner(System.in);
        String name = myObj2.nextLine();

        System.out.println("input category");
        Scanner myObj3 = new Scanner(System.in);
        String category = myObj3.nextLine();

        System.out.println("is it perishable?");
        Scanner myObj4 = new Scanner(System.in);
        boolean perishable = myObj4.nextBoolean();

        System.out.println("input cost");
        Scanner myObj5 = new Scanner(System.in);
        double cost = myObj5.nextDouble();

        System.out.println("input stock");
        Scanner myObj6 = new Scanner(System.in);
        int stock = myObj6.nextInt();

        System.out.println("input sell price");
        Scanner myObj7 = new Scanner(System.in);
        double sellprice = myObj7.nextDouble();

        Stock newstock = new Stock(name,category,perishable,cost,stock,sellprice);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(newstock);

        session.getTransaction().commit();

        session.close();

    }



    @Override
    public void deleteStock(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
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
            session.getTransaction().commit();

        } catch (HibernateException e){
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }


    }

    @Override
    public void addStock(int id){

        Session session = HibernateUtil.getSessionFactory().openSession
                ();
        try {
            session = (Session) HibernateUtil.getSessionFactory().openSession().beginTransaction();
            Stock item = session.get(Stock.class, id);
            int stock = item.getStock();
            stock += 1;
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

    public void printReceipt(){

    }

    @Override
    public Stock getStockById(int i) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();




            Query q = session.createNamedQuery("Stock_getStockRecordById", Stock.class);

            List results = q.setParameter("checkValue", i).getResultList();

            System.out.println("Results: "+results);

            session.getTransaction().commit();
            session.close();

            if (results.size() != 0) {
                Object[] items = results.toArray();

                Object record = items[0];

                Stock stockrecord = (Stock) record;

                return stockrecord;

        }
            else{
                return null;
            }
    }
}

