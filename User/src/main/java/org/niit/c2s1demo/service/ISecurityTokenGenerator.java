package org.niit.c2s1demo.service;

import org.niit.c2s1demo.domain.User;

import java.util.Map;

public interface ISecurityTokenGenerator {
    public Map<String,String> tokenGenerator(User user);
}
