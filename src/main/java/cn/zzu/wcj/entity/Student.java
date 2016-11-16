package cn.zzu.wcj.entity;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String studentId  ;   //ѧ�����
	private String studentName ;  //ѧ������
	private Integer clazzId ;     //�༶����
	private String  sex     ;     //�Ա�
	private String address  ;    //��ַ
	private Date  birth   ;      //����
	private Integer age  ;       //���� 
	private String password  ;   //����
	private String tel     ;    //��ϵ�绰 
	private String photo   ;   //��Ƭ����
	private String note     ;   //���
	private Clazz clazz=new Clazz() ;   //1��ѧ����1���༶
	
	public Student(){}   //�޲ι��췽�� 
	
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
