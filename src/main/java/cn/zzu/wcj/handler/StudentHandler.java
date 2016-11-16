package cn.zzu.wcj.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.zzu.wcj.entity.Clazz;
import cn.zzu.wcj.entity.CreditPerTermAcca;
import cn.zzu.wcj.entity.Score;
import cn.zzu.wcj.entity.Student;
import cn.zzu.wcj.service.IAccaService;
import cn.zzu.wcj.service.IClazzService;
import cn.zzu.wcj.service.IMajorService;
import cn.zzu.wcj.service.IScoreService;
import cn.zzu.wcj.service.IStudentService;
import cn.zzu.wcj.util.ExcelUtils;
import cn.zzu.wcj.util.GenerateChartUtils;
import cn.zzu.wcj.util.IPTimeStamp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;


@RequestMapping("/Student")
@Controller
public class StudentHandler {

  @Autowired
  private IStudentService studentService   ;    //学生服务层 
  
  /*
   *以下两个服务层主要是为了二级联动菜单准备的 
   */
  
  @Autowired
  private IMajorService majorService       ;    //专业服务层
  
  @Autowired
  private IClazzService clazzService        ;    //班级服务层  
  
  @Autowired
  private IScoreService scoreService    ;         //成绩服务层
  
  @Autowired
  private IAccaService accaService     ;        //注入ACCA服务层 
  
  @Autowired
  private IPTimeStamp ipTimeStamp   ;            //上传文件自动重命名
  
  
  @Autowired
  private ExcelUtils excelUtils     ;         //Excel工具
  
  @Autowired
  private GenerateChartUtils  generateChartUtils   ;   //注入图表生成工具类 
  
	
  @SuppressWarnings(value={"unchecked"})
  @RequestMapping("/list")
  public ModelAndView list(@RequestParam(name="currentPage",defaultValue="1")Integer currentPage,
		                   @RequestParam(name="lineSize",defaultValue="5")Integer lineSize,
		                   @RequestParam(name="keyWord",defaultValue="")String keyWord){
	     ModelAndView mv=new ModelAndView("student_list")  ;
	     try{
	    	 Map<String,Object> map=this.studentService.findAll(currentPage, lineSize, keyWord) ;
	    	 List<Student> students=(List<Student>) map.get("students");  //学生集合
	    	 PageInfo<Student> pageInfo=(PageInfo<Student>) map.get("pageInfo") ;  //分页信息
	    	 mv.addObject("students", students)  ;   //放入学生集合模型
	    	 mv.addObject("pageInfo", pageInfo)   ;  //放入分页信息模型
	    	 mv.addObject("keyWord", keyWord)   ;   //回填KeyWord关键词 
  		     mv.addObject("lineSizes", new int[]{5,10,15,20,25,30})  ; //每页显示数据量 
	     }catch(Exception e){
	    	  mv.setViewName("err")  ;    //出现异常,直接跳转到错误页 
	     }
	     return mv ;
  }
  
  //URL解析:http://localhost/ZZUPrj/Student/findDetailsById/201477E0332
  @RequestMapping("/findDetailsById/{studentId}")
  public ModelAndView findDetailsById(@PathVariable("studentId")String studentId){
	     ModelAndView mv=new ModelAndView("student_details")  ;
	     try{
	    	   Student student = this.studentService.findById(studentId) ;  //根据ID查询学生信息
	    	   if(student==null){   //学生信息不存在(可能已经删除),直接抛出异常
	    		   throw new Exception()  ;
	    	   }else{   //学生信息存在,存储学生模型
	    		   mv.addObject("student", student)  ;   //增加学生模型 
	    	   }
	     }catch(Exception e){
	    	   mv.setViewName("err")  ;   //出现异常直接跳转到错误页 
	     }
	     return mv ;
  }
  
