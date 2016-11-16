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
	private IScoreDAO scoreDAO    ;    //ע��ScoreDAO
	

	@Transactional
	public Integer doCreate(Score entity) throws Exception {
		Integer count= 0 ; 
		count=this.scoreDAO.doCreate(entity)  ;  //���ӳɼ� 
		return count;
	}

	@Transactional
	public Integer doUpdate(Score entity) throws Exception {
		Integer count= 0  ;
		count=this.scoreDAO.doUpdate(entity)  ;  //�޸ĳɼ�   
		return count   ;
	}

	@Transactional
	public Integer doRemove(Integer id) throws Exception {
		Integer count= 0   ;
		count=this.scoreDAO.doRemove(id)   ;     //ɾ������ 
		return count  ;
	}

	
	public Score findById(Integer id) throws Exception {
		Score score=this.scoreDAO.findById(id)    ;      //����ID���ҷ��� 
		return score  ;
	}

	public Map<String, Object> findAll(Integer currentPage, Integer lineSize,
			String keyWord) throws Exception {
		if(keyWord==null||"".equals(keyWord)){   //��ѯ�ؼ��ʲ��������ʾ��ѯȫ��
			keyWord="%%"   ;
		}else{   //��ѯ�ؼ��ʴ���,��������֯��ѯ�ؼ���
			keyWord=new String("%"+keyWord+"%").toUpperCase()  ;   //��Ӣ�ĺ��Դ�Сд
		}
		PageHelper.startPage(currentPage, lineSize)   ;   //���÷�ҳ����
		List<Score> scores=this.scoreDAO.findAll(keyWord) ;  //�����б�
		PageInfo<Score> pageInfo=new PageInfo<Score>(scores) ;  //��װ��ҳ����
		Map<String,Object> map=new HashMap<String, Object>()  ;  //��������б�ͷ�ҳ��Ϣ
		map.put("scores", scores)   ;      //��������б� 
		map.put("pageInfo", pageInfo)  ;  //�����ҳ��Ϣ 
		return map   ;
	}

	@Transactional
	public Integer doCreateBatch(List<Score> scores) throws Exception {
        Integer count= 0 ;
        count=this.scoreDAO.doCreateBatch(scores)  ;    //��������
		return count  ;
	}

	public List<Score> findByStudentIdAndTerm(Map<String, Object> map)
			throws Exception {
		List<Score> scores=this.scoreDAO.findByStudentIdAndTerm(map)  ;  //����ѧ�ź�ѧ�ڲ�ѯ
		return scores  ;
	}

}
