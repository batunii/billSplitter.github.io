package com.Splitter.BillSplitter.PoJo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class People {
    @MongoId
    String name;
    boolean eating = true;
    boolean non_veg;
    boolean drinks;

    public People(String name, boolean eating, boolean non_veg, boolean drinks) {
        this.name = name;
        this.eating = eating;
        this.non_veg = non_veg;
        this.drinks = drinks;
    }
    public People(String name, boolean non_veg, boolean drinks) {
        this.name = name;
        this.non_veg = non_veg;
        this.drinks = drinks;
    }
    public People(){};
    public String getName() {
        return name;
    }

    public boolean isEating() {
        return eating;
    }

    public boolean isNon_veg() {
        return non_veg;
    }

    public boolean isDrinks() {
        return drinks;
    }
}
