package cn.zzu.wcj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zzu.wcj.dao.IClazzDAO;
import cn.zzu.wcj.entity.Clazz;
import cn.zzu.wcj.service.IClazzService;

@Service
public class ClazzServiceImpl implements IClazzService {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IClazzDAO clazzDAO   ;    //ע��DAO��
	

	@Transactional
	public Integer doCreate(Clazz entity) throws Exception {
		Integer count=0 ;
		count=this.clazzDAO.doCreate(entity)  ;  //���Ӳ���
		return count;
	}

	@Transactional
	public Integer doUpdate(Clazz entity) throws Exception {
		Integer count= 0   ;
		count=this.clazzDAO.doUpdate(entity)  ;   //��ѯ
		return count;
	}

	@Transactional
	public Integer doRemove(Integer id) throws Exception {
		Integer count= 0 ;     //ͳ��ɾ��������
		//1.ɾ��teach���Ӧ������
		count=count+this.clazzDAO.doRemoveTeach(id)   ;
		//2.ɾ��score���Ӧ������
		count=count+this.clazzDAO.doRemoveScore(id)   ;
		//3.ɾ��student���Ӧ������
		count=count+this.clazzDAO.doRemoveStudent(id)  ;
		//4.ɾ��clazz���Ӧ������
		count=count+this.clazzDAO.doRemove(id)   ;  
		return count;
	}

	@Transactional
	public Clazz findById(Integer id) throws Exception {
		Clazz clazz=this.clazzDAO.findById(id)  ;   //����ID��ѯ
		return clazz   ;
	}

	@Transactional
	public Map<String, Object> findAll(Integer currentPage, Integer lineSize,
			String keyWord) throws Exception {
		    Map<String,Object> map=new HashMap<String, Object>()  ; //�������ݺͷ�ҳ��Ϣ
		    if("".equals(keyWord) || null==keyWord){
		    	 keyWord="%%"  ;   //��ѯȫ��
		    }else{
		    	 keyWord=("%"+keyWord+"%").toUpperCase()   ;    //��֯�ؼ���
		    }
		    PageHelper.startPage(currentPage, lineSize)  ;   //���÷�ҳ����
		    List<Clazz> clazzs=this.clazzDAO.findAll(keyWord)  ; //�༶
            PageInfo<Clazz> pageInfo=new PageInfo<Clazz>(clazzs)  ;   //��ҳ��Ϣ
            map.put("clazzs", clazzs)       ;       //�༶�б�
            map.put("pageInfo", pageInfo)   ;       //��ҳ��Ϣ
		    return map;
	}

	@Transactional
	public Clazz findByName(String clazzName) throws Exception {
		Clazz clazz=this.clazzDAO.findByName(clazzName)  ;     //���ݰ༶���Ʋ��Ұ༶ 
		return clazz   ;
	}

	public List<Clazz> findClazzsByMajorId(Integer majorId) throws Exception {
        List<Clazz> clazzs=this.clazzDAO.findClazzsByMajorId(majorId)  ;
		return clazzs;
	}
	
	

}
