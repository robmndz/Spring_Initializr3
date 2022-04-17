package com.example.spring_initializr3.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data // Lombok
public class Produkt {

    @Id
    @SequenceGenerator(
            name = "produkt_sequence",
            sequenceName = "produkt_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "produkt_sequence"
    )
    private Long id;
    private String namn;
    private String produktnummer;

    // Constructors (Inte aktuellt med Lombok '@Data')

    public Produkt() {
    }

    public Produkt(String namn, String produktnummer) {
        this.namn = namn;
        this.produktnummer = produktnummer;
    }

    public Produkt(Long id, String namn, String produktnummer) {
        this.id = id;
        this.namn = namn;
        this.produktnummer = produktnummer;
    }
}
