package csc1035.project3;

import javax.persistence.*;


@Entity(name = "Transcation")
public class Transaction{


    @Column
    private double money_given;

    @Column
    private double change_returned;



    }

