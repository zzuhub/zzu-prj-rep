package cn.zzu.wcj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zzu.wcj.dao.IScoreDAO;
import cn.zzu.wcj.entity.Score;
import cn.zzu.wcj.service.IScoreService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ScoreServiceImpl implements IScoreService {

	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IScoreDAO scoreDAO    ;    //注入ScoreDAO
	

	@Transactional
	public Integer doCreate(Score entity) throws Exception {
		Integer count= 0 ; 
		count=this.scoreDAO.doCreate(entity)  ;  //增加成绩 
		return count;
	}

	@Transactional
	public Integer doUpdate(Score entity) throws Exception {
		Integer count= 0  ;
		count=this.scoreDAO.doUpdate(entity)  ;  //修改成绩   
		return count   ;
	}

	@Transactional
	public Integer doRemove(Integer id) throws Exception {
		Integer count= 0   ;
		count=this.scoreDAO.doRemove(id)   ;     //删除分数 
		return count  ;
	}

	
	public Score findById(Integer id) throws Exception {
		Score score=this.scoreDAO.findById(id)    ;      //根据ID查找分数 
		return score  ;
	}

	public Map<String, Object> findAll(Integer currentPage, Integer lineSize,
			String keyWord) throws Exception {
		if(keyWord==null||"".equals(keyWord)){   //查询关键词不存在则表示查询全部
			keyWord="%%"   ;
		}else{   //查询关键词存在,则自行组织查询关键词
			keyWord=new String("%"+keyWord+"%").toUpperCase()  ;   //若英文忽略大小写
		}
		PageHelper.startPage(currentPage, lineSize)   ;   //设置分页参数
		List<Score> scores=this.scoreDAO.findAll(keyWord) ;  //分数列表
		PageInfo<Score> pageInfo=new PageInfo<Score>(scores) ;  //封装分页参数
		Map<String,Object> map=new HashMap<String, Object>()  ;  //保存分数列表和分页信息
		map.put("scores", scores)   ;      //保存分数列表 
		map.put("pageInfo", pageInfo)  ;  //保存分页信息 
		return map   ;
	}

	@Transactional
	public Integer doCreateBatch(List<Score> scores) throws Exception {
        Integer count= 0 ;
        count=this.scoreDAO.doCreateBatch(scores)  ;    //批量增加
		return count  ;
	}

	public List<Score> findByStudentIdAndTerm(Map<String, Object> map)
			throws Exception {
		List<Score> scores=this.scoreDAO.findByStudentIdAndTerm(map)  ;  //根据学号和学期查询
		return scores  ;
	}

}
