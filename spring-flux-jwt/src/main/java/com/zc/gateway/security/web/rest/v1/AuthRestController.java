package com.zc.gateway.security.web.rest.v1;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zc.gateway.security.JwtAuthenticationRequest;
import com.zc.gateway.security.JwtAuthenticationResponse;
import com.zc.gateway.security.JwtTokenUtil;
import com.zc.gateway.security.service.CustomReactiveUserDetailsService;

import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(path = "/auth", produces = { APPLICATION_JSON_UTF8_VALUE })
public class AuthRestController {

	private static Logger logger = LoggerFactory.getLogger(AuthRestController.class);

	 @Autowired
	 private CustomReactiveUserDetailsService repo;
	 
	 
	@Autowired private JwtTokenUtil jwtTokenUtil;


	@RequestMapping(method = POST, value = "/token")
	@CrossOrigin("*")
	public Mono<ResponseEntity<JwtAuthenticationResponse>> token(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
		String username =  authenticationRequest.getUsername();
		String password =  authenticationRequest.getPassword();
     
		
		System.out.println("username Found User >>>>  " + username);
		System.out.println("username password >>>>  " + password);


//		User user = this.userDAO.findUserAccount(username);
//        
//        if (user == null) {
//          logger.debug("NOT Found User >>>>  " + user);
//        }
        
//        List<String> roleNames = this.roleDAO.getRoleNames(user.getUserId());
//
//        CustomUserDetails userdetails = new CustomUserDetails(user, roleNames);
        
      //  JwtAuthenticationResponse jwtResponse=new JwtAuthenticationResponse(jwtTokenUtil.generateToken(userdetails), user.getUsername());


		return repo.findByUsername(authenticationRequest.getUsername())
			.map(user -> ok().contentType(APPLICATION_JSON_UTF8).body(
					new JwtAuthenticationResponse(jwtTokenUtil.generateToken(user), user.getUsername()))
			)
			.defaultIfEmpty(notFound().build());
	}
}
