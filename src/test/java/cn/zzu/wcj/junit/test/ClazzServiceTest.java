package cn.zzu.wcj.junit.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.zzu.wcj.entity.Clazz;
import cn.zzu.wcj.entity.Major;
import cn.zzu.wcj.service.IClazzService;

import com.github.pagehelper.PageInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="/applicationContext.xml")
public class ClazzServiceTest {

	@Autowired
	private IClazzService clazzService  ;
	
	@Test
	public void test() {
		assertNotNull(clazzService)  ;
	}
	
	@Test
	public void testFindById() throws Exception{
		Clazz clazz = this.clazzService.findById(9)  ;
		System.out.println("--------------"+clazz);
	}
	
	@Test
	public void testDoUpdate()throws Exception{
		Clazz clazz = this.clazzService.findById(3)  ;
        clazz.setClazzName("����2��");
        clazz.getMajor().setMajorId(2); 
        Integer count=this.clazzService.doUpdate(clazz) ;
        System.out.println("������������:"+count);
	}
	
	@Test
	public void testDoCreate() throws Exception{
		
		Clazz clazz=new Clazz()   ;
		clazz.setClazzName("TestInsert") ;
		Major major=new Major()  ;
		major.setMajorId(2)  ;
		clazz.setMajor(major) ;
		Integer count=this.clazzService.doCreate(clazz)  ;
		System.out.println("������������:"+count);
		
	}
	
	@SuppressWarnings(value={"unchecked"})
	@Test
	public void testFindAll() throws Exception{
		   Map<String,Object> map=this.clazzService.findAll(1, 2, "����") ;
		   List<Clazz> clazzs=(List<Clazz>) map.get("clazzs") ;
		   PageInfo<Clazz> pageInfo=(PageInfo<Clazz>) map.get("pageInfo")   ;
		   System.out.println("----------------��ҳ��Ϣ��------------------------");
		   System.out.println("��ҳ��:"+pageInfo.getPages());
		   System.out.println("��������:"+pageInfo.getTotal());
		   System.out.println("ҳ��:"+pageInfo.getPageNum());
		   System.out.println("ʵ��������:"+pageInfo.getSize());
		   System.out.println("ÿҳ������:"+pageInfo.getPageSize());
		   System.out.println("----------------��ҳ��Ϣ��------------------------");
		   for(Clazz clazz : clazzs){
			   System.out.println(clazz);
		   }
	}
	
	
	//ɾ����������д
	@Test
	public void testDoRemove() throws Exception{
		Integer count=this.clazzService.doRemove(9)  ;  //ɾ��
		System.out.println("ɾ����������:"+count);
	}
	
	@Test
	public void testFindByName() throws Exception{
		String clazzName="����1��" ;
		System.out.println("************"+this.clazzService.findByName(clazzName));
	}
	
   @Test
   public void testFindClazzsByMajorId() throws Exception{
	    System.out.println("***************"+this.clazzService.findClazzsByMajorId(2));
   }
	
	
}
