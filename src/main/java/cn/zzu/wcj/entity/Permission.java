package cn.zzu.wcj.entity;

import java.io.Serializable;

public class Permission implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private Integer permissionId  ;       //Ȩ�ޱ��
	private String permissionName  ;      //Ȩ������
	private String permissionNote  ;     //Ȩ�޼��
	
	
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
