package com.example.dao.controller;


import com.example.dao.repository.DaoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class DaoController {
    DaoRepository daoRepository;

    public DaoController (DaoRepository daoRepository){
        this.daoRepository = daoRepository;
    }

    @GetMapping("/products/fetch-product")
    public String  getOrderByName(@RequestParam("name") String name){
        return daoRepository.getOrderByName(name).toString();
    }
}
