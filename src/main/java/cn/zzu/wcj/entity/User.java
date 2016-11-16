package cn.zzu.wcj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer userId   ;        //�û����
	private String userName   ;     //�û�����
	private String password    ;    //����
	private Date createTime  ;      //����ʱ��
	private Date updateTime   ;    //�޸�ʱ��
	private Date lastLogin    ;    //�ϴε�¼
	private List<Role> roles=new ArrayList<Role>()  ;    //1���û��ж����ɫ


	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getLastLogin() {
		return lastLogin;
	}
	
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", lastLogin=" + lastLogin
				+ ", roles=" + roles + "]";
	}

    



    
	
	
	
  
}