  //URL解析:http://localhost/ZZUPrj/Student/doCreatePre
  @RequestMapping("/doCreatePre")
  public ModelAndView doCreatePre(){
	     ModelAndView mv=new ModelAndView("student_insert")  ;   //跳转到增加页
	     try{
	    	   Map<String, Object> map = this.majorService.findAll(1, 0, "")  ;   //查找全部专业
	    	   mv.addObject("majors", map.get("majors"))  ;   //添加专业集合模型 
	     }catch(Exception e){
	    	   mv.setViewName("err")   ;    //出现异常,直接跳转到错误页 
	     }
	     return mv ;
  }
  
  //URL解析:/ZZUPrj/validateStudentIdExists
  @RequestMapping("/validateStudentIdExists")
  @ResponseBody
  public String validateStudentIdExists(@RequestParam("studentId")String studentId){
	    String jsonStr=null ;
	    try{
	    	 Student student=this.studentService.findById(studentId)  ;   //根据ID查询学生信息
	    	 jsonStr=new ObjectMapper().writeValueAsString(student)   ;   //打包JSON字符串
	    }catch(Exception e){
			 jsonStr="{\"err\":\"err\"}" ;
	    }
	    return jsonStr  ;
  }
  
  //URL解析:/ZZUPrj/Student/getClazzsByMajorId
  @RequestMapping("getClazzsByMajorId")
  @ResponseBody
  public String getClazzsByMajorId(@RequestParam("majorId")Integer majorId){
	     String json=null ;
	     try{
	    	  List<Clazz> clazzs=this.clazzService.findClazzsByMajorId(majorId) ; //取得班级集合
	    	  json=new ObjectMapper().writeValueAsString(clazzs) ;  //拼凑JSON
	     }catch(Exception e){
	    	  json=null ;
	     }
	     return json ;
  }
  
  //URL解析:/ZZUPrj/Student/doCreate
  @RequestMapping("/doCreate")
  public String doCreate(@RequestParam("photoFile") MultipartFile photoFile,
		                 @RequestParam("birth")String birthStr,
		                 @RequestParam("studentId")String studentId,
		                 @RequestParam("studentName")String studentName,
		                 @RequestParam("clazzId")Integer clazzId,
		                 @RequestParam("sex")String sex ,
		                 @RequestParam("address")String address,
		                 @RequestParam("password")String password,
		                 @RequestParam("tel")String tel,
		                 @RequestParam("note")String note,
		                 HttpServletRequest req){
	     String page="redirect:/Student/list"   ;
	     try{
	    	 //没有上传图片
	    	 String photo=null ;
	    	 if(photoFile.getOriginalFilename()==null||"".equals(photoFile.getOriginalFilename())){
	    		  photo="nophoto.jpg" ;
	    	 }else{
	    		  String stuff=photoFile.getOriginalFilename().split("\\.")[1]  ;   //后缀
	    		  this.ipTimeStamp.setIp(req.getRemoteAddr());   //设置IP
	    		  String prefix=this.ipTimeStamp.getIPTimeStampRand() ;  //前缀
	    		  photo=prefix+"."+stuff ;   //照片名
	    		  InputStream input = photoFile.getInputStream();  //得到输入流
	    		  String path=req.getSession().getServletContext().getRealPath("photo") ;  //路径
	    		  String fileName=path+File.separator+photo  ;     //文件全名
	    		  File file=new File(fileName)  ;   //文件
	    		  OutputStream out=new FileOutputStream(file) ;   //文件输入流
	    		  byte[] buffer=new byte[1024]  ;  //缓冲区
	    		  int length= 0 ;
	    		  while((length=input.read(buffer))>0){  //边读边写
	    			  out.write(buffer, 0, length) ;
	    		  }
	    		  out.close();  
	    		  input.close();  
	    	 }
	    	 Date birth=new SimpleDateFormat("yyyy-MM-dd").parse(birthStr)  ; //格式化出生日期
	    	 Student student=new Student();   //实例化学生
	    	 student.setStudentId(studentId); //设置学号
	    	 student.setStudentName(studentName);  //设置学生姓名
	    	 student.setClazzId(clazzId);  //设置班级代码
	    	 student.setSex(sex);     //设置学生性别
	    	 student.setAge(null);   //年龄Oracle函数自动计算,这里传空值即可
	    	 student.setAddress(address);    //设置地址
	    	 student.setBirth(birth);      //设置出生日期
	    	 student.setPassword(password);  //设置密码
	    	 student.setTel(tel);   //设置联系电话
	    	 student.setPhoto(photo);  //设置照片
	    	 student.setNote(note);   //设置简介
	    	 this.studentService.doCreate(student)  ;
	     }catch(Exception e){
	    	   page="err"  ;    //出现异常直接跳转到错误页
	    	   System.out.println(e);
	     }
	     return page ;
  }
  
