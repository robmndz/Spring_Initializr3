package com.example.spring_initializr3.Controllers;


import com.example.spring_initializr3.Models.Kund;
import com.example.spring_initializr3.Repositories.KundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/kund")
public class KundController {

    // Loggning
    private static final Logger log = LoggerFactory.getLogger(KundController.class);

    @Autowired
    private KundRepository kundRepository;


    @RequestMapping("/add")
    public String addNewUser(@RequestParam String namn,
                             @RequestParam (required = false) String adress) {

        Kund n = new Kund();
        n.setNamn(namn);
        n.setAdress(adress);
        kundRepository.save(n);
        log.info("Kund " + namn + " was added to database.");       // Loggning
        return namn + " is saved";
    }

    // 'Add' with Post (to use Postman)
    @PostMapping("/addPost")
    public String addPost(@RequestBody Kund k) {
        kundRepository.save(k);
        return "Saved";
    }

    @RequestMapping("/all")
    public Iterable<Kund> getAllKunder() {
        return kundRepository.findAll();
    }

    @RequestMapping("/getById")
    public Kund getKunderById(@RequestParam Long id) {
        return kundRepository.findById(id).get();
    }
}
