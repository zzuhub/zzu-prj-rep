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
  private IStudentService studentService   ;    //ѧ������� 
  
  /*
   *���������������Ҫ��Ϊ�˶��������˵�׼���� 
   */
  
  @Autowired
  private IMajorService majorService       ;    //רҵ�����
  
  @Autowired
  private IClazzService clazzService        ;    //�༶�����  
  
  @Autowired
  private IScoreService scoreService    ;         //�ɼ������
  
  @Autowired
  private IAccaService accaService     ;        //ע��ACCA����� 
  
  @Autowired
  private IPTimeStamp ipTimeStamp   ;            //�ϴ��ļ��Զ�������
  
  
  @Autowired
  private ExcelUtils excelUtils     ;         //Excel����
  
  @Autowired
  private GenerateChartUtils  generateChartUtils   ;   //ע��ͼ�����ɹ����� 
  
	
  @SuppressWarnings(value={"unchecked"})
  @RequestMapping("/list")
  public ModelAndView list(@RequestParam(name="currentPage",defaultValue="1")Integer currentPage,
		                   @RequestParam(name="lineSize",defaultValue="5")Integer lineSize,
		                   @RequestParam(name="keyWord",defaultValue="")String keyWord){
	     ModelAndView mv=new ModelAndView("student_list")  ;
	     try{
	    	 Map<String,Object> map=this.studentService.findAll(currentPage, lineSize, keyWord) ;
	    	 List<Student> students=(List<Student>) map.get("students");  //ѧ������
	    	 PageInfo<Student> pageInfo=(PageInfo<Student>) map.get("pageInfo") ;  //��ҳ��Ϣ
	    	 mv.addObject("students", students)  ;   //����ѧ������ģ��
	    	 mv.addObject("pageInfo", pageInfo)   ;  //�����ҳ��Ϣģ��
	    	 mv.addObject("keyWord", keyWord)   ;   //����KeyWord�ؼ��� 
  		     mv.addObject("lineSizes", new int[]{5,10,15,20,25,30})  ; //ÿҳ��ʾ������ 
	     }catch(Exception e){
	    	  mv.setViewName("err")  ;    //�����쳣,ֱ����ת������ҳ 
	     }
	     return mv ;
  }
  
  //URL����:http://localhost/ZZUPrj/Student/findDetailsById/201477E0332
  @RequestMapping("/findDetailsById/{studentId}")
  public ModelAndView findDetailsById(@PathVariable("studentId")String studentId){
	     ModelAndView mv=new ModelAndView("student_details")  ;
	     try{
	    	   Student student = this.studentService.findById(studentId) ;  //����ID��ѯѧ����Ϣ
	    	   if(student==null){   //ѧ����Ϣ������(�����Ѿ�ɾ��),ֱ���׳��쳣
	    		   throw new Exception()  ;
	    	   }else{   //ѧ����Ϣ����,�洢ѧ��ģ��
	    		   mv.addObject("student", student)  ;   //����ѧ��ģ�� 
	    	   }
	     }catch(Exception e){
	    	   mv.setViewName("err")  ;   //�����쳣ֱ����ת������ҳ 
	     }
	     return mv ;
  }
  
  //URL����:http://localhost/ZZUPrj/Student/doCreatePre
  @RequestMapping("/doCreatePre")
  public ModelAndView doCreatePre(){
	     ModelAndView mv=new ModelAndView("student_insert")  ;   //��ת������ҳ
	     try{
	    	   Map<String, Object> map = this.majorService.findAll(1, 0, "")  ;   //����ȫ��רҵ
	    	   mv.addObject("majors", map.get("majors"))  ;   //���רҵ����ģ�� 
	     }catch(Exception e){
	    	   mv.setViewName("err")   ;    //�����쳣,ֱ����ת������ҳ 
	     }
	     return mv ;
  }
  
  //URL����:/ZZUPrj/validateStudentIdExists
  @RequestMapping("/validateStudentIdExists")
  @ResponseBody
  public String validateStudentIdExists(@RequestParam("studentId")String studentId){
	    String jsonStr=null ;
	    try{
	    	 Student student=this.studentService.findById(studentId)  ;   //����ID��ѯѧ����Ϣ
	    	 jsonStr=new ObjectMapper().writeValueAsString(student)   ;   //���JSON�ַ���
	    }catch(Exception e){
			 jsonStr="{\"err\":\"err\"}" ;
	    }
	    return jsonStr  ;
  }
  
  //URL����:/ZZUPrj/Student/getClazzsByMajorId
  @RequestMapping("getClazzsByMajorId")
  @ResponseBody
  public String getClazzsByMajorId(@RequestParam("majorId")Integer majorId){
	     String json=null ;
	     try{
	    	  List<Clazz> clazzs=this.clazzService.findClazzsByMajorId(majorId) ; //ȡ�ð༶����
	    	  json=new ObjectMapper().writeValueAsString(clazzs) ;  //ƴ��JSON
	     }catch(Exception e){
	    	  json=null ;
	     }
	     return json ;
  }
  
  //URL����:/ZZUPrj/Student/doCreate
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
	    	 //û���ϴ�ͼƬ
	    	 String photo=null ;
	    	 if(photoFile.getOriginalFilename()==null||"".equals(photoFile.getOriginalFilename())){
	    		  photo="nophoto.jpg" ;
	    	 }else{
	    		  String stuff=photoFile.getOriginalFilename().split("\\.")[1]  ;   //��׺
	    		  this.ipTimeStamp.setIp(req.getRemoteAddr());   //����IP
	    		  String prefix=this.ipTimeStamp.getIPTimeStampRand() ;  //ǰ׺
	    		  photo=prefix+"."+stuff ;   //��Ƭ��
	    		  InputStream input = photoFile.getInputStream();  //�õ�������
	    		  String path=req.getSession().getServletContext().getRealPath("photo") ;  //·��
	    		  String fileName=path+File.separator+photo  ;     //�ļ�ȫ��
	    		  File file=new File(fileName)  ;   //�ļ�
	    		  OutputStream out=new FileOutputStream(file) ;   //�ļ�������
	    		  byte[] buffer=new byte[1024]  ;  //������
	    		  int length= 0 ;
	    		  while((length=input.read(buffer))>0){  //�߶���д
	    			  out.write(buffer, 0, length) ;
	    		  }
	    		  out.close();  
	    		  input.close();  
	    	 }
	    	 Date birth=new SimpleDateFormat("yyyy-MM-dd").parse(birthStr)  ; //��ʽ����������
	    	 Student student=new Student();   //ʵ����ѧ��
	    	 student.setStudentId(studentId); //����ѧ��
	    	 student.setStudentName(studentName);  //����ѧ������
	    	 student.setClazzId(clazzId);  //���ð༶����
	    	 student.setSex(sex);     //����ѧ���Ա�
	    	 student.setAge(null);   //����Oracle�����Զ�����,���ﴫ��ֵ����
	    	 student.setAddress(address);    //���õ�ַ
	    	 student.setBirth(birth);      //���ó�������
	    	 student.setPassword(password);  //��������
	    	 student.setTel(tel);   //������ϵ�绰
	    	 student.setPhoto(photo);  //������Ƭ
	    	 student.setNote(note);   //���ü��
	    	 this.studentService.doCreate(student)  ;
	     }catch(Exception e){
	    	   page="err"  ;    //�����쳣ֱ����ת������ҳ
	    	   System.out.println(e);
	     }
	     return page ;
  }
  
  //�ļ�����
  //URL����:http://localhost/ZZUPrj/Student/downloadTemplate
  @RequestMapping("/downloadTemplate")
  public ResponseEntity<byte[]> downloadTemplate(HttpServletRequest HttpReq){
  	byte[] body=null;
      String path=HttpReq.getServletContext().getRealPath("/upload/studentTemplate.xls")  ;   //ȡ��XLS�ļ�λ��
      try{
      	    InputStream in=new FileInputStream(path) ;   //����������
	        body=new byte[in.available()];               //�����ļ�
	        in.read(body);                               //��ȡ�ļ�
	        in.close();                     //��ȡ�����͹ر�,�ͷ���Դ 
      }catch(Exception e){
      	e.printStackTrace();   //��ʱ���Գ����ӡ�쳣
      }
      
      HttpHeaders headers=new HttpHeaders();
      //��Ӧͷ�����ֺ���Ӧͷ��ֵ
      headers.add("Content-Disposition", "attachment;filename=studentTemplate.xls");
      HttpStatus statusCode=HttpStatus.OK;
      ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
      return response;
  }
  
  
  
  //URL����:http://localhost/ZZUPrj/Student/doCreateBatchPre
  @RequestMapping("/doCreateBatchPre")
  public String doCreateBatchPre(){
	     return "student_batch_insert"   ;    //������������ҳ
  }
  
  //URL����:/ZZUPrj/Student/doBatchCreate
  @RequestMapping("/doBatchCreate")
  public String doBatchCreate(@RequestParam("dataFile") MultipartFile dataFile){
	     String page="redirect:/Student/list"  ;    //��������ִ�о���ת��ѧ���б�ҳ
	     try{
	    	 InputStream input = dataFile.getInputStream();  //������
	    	 List<Student> students=this.excelUtils.readAsStudentList(input) ;
	    	 Integer count=0  ;
	    	 count=this.studentService.doCreateBatch(students)  ;   //��������ѧ����Ϣ
	    	 if(count==0){
	    		 throw new Exception() ;   //�����쳣ֱ����ת������ҳ
	    	 }
	     }catch(Exception e){
	    	   e.printStackTrace();   //��ӡ�쳣 
	    	   page="err"  ;     //��������쳣,ֱ����ת������ҳ
	     }
	     return page ;
  }
  
  
  //URL����:/ZZUPrj/Student/doEdit/777
  @RequestMapping("/doEdit/{studentId}")
  public ModelAndView doEdit(@PathVariable("studentId")String studentId){
	     ModelAndView mv=new ModelAndView("student_update")  ;   //��ת���޸�ҳ
	     try{
	    	 Student student=this.studentService.findById(studentId) ;  //����ѧ��
	    	 if(student==null){   //ѧ����Ϣ�Ѿ�������
	    		 throw new Exception() ;   //ֱ���׳��쳣 
	    	 }else{
	    		 mv.addObject("student", student)  ;   //���ѧ��ģ�� 
	    		 mv.addObject("fmtBirth", new SimpleDateFormat("yyyy-MM-dd").format(student.getBirth())) ;  //��ʽ������
	    		 Map<String, Object> map = this.majorService.findAll(1, 0, "")  ;   //����ȫ��רҵ
		    	 mv.addObject("majors", map.get("majors"))  ;   //���רҵ����ģ�� 
	    	 }
	     }catch(Exception e){
	    	   mv.setViewName("err")  ;    //�����쳣ֱ����ת������ҳ
	     }
	     return mv  ;
  }
  
   //URL����:/ZZUPrj/Student/doUpdate
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
	    	               .parse(birthStr)   ;              //��ʽ��
	    	String photo=null ;   //�ϴ���Ƭ����
	    	if(photoFile.getOriginalFilename()==null||"".equals(photoFile.getOriginalFilename())){
	    		 photo=oldPhoto  ;    //û���ϴ�ͼƬ������ͼƬ
	    	}else{   //���ϴ�ͼƬ
	    		String path=req.getSession().getServletContext().getRealPath("photo") ;  //·��
	    		//1.��ɾ��ԭ��ͼƬ
	    		 String fileName=path+File.separator+oldPhoto  ;     //ԭ����Ƭ����
	    		 File file=null ;
	    		 if(!("nophoto.jpg".equals(oldPhoto))){   //����Ĭ��ͼƬ
		    		  file=new File(fileName) ;    //ԭ����Ƭ�ļ�
		    		 if(!file.delete()){   //ɾ��ʧ��
		    			 throw new Exception() ;   //ɾ��ʧ��,ֱ���׳��쳣
	              	 }   
	    		 }
	    		//2.�����ͼƬ
	    		  String stuff=photoFile.getOriginalFilename().split("\\.")[1]  ;   //��׺
	    		  this.ipTimeStamp.setIp(req.getRemoteAddr());   //����IP
	    		  String prefix=this.ipTimeStamp.getIPTimeStampRand() ;  //ǰ׺
	    		  photo=prefix+"."+stuff ;   //��Ƭ��
	    		  InputStream input = photoFile.getInputStream();  //�õ�������
	    		  fileName=path+File.separator+photo  ;     //�ļ�ȫ��
	    		  file=new File(fileName)  ;   //�ļ�
	    		  OutputStream out=new FileOutputStream(file) ;   //�ļ�������
	    		  byte[] buffer=new byte[1024]  ;  //������
	    		  int length= 0 ;
	    		  while((length=input.read(buffer))>0){  //�߶���д
	    			  out.write(buffer, 0, length) ;
	    		  }
	    		  out.close();  
	    		  input.close();  
 	    	}
	    	//3.���ѧ����Ϣ
	    	Student student=new Student()  ;   //ʵ����ѧ��
	    	student.setStudentId(studentId);   //����ѧ��
	    	student.setStudentName(studentName);  //����ѧ������
	    	student.setClazzId(clazzId) ;     //���ð༶����
	    	student.setSex(sex);        //����ѧ���Ա�
	    	student.setAge(null)  ;     //ѧ�������費��������ν,��Ϊȫ��Oracle������birth�ֶμ���ó�
	    	student.setAddress(address);   //���õ�ַ
	    	student.setBirth(birth);     //���ó�������
	    	student.setPassword(password);   //��������
	    	student.setTel(tel);     //������ϵ�绰
	    	student.setPhoto(photo);  //������Ƭ
	    	student.setNote(note);    //���ü��
	    	this.studentService.doUpdate(student)  ;   //����ѧ����Ϣ 
	    }catch(Exception e){
	    	  page="err"  ;     //�����쳣ֱ����ת������ҳ
	    }
	    return page ;
  }
  
  
  //URL����:http://localhost/ZZUPrj/Student/doRemove/2014888
  @RequestMapping("/doRemove/{studentId}")
  public String doRemove(@PathVariable("studentId")String studentId,HttpServletRequest req){
	     String page="redirect:/Student/list" ;
	     try{
	    	  Student student = this.studentService.findById(studentId)  ;  //ȡ��ѧ����Ϣ
	    	  String photo=student.getPhoto()  ;      //��Ƭ
	    	  if(!("nophoto.jpg".equals(photo))){    //����Ĭ����Ƭ
	    		  String path=req.getSession().getServletContext().getRealPath("photo") ;  //·��
	    		  String fileName=path+File.separator+photo  ;     //��Ƭ����
	    		  File file=new File(fileName)  ;  //�����ļ�
	    		  file.delete()  ;  //ɾ����Ƭ
	    	  }
	    	  Integer count=0;
	    	  count=this.studentService.doRemove(studentId)  ;  //ɾ��ѧ����Ϣ
	    	  if(count==0){
	    		   throw new Exception()  ;    //�����쳣,ֱ����ת������ҳ
	    	  }
	     }catch(Exception e){    //�����쳣,ֱ����ת������ҳ
	    	  page="err"  ;
	     }
	     return page ;
  }
  
   ///URL����:${pageContext.request.contextPath}/Student/loginCheck
  @RequestMapping("/loginCheck")
  public String loginCheck(Student student,HttpServletRequest req){
	     String page="student_op";
	     HttpSession session = req.getSession() ;  //ȡ��Session
	     try{
	    	   Integer count=0 ;
	    	   count=this.studentService.findLogin(student) ;  //��½��֤
	    	   if(count==0){   //��½��֤ʧ��
	    		   session.setAttribute("errMsg", "�������,�����µ�½!");   //���ô�����Ϣ
	    		   page="redirect:/index.jsp"  ;   //��ת��ѧ�����
	    	   }else{
	    		    session.setAttribute("errMsg", "");   //���ô�����Ϣ
	    		    student=this.studentService.findById(student.getStudentId())  ; //����ѧ����Ϣ
	    		    session.setAttribute("student", student) ;   //����ѧ����Ϣ
	    	   }
	     }catch(Exception e){
	    	   page="err"  ;     //�����쳣,ֱ����ת������ҳ
	     }
	     return page ;
  }
  
  
  //URL����:http://localhost/ZZUPrj/Student/doUpdatePassword/2014888
  @RequestMapping("/doUpdatePasswordPre/{studentId}")
  public ModelAndView doUpdatePasswordPre(@PathVariable("studentId")String studentId){
	     ModelAndView mv=new ModelAndView("student_op_update")  ;
	     try{
	    	   Student student = this.studentService.findById(studentId)   ; //����ID��ѯѧ����Ϣ
	    	   if(student==null){
	    		   throw new Exception()  ;  //ѧ����Ϣ�Ѿ���������,ֱ���׳��쳣,��ת������ҳ
	    	   }else{
	    		   mv.addObject("student", student)  ;    //����ѧ��ģ�� 
	    	   }
	     }catch(Exception e){
	    	   mv.setViewName("err")  ;    //��������쳣,ֱ����ת������ҳ
	     }
	     return mv ;
  }
  
  @RequestMapping("/findById")
  @ResponseBody
  public String findById(@RequestParam("studentId")String studentId){
	     String jsonStr=null;
	     try{
	    	  Student student=this.studentService.findById(studentId) ;  //����ID��ѯ
	    	  jsonStr=new ObjectMapper().writeValueAsString(student)  ;  //ת����JSON�ַ���
	     }catch(Exception e){
	    	  jsonStr="{\"err\":\"err\"}"   ;   //�����쳣,���ش�����ʾ
	     }
	     return  jsonStr;
  }
  
  //URL����:/ZZUPrj/Student/doUpdatePassword
  @RequestMapping("/doUpdatePassword")
  public String doUpdatepassword(Student student){
	     String page="redirect:/index.jsp" ;    //�����޸ĺ�Ҫ���µ�½
	     try{
	    	 this.studentService.doUpdatePassword(student)  ;   //����ѧ������
	     }catch(Exception e){
	    	   page="err"  ;         //��������쳣,ֱ����ת������ҳ
	     }
	     return page ;
  }
  
  //URL����:http://localhost/ZZUPrj/Student/findStudentInfo/2014888
  @RequestMapping("findStudentInfo/{studentId}")
  public ModelAndView findStudentInfo(@PathVariable("studentId")String studentId){
	     ModelAndView mv=new ModelAndView("student_op_info") ;   //ѧ����Ϣҳ
	     try{
	    	  Student student=this.studentService.findById(studentId)  ;   //����ID��ѯ
	    	  if(student==null){   //ѧ����Ϣ�Ѿ���ɾ��
	    		   throw new Exception() ;   //ֱ���׳��쳣,��ת������ҳ
	    	  }else{
	    		  mv.addObject("student", student)  ;   //���ѧ����Ϣģ��
	    	  }
	     }catch(Exception e){
	    	  mv.setViewName("err")  ;   //��������쳣����ת������ҳ
	     }
	     return  mv  ;
  }
  
  
  //URL����:${pageContext.request.contextPath}/Student/findScorePre/${sessionScope.student.studentId}
  @RequestMapping("/findScorePre/{studentId}")
  public ModelAndView findScorePre(@PathVariable("studentId")String studentId){
	     ModelAndView mv=new ModelAndView("student_op_score_query") ;   //�ɼ���ѯ��ͼ
	     try{
	    	   Map<Integer,String> termMap=new HashMap<Integer,String>() ;  //����ѧ��
	    	   termMap.put(1, "��1ѧ��")  ;
	    	   termMap.put(2, "��2ѧ��")  ;
	    	   termMap.put(3, "��3ѧ��")  ;
	    	   termMap.put(4, "��4ѧ��")  ;
	    	   termMap.put(5, "��5ѧ��")   ;
	    	   termMap.put(6, "��6ѧ��")   ;
	    	   termMap.put(7, "��7ѧ��")   ;
	    	   termMap.put(8, "��8ѧ��")   ;
	    	   mv.addObject("termMap", termMap)  ;   //���ѧ���б�ģ��
	    	   Map<String,Object> map=new HashMap<String, Object>() ;  //����ѧ�ź�ѧ��
	    	   map.put("studentId", studentId)   ;
	    	   map.put("term", 1)   ;    //��һ�β�ѯ��һѧ�ڵĳɼ�
	    	   List<Score> scores=this.scoreService.findByStudentIdAndTerm(map)  ;  //�����б�
	    	   mv.addObject("scores", scores)  ;   //��ӷ����б�ģ��
	    	   mv.addObject("studentId", studentId) ;   //���ѧ��ѧ��ģ�� 
	     }catch(Exception e){
	    	  mv.setViewName("err")   ;    //��������쳣��ת������ҳ
	     }
	     return mv ;
  }
  
  //URL����:http://localhost/ZZUPrj/Student/getAnysDetails/2014888
  @RequestMapping("/getAnysDetails/{studentId}")
  public ModelAndView  getAnysDetails(@PathVariable("studentId")String studentId,
		                              HttpSession session){
	     ModelAndView mv=new ModelAndView("student_op_acca")   ;
	     try{
	    	  Integer allCount=this.accaService.findAllCount(studentId)  ;  //����ѧ�Ų�ѯרҵ������
	    	  Integer rank=this.accaService.findRankByStudentId(studentId)  ; //����������
	    	  List<CreditPerTermAcca> creditPerTermAccas=null ; //������Ҫ����ͼ�������
	    	  creditPerTermAccas=this.accaService.findCreditPerTermAccasByStudentId(studentId);
	    	  String fileName=this.generateChartUtils.genJFreeChart(creditPerTermAccas, session) ;//�õ����ɵ�ͼ���ļ�����
	    	  mv.addObject("allCount", allCount)  ;  //���רҵ������
	    	  mv.addObject("rank", rank)   ;         //���רҵ����
	    	  mv.addObject("fileName", fileName) ;   //�������ͼ���ļ����ļ���,ǰ̨�Ϳ��Ե���ServletAPIȡ��ͼ����
	     }catch(Exception e){
	    	   mv.setViewName("err")  ;   //��������쳣ֱ����ת������ҳ 
	     }
	     return mv  ;
  }
  
 
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
	
}
