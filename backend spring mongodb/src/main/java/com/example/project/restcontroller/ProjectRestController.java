package com.example.project.restcontroller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Base64;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.model.Address;
import com.example.project.model.Command;
import com.example.project.model.CommandPayload;
import com.example.project.model.SportPlace;
import com.example.project.repo.AddressDAO;
import com.example.project.repo.CommandDAO;
import com.example.project.repo.SportPlaceDAO;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/SportFacilityFinder")
public class ProjectRestController {
	
	@Autowired private AddressDAO addressDAO;
	@Autowired private SportPlaceDAO sportPlaceDAO;
	@Autowired private CommandDAO commandDAO;
	
	@PostConstruct
	public void init() throws IOException {
		
		
		
			
			Address adr1 = new Address("istanbul", "PENDİK", "güllü baglar", "tandogan", "15");
			Address adr2 = new Address("istanbul", "KARTAL", "karliktepe", "soganlik", "28");
			Address adr3 = new Address("istanbul", "TUZLA", "postane", "yaliboyu", "69");
			Address adr4 = new Address("istanbul", "TUZLA", "orta", "üniversite", "40");
			Address adr5= new Address("istanbul", "TUZLA", "postane", "çınarlı", "67");
			Address adr6= new Address("istanbul", "TUZLA", "mercan", "çınarlı", "67");
			Address adr7 = new Address("istanbul", "TUZLA", "tepeören", "karadağ", "6");
			Address adr8 = new Address("istanbul", "TUZLA", "orta", "antakya", "13");
			Address adr9 = new Address("istanbul", "TUZLA", "defne", "kırıkhan", "31");
			Address adr10 = new Address("antalya", "ALANYA", "defne", "kırıkhan", "31");


			addressDAO.save(adr1);
			addressDAO.save(adr2);
			addressDAO.save(adr3);
			addressDAO.save(adr4);
			addressDAO.save(adr5);
			addressDAO.save(adr6);
			addressDAO.save(adr7);
			addressDAO.save(adr8);
			addressDAO.save(adr9);
			addressDAO.save(adr10);

		
			byte[] imageBytes133=null;
			
	
	        byte[] imageBytes1 = loadImageAsBytes("/Users/alperendavran/Downloads/kort-1.jpeg");
	        byte[] imageBytes2 = loadImageAsBytes("/Users/alperendavran/Downloads/kort-2.jpeg");
	        byte[] imageBytes3 = loadImageAsBytes("/Users/alperendavran/Downloads/havuz-1.jpeg");
	        byte[] imageBytes4 = loadImageAsBytes("/Users/alperendavran/Downloads/bina-1.jpeg");
	        byte[] imageBytes5 = loadImageAsBytes("/Users/alperendavran/Downloads/basket-1.jpeg");
	        byte[] imageBytes6 = loadImageAsBytes("/Users/alperendavran/Downloads/havuz-2.jpeg");
	        String base64Image1 = loadImageAsBase64String("/Users/alperendavran/Downloads/kort-1.jpeg");
	        String base64Image2 = loadImageAsBase64String("/Users/alperendavran/Downloads/kort-2.jpeg");
	     // Load images as Base64 strings
	        String base64Image3 = loadImageAsBase64String("/Users/alperendavran/Downloads/havuz-1.jpeg");
	        String base64Image4 = loadImageAsBase64String("/Users/alperendavran/Downloads/bina-1.jpeg");
	        String base64Image5 = loadImageAsBase64String("/Users/alperendavran/Downloads/basket-1.jpeg");
	        String base64Image6 = loadImageAsBase64String("/Users/alperendavran/Downloads/havuz-2.jpeg");


	        String url1="https://uskon.com/wp-content/uploads/2021/07/sabanci-universitesi-spor-salonu-turkiye.jpg";
	        String url2="https://spor.istanbul/wp-content/uploads/2020/06/kort-1.jpg";
	        String url3="https://spor.istanbul/wp-content/uploads/elementor/thumbs/ACXQ6437-1-q9my4l7cltmrdadjlu2dlgyo1f4i629icbn3w4vxdk.jpg"; 
	        String url4="https://www.rodium.com.tr/rodium/galeri/8.jpg"; //rodium spor
	        String url5="https://rodium.com.tr/rodium/tenis/tenis2.jpg"; //rodium
	        String url6="https://www.timursport.com/wp-content/uploads/2015/06/1-1.jpg"; //roduim basket
	        String url7="https://www.aykanzemin.com/wp-content/uploads/2020/01/akrilik-tenis-.jpg"; //tenis
	        String url8="https://fs.ihu.edu.tr/siteler/spormerkezi.ihu.edu.tr/contents/6272e5ae0a681/0x0-dsc-5192-jpg.jpg"; //tenis
	        String url9="https://ahesinsaat.com/uploads/images/k-b-b-gebze-olimpik-yuzme-havuzu-yapim-insaati-49292.jpg"; //yüzme
	        String url10="https://bergama.bel.tr/wp-content/uploads/olimpik-yuzme-havuzu.jpg"; // yüzme
	        String url11="https://spor.istanbul/wp-content/uploads/elementor/thumbs/DJI_0296-q9ln6uo2rkiccstahfqd9vc4dfmgy24i0wg2529yuw.jpg";
	        String url12="https://spor.istanbul/wp-content/uploads/elementor/thumbs/tenis-2-q9ljovcsry1ua9t34hbgl5t25akgagzmdcq9kz2it4.png";
	        String url13="https://spor.istanbul/wp-content/uploads/elementor/thumbs/TRN_0772-q9lf2kqpo4em9tcz63kkymiaq87pi7t6i9qs1nmxmg.jpg";
	        String url14="https://spor.istanbul/wp-content/uploads/2020/06/kort-1.jpg";
	        		
	        // Create SportPlace objects with Base64 images
	        SportPlace plc1 = new SportPlace("ibb pendik spor tesisi", "Football", adr1, "ChIJ126upF_ayhQRV1PbnwE0y58", url3, "0", "\"This state-of-the-art football facility boasts a FIFA-standard pitch and modern amenities. Easily accessible by the E-5 metrobus line, it's perfect for both casual players and professional training. The complex also offers regular workshops and community events");
	        SportPlace plc2 = new SportPlace("ibb kartal hasan doğan spor kompleksi", "Tennis", adr2, "ChIJR80lc3nDyhQRW4we9R5t21E", url2, "0", "Nestled in the heart of Kartal, this tennis complex features multiple clay courts and is easily reachable via the Marmaray line. The facility caters to all levels, offering coaching for beginners and advanced players. Its serene setting makes it");
	        SportPlace plc3 = new SportPlace("ibb tuzla spor tesisi", "Swimming", adr3, "ChIJh493psPdyhQR77TsyhpNcGY", url9, "0", "This swimming center in Tuzla offers Olympic-sized pools and advanced water filtration systems, making it a swimmer's paradise. Reachable via Tuzla train station, it's a popular spot for both competitive swimmers and families. The center also hosts regular aqua-fitness classes");
	        SportPlace plc4 = new SportPlace("sabancı üniversitesi tenis kortu", "Tennis", adr4, "ChIJ-8rEGFLYyhQRemNathDxDPw", url1, "0", "Located within the expansive Sabancı University campus, these tennis courts are a favorite among students and staff. They're easily accessible by university shuttles and offer both hard and clay court options. The courts are often used for inter-collegiate tournaments");
	        SportPlace plc5 = new SportPlace("ibb tuzla sahil spor tesisi", "Tennis", adr5, "ChIJh493psPdyhQR77TsyhpNcGY", url2, "0", "Situated by the picturesque Tuzla coastline, this tennis facility offers breathtaking sea views. It's a short walk from the Tuzla Marina and provides both indoor and outdoor courts for year-round play. The facility is known for its summer tennis camps for children");
	        SportPlace plc6 = new SportPlace("mercan tenis kulübü", "Tennis",  adr6, "ChIJ1xFeszPcyhQRVTBO5LY0Ajk", url8, "0", "This exclusive tennis club in Mercan district boasts top-notch facilities, including LED-lit courts for night play. It's conveniently accessible by the metro and offers a range of membership options. The club regularly hosts local tennis leagues and tournaments");
	        SportPlace plc7 = new SportPlace("milenyum tenis akademisi", "Tennis", adr7, "ChIJMyZowjjXyhQR8nPhwhR4l1Y", url7, "0", "Renowned for its elite coaching staff, Milenyum Tennis Academy is a hub for budding tennis talents. Located near key bus routes, it's easy to reach and offers state-of-the-art training equipment. The academy also organizes yearly tennis clinics with former professionals");
	        SportPlace plc8 = new SportPlace("defne tenis akademisi", "Tennis",  adr8, "ChIJ9xW92LbdyhQRF0_tDwFjzIM", url8, "0", "Defne Tennis Academy offers a family-friendly environment with a focus on junior development programs. Situated close to main city arteries, it's easily accessible by public transport. The academy also features a pro-shop and a cafe for spectators and players");
	        SportPlace plc9 = new SportPlace("rodium", "Tennis", adr9, "ChIJGbhFbEfdyhQRDm2xs0eooeg", url5, "0", "This multi-sport complex in Rodium district is a one-stop destination for sports enthusiasts. Whether it's tennis, swimming, or basketball, the facility offers top-tier courts and pools. It's a short drive from the city center and hosts various community sports events and leagues");
	        SportPlace plc10 = new SportPlace("rodium", "Swimming", adr9, "ChIJqxva5QTcyhQRrPCCS2q5rc4", url4, "0", "This multi-sport complex in Rodium district is a one-stop destination for sports enthusiasts. Whether it's tennis, swimming, or basketball, the facility offers top-tier courts and pools. It's a short drive from the city center and hosts various community sports events and leagues.");
	        SportPlace plc11 = new SportPlace("rodium", "Basketball", adr9, "ChIJXbj_JLPdyhQRiKYZ0DuJGvY", url6, "0", "This multi-sport complex in Rodium district is a one-stop destination for sports enthusiasts. Whether it's tennis, swimming, or basketball, the facility offers top-tier courts and pools. It's a short drive from the city center and hosts various community sports events and leagues.");
	        SportPlace plc12 = new SportPlace("ibb pendik spor tesisi", "Basketball", adr1, "ChIJ126upF_ayhQRV1PbnwE0y58", url6, "0", "Known for its versatile sports offerings, IBB Pendik Spor Tesisi provides excellent basketball and swimming facilities. The center is a hub for local leagues and is accessible by several bus routes. It also has a fitness center and offers personal training services.");
	        SportPlace plc13 = new SportPlace("ibb pendik spor tesisi", "Swimming", adr1, "ChIJ126upF_ayhQRV1PbnwE0y58", url10, "0", "Known for its versatile sports offerings, IBB Pendik Spor Tesisi provides excellent basketball and swimming facilities. The center is a hub for local leagues and is accessible by several bus routes. It also has a fitness center and offers personal training services.");
	        SportPlace plc14 = new SportPlace("ibb kartal hasan doğan spor kompleksi", "Football", adr2, "ChIJR80lc3nDyhQRW4we9R5t21E", url3, "0", "Nestled in the heart of Kartal, this tennis complex features multiple clay courts and is easily reachable via the Marmaray line. The facility caters to all levels, offering coaching for beginners and advanced players. Its serene setting makes it");
	        SportPlace plc15 = new SportPlace("ibb tuzla spor tesisi", "Tennis", adr8, "ChIJh493psPdyhQR77TsyhpNcGY", url11, "0", "This swimming center in Tuzla offers Olympic-sized pools and advanced water filtration systems, making it a swimmer's paradise. Reachable via Tuzla train station, it's a popular spot for both competitive swimmers and families. The center also hosts regular aqua-fitness classes");
	        SportPlace plc16 = new SportPlace("ibb tuzla spor tesisi", "Football", adr8, "ChIJh493psPdyhQR77TsyhpNcGY", url13, "0", "This swimming center in Tuzla offers Olympic-sized pools and advanced water filtration systems, making it a swimmer's paradise. Reachable via Tuzla train station, it's a popular spot for both competitive swimmers and families. The center also hosts regular aqua-fitness classes");
	        SportPlace plc17 = new SportPlace("ibb pendik spor tesisi", "Tennis", adr1, "ChIJ126upF_ayhQRV1PbnwE0y58", url12, "0", "Known for its versatile sports offerings, IBB Pendik Spor Tesisi provides excellent basketball and swimming facilities. The center is a hub for local leagues and is accessible by several bus routes. It also has a fitness center and offers personal training services.");
	        SportPlace plc18 = new SportPlace("antalya ihtisas spor tesisi", "Tennis", adr10, "ChIJD4JKK7pI0xQRnRcBhdgnFOQ", url5, "0", "Known for its versatile sports offerings, ANTALAYA  Spor Tesisi provides excellent basketball and swimming facilities. The center is a hub for local leagues and is accessible by several bus routes. It also has a fitness center and offers personal training services.");
	        SportPlace plc19 = new SportPlace("pendik halısaha", "Football", adr1, "ChIJ6RRn_1vayhQRch3W76O6WgQ", url12, "0", "Known for its versatile sports offerings, IBB Pendik Spor Tesisi provides excellent basketball and swimming facilities. The center is a hub for local leagues and is accessible by several bus routes. It also has a fitness center and offers personal training services.");
	        SportPlace plc20 = new SportPlace("kanarya halısaha", "Football", adr1, "ChIJuy1Zg2vbyhQRH92vVemIkhk", url12, "0", "Known for its versatile sports offerings, IBB Pendik Spor Tesisi provides excellent basketball and swimming facilities. The center is a hub for local leagues and is accessible by several bus routes. It also has a fitness center and offers personal training services.");


			sportPlaceDAO.save(plc1);
			sportPlaceDAO.save(plc2);
			sportPlaceDAO.save(plc3);
			sportPlaceDAO.save(plc4);
			sportPlaceDAO.save(plc5);
			sportPlaceDAO.save(plc6);
			sportPlaceDAO.save(plc7);
			sportPlaceDAO.save(plc8);
			sportPlaceDAO.save(plc9);
			sportPlaceDAO.save(plc10);
			sportPlaceDAO.save(plc11);
			sportPlaceDAO.save(plc12);
			sportPlaceDAO.save(plc13);



			Command com1 = new Command("merhaba",  LocalDateTime.now(), "sevimlikullanici", plc1);
			Command com2 = new Command("selam",  LocalDateTime.now(), "muthiskullanici", plc2);
			Command com3 = new Command("harika",  LocalDateTime.now(), "harikakullanici", plc3);
			
			commandDAO.save(com1);
			commandDAO.save(com2);
			commandDAO.save(com3);
			
		
	}
	
	
    @GetMapping("/sportPlaces/{id}")
    public ResponseEntity<SportPlace> findSportPlaceById(@PathVariable String id) {
        return sportPlaceDAO.findById(id)
                .map(sportPlace -> ResponseEntity.ok(sportPlace))
                .orElse(ResponseEntity.notFound().build());
    }
    
	
    @PostMapping("/sportPlaces/{id}/addRating")
    public ResponseEntity<?> addRatingToSportPlace(@PathVariable String id, @RequestParam double rating) {
        // SportPlace belgesini veritabanından al
        Optional<SportPlace> sportPlaceOptional = sportPlaceDAO.findById(id);
        
        if (!sportPlaceOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        SportPlace sportPlace = sportPlaceOptional.get();
        
        // Yeni derelemeyi ekleyerek güncelle
        sportPlace.addRating(rating);
        sportPlaceDAO.save(sportPlace);
        
        return ResponseEntity.ok("Rating added successfully");
    }
	
	
    @GetMapping("/sportPlaces/{id}/getAverageRating")
    public ResponseEntity<Double> getAverageRatingOfSportPlace(@PathVariable String id) {
        Optional<SportPlace> sportPlaceOptional = sportPlaceDAO.findById(id);

        if (!sportPlaceOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        SportPlace sportPlace = sportPlaceOptional.get();
        double averageRating = sportPlace.getAverageRating();
        
        return ResponseEntity.ok(averageRating);
    }


public String loadImageAsBase64String(String imagePath) throws IOException {
    Path path = Paths.get(imagePath);
    byte[] imageBytes = Files.readAllBytes(path);
    return Base64.getEncoder().encodeToString(imageBytes);
}

	public byte[] loadImageAsBytes(String imagePath) throws IOException {
	    Path path = Paths.get(imagePath);
	    return Files.readAllBytes(path);
	}
	
	
	/********************* ADDRESS ****************************/
	
	@GetMapping("/addresses")
	public List<Address> addresses(){
		return addressDAO.findAll();
	}
	
	/********************* SPORTPLACE ****************************/
	
	@GetMapping("/sportPlaces")
	public List<SportPlace> sportPlaces(){
		return sportPlaceDAO.findAll();
	}
	
	@PostMapping("/sportPlaces/searchByName")
	public List<SportPlace> searchSportPlaceByName(@RequestBody SportPlace sportPlace){
		List<SportPlace> sportPlaces = sportPlaceDAO.findByNameContainsIgnoreCase(sportPlace.getName());	
		return sportPlaces;
	}
	
	@PostMapping("/sportPlaces/searchBySportType")
	public List<SportPlace> searchSportPlaceByType(@RequestBody SportPlace sportPlace){
		List<SportPlace> sportPlaces = sportPlaceDAO.findBySportType(sportPlace.getSportType());	
		return sportPlaces;
	}
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public List<SportPlace> findBySportTypeAndCityAndDistrict(String sportType, String city, String district) {
	    // Case-insensitive search for city and district
	    Criteria cityCriteria = Criteria.where("address.city").regex("^" + Pattern.quote(city) + "$", "i");
	    Criteria districtCriteria = Criteria.where("address.district").regex("^" + Pattern.quote(district) + "$", "i");

	    // Combine the criteria
	    Criteria combinedCriteria = new Criteria().andOperator(cityCriteria, districtCriteria, Criteria.where("sportType").is(sportType));
	    Query query = new Query(combinedCriteria);

	    // Execute the query
	    return mongoTemplate.find(query, SportPlace.class);
	}


	@PostMapping("/sportPlaces/searchSportPlaceByTypeAndCityAndDistrict")
	public List<SportPlace> searchSportPlaceByTypeAndCityAndDistrict(@RequestBody SportPlace sportPlace) {
	    return findBySportTypeAndCityAndDistrict(
	        sportPlace.getSportType(),
	        sportPlace.getAddress().getCity(),
	        sportPlace.getAddress().getDistrict()
	    );
	    
	    
	}
	
	
	
/*    @PostMapping("/sportPlaces/{id}/uploadImage")
    public ResponseEntity<?> uploadImage(@PathVariable String id, @RequestParam("image") MultipartFile file) {
        try {
            SportPlace sportPlace = sportPlaceDAO.findById(id).orElseThrow(() -> new Exception("SportPlace not found"));
            
            byte[] imageBytes = file.getBytes();
            sportPlace.setImage(imageBytes);
            sportPlaceDAO.save(sportPlace);

            return ResponseEntity.ok("Image uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }*/
    
    


		
	/********************* COMMAND ****************************/
	
	@GetMapping("/commands")
	public List<Command> commands(){
		return commandDAO.findAll();
	}
	
	@PostMapping("/commands/searchbysportplace")
	public List<Command> commandsBySportPlaceId(@RequestBody CommandPayload payload){
		
		SportPlace sportPlace = new SportPlace();
		sportPlace.setId(payload.getSportPlaceId());
		
		List<Command> commands = commandDAO.findBySportPlace(sportPlace);
		return commands;
	}
	
	@PostMapping("/commands/save")
	public Command saveCommand(@RequestBody CommandPayload payload) {
		
		SportPlace sportPlace = new SportPlace();
		sportPlace.setId(payload.getSportPlaceId());
		
		Command commandToSave = new Command(payload.getText(), payload.getDate(), 
				payload.getUsername(), sportPlace);
		
		Command commandSaved = commandDAO.save(commandToSave);
		return commandSaved;
	}
	

}

