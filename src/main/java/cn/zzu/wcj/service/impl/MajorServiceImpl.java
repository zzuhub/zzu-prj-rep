package cn.zzu.wcj.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zzu.wcj.dao.IMajorDAO;
import cn.zzu.wcj.entity.Major;
import cn.zzu.wcj.service.IMajorService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MajorServiceImpl implements IMajorService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IMajorDAO majorDAO   ;
	
	@Transactional
	public Integer doCreate(Major entity) throws Exception {
		Integer count= 0  ;
		entity.setCreateTime(new Date())  ;        //���ô���ʱ��Ϊ��ǰʱ�� 
		count=this.majorDAO.doCreate(entity)  ;   //����רҵ		
		return count;
	}

	@Transactional
	public Integer doUpdate(Major entity) throws Exception {
		Integer count= 0 ;
		count=this.majorDAO.doUpdate(entity) ;   //�޸�רҵ
		return count ;
	}

	@Transactional
	public Integer doRemove(Integer id) throws Exception {
		Integer count=0 ;
		//1.score
		count = count+this.majorDAO.doRemoveScore(id) ;
		
		//2.student 
		count = count+this.majorDAO.doRemoveStudent(id) ;
		
		
		//3.teach
		count = count+this.majorDAO.doRemoveTeach(id) ;
		
		//4.clazz
		count = count+this.majorDAO.doRemoveClazz(id) ;
		
		//5.major
		count = count+this.majorDAO.doRemove(id) ;
		
		return count;
	}

	@Transactional
	public Major findById(Integer id) throws Exception {
		Major major = this.majorDAO.findById(id)  ;
		return major;
	}

	@Transactional
	public Map<String,Object> findAll(Integer currentPage, Integer lineSize,
			String keyWord) throws Exception {
		Map<String,Object> map=new HashMap<String, Object>()  ;  //����
		//1.��֯��ҳ�ؼ���
        if("".equals(keyWord) || null==keyWord){
        	 keyWord="%%"  ;   //��ѯȫ��
        }else{
        	 keyWord="%"+keyWord+"%"  ;    //��֯�ؼ��� 
        }		
		
		//2.ʹ��PageHelper
        PageHelper.startPage(currentPage, lineSize)   ;
		
		//3.ȡ��רҵ����
        List<Major> majors=this.majorDAO.findAll(keyWord)  ;
		
		//4.������ҳ��Ϣ
        PageInfo<Major> pageInfo=new PageInfo<Major>(majors)   ;
		
		//5.��map�б�������
		map.put("majors", majors)       ;
		map.put("pageInfo", pageInfo)   ;
        
        
		return map;
	}

	@Transactional
	public Major findMajorByName(String majorName) throws Exception {
        Major major=this.majorDAO.findMajorByName(majorName)  ;
		return major;
	}

}
