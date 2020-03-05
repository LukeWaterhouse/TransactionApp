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
     */
    @Override
    public void updateStock() {
        Session session = HibernateUtil.getSessionFactory().openSession
                ();

        Scanner in = new Scanner(System.in);
        System.out.println("Input the item id.");
        int id= in.nextInt();

        System.out.println("Would you like to add or delete stock? " +"/n"+
                "0 - Add Stock" + "/n" + "1 - Delete Stock.");
        int add_del = in.nextInt();

        while (add_del !=0 && add_del !=1){

            System.out.println("Only 0 and 1 are acceptable.");
            add_del = in.nextInt();
        }

        System.out.println("Please give the amount of stock.");
        int n = in.nextInt();
        Stock item = session.get(Stock.class, id);

        while(n>item.getStock() && add_del==1){
            System.out.println("Please give an appropriate amount to delete.");
            n = in.nextInt();
        }

        if (add_del == 1){

            if(item.getStock() > n)
                deleteStock(id, n);
            else if (item.getStock() == n){
                session.beginTransaction();
                session.delete(item);
                session.getTransaction().commit();
                session.close();
            }

        }
        else addStock(id, n);
        }

    }

