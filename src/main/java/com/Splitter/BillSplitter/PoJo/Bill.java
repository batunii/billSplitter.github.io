package com.Splitter.BillSplitter.PoJo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.HashMap;

@Document
public class Bill {
    @MongoId
    String restrName;
    HashMap<String, Double> people;
    Integer totalAmount;
    Integer nonVegAmount;
    Integer alcohol;


    public Bill(String restrName, HashMap<String,Double> people, Integer amount, Integer nonVegAmount,
    Integer alcohol) {
        this.restrName = restrName;
        this.people = people;
        this.totalAmount = amount;
        this.nonVegAmount = nonVegAmount;
        this.alcohol = alcohol;
    }
    public Bill()
    {}

    public HashMap<String, Double> getPeople() {
        return people;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public Integer getNonVegAmount() {
        return nonVegAmount;
    }

    public Integer getAlcohol() {
        return alcohol;
    }

    public String getRestrName() {
        return restrName;
    }
    public void setPeople(HashMap<String, Double> people)
    {
        this.people = people;
    }
}
