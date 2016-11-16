package cn.zzu.wcj.entity;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String studentId  ;   //学生编号
	private String studentName ;  //学生姓名
	private Integer clazzId ;     //班级代码
	private String  sex     ;     //性别
	private String address  ;    //地址
	private Date  birth   ;      //生日
	private Integer age  ;       //年龄 
	private String password  ;   //密码
	private String tel     ;    //联系电话 
	private String photo   ;   //照片名称
	private String note     ;   //简介
	private Clazz clazz=new Clazz() ;   //1个学生有1个班级
	
	public Student(){}   //无参构造方法 
	
	public Student(String studentId, String studentName, Integer clazzId,
			String sex, String address, Date birth, Integer age,
			String password, String tel, String photo, String note) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.clazzId = clazzId;
		this.sex = sex;
		this.address = address;
		this.birth = birth;
		this.age = age;
		this.password = password;
		this.tel = tel;
		this.photo = photo;
		this.note = note;
	}




	public String getStudentId() {
		return studentId;
	}
	
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public Integer getClazzId() {
		return clazzId;
	}
	
	public void setClazzId(Integer clazzId) {
		this.clazzId = clazzId;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Date getBirth() {
		return birth;
	}
	
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName="
				+ studentName + ", clazzId=" + clazzId + ", sex=" + sex
				+ ", address=" + address + ", birth=" + birth + ", age=" + age
				+ ", password=" + password + ", tel=" + tel + ", photo="
				+ photo + ", note=" + note + ", clazz=" + clazz + "]";
	}

	
	
	
	
	
}
