package cn.zzu.wcj.junit.test;



import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.zzu.wcj.dao.IPermissionDAO;
import cn.zzu.wcj.entity.Permission;
import cn.zzu.wcj.service.IPermissionService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="/applicationContext.xml")
public class PermissionServiceTest {

	@Autowired
	private IPermissionService permissionService  ;
	
	@Test
	public void test() {
		System.out.println(permissionService);
	}
	
	@Autowired
	private IPermissionDAO permissionDAO  ;
	
	
	@Test
	public void testDoCreate()throws Exception{
		  Permission permission=new Permission()  ;
		  permission.setPermissionId(66);
		  permission.setPermissionName("������Ա");
		  permission.setPermissionNote("����ר��");
		  Integer count=permissionService.doCreate(permission) ;
		  System.out.println("��������:"+count);
	}
	
	@Test
	public void testDoUpdate() throws Exception{
		   Permission permission=permissionService.findById(66) ;
		   permission.setPermissionName("�����޸Ĳ���");
		   permission.setPermissionNote("�����޸Ĳ���");
		   Integer count=permissionService.doUpdate(permission)   ;
		   System.out.println("�޸�����:"+count);
	}
	
	@Test
	public void testDoRemove() throws Exception{
		  Integer count=permissionService.doRemove(66)   ;
		  System.out.println("ɾ������:"+count);
	}
	
	@Test
	public void testFindById() throws Exception{
		  System.out.println(permissionService.findById(1));
	}
	
	@Test
	public void testFindPersByUserName() throws Exception{
		     List<String> perms=permissionService.findByUserName("manager") ;
		     System.out.println(perms.size());
	}

	@Test
	public void testFindAll() throws Exception{
		PageHelper.startPage(-1, 0);  //��ʾ��ѯȫ��
		List<Permission> permissions = this.permissionDAO.findAll() ;
		PageInfo<Permission> pageInfo=new PageInfo<Permission>(permissions) ;  //ȡ�÷�ҳ��Ϣ
		System.out.println("��ҳ��:pageSize="+pageInfo.getPages());
		System.out.println("��ǰҳ:currentPage="+pageInfo.getPageNum());
		System.out.println("ÿҳ��ʾ������:lineSize="+pageInfo.getPageSize());
		System.out.println("��¼����:allRecords="+pageInfo.getTotal());
	}
	
}
