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
@RequestMapping("growmate/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    // get informacoes do utilizador
    @GetMapping("/{idUser}")
    public ResponseEntity<User> getUserInfo(@PathVariable(value = "idUser") Long idUser) throws ResourceNotFoundException{
        return ResponseEntity.ok().body(userService.getUser(idUser));
    }


    // Used to change a User's password
    @PutMapping("/{idUser}/changePassword")
    public ResponseEntity<SuccessfulRequest> changePassword(@PathVariable(value = "idUser") Long idUser,
                                                            @RequestParam(value = "oldPassword") String oldPassword,
                                                            @RequestParam(value = "newPassword") String newPassword) throws Exception {
        return ResponseEntity.ok().body(userService.changePassword(idUser, oldPassword, newPassword));
    }

    // Used to update a User's profile info
    @PutMapping("/{idUser}/editProfile")
    public ResponseEntity<SuccessfulRequest> editProfile(@PathVariable(value = "idUser") Long idUser,
                                                         @RequestParam(value = "name", required = false) String name,
                                                         @RequestParam(value = "email", required = false) String email,
                                                         @RequestParam(value = "dateOfBirth", required = false) Date dob,
                                                         @RequestParam(value = "address", required = false) String address) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(userService.editProfile(idUser, name, email, dob, address));
    }
    
}