  //文件下载
  //URL解析:http://localhost/ZZUPrj/Student/downloadTemplate
  @RequestMapping("/downloadTemplate")
  public ResponseEntity<byte[]> downloadTemplate(HttpServletRequest HttpReq){
  	byte[] body=null;
      String path=HttpReq.getServletContext().getRealPath("/upload/studentTemplate.xls")  ;   //取得XLS文件位置
      try{
      	    InputStream in=new FileInputStream(path) ;   //创建输入流
	        body=new byte[in.available()];               //保存文件
	        in.read(body);                               //读取文件
	        in.close();                     //读取结束就关闭,释放资源 
      }catch(Exception e){
      	e.printStackTrace();   //暂时测试程序打印异常
      }
      
      HttpHeaders headers=new HttpHeaders();
      //响应头的名字和响应头的值
      headers.add("Content-Disposition", "attachment;filename=studentTemplate.xls");
      HttpStatus statusCode=HttpStatus.OK;
      ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
      return response;
  }
  
  
  
  //URL解析:http://localhost/ZZUPrj/Student/doCreateBatchPre
  @RequestMapping("/doCreateBatchPre")
  public String doCreateBatchPre(){
	     return "student_batch_insert"   ;    //到达批量增加页
  }
  
  //URL解析:/ZZUPrj/Student/doBatchCreate
  @RequestMapping("/doBatchCreate")
  public String doBatchCreate(@RequestParam("dataFile") MultipartFile dataFile){
	     String page="redirect:/Student/list"  ;    //程序正常执行就跳转到学生列表页
	     try{
	    	 InputStream input = dataFile.getInputStream();  //输入流
	    	 List<Student> students=this.excelUtils.readAsStudentList(input) ;
	    	 Integer count=0  ;
	    	 count=this.studentService.doCreateBatch(students)  ;   //批量增加学生信息
	    	 if(count==0){
	    		 throw new Exception() ;   //出现异常直接跳转到错误页
	    	 }
	     }catch(Exception e){
	    	   e.printStackTrace();   //打印异常 
	    	   page="err"  ;     //程序出现异常,直接跳转到错误页
	     }
	     return page ;
  }
  
  
  //URL解析:/ZZUPrj/Student/doEdit/777
  @RequestMapping("/doEdit/{studentId}")
  public ModelAndView doEdit(@PathVariable("studentId")String studentId){
	     ModelAndView mv=new ModelAndView("student_update")  ;   //跳转到修改页
	     try{
	    	 Student student=this.studentService.findById(studentId) ;  //查找学生
	    	 if(student==null){   //学生信息已经不存在
	    		 throw new Exception() ;   //直接抛出异常 
	    	 }else{
	    		 mv.addObject("student", student)  ;   //添加学生模型 
	    		 mv.addObject("fmtBirth", new SimpleDateFormat("yyyy-MM-dd").format(student.getBirth())) ;  //格式化回显
	    		 Map<String, Object> map = this.majorService.findAll(1, 0, "")  ;   //查找全部专业
		    	 mv.addObject("majors", map.get("majors"))  ;   //添加专业集合模型 
	    	 }
	     }catch(Exception e){
	    	   mv.setViewName("err")  ;    //出现异常直接跳转到错误页
	     }
	     return mv  ;
  }
  
