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
	private ICourseDAO courseDAO   ;   //ע��DAO

	private static final long serialVersionUID = 1L;

	@Transactional
	public Integer doCreate(Course entity) throws Exception {
		Integer count= 0 ;
		count=this.courseDAO.doCreate(entity) ;  //���ӿγ� 
		return count;
	}

	@Transactional
	public Integer doUpdate(Course entity) throws Exception {
		Integer count=0 ;
		count=this.courseDAO.doUpdate(entity)  ;
		return count;
	}

	/**
	 * ɾ���γ���һ��ӵ�����:
	 * 1.ɾ��score���ж�Ӧ�ļ�¼
	 * 2.ɾ��teach���ж�Ӧ�ļ�¼
	 * 3.ɾ��course���ж�Ӧ�ļ�¼ 
	 */
	@Transactional
	public Integer doRemove(Integer id) throws Exception {
		Integer count= 0 ;
		count=count+this.courseDAO.doRemoveScore(id) ;  //ɾ��score
		count=count+this.courseDAO.doRemoveTeach(id) ;  //ɾ��teach
		count=count+this.courseDAO.doRemove(id)  ;  //ɾ��course
		return count;
	}

	
	@Transactional
	public Course findById(Integer id) throws Exception {
		 Course course=null ;
		 course=this.courseDAO.findById(id)  ;   //����ID��ѯ�γ� 
		 return course ;
	}
	
	/**
	 * currentPage Ҫ��ʾ��ҳ��
	 * lineSize  ÿҳ��ʾ�������� 
	 * keyWord   ��ѯ�ؼ��� 
	 * Map<String,Object> �����ҳ��Ϣ������
	 */
	@Transactional
	public Map<String,Object> findAll(Integer currentPage,Integer lineSize,String keyWord)throws Exception{
		   List<Course> courses=null ;   //����γ� 
		   Map<String,Object> map=new HashMap<String,Object>()  ;   //�������� 
		   if(!(keyWord==null||"".equals(keyWord))){   //��Ϊ��
			    keyWord=keyWord.toUpperCase() ;  //��д
			    keyWord="%"+keyWord+"%";
		   }else{
			   keyWord="%%"  ;
		   }
		   PageHelper.startPage(currentPage, lineSize)  ;   //ȷ����ҳ���� 
		   courses=this.courseDAO.findAll(keyWord)  ;  //��װ��ҳ��ѯ��ħ����ѯ����Ϣ 
		   PageInfo<Course> pageInfo=new PageInfo<Course>(courses)  ;  //��װ��ҳ��Ϣ
		   map.put("courses", courses)  ;     //����γ���Ϣ
		   map.put("pageInfo", pageInfo)  ;   //�����ҳ��Ϣ 
		   return map ;
	}

	public Integer doCreateBatch(List<Course> courses) throws Exception {
		Integer count=0  ;
		count=this.courseDAO.doCreateBatch(courses)  ;  //�������� 
		return count;
	}

	public List<Course> findByTerm(Integer term) throws Exception {
		List<Course> courses = this.courseDAO.findByTerm(term);
		return courses;
	}
	

}
