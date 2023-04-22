package org.niit.c2s1demo.service;

import org.niit.c2s1demo.domain.User;

import java.util.List;

public interface IUserService {

    public List<User> getUser();
    public User saveUser(User user);
    public String deleteUser(String email);
    public User updateUser(User user);

    public List<User> getByFirstName(String firstName);
    public User loginCheck(String emailId,String password);
}
