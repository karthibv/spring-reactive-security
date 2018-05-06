package com.zc.gateway.security.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zc.gateway.security.model.Role;
import com.zc.gateway.security.model.UserRole;


@Repository
@Transactional
public class RoleDAO {
		@Autowired
	    private EntityManager entityManager;
	 
	    public List<String> getRoleNames(Long userId) {  
	        String sql = "Select ur.role.roleName from " + UserRole.class.getName() + " ur " //
	                + " where ur.user.userId = :userId ";
	        Query query = this.entityManager.createQuery(sql, String.class);
	        query.setParameter("userId", userId);
	        return query.getResultList();
	    }
	    
	  
	    public Role findByRoleName(String roleName) {
	        try {
	            String sql = "Select e from " + Role.class.getName() + " e " //
	                    + " Where e.roleName = :roleName ";
	 
	            Query query = entityManager.createQuery(sql, Role.class);
	            query.setParameter("roleName", roleName);
	 
	            return (Role) query.getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }
	    public void save(UserRole userRole) {  
	    		entityManager.persist(userRole);
	    }
}
