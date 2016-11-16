package cn.zzu.wcj.service;

import java.util.List;
import java.util.Map;

import cn.zzu.wcj.entity.Permission;

public interface IPermissionService extends IBaseService<Integer, Permission>{
	   
	  
	  List<String> findByUserName(String userName)throws Exception ;  //����UserName��ѯ   
	  
	  //��ҳ��ѯ
	  Map<String,Object> findAll(Integer currentPage,Integer lineSize)throws Exception ;
}