   //URL解析:/ZZUPrj/Student/doUpdate
  @RequestMapping("/doUpdate")
  public String doUpdate(@RequestParam("photoFile") MultipartFile photoFile,
          @RequestParam("birth")String birthStr,
          @RequestParam("studentId")String studentId,
          @RequestParam("studentName")String studentName,
          @RequestParam("clazzId")Integer clazzId,
          @RequestParam("sex")String sex ,
          @RequestParam("address")String address,
          @RequestParam("password")String password,
          @RequestParam("tel")String tel,
          @RequestParam("note")String note,
          @RequestParam("oldPhoto")String oldPhoto,
          HttpServletRequest req){
	    String page="redirect:/Student/list" ;
	    try{
	    	Date birth=new SimpleDateFormat("yyyy-MM-dd")
	    	               .parse(birthStr)   ;              //格式化
	    	String photo=null ;   //上传照片名字
	    	if(photoFile.getOriginalFilename()==null||"".equals(photoFile.getOriginalFilename())){
	    		 photo=oldPhoto  ;    //没有上传图片就用老图片
	    	}else{   //有上传图片
	    		String path=req.getSession().getServletContext().getRealPath("photo") ;  //路径
	    		//1.先删除原有图片
	    		 String fileName=path+File.separator+oldPhoto  ;     //原有照片名字
	    		 File file=null ;
	    		 if(!("nophoto.jpg".equals(oldPhoto))){   //不是默认图片
		    		  file=new File(fileName) ;    //原有照片文件
		    		 if(!file.delete()){   //删除失败
		    			 throw new Exception() ;   //删除失败,直接抛出异常
	              	 }   
	    		 }
	    		//2.添加新图片
	    		  String stuff=photoFile.getOriginalFilename().split("\\.")[1]  ;   //后缀
	    		  this.ipTimeStamp.setIp(req.getRemoteAddr());   //设置IP
	    		  String prefix=this.ipTimeStamp.getIPTimeStampRand() ;  //前缀
	    		  photo=prefix+"."+stuff ;   //照片名
	    		  InputStream input = photoFile.getInputStream();  //得到输入流
	    		  fileName=path+File.separator+photo  ;     //文件全名
	    		  file=new File(fileName)  ;   //文件
	    		  OutputStream out=new FileOutputStream(file) ;   //文件输入流
	    		  byte[] buffer=new byte[1024]  ;  //缓冲区
	    		  int length= 0 ;
	    		  while((length=input.read(buffer))>0){  //边读边写
	    			  out.write(buffer, 0, length) ;
	    		  }
	    		  out.close();  
	    		  input.close();  
 	    	}
	    	//3.添加学生信息
	    	Student student=new Student()  ;   //实例化学生
	    	student.setStudentId(studentId);   //设置学号
	    	student.setStudentName(studentName);  //设置学生名字
	    	student.setClazzId(clazzId) ;     //设置班级代码
	    	student.setSex(sex);        //设置学生性别
	    	student.setAge(null)  ;     //学生年龄设不设置无所谓,因为全靠Oracle函数和birth字段计算得出
	    	student.setAddress(address);   //设置地址
	    	student.setBirth(birth);     //设置出生日期
	    	student.setPassword(password);   //设置密码
	    	student.setTel(tel);     //设置联系电话
	    	student.setPhoto(photo);  //设置照片
	    	student.setNote(note);    //设置简介
	    	this.studentService.doUpdate(student)  ;   //更新学生信息 
	    }catch(Exception e){
	    	  page="err"  ;     //出现异常直接跳转到错误页
	    }
	    return page ;
  }
  
  
  //URL解析:http://localhost/ZZUPrj/Student/doRemove/2014888
  @RequestMapping("/doRemove/{studentId}")
  public String doRemove(@PathVariable("studentId")String studentId,HttpServletRequest req){
	     String page="redirect:/Student/list" ;
	     try{
	    	  Student student = this.studentService.findById(studentId)  ;  //取得学生信息
	    	  String photo=student.getPhoto()  ;      //照片
	    	  if(!("nophoto.jpg".equals(photo))){    //不是默认照片
	    		  String path=req.getSession().getServletContext().getRealPath("photo") ;  //路径
	    		  String fileName=path+File.separator+photo  ;     //照片名字
	    		  File file=new File(fileName)  ;  //创建文件
	    		  file.delete()  ;  //删除照片
	    	  }
	    	  Integer count=0;
	    	  count=this.studentService.doRemove(studentId)  ;  //删除学生信息
	    	  if(count==0){
	    		   throw new Exception()  ;    //出现异常,直接跳转到错误页
	    	  }
	     }catch(Exception e){    //出现异常,直接跳转到错误页
	    	  page="err"  ;
	     }
	     return page ;
  }
  
