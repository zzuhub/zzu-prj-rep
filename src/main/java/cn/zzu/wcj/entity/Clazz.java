package cn.zzu.wcj.entity;

public class Clazz {
   
	private Integer clazzId  ;        //�༶����
	private String clazzName   ;     //�༶����
	private Major major=new Major()        ;     //1���༶����һ��רҵ
	
	
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
