package csc1035.project3;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.persistence.Query;

/*test*/

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


        boolean perishable;
        String isperishable = "";


            System.out.println("is it perishable?(true or false)");

            Scanner myObj4 = new Scanner(System.in);
            isperishable = myObj4.nextLine();

            while(!isperishable.matches("true|false")){
                System.out.println("Not a valid input please input true or false");
                isperishable = myObj4.nextLine();
            }

            perishable = Boolean.getBoolean(isperishable);


        System.out.println("input cost(2dp)");
        Scanner myObj5 = new Scanner(System.in);
        double cost = myObj5.nextDouble();

        System.out.println("input stock");
        Scanner myObj6 = new Scanner(System.in);
        int stock = myObj6.nextInt();

        System.out.println("input sell price(2dp)");
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

    public double getPrice(int id) {
        double answer = 0.00;
        Session session = HibernateUtil.getSessionFactory().openSession
                ();
        try {
            session = (Session) HibernateUtil.getSessionFactory().openSession().
                    beginTransaction();
            Stock item = session.get(Stock.class, id);
            double price = item.getSell_price();
            answer = price;


        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return answer;
    }








        public void addTransaction(){
        List<Integer> translist = new ArrayList<Integer>();
        double TotalCost = 0;


        System.out.printf("Please input id of item");
        Scanner myObj = new Scanner(System.in); //scans for id of item to buy
        int id = myObj.nextInt();
        translist.add(id); //adds its to shopping list
        TotalCost += getPrice(id);
        deleteStock(id);
        Session session = HibernateUtil.getSessionFactory().openSession();


    }

    public void printReceipt(){

    }

    public static void main(String[] args) {
        X.addItem();


    }
}

