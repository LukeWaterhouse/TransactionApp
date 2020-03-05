package csc1035.project3;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EPOS implements Interface {

    @Override
    public void addItem(){

        System.out.print("input name >> ");
        Scanner myObj2 = new Scanner(System.in);
        String name = myObj2.nextLine();

        System.out.print("input category >> ");
        Scanner myObj3 = new Scanner(System.in);
        String category = myObj3.nextLine();

        System.out.print("is it perishable? (type true or false) >>");
        Scanner myObj4 = new Scanner(System.in);
        boolean perishable = myObj4.nextBoolean();

        System.out.print("input cost >> ");
        Scanner myObj5 = new Scanner(System.in);
        double cost = myObj5.nextDouble();

        System.out.print("input stock >> ");
        Scanner myObj6 = new Scanner(System.in);
        int stock = myObj6.nextInt();

        System.out.print("input sell price >> ");
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

    @Override
    public List<Stock> getStock() {

        List<Stock> arrayResults = new ArrayList<Stock>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query q = session.createNamedQuery("Stock_getStock",Stock.class);
        List results = q.getResultList();

        session.getTransaction().commit();
        session.close();

        if (results.size() != 0) {

            for (Object o: results.toArray()) {
                arrayResults.add((Stock) o);

            }

            return arrayResults;
        }

        return null;
    }

    @Override
    public void asciiOut(List<Stock> records){

        System.out.println("+--------+--------------------+------------+------------+----------+-------+------------+");
        System.out.println("| Id     | Name               | Category   | Perishable | Cost     | Stock | Sell Price |");
        System.out.println("+--------+--------------------+------------+------------+----------+-------+------------+");


        //The format string below creates an equal size for all the variables to ensure the table keeps it shape.
        for (Stock record:records) {
            System.out.format("| %-6s | %-18s | %-10s | %-10s | %-8s | %-5s | %-10s |%n",record.getId(),
                    record.getName(),record.getCategory(),record.isPerishable(),record.getCost(),record.getStock(),
                    record.getSell_price());
        }
        System.out.println("+--------+--------------------+------------+------------+----------+-------+------------+");
    }
}

