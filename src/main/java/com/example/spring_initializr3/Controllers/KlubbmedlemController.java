package com.example.spring_initializr3.Controllers;

import com.example.spring_initializr3.Models.Klubbmedlem;
import com.example.spring_initializr3.Models.Kund;
import com.example.spring_initializr3.Repositories.KlubbmedlemRepository;
import com.example.spring_initializr3.Repositories.KundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/klubbmedlem")
public class KlubbmedlemController {

    // Loggning
    private static final Logger log = LoggerFactory.getLogger(KlubbmedlemController.class);

    @Autowired
    private KundRepository kundRepository;
    @Autowired
    private KlubbmedlemRepository klubbmedlemRepository;

    /*
    En befintlig kund blir medlem.
    Vi skickar in en referens till den kund som ska bli medlem:
    http://localhost:8080/klubbmedlem/add?medlemsnummer=1&kund=1
     */
    @RequestMapping("/add")
    public String addNewKlubbmedlem(@RequestParam String medlemsnummer,
                                    @RequestParam Long kund) {

        Klubbmedlem km = new Klubbmedlem();
        km.setMedlemsnummer(medlemsnummer);

        Kund k = kundRepository.findById(kund).get();
        if (k != null) {
            km.setKund(k);
        }

        klubbmedlemRepository.save(km);
        log.info("Klubbmedlem " + medlemsnummer + " was added to database.");       // Loggning
        return "Klubbmedlem " + medlemsnummer + " is Saved.";
    }

    // Med det här 'RequestMapping' kan vi skapa en ny kund med medlemskap direkt.
    @RequestMapping("/addKlubbmedlem")
    public String addPost(@RequestParam String medlemsnummer,
                          @RequestParam String namn,
                          @RequestParam String adress) {
        klubbmedlemRepository.save(new Klubbmedlem(medlemsnummer, new Kund(namn, adress)));
        return "Saved";
    }

    // Vi kan även använda 'PostMapping' för att skapa en ny kund med medlemskap direkt.
    // Det här är en kombination mellan RequestParam och RequestBody
    // POSTMAN: localhost:8080/klubbmedlem/test?medlemsnummer=3 (vi skriver i Body JSON object)
    @PostMapping("/addKlubbmedlemPost")
    public String test(@RequestParam String medlemsnummer,
                       @RequestBody Kund k) {
        klubbmedlemRepository.save(new Klubbmedlem(medlemsnummer, k));
        return "Saved (Test)";
    }


    @RequestMapping("/all")
    public Iterable<Klubbmedlem> getAllKlubbmedlem() {
        return klubbmedlemRepository.findAll();
    }

    @RequestMapping("/getById")
    public Klubbmedlem getKMById(@RequestParam Long id) {
        return klubbmedlemRepository.findById(id).get();
    }



}
