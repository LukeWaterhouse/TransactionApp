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

        System.out.print("Input name >> ");
        Scanner myObj2 = new Scanner(System.in);
        String name = myObj2.nextLine();
        System.out.print("Input category >> ");
        Scanner myObj3 = new Scanner(System.in);
        String category = myObj3.nextLine();

        boolean perishable;
        String isperishable = "";

        System.out.print("Is it perishable?(true or false) >> ");
        Scanner myObj4 = new Scanner(System.in);
        isperishable = myObj4.nextLine();
        while(!isperishable.matches("true|false")){
            System.out.print("Not a valid input please input true or false >> ");
            isperishable = myObj4.nextLine();
        }
        perishable = Boolean.getBoolean(isperishable);

        System.out.print("Input cost >> ");
        Scanner myObj5 = new Scanner(System.in);
        double cost = myObj5.nextDouble();
        System.out.print("Input stock >> ");
        Scanner myObj6 = new Scanner(System.in);
        int stock = myObj6.nextInt();
        System.out.print("Input sell price >> ");
        Scanner myObj7 = new Scanner(System.in);
        double sellprice = myObj7.nextDouble();
        Stock newstock = new Stock(name,category,perishable,cost,stock,sellprice);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(newstock);
        session.getTransaction().commit();
        session.close();
    }

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
     */
    @Override
    public void updateStock() {
        Session session = HibernateUtil.getSessionFactory().openSession
                ();

        try{

        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the ID >> ");
        int id = sc.nextInt();

        Scanner in = new Scanner(System.in);
        System.out.print("Would you like to add or delete stock? "+"\n"+
                "1 - Add stock "+ "\n"+
                        "2 - Delete stock" + "\n" + "\n" + ">> ");
        int add_del = in.nextInt();
        while (add_del !=1 && add_del !=2){
            System.out.println("Only 1 and 2 are acceptable.");
            add_del = in.nextInt();
        }
        System.out.print("Please give the amount of stock:" +"\n"+"\n"+">> ");



        int n = in.nextInt();

            Stock item = session.get(Stock.class, id);

            while(n>item.getStock() && add_del==2){
                System.out.print("Please give an appropriate amount to delete" +"\n"+"\n"+">> ");
                n = in.nextInt();
            }

            if (add_del == 2){

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
        catch(Exception e){
            System.out.println("Invalid Input, please try again");

        }
    }


    @Override
    public void printReceipt(){

    }




    @Override
    public Stock getStockById() {

        try {


            Scanner sc = new Scanner(System.in);
            System.out.print("Please enter the ID >> ");
            int choice = sc.nextInt();


            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createNamedQuery("Stock_getStockRecordById", Stock.class);
            List results = q.setParameter("checkValue", choice).getResultList();

            session.getTransaction().commit();
            session.close();

            if (results.size() != 0) {
                Object[] items = results.toArray();
                Object record = items[0];
                Stock stockrecord = (Stock) record;

                return stockrecord;
            } else {
                return null;
            }
        }
        catch(Exception e){
            System.out.println("Invalid Input, please try again."+"\n");
            return getStockById();
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

    public void asciiOut(List<Stock> records){

        System.out.println("+--------+--------------------+--------------------+------------+----------+-------+------------+");
        System.out.println("| Id     | Name               | Category           | Perishable | Cost     | Stock | Sell Price |");
        System.out.println("+--------+--------------------+--------------------+------------+----------+-------+------------+");

        for (Stock record:records) {
            System.out.format("| %-6s | %-18s | %-18s | %-10s | %-8s | %-5s | %-10s |%n",record.getId(),
                    record.getName(),record.getCategory(),record.isPerishable(),record.getCost(),record.getStock(),
                    record.getSell_price());
        }
        System.out.println("+--------+--------------------+--------------------+------------+----------+-------+------------+");
        System.out.println();
    }
}

