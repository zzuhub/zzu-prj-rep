package cn.zzu.wcj.handler;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;

import cn.zzu.wcj.entity.Course;
import cn.zzu.wcj.entity.Score;
import cn.zzu.wcj.service.ICourseService;
import cn.zzu.wcj.service.IScoreService;
import cn.zzu.wcj.util.ExcelUtils;

@Controller
@RequestMapping("/Score")
public class ScoreHandler {

	@Autowired
	private IScoreService scoreService   ;   //ע��ScoreService����� 
	
	@Autowired
	private ICourseService courseService  ;   //ע��CourseService�����
	
	
	@Autowired
	private ExcelUtils excelUtils  ;           //����Excel������
	
	//URL����:http://localhost/ZZUPrj/Score/list
	/**
	 * ��ҳ+ģ����ѯ
	 * @param currentPage ��ǰҳ
	 * @param lineSize    ÿҳ��ʾ������
	 * @param keyWord     ��ѯ�ؼ���
	 * @return  ģ��(��ҳ��Ϣ[PageInfo]�ͳɼ��б�[List<Score>])
	 */
	@SuppressWarnings(value={"unchecked"})
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(name="currentPage",defaultValue="1")Integer currentPage,
			                 @RequestParam(name="lineSize",defaultValue="5")Integer lineSize   ,
			                 @RequestParam(name="keyWord",defaultValue="")String keyWord){
		  ModelAndView mv=new ModelAndView("score_list")  ;  //��ת���б�ҳ 
		  try{
			   Map<String, Object> map = this.scoreService.findAll(currentPage, lineSize, keyWord) ;
			   List<Score> scores=(List<Score>) map.get("scores") ;   //�����б�
			   PageInfo<Score> pageInfo=(PageInfo<Score>) map.get("pageInfo");  //��ҳ��Ϣ
			   mv.addObject("scores", scores)  ;  //��ӷ����б�ģ��
			   mv.addObject("pageInfo", pageInfo) ;   //��ӷ�ҳ��Ϣģ��
			   mv.addObject("lineSizes", new int[]{5,10,15,20,25,30})  ; //����
			   mv.addObject("keyWord", keyWord)  ;   //����
		  }catch(Exception e){
			   mv.setViewName("err")  ;    //�����쳣��ֱ����ת������ҳ
		  }
		  return mv  ;
	}
	
	
	//URL����:http://localhost/ZZUPrj/Score/doCreatePre
	@RequestMapping("/doCreatePre")
	public ModelAndView doCreatePre(){
		   ModelAndView mv=new ModelAndView("score_insert") ;
		   try{
			   Map<Integer,String> termMap=new HashMap<Integer,String>() ;
		 	   termMap.put(1, "��1ѧ��")  ;
		 	   termMap.put(2, "��2ѧ��")  ;
		 	   termMap.put(3, "��3ѧ��")  ;
		 	   termMap.put(4, "��4ѧ��")  ;
		 	   termMap.put(5, "��5ѧ��")   ;
		 	   termMap.put(6, "��6ѧ��")   ;
		 	   termMap.put(7, "��7ѧ��")   ;
		 	   termMap.put(8, "��8ѧ��")   ;
		 	   mv.addObject("termMap", termMap)  ;   //��װ����ģ��
	 	   }catch(Exception e){
	 		    mv.setViewName("err") ;   //�����쳣ֱ����ת������ҳ 
	 	   }
		   return mv  ;  
	}
	
	@ResponseBody
	@RequestMapping("/findCoursesByTerm")
	public String findCoursesByTerm(@RequestParam("term")Integer term){
		   String jsonStr=null ;
		   try{
			   List<Course> courses = this.courseService.findByTerm(term)  ;  //���ݿγ�ID����
			   jsonStr=new ObjectMapper().writeValueAsString(courses)  ;      //���ؿγ��б��JSON
		   }catch(Exception e){
			   jsonStr="{\"err\":\"err\"}"  ;  //ϵͳ�쳣 
		   }
		   return jsonStr ;
	}
	
	//URL����:/ZZUPrj/Score/doCreate
	@RequestMapping("/doCreate")
	public String doCreate(Score score){
		   String page="redirect:/Score/list"   ;  
		   try{
			   this.scoreService.doCreate(score)  ;   //���ӷ�����Ϣ
		   }catch(Exception e){
			     page="err"  ;      //�����쳣,ֱ����ת������ҳ 
		   }
		   return page ;
	}
	
	//URL����:http://localhost/ZZUPrj/Score/doEdit/5
	@RequestMapping("/doEdit/{scoreId}")
	public ModelAndView doEdit(@PathVariable("scoreId")Integer scoreId){
		  ModelAndView mv=new ModelAndView("score_update")   ;  //ת�����޸���ͼ
		  try{
			   Score score=this.scoreService.findById(scoreId)  ;   //���ݳɼ�����ҵ��ɼ�
			   if(score==null){   //�ɼ��Ѿ�������
				   throw new Exception()  ;     //ֱ���׳��쳣,��ת������ҳ 
			   }else{
				    mv.addObject("score", score)  ;    //��ӷ���ģ�� 
			   }
		  }catch(Exception e){
			   mv.setViewName("err")  ;   //��������쳣,ֱ����ת������ҳ
		  }
		  return mv ;
	}
	
	//URL����:/ZZUPrj/Score/doUpdate
	@RequestMapping("/doUpdate")
	public String doUpdate(Score score){
		   String page="redirect:/Score/list"   ;    //Ĭ����ת���ɼ��б�
		   try{
			     Integer count=this.scoreService.doUpdate(score) ;   //�޸ĳɼ�
			     if(count==0){   //���ܳɼ��Ѿ�ɾ���� 
			    	  throw new Exception() ;  //ֱ���׳��쳣
			     }
		   }catch(Exception e){     //ϵͳ�쳣����ת������ҳ
			     //e.printStackTrace();
			     page="err"  ;
		   }
		   return page ;
	}
	
	//URL����:http://localhost/ZZUPrj/Score/doRemove/3
	@RequestMapping("/doRemove/{scoreId}")
	public String doRemove(@PathVariable("scoreId")Integer scoreId){
		    String page="redirect:/Score/list"  ;
		    try{
		    	 Integer count=this.scoreService.doRemove(scoreId) ;  //����IDɾ��
		    	 if(count==0){   //ɾ��ǰ�Ѿ���ɾ����
		    		  throw new Exception()  ;   //ֱ����ת������ҳ
		    	 }
		    }catch(Exception e){   //�����쳣ֱ����ת������ҳ 
		    	 page="err"  ;
		    }
		    return page  ;
	}
	
	//URL����:http://localhost/ZZUPrj/Score/downloadTemplate
	 @RequestMapping("/downloadTemplate")
	  public ResponseEntity<byte[]> downloadTemplate(HttpServletRequest HttpReq){
	  	byte[] body=null;
	      String path=HttpReq.getServletContext().getRealPath("/upload/scoreTemplate.xls")  ;   //ȡ��XLS�ļ�λ��
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
	      headers.add("Content-Disposition", "attachment;filename=scoreTemplate.xls");
	      HttpStatus statusCode=HttpStatus.OK;
	      ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
	      return response;
	  }
	  
	  //URL����:http://localhost/ZZUPrj/Score/doCreateBatchPre
	  @RequestMapping("/doCreateBatchPre")
	  public String doCreateBatchPre(){
		     return "score_batch_insert"  ;   //��ת������ҳ��
	  }
	  
	  //URL����:/ZZUPrj/Score/doBatchCreate
	  @RequestMapping("/doBatchCreate")
	  public String doBatchCreate(@RequestParam("dataFile") MultipartFile dataFile){
		     String page="redirect:/Score/list"  ;    //��������ִ�о���ת��ѧ���б�ҳ
		     try{
		    	 InputStream input = dataFile.getInputStream();  //������
             	 List<Score> scores=this.excelUtils
             			                .readAsScoreList(input)  ;  //��Excel�����ɳɼ��б�
             	 this.scoreService.doCreateBatch(scores)  ;   //��������
		     }catch(Exception e){
		    	   page="err"  ;     //��������쳣,ֱ����ת������ҳ
		     }
		     return page ;
	  }
	  
   /*Ajax�������������:
    *  1.var url="/ZZUPrj/Score/findScoreByTermAndStudentId"  ;             //����URL
	*  2.var args={"time":new Date(),"term":term,"studentId":studentId} ;   //�������
    */
	@ResponseBody
	@RequestMapping("/findScoreByTermAndStudentId")
	public String findScoreByTermAndStudentId(@RequestParam("term")Integer term,
			                                  @RequestParam("studentId")String studentId)
	                                                                                     {
		   String jsonStr=null ;
		   try{
			   Map<String, Object> map=new HashMap<String,Object>() ;   //����SQL����
			   map.put("term", term)  ;   //ѧ��
			   map.put("studentId", studentId) ;  //ѧ��
			   List<Score> scores=this.scoreService.findByStudentIdAndTerm(map) ;
			   jsonStr=new ObjectMapper().writeValueAsString(scores) ;  //ת����JSON�ַ���
		   }catch(Exception e){
			   jsonStr="{\"err\":\"err\"}" ;   //���ش�����Ϣ
		   }
		   return jsonStr   ;   //����JSON��ʽ������
		
	}
	
	
	
	
	
	
	
}
