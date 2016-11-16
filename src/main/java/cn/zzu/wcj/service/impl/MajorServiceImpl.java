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
		entity.setCreateTime(new Date())  ;        //设置创建时间为当前时间 
		count=this.majorDAO.doCreate(entity)  ;   //增加专业		
		return count;
	}

	@Transactional
	public Integer doUpdate(Major entity) throws Exception {
		Integer count= 0 ;
		count=this.majorDAO.doUpdate(entity) ;   //修改专业
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
		Map<String,Object> map=new HashMap<String, Object>()  ;  //保存
		//1.组织分页关键词
        if("".equals(keyWord) || null==keyWord){
        	 keyWord="%%"  ;   //查询全部
        }else{
        	 keyWord="%"+keyWord+"%"  ;    //组织关键词 
        }		
		
		//2.使用PageHelper
        PageHelper.startPage(currentPage, lineSize)   ;
		
		//3.取得专业集合
        List<Major> majors=this.majorDAO.findAll(keyWord)  ;
		
		//4.创建分页信息
        PageInfo<Major> pageInfo=new PageInfo<Major>(majors)   ;
		
		//5.向map中保存数据
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
