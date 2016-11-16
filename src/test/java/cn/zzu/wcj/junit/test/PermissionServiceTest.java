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
		  permission.setPermissionName("测试人员");
		  permission.setPermissionNote("测试专用");
		  Integer count=permissionService.doCreate(permission) ;
		  System.out.println("增加行数:"+count);
	}
	
	@Test
	public void testDoUpdate() throws Exception{
		   Permission permission=permissionService.findById(66) ;
		   permission.setPermissionName("测试修改操作");
		   permission.setPermissionNote("测试修改操作");
		   Integer count=permissionService.doUpdate(permission)   ;
		   System.out.println("修改行数:"+count);
	}
	
	@Test
	public void testDoRemove() throws Exception{
		  Integer count=permissionService.doRemove(66)   ;
		  System.out.println("删除行数:"+count);
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
		PageHelper.startPage(-1, 0);  //表示查询全部
		List<Permission> permissions = this.permissionDAO.findAll() ;
		PageInfo<Permission> pageInfo=new PageInfo<Permission>(permissions) ;  //取得分页信息
		System.out.println("总页数:pageSize="+pageInfo.getPages());
		System.out.println("当前页:currentPage="+pageInfo.getPageNum());
		System.out.println("每页显示数据量:lineSize="+pageInfo.getPageSize());
		System.out.println("记录条数:allRecords="+pageInfo.getTotal());
	}
	
}
