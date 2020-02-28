<<<<<<< HEAD
/**
 * @author Ben Thompson-Watson
 * @version 1.2
 * @since 28-02-2020
 */


package csc1035.project3;

import javax.persistence.*;

/**
 * Class table for stock.
 */

@Entity(name = "Stock")
@NamedQuery(name = "Stock_getStockRecordById", query= "from Stock s where s.id = :checkValue")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

//    @OneToOne(mappedBy = "transaction")
//    private

    @Column
    private String name;

    @Column
    private String category;

    @Column
    private boolean perishable;

    @Column
    private double cost;

    @Column
    private int stock;

    @Column
    private double sell_price;

    /**
     * Constructor for Stock when a new object is created.
     * Initialises values for object.
     *
     * @param name name of the stock.
     * @param category name of category.
     * @param perishable is the product perishable or not.
     * @param cost how much the product is.
     * @param stock how much there is of this product.
     * @param sell_price how much it is sold for in the shop.
     */

    public Stock(String name, String category, boolean perishable, double cost, int stock, double sell_price){

        this.name = name;
        this.category = category;
=======
package main.java.csc1035.project3;

import javax.persistence.*;

@Entity(name = "Stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "catergory")
    private String catergory;

    @Column(name = "perishable")
    private boolean perishable;

    @Column(name = "cost")
    private double cost;

    @Column(name = "stock")
    private int stock;

    @Column(name = "sell_price")
    private double sell_price;

    public Stock(String name, String catergory, boolean perishable, double cost, int stock, double sell_price){

        this.name = name;
        this.catergory = catergory;
>>>>>>> Adding master branch files.
        this.perishable = perishable;
        this.cost = cost;
        this.stock = stock;
        this.sell_price = sell_price;
    }

<<<<<<< HEAD
    public Stock(){

    }

    /**
     * Gets the id of a product.
     *
     * @return id
     */
=======
>>>>>>> Adding master branch files.
    public int getId() {
        return id;
    }

<<<<<<< HEAD

    /**
     * Gets the name of a product.
     *
     * @return name
     */
=======
>>>>>>> Adding master branch files.
    public String getName() {
        return name;
    }

<<<<<<< HEAD

    /**
     * Change the name of the product
     */
=======
>>>>>>> Adding master branch files.
    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
    /**
     * Gets the category of a product.
     *
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Change the category of the product
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns true or false depending on if the product
     * is perishable.
     *
     * @return perishable
     */
=======
    public String getCatergory() {
        return catergory;
    }

    public void setCatergory(String catergory) {
        this.catergory = catergory;
    }

>>>>>>> Adding master branch files.
    public boolean isPerishable() {
        return perishable;
    }

<<<<<<< HEAD
    /**
     * Change if the product is perishable or not.
     */
=======
>>>>>>> Adding master branch files.
    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }

<<<<<<< HEAD
    /**
     * Gets the cost of the product.
     *
     * @return cost
     */
=======
>>>>>>> Adding master branch files.
    public double getCost() {
        return cost;
    }

<<<<<<< HEAD
    /**
     * Change the cost of the product
     */
=======
>>>>>>> Adding master branch files.
    public void setCost(double cost) {
        this.cost = cost;
    }

<<<<<<< HEAD
    /**
     * Gets the quantity of stock for that product.
     *
     * @return stock
     */
=======
>>>>>>> Adding master branch files.
    public int getStock() {
        return stock;
    }

<<<<<<< HEAD
    /**
     * Change the quantity of the stock for that product.
     */
=======
>>>>>>> Adding master branch files.
    public void setStock(int stock) {
        this.stock = stock;
    }

<<<<<<< HEAD
    /**
     * Get the sell price of the product.
     *
     * @return sell_price
     */
=======
>>>>>>> Adding master branch files.
    public double getSell_price() {
        return sell_price;
    }

<<<<<<< HEAD
    /**
     * Change the sell_price of the product
     */
    public void setSell_price(double sell_price) {
        this.sell_price = sell_price;
    }

    /**
     * String representation of stock object.
     */

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", perishable=" + perishable +
                ", cost=" + cost +
                ", stock=" + stock +
                ", sell_price=" + sell_price +
                '}';
    }
=======
    public void setSell_price(double sell_price) {
        this.sell_price = sell_price;
    }
>>>>>>> Adding master branch files.
}
