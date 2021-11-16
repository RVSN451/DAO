package com.example.dao.repository;

import com.example.dao.model.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaoRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String selectOrderByName;
    private final String selectProductByName;


    public DaoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.selectOrderByName = read("getOrderByName_select.sql");
        this.selectProductByName = read("getProductByName_select.sql");
    }


    public List<Order> getOrderByName(String name){
        List<Order> listOrder= new ArrayList<>();
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("name", name);
        SqlRowSet rowSet = namedParameterJdbcTemplate
                .queryForRowSet(selectOrderByName, namedParameters);
        while (rowSet.next()){
            listOrder.add(new Order(
                    rowSet.getString("customName"),
                    rowSet.getString("surname"),
                    rowSet.getString("product_name"),
                    rowSet.getString("amount"))
            );
        }
        return listOrder;
    }


    public String  getProductByName(String name) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("name", name);
        return namedParameterJdbcTemplate
                .queryForObject(selectProductByName, namedParameters, String.class);
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

