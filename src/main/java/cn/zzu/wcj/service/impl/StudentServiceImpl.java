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
		count=this.studentDAO.doCreate(entity)  ;   //����ѧ����Ϣ
		return count   ;
	}

	@Transactional
	public Integer doUpdate(Student entity) throws Exception {
		Integer count= 0;
		count=this.studentDAO.doUpdate(entity)  ;    //�޸�ѧ����Ϣ 
		return count  ;
	}

	
	@Transactional
	public Integer doRemove(String id) throws Exception {
	    Integer count= 0 ;
	    //1.��ɾ��score���Ӧ������
	    count=this.studentDAO.doRemoveScore(id)  ;
	    //2.��ɾ��student���Ӧ������
	    count=count+this.studentDAO.doRemove(id)  ;
		return count;
	}

	
	public Student findById(String id) throws Exception {
		Student student=null ;
		student=this.studentDAO.findById(id)  ;    //����ID��ѯѧ����Ϣ 
		return student  ;
	}

	@Transactional
	public Integer doCreateBatch(List<Student> students) throws Exception {
		Integer count=0  ;
		count=this.studentDAO.doCreateBatch(students)  ;   //������������ 
		return count;
	}

	public Map<String, Object> findAll(Integer currentPage, Integer lineSize,
			String keyWord) throws Exception {
		if(keyWord==null || "".equals(keyWord)){    //��ѯ�ؼ���Ϊ��
			keyWord="%%"  ;     //��ʾ��ѯȫ�� 
		}else{
			 keyWord=new String("%"+keyWord+"%").toUpperCase() ; //��֯��ѯ�ؼ���,�����ִ�Сд
		}
		Map<String,Object> map=new HashMap<String, Object>() ;   //�������ݼ��Ϻͷ�ҳ��Ϣ
		PageHelper.startPage(currentPage, lineSize)   ;          //ȷ����ҳ����
		List<Student> students=this.studentDAO.findAll(keyWord) ;  //ģ����ѯ
		PageInfo<Student> pageInfo=new PageInfo<Student>(students)  ;      //�����ҳ��Ϣ
		map.put("students", students)   ;     //����ѧ����Ϣ
		map.put("pageInfo", pageInfo)   ;     //�����ҳ��Ϣ 
		return map   ;
	}

	
	public Integer findLogin(Student student) throws Exception {
		Integer count= 0 ;
		count=this.studentDAO.findLogin(student) ;    //��¼��֤ 
		return count  ;
	}

	@Transactional
	public Integer doUpdatePassword(Student student) throws Exception {
		Integer count= 0  ;
		count=this.studentDAO.doUpdatePassword(student)  ;   //�޸����� 
		return count  ;
	}

}
