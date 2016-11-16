package cn.zzu.wcj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer roleId  ;
	private String roleName  ;
	private List<Permission> permissions =new ArrayList<Permission>() ;
	
	public Integer getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@JsonIgnore
	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", permissions=" + permissions + "]";
	}










    
	
	
	
	

}
