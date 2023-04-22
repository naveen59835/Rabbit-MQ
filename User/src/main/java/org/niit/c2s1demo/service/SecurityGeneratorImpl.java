package org.niit.c2s1demo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.niit.c2s1demo.domain.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class SecurityGeneratorImpl implements ISecurityTokenGenerator{
    @Override
    public Map<String, String> tokenGenerator(User user) {

        String jwtToken=null;
        jwtToken=    Jwts.builder().setSubject(user.getFirstName()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"Security Key").compact();
Map<String,String>map=new  HashMap();
map.put("token",jwtToken);
map.put("massage","User Logged In Successfully");
return map;
    }
}
