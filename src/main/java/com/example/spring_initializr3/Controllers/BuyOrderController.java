package com.example.spring_initializr3.Controllers;

import com.example.spring_initializr3.Models.BuyOrder;
import com.example.spring_initializr3.Models.Kund;
import com.example.spring_initializr3.Repositories.BuyOrderRepository;
import com.example.spring_initializr3.Repositories.KundRepository;
import com.example.spring_initializr3.Repositories.ProduktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/buyOrder")
public class BuyOrderController {

    @Autowired
    private KundRepository kundRepository;
    @Autowired
    private BuyOrderRepository buyOrderRepository;

    @RequestMapping("/add")
    public String addNewBuyOrder(@RequestParam String ordernummer,
                                    @RequestParam Long id) {

        BuyOrder bo = new BuyOrder();
        bo.setOrdernummer(ordernummer);

        Kund k = kundRepository.findById(id).get();
        if (k != null) {
            bo.setKund(k);
        }

        buyOrderRepository.save(bo);
        return "Ordernummer " + ordernummer + " is Saved.";
    }

    //Kräver cascade=CascadeType.ALL på kund i BuyOrder
    @RequestMapping("/add2")
    public String addNewBuyOrder2(@RequestParam String ordernummer,
                                  @RequestParam String namn,
                                  @RequestParam String adress) {

        BuyOrder bo = new BuyOrder();
        bo.setOrdernummer(ordernummer);

        Kund k = new Kund();
        k.setNamn(namn);
        k.setAdress(adress);
        bo.setKund(k);

        buyOrderRepository.save(bo);
        return "Order med nr " + ordernummer + " is Saved";
    }


    @RequestMapping("/all")
    public Iterable<BuyOrder> getAllBuyOrders() {
        return buyOrderRepository.findAll();
    }

    @RequestMapping("/getById")
    public BuyOrder getBuyOrderById(@RequestParam Long id) {
        return buyOrderRepository.findById(id).get();
    }
}
