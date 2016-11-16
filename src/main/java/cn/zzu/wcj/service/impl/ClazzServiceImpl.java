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
	private IClazzDAO clazzDAO   ;    //注入DAO层
	

	@Transactional
	public Integer doCreate(Clazz entity) throws Exception {
		Integer count=0 ;
		count=this.clazzDAO.doCreate(entity)  ;  //增加操作
		return count;
	}

	@Transactional
	public Integer doUpdate(Clazz entity) throws Exception {
		Integer count= 0   ;
		count=this.clazzDAO.doUpdate(entity)  ;   //查询
		return count;
	}

	@Transactional
	public Integer doRemove(Integer id) throws Exception {
		Integer count= 0 ;     //统计删除数据量
		//1.删除teach表对应的数据
		count=count+this.clazzDAO.doRemoveTeach(id)   ;
		//2.删除score表对应的数据
		count=count+this.clazzDAO.doRemoveScore(id)   ;
		//3.删除student表对应的数据
		count=count+this.clazzDAO.doRemoveStudent(id)  ;
		//4.删除clazz表对应的数据
		count=count+this.clazzDAO.doRemove(id)   ;  
		return count;
	}

	@Transactional
	public Clazz findById(Integer id) throws Exception {
		Clazz clazz=this.clazzDAO.findById(id)  ;   //根据ID查询
		return clazz   ;
	}

	@Transactional
	public Map<String, Object> findAll(Integer currentPage, Integer lineSize,
			String keyWord) throws Exception {
		    Map<String,Object> map=new HashMap<String, Object>()  ; //保存数据和分页信息
		    if("".equals(keyWord) || null==keyWord){
		    	 keyWord="%%"  ;   //查询全部
		    }else{
		    	 keyWord=("%"+keyWord+"%").toUpperCase()   ;    //组织关键词
		    }
		    PageHelper.startPage(currentPage, lineSize)  ;   //设置分页参数
		    List<Clazz> clazzs=this.clazzDAO.findAll(keyWord)  ; //班级
            PageInfo<Clazz> pageInfo=new PageInfo<Clazz>(clazzs)  ;   //分页信息
            map.put("clazzs", clazzs)       ;       //班级列表
            map.put("pageInfo", pageInfo)   ;       //分页信息
		    return map;
	}

	@Transactional
	public Clazz findByName(String clazzName) throws Exception {
		Clazz clazz=this.clazzDAO.findByName(clazzName)  ;     //根据班级名称查找班级 
		return clazz   ;
	}

	public List<Clazz> findClazzsByMajorId(Integer majorId) throws Exception {
        List<Clazz> clazzs=this.clazzDAO.findClazzsByMajorId(majorId)  ;
		return clazzs;
	}
	
	

}
