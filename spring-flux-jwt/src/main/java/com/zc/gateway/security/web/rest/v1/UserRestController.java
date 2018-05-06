package com.zc.gateway.security.web.rest.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zc.gateway.security.model.User;
import com.zc.gateway.security.service.UserDAO;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



@RestController
@RequestMapping(path = "/api/user", produces = { APPLICATION_JSON_UTF8_VALUE })
public class UserRestController {
	private static Logger logger = LoggerFactory.getLogger(UserRestController.class);

	
	 @Autowired
	 private UserDAO repo;
	 
	@GetMapping("/list")
//	@PreAuthorize("hasRole('ADMIN')")
	List<User> list() {
		System.out.println(" list >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		logger.debug("UserRestController> list");
		return this.repo.findAllUsers();
	}

}
