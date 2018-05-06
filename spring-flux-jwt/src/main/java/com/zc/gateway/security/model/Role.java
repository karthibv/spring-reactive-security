package com.zc.gateway.security.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ZC_ROLE")
public class Role {

    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ROLE_ID")
	private int roleId;
	
	@Column(name="ROLE_NAME")
	private String roleName;
	
	@Column(name="ROLE_CATEGORY")
	private String roleCategory;
	
	@Column(name="SUB_ROLE_CATEGORY")
	private String subRoleCategory;
	
	@Column(name="ROLE_DESCRIPTION")
	private String roleDesc;
	
	@Column(name="REC_STATUS")
	private String recStatus;
	
//	@OneToMany
//	@JoinTable(name = "ZC_USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
//	private Set<User> userRoles;
//	
//	public Set<User> getUserRoles() {
//		return userRoles;
//	}
//	public void setUserRoles(Set<User> userRoles) {
//		this.userRoles = userRoles;
//	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleCategory() {
		return roleCategory;
	}
	public void setRoleCategory(String roleCategory) {
		this.roleCategory = roleCategory;
	}
	public String getSubRoleCategory() {
		return subRoleCategory;
	}
	public void setSubRoleCategory(String subRoleCategory) {
		this.subRoleCategory = subRoleCategory;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getRecStatus() {
		return recStatus;
	}
	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}
}