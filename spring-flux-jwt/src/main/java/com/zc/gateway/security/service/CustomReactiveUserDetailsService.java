package com.zc.gateway.security.service;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zc.gateway.security.model.User;

import reactor.core.publisher.Mono;

@Service
public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(CustomReactiveUserDetailsService.class);

    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private RoleDAO roleDAO;
    
    
    @Override
    public Mono<UserDetails> findByUsername(String username) {
    	
    	System.out.println("CustomReactiveUserDetailsService- findByUsername " + username);
    	User user = this.userDAO.findUserAccount(username);
        
        if (user == null) {
          logger.debug("NOT Found User >>>>  " + user);

            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
    	System.out.println("CustomReactiveUserDetailsService- found user " + user);

        logger.debug("Found User >>>>  " + user);

        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.roleDAO.getRoleNames(user.getUserId());

        CustomUserDetails userdetails = new CustomUserDetails(user, roleNames);
    	
        return Mono.just(userdetails);
    }
}