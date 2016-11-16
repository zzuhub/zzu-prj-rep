package cn.zzu.wcj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zzu.wcj.dao.ICourseDAO;
import cn.zzu.wcj.entity.Course;
import cn.zzu.wcj.service.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService {

	
	@Autowired
	private ICourseDAO courseDAO   ;   //注入DAO

	private static final long serialVersionUID = 1L;

	@Transactional
	public Integer doCreate(Course entity) throws Exception {
		Integer count= 0 ;
		count=this.courseDAO.doCreate(entity) ;  //增加课程 
		return count;
	}

	@Transactional
	public Integer doUpdate(Course entity) throws Exception {
		Integer count=0 ;
		count=this.courseDAO.doUpdate(entity)  ;
		return count;
	}

	/**
	 * 删除课程是一项复杂的任务:
	 * 1.删除score表中对应的记录
	 * 2.删除teach表中对应的记录
	 * 3.删除course表中对应的记录 
	 */
	@Transactional
	public Integer doRemove(Integer id) throws Exception {
		Integer count= 0 ;
		count=count+this.courseDAO.doRemoveScore(id) ;  //删除score
		count=count+this.courseDAO.doRemoveTeach(id) ;  //删除teach
		count=count+this.courseDAO.doRemove(id)  ;  //删除course
		return count;
	}

	
	@Transactional
	public Course findById(Integer id) throws Exception {
		 Course course=null ;
		 course=this.courseDAO.findById(id)  ;   //根据ID查询课程 
		 return course ;
	}
	
	/**
	 * currentPage 要显示的页码
	 * lineSize  每页显示的数据量 
	 * keyWord   查询关键词 
	 * Map<String,Object> 保存分页信息和数据
	 */
	@Transactional
	public Map<String,Object> findAll(Integer currentPage,Integer lineSize,String keyWord)throws Exception{
		   List<Course> courses=null ;   //保存课程 
		   Map<String,Object> map=new HashMap<String,Object>()  ;   //保存数据 
		   if(!(keyWord==null||"".equals(keyWord))){   //不为空
			    keyWord=keyWord.toUpperCase() ;  //大写
			    keyWord="%"+keyWord+"%";
		   }else{
			   keyWord="%%"  ;
		   }
		   PageHelper.startPage(currentPage, lineSize)  ;   //确定分页参数 
		   courses=this.courseDAO.findAll(keyWord)  ;  //封装分页查询和魔化查询的信息 
		   PageInfo<Course> pageInfo=new PageInfo<Course>(courses)  ;  //封装分页信息
		   map.put("courses", courses)  ;     //放入课程信息
		   map.put("pageInfo", pageInfo)  ;   //放入分页信息 
		   return map ;
	}

	public Integer doCreateBatch(List<Course> courses) throws Exception {
		Integer count=0  ;
		count=this.courseDAO.doCreateBatch(courses)  ;  //批量增加 
		return count;
	}

	public List<Course> findByTerm(Integer term) throws Exception {
		List<Course> courses = this.courseDAO.findByTerm(term);
		return courses;
	}
	

}
