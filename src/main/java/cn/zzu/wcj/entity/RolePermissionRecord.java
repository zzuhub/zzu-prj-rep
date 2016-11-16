package cn.zzu.wcj.entity;

public class RolePermissionRecord {
    
	private Integer roleId   ;
	private Integer permissionId   ;
	
	
	 public RolePermissionRecord() {}
	public RolePermissionRecord(Integer roleId, Integer permissionId) {
		super();
		this.roleId = roleId;
		this.permissionId = permissionId;
	}


	public Integer getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public Integer getPermissionId() {
		return permissionId;
	}
	
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	@Override
	public String toString() {
		return "RolePermissionRecord [roleId=" + roleId + ", permissionId="
				+ permissionId + "]";
	}
	
	
	
	
}
