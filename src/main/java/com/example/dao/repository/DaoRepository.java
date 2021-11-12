package com.example.dao.repository;

import com.example.dao.model.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class DaoRepository {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DaoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public Order getOrderByName(String name){
        String selectSql = read("getOrderByName_select.sql");
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("name", name);
        return namedParameterJdbcTemplate
                .queryForObject(selectSql, namedParameters, Order.class);
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

