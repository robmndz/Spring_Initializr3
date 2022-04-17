package com.example.spring_initializr3.Controllers;

import com.example.spring_initializr3.Models.Produkt;
import com.example.spring_initializr3.Repositories.ProduktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/produkt")
public class ProduktController {

    @Autowired
    private ProduktRepository produktRepository;

    @RequestMapping("/add")
    public String addNew(@RequestParam String namn,
                         @RequestParam String produktnummer) {

        Produkt p = new Produkt();
        p.setNamn(namn);
        p.setProduktnummer(produktnummer);
        produktRepository.save(p);
        return namn + " is Saved";
    }

    @RequestMapping("/all")
    public Iterable<Produkt> getAll() {
        Iterable<Produkt> k = produktRepository.findAll();
        return k;
    }

    @RequestMapping("/getById")
    public Produkt getById(@RequestParam Long id) {
        return produktRepository.findById(id).get();
    }
}
