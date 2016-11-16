package cn.zzu.wcj.junit.test;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.zzu.wcj.entity.Role;
import cn.zzu.wcj.entity.User;
import cn.zzu.wcj.service.IUserService;
import cn.zzu.wcj.util.EncryptUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"/applicationContext.xml"})
public class UserServiceTest {

	@Autowired
	private IUserService userService   ;
	
	@Autowired
	private EncryptUtils encryptUtils  ;
	
	
	
	@Test
	public void test() {
		assertNotNull(userService);
	}
	
	@Test
	public void testDoCreate() throws Exception{
		User user=new User()   ;
		user.setUserName("admin");
		user.setPassword("admin1234");
		user.setCreateTime(new Date());
		Integer count= 0  ;
		Role role=new Role()  ;
		role.setRoleId(2);
		
		user.setRoles(Arrays.asList(role)) ;   //1���û��ж����ɫ
		
		count=userService.doCreate(user)   ;   //�����û�
		System.out.println((count>0?"�������ݳɹ�:":"��������ʧ��:")+count);
		
	}
	
	@Test
	public void testDoUpdate() throws Exception{
		 User user = this.userService.findById(3);
		 user.setUserName("manager");
		 user.setPassword("manager1234");
		 Role role=new Role()  ;
		 role.setRoleId(3);
		 user.setRoles(Arrays.asList(role)) ;   //1���û��ж����ɫ
		 Integer count=this.userService.doUpdate(user) ;
		 System.out.println("��������:"+count);
	}
	
	@Test
	public void testRemove() throws Exception{
		  Integer count=this.userService.doRemove(4)  ;
		  System.out.println("ɾ����������:"+count);
	}
	
	@Test
	public void testFindById()throws Exception{
		  User user=this.userService.findById(4)  ;
		  System.out.println(user);
	}
	

	
	@Test
	public void testFindByUserName() throws Exception{
		System.out.println(userService.findByUserName("admin1"));
	}
	
    @Test
    public void testFindAll()throws Exception{
    	  List<User> users=this.userService.findAll()  ;    //���ҵ�ȫ���û�
    	  for(User user : users){
    		  System.out.println("�û���:"+user.getUserName()+",����:"+user.getPassword());
    	  }
    }
	
	

}