   ///URL解析:${pageContext.request.contextPath}/Student/loginCheck
  @RequestMapping("/loginCheck")
  public String loginCheck(Student student,HttpServletRequest req){
	     String page="student_op";
	     HttpSession session = req.getSession() ;  //取得Session
	     try{
	    	   Integer count=0 ;
	    	   count=this.studentService.findLogin(student) ;  //登陆验证
	    	   if(count==0){   //登陆验证失败
	    		   session.setAttribute("errMsg", "密码错误,请重新登陆!");   //设置错误信息
	    		   page="redirect:/index.jsp"  ;   //跳转到学生入口
	    	   }else{
	    		    session.setAttribute("errMsg", "");   //设置错误信息
	    		    student=this.studentService.findById(student.getStudentId())  ; //查找学生信息
	    		    session.setAttribute("student", student) ;   //保存学生信息
	    	   }
	     }catch(Exception e){
	    	   page="err"  ;     //出现异常,直接跳转到错误页
	     }
	     return page ;
  }
  
  
  //URL解析:http://localhost/ZZUPrj/Student/doUpdatePassword/2014888
  @RequestMapping("/doUpdatePasswordPre/{studentId}")
  public ModelAndView doUpdatePasswordPre(@PathVariable("studentId")String studentId){
	     ModelAndView mv=new ModelAndView("student_op_update")  ;
	     try{
	    	   Student student = this.studentService.findById(studentId)   ; //根据ID查询学生信息
	    	   if(student==null){
	    		   throw new Exception()  ;  //学生信息已经不存在了,直接抛出异常,跳转到错误页
	    	   }else{
	    		   mv.addObject("student", student)  ;    //保存学生模型 
	    	   }
	     }catch(Exception e){
	    	   mv.setViewName("err")  ;    //程序出现异常,直接跳转到错误页
	     }
	     return mv ;
  }
  
  @RequestMapping("/findById")
  @ResponseBody
  public String findById(@RequestParam("studentId")String studentId){
	     String jsonStr=null;
	     try{
	    	  Student student=this.studentService.findById(studentId) ;  //根据ID查询
	    	  jsonStr=new ObjectMapper().writeValueAsString(student)  ;  //转化成JSON字符串
	     }catch(Exception e){
	    	  jsonStr="{\"err\":\"err\"}"   ;   //出现异常,返回错误提示
	     }
	     return  jsonStr;
  }
  
  //URL解析:/ZZUPrj/Student/doUpdatePassword
  @RequestMapping("/doUpdatePassword")
  public String doUpdatepassword(Student student){
	     String page="redirect:/index.jsp" ;    //密码修改后要重新登陆
	     try{
	    	 this.studentService.doUpdatePassword(student)  ;   //更新学生密码
	     }catch(Exception e){
	    	   page="err"  ;         //程序出现异常,直接跳转到错误页
	     }
	     return page ;
  }
  
