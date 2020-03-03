package csc1035.project3;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.Scanner;

public class EPOS implements Interface {

    public static EPOS X = new EPOS();

    public void addItem(){
        System.out.println("input id number");
        Scanner myObj = new Scanner(System.in);
        int idnumber = myObj.nextInt();

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

        Stock newstock = new Stock(idnumber,name,category,perishable,cost,stock,sellprice);
        Session session = csc1035.project3.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(newstock);

        session.getTransaction().commit();

        session.close();

    }




    public void deleteStock(int id){
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

    public void addStock(int id){
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

    public void printReceipt(){

    }

    public static void main(String[] args) {
        X.addItem();


    }
}

