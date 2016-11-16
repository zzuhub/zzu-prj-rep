package cn.zzu.wcj.entity;

import java.io.Serializable;

public class Score implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer scoreId  ;   //成绩编号
	private Integer score    ;  //成绩
	private Student student=new Student()  ;   //1个学生有1门成绩
	private Course course=new Course()  ;      //1门成绩对应1门课程
	
	
	public Integer getScoreId() {
		return scoreId;
	}
	
	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}
	
	public Integer getScore() {
		return score;
	}
	
	public void setScore(Integer score) {
		this.score = score;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Score [scoreId=" + scoreId + ", score=" + score + ", student="
				+ student + ", course=" + course + "]";
	}
	
	
	

}