  //URL解析:http://localhost/ZZUPrj/Student/findStudentInfo/2014888
  @RequestMapping("findStudentInfo/{studentId}")
  public ModelAndView findStudentInfo(@PathVariable("studentId")String studentId){
	     ModelAndView mv=new ModelAndView("student_op_info") ;   //学生信息页
	     try{
	    	  Student student=this.studentService.findById(studentId)  ;   //根据ID查询
	    	  if(student==null){   //学生信息已经被删除
	    		   throw new Exception() ;   //直接抛出异常,跳转到错误页
	    	  }else{
	    		  mv.addObject("student", student)  ;   //添加学生信息模型
	    	  }
	     }catch(Exception e){
	    	  mv.setViewName("err")  ;   //程序出现异常就跳转到错误页
	     }
	     return  mv  ;
  }
  
  
  //URL解析:${pageContext.request.contextPath}/Student/findScorePre/${sessionScope.student.studentId}
  @RequestMapping("/findScorePre/{studentId}")
  public ModelAndView findScorePre(@PathVariable("studentId")String studentId){
	     ModelAndView mv=new ModelAndView("student_op_score_query") ;   //成绩查询视图
	     try{
	    	   Map<Integer,String> termMap=new HashMap<Integer,String>() ;  //保存学期
	    	   termMap.put(1, "第1学期")  ;
	    	   termMap.put(2, "第2学期")  ;
	    	   termMap.put(3, "第3学期")  ;
	    	   termMap.put(4, "第4学期")  ;
	    	   termMap.put(5, "第5学期")   ;
	    	   termMap.put(6, "第6学期")   ;
	    	   termMap.put(7, "第7学期")   ;
	    	   termMap.put(8, "第8学期")   ;
	    	   mv.addObject("termMap", termMap)  ;   //添加学期列表模型
	    	   Map<String,Object> map=new HashMap<String, Object>() ;  //保存学号和学期
	    	   map.put("studentId", studentId)   ;
	    	   map.put("term", 1)   ;    //第一次查询第一学期的成绩
	    	   List<Score> scores=this.scoreService.findByStudentIdAndTerm(map)  ;  //分数列表
	    	   mv.addObject("scores", scores)  ;   //添加分数列表模型
	    	   mv.addObject("studentId", studentId) ;   //添加学生学号模型 
	     }catch(Exception e){
	    	  mv.setViewName("err")   ;    //程序出现异常跳转到错误页
	     }
	     return mv ;
  }
  
  //URL解析:http://localhost/ZZUPrj/Student/getAnysDetails/2014888
  @RequestMapping("/getAnysDetails/{studentId}")
  public ModelAndView  getAnysDetails(@PathVariable("studentId")String studentId,
		                              HttpSession session){
	     ModelAndView mv=new ModelAndView("student_op_acca")   ;
	     try{
	    	  Integer allCount=this.accaService.findAllCount(studentId)  ;  //根据学号查询专业总人数
	    	  Integer rank=this.accaService.findRankByStudentId(studentId)  ; //查找总排名
	    	  List<CreditPerTermAcca> creditPerTermAccas=null ; //保存需要生成图表的数据
	    	  creditPerTermAccas=this.accaService.findCreditPerTermAccasByStudentId(studentId);
	    	  String fileName=this.generateChartUtils.genJFreeChart(creditPerTermAccas, session) ;//得到生成的图表文件名称
	    	  mv.addObject("allCount", allCount)  ;  //添加专业总人数
	    	  mv.addObject("rank", rank)   ;         //添加专业排名
	    	  mv.addObject("fileName", fileName) ;   //添加生成图表文件的文件名,前台就可以调用ServletAPI取得图表啦
	     }catch(Exception e){
	    	   mv.setViewName("err")  ;   //程序出现异常直接跳转到错误页 
	     }
	     return mv  ;
  }
  
 
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
	
}
