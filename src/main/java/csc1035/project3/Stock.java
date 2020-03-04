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
        this.perishable = perishable;
        this.cost = cost;
        this.stock = stock;
        this.sell_price = sell_price;
    }

    public Stock(){

    }

    /**
     * Gets the id of a product.
     *
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Gets the name of a product.
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Change the name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

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
    public boolean isPerishable() {
        return perishable;
    }

    /**
     * Change if the product is perishable or not.
     */
    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }

    /**
     * Gets the cost of the product.
     *
     * @return cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Change the cost of the product
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Gets the quantity of stock for that product.
     *
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Change the quantity of the stock for that product.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Get the sell price of the product.
     *
     * @return sell_price
     */
    public double getSell_price() {
        return sell_price;
    }

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
}
