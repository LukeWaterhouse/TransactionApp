<<<<<<< HEAD
package csc1035.project3;

import javax.persistence.*;


@Entity(name = "Stock")
=======
package main.java.csc1035.project3;

import javax.persistence.*;

>>>>>>> 7e718d9bb89c6bed3c08753bec6222c14401eb9c
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Stock_id", updatable = false, nullable = false)
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

    public Stock(int id, String name, String catergory, boolean perishable, double cost, int stock, double sell_price){

        this.id = id;
        this.name = name;
        this.catergory = catergory;
        this.perishable = perishable;
        this.cost = cost;
        this.stock = stock;
        this.sell_price = sell_price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatergory() {
        return catergory;
    }

    public void setCatergory(String catergory) {
        this.catergory = catergory;
    }

    public boolean isPerishable() {
        return perishable;
    }

    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getSell_price() {
        return sell_price;
    }

    public void setSell_price(double sell_price) {
        this.sell_price = sell_price;
    }
}
<<<<<<< HEAD

=======
>>>>>>> 7e718d9bb89c6bed3c08753bec6222c14401eb9c
