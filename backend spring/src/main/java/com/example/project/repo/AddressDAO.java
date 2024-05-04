package com.example.project.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.project.model.Address;


public interface AddressDAO extends MongoRepository<Address, String>{

}
