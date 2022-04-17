package com.example.spring_initializr3.Controllers;

import com.example.spring_initializr3.Models.Produkt;
import com.example.spring_initializr3.Repositories.ProduktRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProduktControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProduktRepository mockRepository;

    @BeforeEach
    public void init() {

        Produkt p1 = new Produkt(1L, "docka", "001");
        Produkt p2 = new Produkt(2L, "nalle", "002");
        Produkt p3 = new Produkt(3L, "byxor", "003");
        when(mockRepository.findById(1L)).thenReturn(Optional.of(p1));
        when(mockRepository.findAll()).thenReturn(List.of(p1, p2, p3));
    }


    //
    @Test
    void addNew() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/produkt/add?namn=boll&produktnummer=004")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("boll is Saved")));
    }

    // http://localhost:8080/produkt/getById?id=1
    @Test
    void getById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/produkt/getById?id=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                         "id": 1,
                         "namn": "docka",
                         "produktnummer": "001"
                         }
                        """));
    }

    // http://localhost:8080/produkt/all
    @Test
    void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/produkt/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                        {
                        "id": 1,
                        "namn": "docka",
                        "produktnummer": "001"
                        },
                        {
                        "id": 2,
                        "namn": "nalle",
                        "produktnummer": "002"
                        },
                        {
                        "id": 3,
                        "namn": "byxor",
                        "produktnummer": "003"
                        }
                        ]
                        """));
    }

}