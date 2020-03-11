package csc1035.project3;

import java.util.List;

public interface Interface {

    /**
     * Add an item to the Stock table taking user inputs from the user.
     */
    public void addItem();

    /**
     * Decreases the stock value of an element by n.
     *
     * @param id
     * @param n
     */
    public void deleteStock(int id, int n);


    /**
     * Increases the stock value of an element by n.
     *
     * @param id
     * @param n
     */
    public void addStock(int id, int n);


    /**
     * Print a receipt of a transaction
     */
    public void printReceipt(Transactions t);


    /**
     * Ask the user for an id and return the stock object for that id.
     * @return Stock object
     */
    public Stock getStockById();


    /**
     * Decreases/Increases the stock value of an element by n,
     * without a transaction.
     */
    public void updateStock();

    /**
     * Return all the stock in the table as a list of stock items
     * @return list in the format Stock[]
     */
    public List<Stock> getStock();

    /**
     * Takes user input of one or more id's and creates a transaction object
     * for the shopping basket, storing all the stock objects at that transaction
     * and joining them in a many-to-many relationship.
     */
    public void addTransaction();

}
