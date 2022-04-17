package com.example.spring_initializr3.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data // Lombok
public class Klubbmedlem {

    @Id
    @SequenceGenerator(
            name = "klubbmedlem_sequence",
            sequenceName = "klubbmedlem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "klubbmedlem_sequence"
    )
    private Long id;
    private String medlemsnummer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Kund kund;

    /*
    @OneToOne
    @JoinColumn(name="kundId", referencedColumnName = "id")
    private Kund kund;
     */

    // Constructors (Inte aktuellt med Lombok '@Data')
    public Klubbmedlem() {
    }

    public Klubbmedlem(Long id, String medlemsnummer) {
        this.id = id;
        this.medlemsnummer = medlemsnummer;
    }


    // Jag skapar en konstruktor med båda 'medlemsnummer' och 'kund'
    // för att kunna skaffa en ny kund med medlemskapet i KlubbmedlemController
    public Klubbmedlem(String medlemsnummer, Kund kund) {
        this.medlemsnummer = medlemsnummer;
        this.kund = kund;
    }
}
