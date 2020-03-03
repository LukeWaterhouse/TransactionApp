package csc1035.project3;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "Transactions")
public class Transactions{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private int id;

    @ManyToMany(mappedBy = "transactions")
    private Set<Stock> stock = new HashSet<>();

    public Transactions(int id){
        this.id = id;

    }

    @Column
    private double money_given;

    @Column
    private double change_returned;

    public Transactions(double money_given, double change_returned){


        this.money_given = money_given;
        this.change_returned = change_returned;

    }
    public double getMoney_given() {
        return money_given;
    }

    public void setMoney_given(double money_given) {
        this.money_given = money_given;
    }

    public double getChange_returned() {
        return change_returned;
    }

    public void setChange_returned(double change_returned) {
        this.change_returned = change_returned;
    }

    }
