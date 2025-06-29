package pi.growmate.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pi.growmate.datamodel.division.Division;
import pi.growmate.datamodel.plant.Plant;
import pi.growmate.datamodel.plant.PlantCondition;
import pi.growmate.datamodel.sensors.PlantSensor;
import pi.growmate.datamodel.species.PlantSpecies;
import pi.growmate.datamodel.user.User;
import pi.growmate.datamodel.user.UserType;
import pi.growmate.exceptions.ResourceNotFoundException;
import pi.growmate.repositories.division.DivisionRepository;
import pi.growmate.repositories.plant.PlantRepository;
import pi.growmate.repositories.plant.PlantSensorRepository;
import pi.growmate.repositories.species.PlantSpeciesRepository;
import pi.growmate.repositories.tasks.TaskSettingsRepository;
import pi.growmate.repositories.user.UserRepository;
import pi.growmate.utils.SuccessfulRequest;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    DivisionRepository divisionRepository;

    @Autowired
    PlantSpeciesRepository plantSpeciesRepository;

    @Autowired 
    PlantSensorRepository plantSensorRepository;

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    TaskSettingsRepository taskSettingsRepository;

    @Autowired
    AlgorithmsService algorithmsService;

    // funcao que ira buscar informacao sobre o utilizador
    public User getUser(long userID) throws ResourceNotFoundException{
        return userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User with ID: " + userID + " not found."));
    }

    // get da lista de plantas do utilizador
    public List<Plant> getUserPlants(long idUser) throws ResourceNotFoundException{
        return getUser(idUser).getPlants();
    }

    //post nova planta -> adicionar nova planta a lista de plantas do utilizador.
    public SuccessfulRequest addNewPlantToUserInventory(long user_id,
                                                        String plantName,
                                                        String photo,
                                                        Long species_id,
                                                        Long division_id,
                                                        Long sensor_id,
                                                        Date date) throws ResourceNotFoundException{

        Plant plant = new Plant();

        User user = getUser(user_id);

        plant.setOwner(user);
        plant.setName(plantName);
        plant.setPlantPhoto(photo);
        plant.setPlantCondition(PlantCondition.GREAT);
        plant.setSpecies(getPlantSpecies(species_id));

        if(date != null){
            plant.setPlantationDate(date);
        }else{
            plant.setPlantationDate(Date.valueOf(LocalDate.now()));
        }

        if (division_id != null) {
            Division division = getDivision(user, division_id);
            plant.setDivision(division);
        }

        if (sensor_id != null) {
            PlantSensor sensor = getPlantSensor(sensor_id);
            plant.addSensor(sensor);
        }
        
        plantRepository.save(plant);

        // Create new entries into the Task Settings Repository, relating to the newly added plant, one for each type of task
        algorithmsService.addTasksForNewPlant(plant);

        return new SuccessfulRequest("The plant was added successfully!");
    }

    public User confirmLogin(String email, String password) throws Exception {
        if(email == null || password == null){
            throw new ResourceNotFoundException("No e-mail or password found on the Login Request.");
        }

        User user = userRepository.findFirstByEmail(email);

        if(user == null){
            throw new ResourceNotFoundException("User with e-mail: " + email + " not found.");
        }

        if(!this.checkPassword(password, user)){
            throw new Exception("Invalid login credentials");
        }else{
            // Recalculate Plant's condition
            user.getPlants().forEach(plant -> algorithmsService.setNewPlantCondition(plant));

            return user;
        }
    }

    @Transactional
    public SuccessfulRequest createNewProfile(String name, String email, String password, String profilePhoto, Date dob, Long experience, String address, String userType) throws ResourceNotFoundException {
        User newUser = new User();
        UserType type;

        if(userRepository.existsByEmail(email)){
            throw new ResourceNotFoundException("That e-mail already exists in the platform!");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        log.info(passwordEncoder.encode(password));

        newUser.setDead_plants(0L);
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setDateOfBirth(dob);
        newUser.setExp(experience);
        newUser.setAddress(address);

        if(profilePhoto != null){
            newUser.setProfilePhoto(profilePhoto);
        }

        switch (userType) {
            case "PREMIUM" -> type = UserType.PREMIUM;
            case "NON-PREMIUM" -> type = UserType.NORMAL;
            default -> throw new IllegalArgumentException("Invalid user type: " + userType);
        };

        newUser.setUserType(type);

        userRepository.save(newUser);
        userRepository.flush();

        return new SuccessfulRequest("User created successfully!");
    }

    public List<User> getAllUsers(String email, String password) throws Exception{
        if(email == null || password == null){
            throw new ResourceNotFoundException("No e-mail or password found on the Login Request.");
        }

        User user = userRepository.findFirstByEmail(email);

        if(user == null){
            throw new ResourceNotFoundException("User with e-mail: " + email + " not found.");
        }

        if(! this.checkPassword(password, user)){
            throw new Exception("Invalid login credentials");
        }else{
            return userRepository.findAll();
        }
    }

    public SuccessfulRequest changePassword(Long userID, String oldPassword, String newPassword) throws Exception {
        User user = this.checkIfUserExists(userID);

        if(!this.checkPassword(oldPassword, user)){
            throw new Exception("Invalid login credentials");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return new SuccessfulRequest("Password changed successfully!");
    }

    public SuccessfulRequest editProfile(Long userID, String name, String email, Date dob, String address) throws ResourceNotFoundException {
        User user = this.checkIfUserExists(userID);

        user.setName(name != null ? name : user.getName());
        user.setEmail(email != null ? email : user.getEmail());
        user.setDateOfBirth(dob != null ? dob : user.getDateOfBirth());
        user.setAddress(address != null ? address : user.getAddress());

        userRepository.save(user);

        return new SuccessfulRequest("Profile information changed successfully!");
    }

    public List<PlantSpecies> recommendPlantSpecies(long idUser) throws ResourceNotFoundException{
        //different score for different parameters

        //get all plants of a user
        List<Plant> plants =  getUserPlants(idUser);

        //sigmoid function to calculate score based on the number of plants a user has -> score between 1 and 5
        double k = 0.2;
        double x0 = 10.0; 
        double minValue = 1.0;
        double maxValue = 5.0; 
        
        double exponent = -k * (plants.size() - x0);
        double sigmoidValue = 1 / (1 + Math.exp(exponent));
        
        double sigmoid_score = minValue + sigmoidValue * (maxValue - minValue);

        log.info("plants in user inventory: {}", plants.size());
        
        //average score based on plant conditions 
        double average_plant_condition = plants.stream()
            .mapToInt(plant -> {
                if (plant.getPlantCondition() == PlantCondition.GREAT) {
                    return 5;
                } else if (plant.getPlantCondition() == PlantCondition.NORMAL) {
                    return 3;
                } else if (plant.getPlantCondition() == PlantCondition.BAD) {
                    return 1;
                } else {
                    return 0; // Default score if condition is unknown
                }
            })
            .average()
            .orElse(0);

        double average_plant_difficulty = plants.stream()
            .mapToInt(plant -> plant.getSpecies().getDifficulty())
            .average()
            .orElse(0);

        double overall_score = sigmoid_score*0.15 + getUser(idUser).getExp()*0.1 + average_plant_condition * 0.55 + average_plant_difficulty * 0.2;

        log.info("simgmoid score: {}", sigmoid_score);
        log.info("average condition: {}", average_plant_condition);
        log.info("average difficulty: {}", average_plant_difficulty);
        log.info("user experience: {}", getUser(idUser).getExp());
        log.info("overall score: {}", overall_score);

        return plantSpeciesRepository.findAll().stream()
                .filter(plantSpecies -> plantSpecies.getDifficulty() == Math.round(overall_score))
                .collect(Collectors.toList());
    
    }

    // Auxilliary Functions
    private User checkIfUserExists(long userID) throws ResourceNotFoundException {
        return userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User with ID: " + userID + " not found."));
    }

    private Division getDivision(User user, long divisionID) throws ResourceNotFoundException {
        return user.getDivisions().stream()
                .filter(d -> d.getId().equals(divisionID))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Division with ID: " + divisionID + " not found."));
    }

    private PlantSpecies getPlantSpecies(long id_species) throws ResourceNotFoundException{
        return plantSpeciesRepository.findById(id_species).orElseThrow(() -> new ResourceNotFoundException("Plant species with ID: " + id_species + " not found."));
    }

    private PlantSensor getPlantSensor(long id_sensor) throws ResourceNotFoundException{
        return plantSensorRepository.findById(id_sensor).orElseThrow(() -> new ResourceNotFoundException("PlantSensor with id " + id_sensor + " not found."));
    }

    private boolean checkPassword(String password, User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return passwordEncoder.matches(password, user.getPassword());
    }
}
