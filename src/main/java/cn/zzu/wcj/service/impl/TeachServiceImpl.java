package cn.zzu.wcj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zzu.wcj.dao.ITeachDAO;
import cn.zzu.wcj.entity.Teach;
import cn.zzu.wcj.service.ITeachService;

@Service
public class TeachServiceImpl implements ITeachService {

	
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITeachDAO teachDAO   ;
	
	
	@Transactional
	public Integer doCreate(Teach entity) throws Exception {
		Integer count=0 ;
		count=this.teachDAO.doCreate(entity)  ;
		return count;
	}

	@Transactional
	public Integer doUpdate(Teach entity) throws Exception {
		Integer count=0   ;
		count=this.teachDAO.doUpdate(entity)  ;
		return count;
	}

	@Transactional
	public Integer doRemove(Integer id) throws Exception {
		Integer count= 0 ;
		count=this.teachDAO.doRemove(id)  ;
		return count   ;
	}

	public Teach findById(Integer id) throws Exception {
        Teach teach=null    ;
        teach=this.teachDAO.findById(id)     ;  //根据ID查询授课信息
		return teach        ;
	}

	public Map<String,Object> findAll(Integer currentPage, Integer lineSize)
			throws Exception {
		Map<String,Object> map=new HashMap<String, Object>()   ;
		PageHelper.startPage(currentPage, lineSize)   ;  //设置分页参数
		List<Teach> teachs=this.teachDAO.findAll()   ;   //教学列表
		PageInfo<Teach> pageInfo=new PageInfo<Teach>(teachs)  ;   //得到分页参数
		map.put("teachs", teachs)  ;
		map.put("pageInfo", pageInfo)  ;
		return map;
	}

	
	

}
