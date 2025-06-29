package pi.growmate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.growmate.datamodel.user.User;
import pi.growmate.exceptions.ResourceNotFoundException;
import pi.growmate.services.UserService;
import pi.growmate.utils.SuccessfulRequest;

import java.sql.Date;

@RestController
@RequestMapping("growmate/public")
@CrossOrigin
public class PublicController {
    @Autowired
    UserService userService;

    /*
    password = password123
     */

    // API method for user login. Confirms if the password is the same as the encrypted version on the database, and returns the info of the User.
    @PostMapping("/login")
    public ResponseEntity<User> getUserInfo(@RequestParam(value = "email") String email,
                                            @RequestParam(value = "password") String password) throws Exception {
        return ResponseEntity.ok().body(userService.confirmLogin(email, password));
    }

    // Registers a new profile in the platform
    @PostMapping("/register")
    public ResponseEntity<SuccessfulRequest> createNewProfile(@RequestParam(value = "name") String name,
                                                              @RequestParam(value = "email") String email,
                                                              @RequestParam(value = "password") String password,
                                                              @RequestParam(value = "profilePhoto", required = false) String profilePhoto,
                                                              @RequestParam(value = "dateOfBirth") Date dob,
                                                              @RequestParam(value = "experience", required = false) Long experience,
                                                              @RequestParam(value = "address") String address,
                                                              @RequestParam(value = "userType") String userType) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(userService.createNewProfile(name, email, password, profilePhoto, dob, experience, address, userType));
    }
}
