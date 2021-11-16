package com.example.dao.controller;


import com.example.dao.model.Order;
import com.example.dao.repository.DaoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DaoController {
    DaoRepository daoRepository;

    public DaoController (DaoRepository daoRepository){
        this.daoRepository = daoRepository;
    }

    @GetMapping("/products/fetch-order")
    public List<String>  getOrderByName(@RequestParam("name") String name){
        List<String> listOrder = new ArrayList<>();
        List<Order> order = daoRepository.getOrderByName(name);
         for(Order o : order){
             listOrder.add(o.toString());
         }

         return listOrder;
    }

    @GetMapping("/products/fetch-product")
    public String  getProductByName(@RequestParam("name") String name){
        return daoRepository.getProductByName(name);
    }

    @GetMapping("/hello")
    public String  getHello(){
        return "Hello from DAO";
    }
}
