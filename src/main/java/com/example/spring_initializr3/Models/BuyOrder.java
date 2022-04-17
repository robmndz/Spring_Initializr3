package com.example.spring_initializr3.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class BuyOrder {

    @Id
    @SequenceGenerator(
            name = "buyOrder_sequence",
            sequenceName = "buyOrder_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "buyOrder_sequence"
    )
    private Long id;
    private String ordernummer;

    /*
    @ManyToOne
    @JoinColumn(name="kundId", referencedColumnName = "id")
    private Kund kund;
     */

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn
    private Kund kund;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable
    private List<Produkt> products;

    // Constructors
    public BuyOrder() {
    }

    public BuyOrder(String ordernummer, Kund kund) {
        this.ordernummer = ordernummer;
        this.kund = kund;
    }

    public BuyOrder(String ordernummer, Kund kund, List<Produkt> products) {
        this.ordernummer = ordernummer;
        this.kund = kund;
        this.products = products;
    }
}
