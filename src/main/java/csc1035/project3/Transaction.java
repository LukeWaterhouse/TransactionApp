package csc1035.project3;
import javax.persistence.*;


@Entity(name = "Transaction")
public class Transaction{

    @Id
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Stock stock;

    public Transaction(){

    }

    @Column
    private double money_given;

    @Column
    private double change_returned;

    public Transaction(double money_given, double change_returned){


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

