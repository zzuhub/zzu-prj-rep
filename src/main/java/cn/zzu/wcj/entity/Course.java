package cn.zzu.wcj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer courseId    ;          //�γ̱��
	private String courseName  ;          //�γ����� 
	private Integer term        ;         //ѧ�ڱ��{1,2,3,4,5,6,7,8}
	private Integer credit      ;         //ѧ��
	private String note         ;         //���
	private List<Teacher> teachers=new ArrayList<Teacher>()  ;     //1�ſγ��ж�λ��ʦ�ڿ�
	private List<Major> majors =new ArrayList<Major>()  ;          //1�ſγ��ж��רҵ����
	
	
	
	public Integer getCourseId() {
		return courseId;
	}
	
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public Integer getTerm() {
		return term;
	}
	
	public void setTerm(Integer term) {
		this.term = term;
	}
	
	public Integer getCredit() {
		return credit;
	}
	
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<Major> getMajors() {
		return majors;
	}

	public void setMajors(List<Major> majors) {
		this.majors = majors;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName
				+ ", term=" + term + ", credit=" + credit + ", note=" + note
				+ ", teachers=" + teachers + ", majors=" + majors + "]";
	}


	
	
	
	
	
	
	
	

}
