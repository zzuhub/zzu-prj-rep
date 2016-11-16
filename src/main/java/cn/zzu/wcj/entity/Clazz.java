package cn.zzu.wcj.entity;

public class Clazz {
   
	private Integer clazzId  ;        //班级代码
	private String clazzName   ;     //班级名称
	private Major major=new Major()        ;     //1个班级属于一个专业
	
	
	public Integer getClazzId() {
		return clazzId;
	}
	
	public void setClazzId(Integer clazzId) {
		this.clazzId = clazzId;
	}
	
	public String getClazzName() {
		return clazzName;
	}
	
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "Clazz [clazzId=" + clazzId + ", clazzName=" + clazzName
				+ ", major=" + major + "]";
	}

	
	
	
	
}
