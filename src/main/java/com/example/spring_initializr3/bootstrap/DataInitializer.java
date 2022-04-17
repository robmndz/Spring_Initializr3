package com.example.spring_initializr3.bootstrap;

import com.example.spring_initializr3.Models.BuyOrder;
import com.example.spring_initializr3.Models.Klubbmedlem;
import com.example.spring_initializr3.Models.Kund;
import com.example.spring_initializr3.Models.Produkt;
import com.example.spring_initializr3.Repositories.BuyOrderRepository;
import com.example.spring_initializr3.Repositories.KlubbmedlemRepository;
import com.example.spring_initializr3.Repositories.KundRepository;
import com.example.spring_initializr3.Repositories.ProduktRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    //Denna metod kommer bara att köras om den har en @Bean-annotering
    //Fyller databasen före start

    private final BuyOrderRepository buyOrderRepository;
    private final KlubbmedlemRepository klubbmedlemRepository;
    private final KundRepository kundRepository;
    private final ProduktRepository produktRepository;

    public DataInitializer(BuyOrderRepository buyOrderRepository,
                           KlubbmedlemRepository klubbmedlemRepository,
                           KundRepository kundRepository,
                           ProduktRepository produktRepository) {
        this.buyOrderRepository = buyOrderRepository;
        this.klubbmedlemRepository = klubbmedlemRepository;
        this.kundRepository = kundRepository;
        this.produktRepository = produktRepository;
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {

            klubbmedlemRepository.deleteAll();
            buyOrderRepository.deleteAll();
            kundRepository.deleteAll();
            produktRepository.deleteAll();

            Kund k = new Kund("Nisse", "Drottninggatan 2");
            Kund k1 = new Kund("Bella", "Drottninggatan 3");
            Kund k2 = new Kund("Milla", "Drottninggatan 4");
            kundRepository.save(k1);

            Klubbmedlem km = new Klubbmedlem("123", k);
            klubbmedlemRepository.save(km);

            Produkt p1 = new Produkt("skor", "001");
            Produkt p2 = new Produkt("strumpor", "002");
            BuyOrder bo = new BuyOrder("34", k2, List.of(p1, p2));
            buyOrderRepository.save(bo);


        };
    }
}
