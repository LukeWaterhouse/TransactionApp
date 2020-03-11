package csc1035.project3;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Class table for transaction
 */


@Entity(name = "Transactions")
public class Transactions{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id", updatable = false, nullable = false)
    private int id;

    @ManyToMany
    @JoinTable(
            name = "STOCK_TRANSACTION",
            joinColumns = {@JoinColumn(name = "transaction_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    public Set<Stock> stock = new HashSet<>();

    /**
     * Creates a join table between the stock_id and transactions id.
     * Implements many to many relationship.

     */

    @Column
    private double money_given;

    @Column
    private double change_returned;

    /**
     * Constructor for Transaction when a new object is created.
     * Initialises values for object.
     *
     * @param money_given how much money is given by the customer.
     * @param change_returned how much change is returned to the customer.

     */


    public Transactions( double money_given, double change_returned){

        this.money_given = money_given;
        this.change_returned = change_returned;

    }
    /**
     * Gets the money given by the customer.
     *
     * @returns money given.
     */
    public double getMoney_given() {
        return money_given;
    }

    /**
     * Change the money given by the customer.
     */

    public void setMoney_given(double money_given) {
        this.money_given = money_given;
    }
    /**
     * Gets the change returned to the customer.
     *
     * @returns change returned.
     */

    public double getChange_returned() {
        return change_returned;
    }
    /**
     * Sets the change given to the customer.
     */

    public void setChange_returned(double change_returned) {
        this.change_returned = change_returned;
    }
    /**
     * Gets the stock object.
     *
     * @returns stock.
     */

    public Set<Stock> getStock() {
        return stock;
    }

    /**
     * Sets the stock object.
     */
    public void setStock(Set<Stock> stock) {
        this.stock = stock;
    }


    }
