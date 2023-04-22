package org.niit.c2s1demo.service;

import org.niit.c2s1demo.domain.User;
import org.niit.c2s1demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        if (userRepository.findById(user.getEmail()).isEmpty()) {
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public String deleteUser(String email) {
        if(userRepository.findById(email).isEmpty()){
            return "User Does Not Exist";
        }
        userRepository.deleteById(email);
        return "User Deleted Successfully";
    }

    @Override
    public User updateUser(User user) {
       if(userRepository.findById(user.getEmail()).isEmpty()){
           return null;
       }
        User oldUser=userRepository.findById(user.getEmail()).get();
       oldUser.setFirstName(user.getFirstName());
       oldUser.setLastName(user.getLastName());
       oldUser.setPassword(user.getPassword());
        return  userRepository.save(oldUser);

    }

    @Override
    public List<User> getByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    @Override
    public User loginCheck(String emailId, String password) {
        if(userRepository.findById(emailId).isPresent()){
            User user=userRepository.findById(emailId).get();
            if(user.getPassword().equals(password)){
                return user;
            }else
                return null;
        }
        return null;
    }
}
