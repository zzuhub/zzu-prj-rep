package cn.zzu.wcj.junit.test;

import static org.junit.Assert.assertNotNull;


import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.zzu.wcj.entity.Permission;
import cn.zzu.wcj.entity.Role;
import cn.zzu.wcj.service.IRoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="/applicationContext.xml")
public class RoleServiceTest {

	@Autowired
	private IRoleService roleService   ;
	
	@Test
	public void test() {
		assertNotNull(roleService);
	}
	
	@Test
	public void testDoCreate() throws Exception{
		Role role=new Role()  ;
		role.setRoleName("�����ǲ��Խ�ɫ") ;
		Permission permission=new Permission() ;
		permission.setPermissionId(1);
		role.setPermissions(Arrays.asList(permission));     //1����ɫ�ж���Ȩ��
		Integer count=this.roleService.doCreate(role)  ;
		System.out.println("������������:"+count);
	}
	
	@Test
	public void testDoUpdate() throws Exception{
		Role role=this.roleService.findById(4) ;
		role.setRoleName("���ӱ��޸��˰�����");
		Permission permission1=new Permission()  ;
		permission1.setPermissionId(20);
		Permission permission2=new Permission()  ;
		permission2.setPermissionId(10);
		role.setPermissions(Arrays.asList(permission1,permission2));
		Integer count=this.roleService.doUpdate(role) ;
		System.out.println("������������:"+count);
	}
	
	@Test
	public void testFindById() throws Exception{
		Role role=roleService.findById(4)  ;
		System.out.println("Ȩ�޸���:"+role.getPermissions().size());
	}
	
	@Test
	public void testDoRemove() throws Exception{
		Integer count=this.roleService.doRemove(4);
		System.out.println("ɾ����������:"+count);
	}
	
    @Test
    public void testFindByUserName()throws Exception{
    	System.out.println(this.roleService.findByUserName("manager").size());
    }
	
	@Test
	public void testFindAll()throws Exception{  
		   List<Role> roles=this.roleService.findAll()   ;
		   for(Role role : roles){
			   System.out.println(role.getRoleId()+"--->"+role.getRoleName());
		   }
	}
	
	@Test
	public void testFindByName() throws Exception{
		 Role role = this.roleService.findByRoleName("SuperAdmin");
		 System.out.println(new ObjectMapper().writeValueAsString(role));
	}
    
    
}
