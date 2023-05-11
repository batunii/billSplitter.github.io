package com.Splitter.BillSplitter.Controller;

import com.Splitter.BillSplitter.PoJo.Bill;
import com.Splitter.BillSplitter.PoJo.People;
import com.Splitter.BillSplitter.Repository.RepoBill;
import com.Splitter.BillSplitter.Repository.RepoUser;
import com.Splitter.BillSplitter.Service.BillCalc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class Controller {
    @Autowired
    BillCalc billCalc;
    @Autowired
    RepoUser repoUser;
    @Autowired
    RepoBill repoBill;

    @CrossOrigin
    @GetMapping("/getBillStruct")
    HashMap<String, Double> getBill(@RequestParam String restrName)
    {
        return billCalc.getBill(restrName);
    }
    @PostMapping("/addPerson")
    String addPeople(@RequestBody People people)
    {
        repoUser.insert(people);
        return ("Added new User: "+people.getName());
    }
    @PostMapping("/addBill")
    HashMap<String, Double> addBill(@RequestBody HashMap<String, Object> payLoad)
    {
        return billCalc.addBill(payLoad);
    }

    @PostMapping("/addSpecial")
    HashMap<String, Double>addSpecial(@RequestBody HashMap<String,Object> payLoad)
    {
        return billCalc.addSpecial((ArrayList<String>) payLoad.get("person"),
                (Double) payLoad.get("amount"), (String) payLoad.get("restrName"));
    }
}
