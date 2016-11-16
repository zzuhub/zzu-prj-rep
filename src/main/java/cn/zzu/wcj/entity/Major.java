package cn.zzu.wcj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Major implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer majorId   ;
	private String majorName  ;
	private String note      ;
	private Date createTime ;
	private List<Clazz> clazzs=new ArrayList<Clazz>()  ;  
	
	
	public Integer getMajorId() {
		return majorId;
	}
	
	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}
	
	public String getMajorName() {
		return majorName;
	}
	
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Clazz> getClazzs() {
		return clazzs;
	}

	public void setClazzs(List<Clazz> clazzs) {
		this.clazzs = clazzs;
	}

	@Override
	public String toString() {
		return "Major [majorId=" + majorId + ", majorName=" + majorName
				+ ", note=" + note + ", createTime=" + createTime + ", clazzs="
				+ clazzs + "]";
	}


	
	
	
	

	
	

}
