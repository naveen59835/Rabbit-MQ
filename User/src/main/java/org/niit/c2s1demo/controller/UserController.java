package org.niit.c2s1demo.controller;

import org.niit.c2s1demo.domain.User;
import org.niit.c2s1demo.service.ISecurityTokenGenerator;
import org.niit.c2s1demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/userService")
public class UserController {

    IUserService iUserService;
    ISecurityTokenGenerator iSecurityTokenGenerator;
@Autowired
    public UserController(IUserService iUserService, ISecurityTokenGenerator iSecurityTokenGenerator) {
        this.iUserService = iUserService;
        this.iSecurityTokenGenerator = iSecurityTokenGenerator;
    }


    @GetMapping("/user")
    public ResponseEntity<?> getAllData(){
    return new ResponseEntity<>(iUserService.getUser(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveData(@RequestBody User user){
    return new ResponseEntity<>(iUserService.saveUser(user),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody User user){
    User result=iUserService.loginCheck(user.getEmail(),user.getPassword());
    if(result!=null){
    Map<String,String>map= iSecurityTokenGenerator.tokenGenerator(result);
    return new ResponseEntity<>(map,HttpStatus.OK);
    }else{
        return new ResponseEntity<>("Invalid User or User Does Not Exist",HttpStatus.NOT_FOUND);
    }
    }


    @PutMapping("/user")
    public ResponseEntity<?> updateData(@RequestBody User user){
    return new ResponseEntity<>(iUserService.updateUser(user),HttpStatus.OK);
    }
@DeleteMapping("/user/{email}")
public ResponseEntity<?> deleteData(@PathVariable String email){
return new ResponseEntity<>(iUserService.deleteUser(email),HttpStatus.OK);
}
@GetMapping("/user/{firstName}")
    public ResponseEntity<?>getByFirstName(@PathVariable String firstName){
return new ResponseEntity<>(iUserService.getByFirstName(firstName),HttpStatus.FOUND);
}
}
