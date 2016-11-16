package cn.zzu.wcj.entity;

import java.io.Serializable;

public class Permission implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private Integer permissionId  ;       //权限编号
	private String permissionName  ;      //权限名称
	private String permissionNote  ;     //权限简介
	
	
	public Integer getPermissionId() {
		return permissionId;
	}
	
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	
	public String getPermissionName() {
		return permissionName;
	}
	
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionNote() {
		return permissionNote;
	}

	public void setPermissionNote(String permissionNote) {
		this.permissionNote = permissionNote;
	}

	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId + ", permissionName="
				+ permissionName + ", permissionNote=" + permissionNote + "]";
	}

    
	
	
	
}
