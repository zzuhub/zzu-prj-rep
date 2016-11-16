package cn.zzu.wcj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zzu.wcj.dao.ITeacherDAO;
import cn.zzu.wcj.entity.Teacher;
import cn.zzu.wcj.service.ITeacherService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TeacherServiceImpl implements ITeacherService {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ITeacherDAO teacherDAO ;
	
    @Transactional
	public Integer doCreate(Teacher entity) throws Exception {
		Integer count=this.teacherDAO.doCreate(entity)  ;
		return count;
	}
    
    @Transactional
	public Integer doUpdate(Teacher entity) throws Exception {
		Integer count=this.teacherDAO.doUpdate(entity)  ;
		return count;
	}

	/**
	 * ɾ����һ��ӵ�����
	 */
	@Transactional
    public Integer doRemove(Integer id) throws Exception {
		Integer count= 0 ;
		count=this.teacherDAO.doRemovePre(id)  ;  //��ɾ���м��teach��Ӧ�ļ�¼
		count=count+this.teacherDAO.doRemove(id)  ;   //��ɾ��teacher���Ӧ�ļ�¼
		return count;
	}

	@Transactional
	public Teacher findById(Integer id) throws Exception {
		Teacher teacher=this.teacherDAO.findById(id)  ;   //����ID��ѯ
		return teacher;
	}

	@Transactional
	public Map<String,Object> findAll(Integer currentPage, Integer lineSize,
			String keyWord) throws Exception {
		Map<String,Object> map=new HashMap<String, Object>()   ;    //map���ϱ����ҳ��Ϣ������
		PageHelper.startPage(currentPage, lineSize)           ;     //���÷�ҳ����
		if("".equals(keyWord) || keyWord==null){    //��֯�ؼ���
			keyWord="%"+keyWord+"%"  ;
		}else{
			keyWord="%"+keyWord+"%" ;
		}
		List<Teacher> teachers=this.teacherDAO.findAll(keyWord) ;   //ģ����ѯ
		PageInfo<Teacher> pageInfo=new PageInfo<Teacher>(teachers) ; //��ȡ��ҳ��Ϣ
		map.put("teachers", teachers)   ;
		map.put("pageInfo", pageInfo)   ;
		return map;
	}

}
