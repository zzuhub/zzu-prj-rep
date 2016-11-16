package cn.zzu.wcj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zzu.wcj.dao.IStudentDAO;
import cn.zzu.wcj.entity.Student;
import cn.zzu.wcj.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IStudentDAO studentDAO   ;
	
	@Transactional
	public Integer doCreate(Student entity) throws Exception {
		Integer count= 0 ;
		count=this.studentDAO.doCreate(entity)  ;   //增加学生信息
		return count   ;
	}

	@Transactional
	public Integer doUpdate(Student entity) throws Exception {
		Integer count= 0;
		count=this.studentDAO.doUpdate(entity)  ;    //修改学生信息 
		return count  ;
	}

	
	@Transactional
	public Integer doRemove(String id) throws Exception {
	    Integer count= 0 ;
	    //1.先删除score表对应的数据
	    count=this.studentDAO.doRemoveScore(id)  ;
	    //2.在删除student表对应的数据
	    count=count+this.studentDAO.doRemove(id)  ;
		return count;
	}

	
	public Student findById(String id) throws Exception {
		Student student=null ;
		student=this.studentDAO.findById(id)  ;    //根据ID查询学生信息 
		return student  ;
	}

	@Transactional
	public Integer doCreateBatch(List<Student> students) throws Exception {
		Integer count=0  ;
		count=this.studentDAO.doCreateBatch(students)  ;   //批量增加数据 
		return count;
	}

	public Map<String, Object> findAll(Integer currentPage, Integer lineSize,
			String keyWord) throws Exception {
		if(keyWord==null || "".equals(keyWord)){    //查询关键词为空
			keyWord="%%"  ;     //表示查询全部 
		}else{
			 keyWord=new String("%"+keyWord+"%").toUpperCase() ; //组织查询关键词,不区分大小写
		}
		Map<String,Object> map=new HashMap<String, Object>() ;   //保存数据集合和分页信息
		PageHelper.startPage(currentPage, lineSize)   ;          //确定分页参数
		List<Student> students=this.studentDAO.findAll(keyWord) ;  //模糊查询
		PageInfo<Student> pageInfo=new PageInfo<Student>(students)  ;      //保存分页信息
		map.put("students", students)   ;     //保存学生信息
		map.put("pageInfo", pageInfo)   ;     //保存分页信息 
		return map   ;
	}

	
	public Integer findLogin(Student student) throws Exception {
		Integer count= 0 ;
		count=this.studentDAO.findLogin(student) ;    //登录验证 
		return count  ;
	}

	@Transactional
	public Integer doUpdatePassword(Student student) throws Exception {
		Integer count= 0  ;
		count=this.studentDAO.doUpdatePassword(student)  ;   //修改密码 
		return count  ;
	}

}
