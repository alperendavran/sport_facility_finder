package com.example.project.repo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.project.model.Command;
import com.example.project.model.SportPlace;

public interface CommandDAO extends MongoRepository<Command, String>{
	
	public List<Command> findBySportPlace(SportPlace sportPlace);
	
	
}
