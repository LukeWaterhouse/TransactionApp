package csc1035.project3;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.*;

public class EPOS implements Interface {


    /**
     * Add an item to the Stock table taking user inputs from the user.
     */
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
        Session session = csc1035.project3.HibernateUtil.getSessionFactory().openSession();
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
     * Increases the stock value of an element by n.
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


    /**
     * Print a receipt of a transaction
     */
    @Override
    public void printReceipt(Transactions t){

        Set<Stock> stockList =  t.getStock();

        String s = String.format("Item               Price");
        String s1 = String.format("----               -----");
        System.out.print(s + "\n"+ s1 +"\n");

//        Transactions trans = session.get(Transactions.class, transactionID);

        double total = t.getMoney_given() - t.getChange_returned();


        for (Stock tempStock:stockList) {


            System.out.print(String.format("%-17s %5s",tempStock.getName(),tempStock.getSell_price()));
            System.out.println();
        }
        String lastline = String.format("Total              " + total+"\n");
        System.out.println(s1 +"\n"+ lastline);
        System.out.println("Paid               " + t.getMoney_given()+"\n");
        System.out.println("Change             " + t.getChange_returned()+"\n");

//       Session session = HibernateUtil.getSessionFactory().openSession();
//
//       session.beginTransaction();
//
//       Query q = session.createQuery("from Stock s where s.id = 18");
//
//       q.getResultList();
//
//        for (Object o:
//             ) {
//
//        }
//
//       System.out.println(q);
    }


    /**
     * Gets the price of a stock object using an id.
     * @param id
     * @return price of item
     */
    public double getPrice(int id) {
        double answer = 0.00;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createNamedQuery("Stock_getStockRecordById", Stock.class);
        List results = q.setParameter("checkValue", id).getResultList();
        session.getTransaction().commit();
        session.close();
        if (results.size() != 0) {
            Object[] items = results.toArray();
            Object record = items[0];
            Stock item = (Stock) record;
            double price = item.getSell_price();
            answer = price;

            return answer;
        }
        else{
            return 0.00;
        }
    }


    /**
     * Takes user input of one or more id's and creates a transaction object
     * for the shopping basket, storing all the stock objects at that transaction
     * and joining them in a many-to-many relationship.
     */
    @Override
    public void addTransaction() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Stock> translist = new ArrayList<>();
        double TotalCost = 0;
        int exitoption = 0;
        while (exitoption != 1) {
            System.out.print("Please input id of item >> ");
            Scanner myObj = new Scanner(System.in); //scans for id of item to buy
            int id = myObj.nextInt();
            Query q = session.createNamedQuery("Stock_getStockRecordById", Stock.class);
            List results = q.setParameter("checkValue", id).getResultList();
            if (results.size() != 0) {
                Object[] items = results.toArray();
                Object record = items[0];
                Stock item = (Stock) record;
                translist.add(item); //adds its to shopping list
                TotalCost += getPrice(id);// adds cost to total price
                deleteStock(id, 1);
                System.out.print("Please press 1 to exit or 2 to continue shopping >> ");
                exitoption = myObj.nextInt();
            }
        }
        double moneyGiven = 0.00;
        Scanner myObj2 = new Scanner(System.in);

        while (moneyGiven < TotalCost) {
            System.out.print("asking price is " + TotalCost+"\n");
            System.out.print("Please input the money to give (must be at least as much as the price) >> ");
            moneyGiven = myObj2.nextDouble();
        }
        double change = moneyGiven - TotalCost;
        Set<Stock> stockSet = new HashSet<>(translist);
        Transactions X = new Transactions(moneyGiven,change);
        X.setStock(stockSet);
        session.persist(X);
        session.getTransaction().commit();
        printReceipt(X);
    }

    /**
     * Ask the user for an id and return the stock object for that id.
     * @return Stock object
     */
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

    /**
     * Return all the stock in the table as a list of stock items
     * @return list in the format Stock[]
     */
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


    /**
     * Take an input of a list of stock records and print it out in the form of an ascii table
     * @param records
     */
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