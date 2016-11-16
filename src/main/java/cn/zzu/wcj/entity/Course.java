package cn.zzu.wcj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer courseId    ;          //课程编号
	private String courseName  ;          //课程名称 
	private Integer term        ;         //学期编号{1,2,3,4,5,6,7,8}
	private Integer credit      ;         //学分
	private String note         ;         //简介
	private List<Teacher> teachers=new ArrayList<Teacher>()  ;     //1门课程有多位老师授课
	private List<Major> majors =new ArrayList<Major>()  ;          //1门课程有多个专业开设
	
	
	
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
