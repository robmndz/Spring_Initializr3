package com.example.spring_initializr3.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data // Lombok
public class Kund {

    @Id
    @SequenceGenerator(
            name = "kund_sequence",
            sequenceName = "kund_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "kund_sequence"
    )
    private Long id;
    private String namn;
    private String adress;

//       @JsonIgnore
//       @OneToMany(mappedBy = "kund")
//       private List<BuyOrder> orders;

    // Constructors (Inte aktuellt med Lombok '@Data')
    public Kund() {
    }

    public Kund(Long id, String namn, String adress) {
        this.id = id;
        this.namn = namn;
        this.adress = adress;
    }

    public Kund(String namn, String adress) {
        this.namn = namn;
        this.adress = adress;
    }
}
