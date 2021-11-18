package com.example.dao.controller;


//import com.example.dao.model.Order;
import com.example.dao.repository.DaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DaoController {
    DaoRepository daoRepository;


    public DaoController (DaoRepository daoRepository){
        this.daoRepository = daoRepository;
    }

    @GetMapping("/products/fetch-order")
    public List<String>  getOrderByName(@RequestParam("name") String name){

         return daoRepository.getOrderByName(name);
    }

    //Метод работает только в случае наличия только одного Заказа (Order) у CustomName
    @GetMapping("/products/fetch-product")
    public String  getProductByName(@RequestParam("name") String name){
        return daoRepository.getProductByName(name);
    }

    //Вспомогательный метод (для разработчика)
    @GetMapping("/hello")
    public String  getHello(){
        return daoRepository.getHello();
    }
}
