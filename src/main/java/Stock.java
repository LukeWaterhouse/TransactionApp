import javax.persistence.*;

@Entity
@Table(name = "STOCK")
public class Stock {

    @Id
    @Column(name= "Id")
    private int id;

    @Column (name="Name")
    private String name;

    @Column (name= "Category")
    private String category;

    @Column(name= "Perishable")
    private boolean perishable;

    @Column(name= "Cost")
    private double cost;

    @Column(name= "Stock")
    private int stock;

    @Column(name= "Sell_price")
    private double sell_price;

    public Stock(int id, String name, String category, boolean perishable, double cost,
                 int stock, double sell_price){

        this.id=id;
        this.name=name;
        this.category=category;
        this.perishable=perishable;
        this.cost=cost;
        this.stock=stock;
        this.sell_price=sell_price;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public boolean isPerishable() {
        return perishable;
    }

    public double getCost() {
        return cost;
    }

    public int getStock() {
        return stock;
    }

    public double getSell_price() {
        return sell_price;
    }
}
