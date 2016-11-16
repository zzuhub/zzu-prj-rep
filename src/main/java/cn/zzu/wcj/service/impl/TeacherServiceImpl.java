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
	 * 删除是一项复杂的任务
	 */
	@Transactional
    public Integer doRemove(Integer id) throws Exception {
		Integer count= 0 ;
		count=this.teacherDAO.doRemovePre(id)  ;  //先删除中间表teach对应的记录
		count=count+this.teacherDAO.doRemove(id)  ;   //在删除teacher表对应的记录
		return count;
	}

	@Transactional
	public Teacher findById(Integer id) throws Exception {
		Teacher teacher=this.teacherDAO.findById(id)  ;   //根据ID查询
		return teacher;
	}

	@Transactional
	public Map<String,Object> findAll(Integer currentPage, Integer lineSize,
			String keyWord) throws Exception {
		Map<String,Object> map=new HashMap<String, Object>()   ;    //map集合保存分页信息和数据
		PageHelper.startPage(currentPage, lineSize)           ;     //设置分页参数
		if("".equals(keyWord) || keyWord==null){    //组织关键词
			keyWord="%"+keyWord+"%"  ;
		}else{
			keyWord="%"+keyWord+"%" ;
		}
		List<Teacher> teachers=this.teacherDAO.findAll(keyWord) ;   //模糊查询
		PageInfo<Teacher> pageInfo=new PageInfo<Teacher>(teachers) ; //获取分页信息
		map.put("teachers", teachers)   ;
		map.put("pageInfo", pageInfo)   ;
		return map;
	}

}
