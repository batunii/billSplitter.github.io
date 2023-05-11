package com.Splitter.BillSplitter.Repository;

import com.Splitter.BillSplitter.PoJo.People;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUser extends MongoRepository<People, String> {
}
