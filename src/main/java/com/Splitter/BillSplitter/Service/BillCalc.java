package com.Splitter.BillSplitter.Service;

import com.Splitter.BillSplitter.PoJo.Bill;
import com.Splitter.BillSplitter.PoJo.People;
import com.Splitter.BillSplitter.Repository.RepoBill;
import com.Splitter.BillSplitter.Repository.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

@Service
public class BillCalc {
    @Autowired
    RepoUser repoUser;
    @Autowired
    RepoBill repoBill;

    public HashMap<String,Double> getBill(String restrName)
    {
        return repoBill.findById(restrName).get().getPeople();
    }

  public HashMap<String, Double> calcBill(ArrayList<String>peopleList,
                                          HashMap<String,Double>structBill,
                                          Integer totalAmount,
                                          Integer nonVegAmount,
                                          Integer alcohol)
  {
        ArrayList<People>people = new ArrayList<>(
              repoUser.findAllById(peopleList));

      int indexAll = people.size();
      long indexNonVeg = people.stream()
              .filter(People::isNon_veg).count();
      long indexAlcohol = people.stream()
              .filter(People::isDrinks).count();

      for (People person : people) {
          Double amount = 0.0;
          if(person.isNon_veg())
          {amount = amount+nonVegAmount/indexNonVeg;}
          if(person.isDrinks())
          {amount = amount+alcohol/indexAlcohol;}
          amount = amount+totalAmount/indexAll;
          structBill.put(person.getName(),amount);
      }
      return structBill;
  }

  public HashMap<String, Double> addSpecial(ArrayList<String> personName,
                                            Double amount, String restrName)
  {
      Bill newBill = repoBill.findById(restrName).orElseGet(()->
      {
          HashMap<String,Double> bills = new HashMap<>();
          for(String person : personName)
          {
              bills.put(person,0.0);
          }
          return new Bill(restrName,bills,amount.intValue(),0,0);
      });
      HashMap<String, Double> structBill = new HashMap<>(newBill.getPeople());
      for(String person:personName)
      {if(!structBill.containsKey(person))
      {structBill.putIfAbsent(person,amount/ personName.size());}
      else {
          structBill.computeIfPresent(person, (k, v) -> {
              v = v + (amount/ personName.size());
              return v;
          });
      }}
      newBill.setPeople(structBill);
      repoBill.save(newBill);
    return structBill;
  }

    public HashMap<String,Double> addBill(HashMap<String,Object>payLoad) {
      HashMap<String,Double> billPeople = new HashMap<>();
      for(String person : (ArrayList<String>)payLoad.get("people"))
      {
          billPeople.put(person,0.0);
      }
      repoBill.insert(new Bill(
              (String)payLoad.get("restrName"), calcBill(
              (ArrayList<String>)payLoad.get("people"),billPeople,(Integer)payLoad.get("totalAmount"),
              (Integer)payLoad.get("nonVegAmount"),(Integer)payLoad.get("alcohol")),
              (Integer)payLoad.get("totalAmount"),(Integer)payLoad.get("nonVegAmount"),
              (Integer)payLoad.get("alcohol")
      ));
      return billPeople;
    }
}
