package com.zc.gateway.security.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zc.gateway.security.model.User;


@Repository
@Transactional
public class UserDAO {
	  private static Logger logger = LoggerFactory.getLogger(UserDAO.class);

	@Autowired
    private EntityManager entityManager;
 
    public User findUserAccount(String email) {
        try {
            String sql = "Select e from " + User.class.getName() + " e " //
                    + " Where e.email = :email ";
 
            Query query = entityManager.createQuery(sql, User.class);
            query.setParameter("email", email);
 
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    
    public List<User> findAllUsers() {
        try {
        	
        	logger.debug("findAllUsers");
            String sql = "Select e from " + User.class.getName() +" e ";
 
            Query query = entityManager.createQuery(sql);
           // query.setParameter("email", email);
 
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

	public void register(User user) {
        logger.debug("UserDAO->register >>>>  " + user.getEmail());

		String sql = "Select e from " + User.class.getName() + " e " //
				+ " Where e.email = :email ";
		Query query = entityManager.createQuery(sql, User.class);
		query.setParameter("email", user.getEmail());
		List<User> existing = query.getResultList();
        logger.debug("UserDAO->result find by id >>>>  " + existing.size());

		if (existing.size() == 0) {
			entityManager.persist(user);
		}

	}
    
    
    
}
