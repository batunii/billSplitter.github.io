package com.Splitter.BillSplitter.Repository;

import com.Splitter.BillSplitter.PoJo.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoBill extends MongoRepository<Bill, String> {
}
