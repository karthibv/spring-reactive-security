package com.zc.gateway.security.model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name="ZC_USER")
public class User  {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "USER_ID")
		private Long userId;
		
		@Column(name = "EMAIL_ID", nullable = false, unique = true)
		@Email(message = "Please provide a valid e-mail")
		@NotEmpty(message = "Please provide an e-mail")
		private String email;
	   
		@Column(name="FULL_NAME")
	    private String username;
	    
	    @Column(name = "FIRST_NAME")
		@NotEmpty(message = "Please provide your first name")
		private String firstName;
		
		@Column(name = "LAST_NAME")
		@NotEmpty(message = "Please provide your last name")
		private String lastName;
	    
	    @Column(name="PASSWORD")
	    private String password;
	   
	    @Column(name="USER_TYPE")
	    private String userType;
	    
	    @Column(name="ZAVYCARE_ID")
	    private String zavyCareID;
	   
	    @Column(name="PRIMARY_ID_TYPE")
	    private String primaryIDType;
	   
	    @Column(name="PRIMARY_ID_NUMBER")
	    private String primaryIDNumber;

	    @Column(name="SECONDARY_ID_TYPE")
	    private String secondaryIDType;
	   
	    @Column(name="SECONDARY_ID_NUMBER")
	    private String secondaryIDNumber;

	    @Column(name="TEL_OFF")
	    private String telOffice;
	   
	    @Column(name="TEL_HOME")
	    private String telHome;
	   
	    @Column(name="TEL_MOB")
	    private String telMobile;
	   
	    @Column(name="LANG_CODE")
	    private String langCode;
	    
	    @Column(name="COUNTRY_CODE")
	    private String countryCode;
	    
	    @Column(name="LAST_LOGIN_DATE")
	    private String lastLognDateTime;
	    
	    @Column(name="CURRENT_LOGON_FLAG")
	    private String currentLoginFlag;
	   
	    @Column(name="REC_STATUS")
	    private String recStatus;
	   
	    

	    
	    
	    public User() { }
	    
	    public User(User user) {
			this(user.getUserId(), user.getEmail(), user.getUsername(), user.getFirstName(), user.getLastName(),user.getPassword());
	    }
	    
	    public User(Long userId, String email, String username, String firstName, String lastName,String password) {
	        this.userId = userId;
	        this.email = email;
	        this.firstName= firstName;
	        this.lastName = lastName;
	        this.username = username;
	        this.password = password;
	      //  this.roles = roles;
	    }

	    public Long getUserId() {
	        return userId;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public String getPassword() {
	        return password;
	    }
	    
//	    public Set<Role> getRoles() {
//	        return roles;
//	    }
	    
	    public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public void setPassword(String password) {
			this.password = password;
		}

//		public void setRoles(Set<Role> roles) {
//			this.roles = roles;
//		}
//		

	    public String getUserType() {
			return userType;
		}

		public void setUserType(String userType) {
			this.userType = userType;
		}

		public String getZavyCareID() {
			return zavyCareID;
		}

		public void setZavyCareID(String zavyCareID) {
			this.zavyCareID = zavyCareID;
		}

		public String getPrimaryIDType() {
			return primaryIDType;
		}

		public void setPrimaryIDType(String primaryIDType) {
			this.primaryIDType = primaryIDType;
		}

		public String getPrimaryIDNumber() {
			return primaryIDNumber;
		}

		public void setPrimaryIDNumber(String primaryIDNumber) {
			this.primaryIDNumber = primaryIDNumber;
		}

		public String getSecondaryIDType() {
			return secondaryIDType;
		}

		public void setSecondaryIDType(String secondaryIDType) {
			this.secondaryIDType = secondaryIDType;
		}

		public String getSecondaryIDNumber() {
			return secondaryIDNumber;
		}

		public void setSecondaryIDNumber(String secondaryIDNumber) {
			this.secondaryIDNumber = secondaryIDNumber;
		}

		public String getTelOffice() {
			return telOffice;
		}

		public void setTelOffice(String telOffice) {
			this.telOffice = telOffice;
		}

		public String getTelHome() {
			return telHome;
		}

		public void setTelHome(String telHome) {
			this.telHome = telHome;
		}

		public String getTelMobile() {
			return telMobile;
		}

		public void setTelMobile(String telMobile) {
			this.telMobile = telMobile;
		}

		public String getLangCode() {
			return langCode;
		}

		public void setLangCode(String langCode) {
			this.langCode = langCode;
		}

		public String getCountryCode() {
			return countryCode;
		}

		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}

		public String getLastLognDateTime() {
			return lastLognDateTime;
		}

		public void setLastLognDateTime(String lastLognDateTime) {
			this.lastLognDateTime = lastLognDateTime;
		}

		public String getCurrentLoginFlag() {
			return currentLoginFlag;
		}

		public void setCurrentLoginFlag(String currentLoginFlag) {
			this.currentLoginFlag = currentLoginFlag;
		}


	    public String getRecStatus() {
			return recStatus;
		}

		public void setRecStatus(String recStatus) {
			this.recStatus = recStatus;
		}

		
//		public List<String> getRoles() {
//	        return roles;
//	    }
//
//	    public void setRoles(List<String> roles) {
//	        this.roles = roles;
//	    }
	    

//		@Override
//		public boolean isAccountNonExpired() {
//			// TODO Auto-generated method stub
//			return true;
//		}
//
//		@Override
//		public boolean isAccountNonLocked() {
//			// TODO Auto-generated method stub
//			return true;
//		}
//
//		@Override
//		public boolean isCredentialsNonExpired() {
//			// TODO Auto-generated method stub
//			return true;
//		}
//
//		@Override
//		public boolean isEnabled() {
//			// TODO Auto-generated method stub
//			return true;
//		}
//
//		
//		@Override
//	    public Collection<? extends GrantedAuthority> getAuthorities() {
//	        List<GrantedAuthority> authorities = new ArrayList(this.roles.size());
//	        for(String role : this.roles) {
//	            authorities.add(new SimpleGrantedAuthority(role));
//	        }
//
//	        return authorities;
//	    }

}
