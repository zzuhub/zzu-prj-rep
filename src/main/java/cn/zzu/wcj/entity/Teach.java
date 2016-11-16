package cn.zzu.wcj.entity;

public class Teach {
   
	private Integer teachId ;      //教学代码
	private Integer teacherId ;   //教师代码
	private Integer courseId  ;   //课程代码
	private Integer clazzId   ;   //班级代码
	private Teacher teacher=new Teacher()   ;     //1个教学对应1个教师
	private Course course  =new Course()     ;   //1个教学对应1门课程
	private Clazz  clazz =new Clazz()    ;       //1个教学对应1个班级
	
	public Integer getTeachId() {
		return teachId;
	}
	
	public void setTeachId(Integer teachId) {
		this.teachId = teachId;
	}
	
	public Integer getTeacherId() {
		return teacherId;
	}
	
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	public Integer getCourseId() {
		return courseId;
	}
	
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
	public Integer getClazzId() {
		return clazzId;
	}
	
	public void setClazzId(Integer clazzId) {
		this.clazzId = clazzId;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	@Override
	public String toString() {
		return "Teach [teachId=" + teachId + ", teacherId=" + teacherId
				+ ", courseId=" + courseId + ", clazzId=" + clazzId
				+ ", teacher=" + teacher + ", course=" + course + ", clazz="
				+ clazz + "]";
	}
	
	
	
	
	
	
	
}
