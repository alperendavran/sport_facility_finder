package com.example.project.repo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.model.SportPlace;

public interface SportPlaceDAO extends MongoRepository<SportPlace, String>{
	
	public List<SportPlace> findBySportType(String sportType);
	//public List<SportPlace> findBySportTypeAndCityAndDistrict(String sportType, String city, String district);
	public SportPlace findByName(String name);
	public List<SportPlace> findByNameIgnoreCase(String name);
	public List<SportPlace> findByNameContainsIgnoreCase(String name);

	
}
