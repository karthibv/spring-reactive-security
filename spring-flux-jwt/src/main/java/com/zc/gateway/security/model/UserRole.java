package com.zc.gateway.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ZC_USER_ROLE")
public class UserRole {

	

		@Id
	    @GeneratedValue
	    @Column(name = "ZC_UR_ID", nullable = false)
	    private Long id;
	 
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "USER_ID", nullable = false)
	    private User user;
	 
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "ROLE_ID", nullable = false)
	    private Role role;
	    
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}
}
