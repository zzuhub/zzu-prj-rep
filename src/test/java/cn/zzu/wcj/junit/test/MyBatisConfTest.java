package cn.zzu.wcj.junit.test;



import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="/applicationContext.xml")
public class MyBatisConfTest {

	@Autowired
	private DataSource dataSource  ;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory   ;
	
	@Autowired
	private MapperScannerConfigurer mapperScannerConfigurer  ;
	
	@Autowired
	private DataSourceTransactionManager transactionManager  ;
	
	@Test
	public void testDataSource() {
		System.out.println(dataSource);
	}
	
	@Test
	public void testSqlSessionFactory(){
		System.out.println(sqlSessionFactory);
	}
	
    @Test
    public void testMapperScannerConfigurer(){
    	System.out.println(mapperScannerConfigurer);
    }
    
    @Test
    public void testTranscationManager(){
    	System.out.println(transactionManager);
    }
    
    

}
