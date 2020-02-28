package csc1035.project3;

import javax.persistence.*;


@Entity(name = "Transcation")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;


}