package cn.zzu.wcj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zzu.wcj.dao.IPermissionDAO;
import cn.zzu.wcj.entity.Permission;
import cn.zzu.wcj.service.IPermissionService;

@Transactional
@Service
public class PermissionServiceImpl implements IPermissionService {


	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IPermissionDAO permissionDAO  ;      //ע��IPermissionDAO

	public Integer doCreate(Permission entity) throws Exception {
		Integer count=0 ;
		count=this.permissionDAO.doCreate(entity)  ;
		return count;
	}

	public Integer doUpdate(Permission entity) throws Exception {
		Integer count=0 ;
		count = this.permissionDAO.doUpdate(entity)  ;
		return count;
	}

	public Integer doRemove(Integer id) throws Exception {
		Integer count=0 ; 
		count=this.permissionDAO.doRemove(id)  ;
		return count;
	}

	public Permission findById(Integer id) throws Exception {
		Permission permission=null ;
		permission=this.permissionDAO.findById(id)  ;
		return permission;
	}

	public List<String> findByUserName(String userName) throws Exception {
		List<String> perms=null ;
		perms=this.permissionDAO.findByUserName(userName)  ;
		return perms;
	}

	public Map<String, Object> findAll(Integer currentPage, Integer lineSize)
			throws Exception {
        Map<String,Object> map=new HashMap<String, Object>()  ;   //�����ҳ���ݺͷ�ҳԪ����
        PageHelper.startPage(currentPage, lineSize) ;   //���÷�ҳ����
        List<Permission> permissions = this.permissionDAO.findAll()  ; //ȡ��Ȩ��
        PageInfo<Permission> pageInfo=new PageInfo<Permission>(permissions) ;   //�����ҳ��Ϣ
        map.put("permissions", permissions)  ;   //������������
        map.put("pageInfo", pageInfo)  ;   //�����ҳ��Ϣ
		return map;
	}

}
