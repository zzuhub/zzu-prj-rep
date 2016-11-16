package cn.zzu.wcj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer userId   ;        //用户编号
	private String userName   ;     //用户名称
	private String password    ;    //密码
	private Date createTime  ;      //创建时间
	private Date updateTime   ;    //修改时间
	private Date lastLogin    ;    //上次登录
	private List<Role> roles=new ArrayList<Role>()  ;    //1个用户有多个角色


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
