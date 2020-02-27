import javax.persistence.*;

@Entity(name = "Stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column
    private String product;

    @Column
    private String category;

    @Column
    private boolean perishable;

    @Column
    private float cost;

    @Column
    private int productLevel;

    @Column
    private float sell_price;

    public Stock(int id, String product, String category, boolean perishable, float cost, int productLevel, float sell_price){
        this.id = id;
        this.product = product;
        this.category = category;
        this.perishable = perishable;
        this.cost = cost;
        this.sell_price = sell_price;
        this.productLevel = productLevel;
    }

    public Stock(int id) { this.id = id; }

    public int getId(){
        return id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isPerishable() {
        return perishable;
    }

    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getProductLevel() {
        return productLevel;
    }

    public void setProductLevel(int productLevel) {
        this.productLevel = productLevel;
    }

    public float getSell_price() {
        return sell_price;
    }

    public void setSell_price(float sell_price) {
        this.sell_price = sell_price;
    }
}
