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
	private IScoreService scoreService   ;   //注入ScoreService服务层 
	
	@Autowired
	private ICourseService courseService  ;   //注入CourseService服务层
	
	
	@Autowired
	private ExcelUtils excelUtils  ;           //导入Excel工具类
	
	//URL解析:http://localhost/ZZUPrj/Score/list
	/**
	 * 分页+模糊查询
	 * @param currentPage 当前页
	 * @param lineSize    每页显示数据量
	 * @param keyWord     查询关键词
	 * @return  模型(分页信息[PageInfo]和成绩列表[List<Score>])
	 */
	@SuppressWarnings(value={"unchecked"})
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(name="currentPage",defaultValue="1")Integer currentPage,
			                 @RequestParam(name="lineSize",defaultValue="5")Integer lineSize   ,
			                 @RequestParam(name="keyWord",defaultValue="")String keyWord){
		  ModelAndView mv=new ModelAndView("score_list")  ;  //跳转到列表页 
		  try{
			   Map<String, Object> map = this.scoreService.findAll(currentPage, lineSize, keyWord) ;
			   List<Score> scores=(List<Score>) map.get("scores") ;   //分数列表
			   PageInfo<Score> pageInfo=(PageInfo<Score>) map.get("pageInfo");  //分页信息
			   mv.addObject("scores", scores)  ;  //添加分数列表模型
			   mv.addObject("pageInfo", pageInfo) ;   //添加分页信息模型
			   mv.addObject("lineSizes", new int[]{5,10,15,20,25,30})  ; //回显
			   mv.addObject("keyWord", keyWord)  ;   //回显
		  }catch(Exception e){
			   mv.setViewName("err")  ;    //出现异常就直接跳转到错误页
		  }
		  return mv  ;
	}
	
	
	//URL解析:http://localhost/ZZUPrj/Score/doCreatePre
	@RequestMapping("/doCreatePre")
	public ModelAndView doCreatePre(){
		   ModelAndView mv=new ModelAndView("score_insert") ;
		   try{
			   Map<Integer,String> termMap=new HashMap<Integer,String>() ;
		 	   termMap.put(1, "第1学期")  ;
		 	   termMap.put(2, "第2学期")  ;
		 	   termMap.put(3, "第3学期")  ;
		 	   termMap.put(4, "第4学期")  ;
		 	   termMap.put(5, "第5学期")   ;
		 	   termMap.put(6, "第6学期")   ;
		 	   termMap.put(7, "第7学期")   ;
		 	   termMap.put(8, "第8学期")   ;
		 	   mv.addObject("termMap", termMap)  ;   //加装数据模型
	 	   }catch(Exception e){
	 		    mv.setViewName("err") ;   //出现异常直接跳转到错误页 
	 	   }
		   return mv  ;  
	}
	
	@ResponseBody
	@RequestMapping("/findCoursesByTerm")
	public String findCoursesByTerm(@RequestParam("term")Integer term){
		   String jsonStr=null ;
		   try{
			   List<Course> courses = this.courseService.findByTerm(term)  ;  //根据课程ID查找
			   jsonStr=new ObjectMapper().writeValueAsString(courses)  ;      //返回课程列表的JSON
		   }catch(Exception e){
			   jsonStr="{\"err\":\"err\"}"  ;  //系统异常 
		   }
		   return jsonStr ;
	}
	
	//URL解析:/ZZUPrj/Score/doCreate
	@RequestMapping("/doCreate")
	public String doCreate(Score score){
		   String page="redirect:/Score/list"   ;  
		   try{
			   this.scoreService.doCreate(score)  ;   //增加分数信息
		   }catch(Exception e){
			     page="err"  ;      //出现异常,直接跳转到错误页 
		   }
		   return page ;
	}
	
	//URL解析:http://localhost/ZZUPrj/Score/doEdit/5
	@RequestMapping("/doEdit/{scoreId}")
	public ModelAndView doEdit(@PathVariable("scoreId")Integer scoreId){
		  ModelAndView mv=new ModelAndView("score_update")   ;  //转跳到修改视图
		  try{
			   Score score=this.scoreService.findById(scoreId)  ;   //根据成绩编号找到成绩
			   if(score==null){   //成绩已经不存在
				   throw new Exception()  ;     //直接抛出异常,跳转到错误页 
			   }else{
				    mv.addObject("score", score)  ;    //添加分数模型 
			   }
		  }catch(Exception e){
			   mv.setViewName("err")  ;   //程序出现异常,直接跳转到错误页
		  }
		  return mv ;
	}
	
	//URL解析:/ZZUPrj/Score/doUpdate
	@RequestMapping("/doUpdate")
	public String doUpdate(Score score){
		   String page="redirect:/Score/list"   ;    //默认跳转到成绩列表
		   try{
			     Integer count=this.scoreService.doUpdate(score) ;   //修改成绩
			     if(count==0){   //可能成绩已经删除了 
			    	  throw new Exception() ;  //直接抛出异常
			     }
		   }catch(Exception e){     //系统异常就跳转到错误页
			     //e.printStackTrace();
			     page="err"  ;
		   }
		   return page ;
	}
	
	//URL解析:http://localhost/ZZUPrj/Score/doRemove/3
	@RequestMapping("/doRemove/{scoreId}")
	public String doRemove(@PathVariable("scoreId")Integer scoreId){
		    String page="redirect:/Score/list"  ;
		    try{
		    	 Integer count=this.scoreService.doRemove(scoreId) ;  //根据ID删除
		    	 if(count==0){   //删除前已经被删除了
		    		  throw new Exception()  ;   //直接跳转到错误页
		    	 }
		    }catch(Exception e){   //出现异常直接跳转到错误页 
		    	 page="err"  ;
		    }
		    return page  ;
	}
	
	//URL解析:http://localhost/ZZUPrj/Score/downloadTemplate
	 @RequestMapping("/downloadTemplate")
	  public ResponseEntity<byte[]> downloadTemplate(HttpServletRequest HttpReq){
	  	byte[] body=null;
	      String path=HttpReq.getServletContext().getRealPath("/upload/scoreTemplate.xls")  ;   //取得XLS文件位置
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
	      headers.add("Content-Disposition", "attachment;filename=scoreTemplate.xls");
	      HttpStatus statusCode=HttpStatus.OK;
	      ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
	      return response;
	  }
	  
	  //URL解析:http://localhost/ZZUPrj/Score/doCreateBatchPre
	  @RequestMapping("/doCreateBatchPre")
	  public String doCreateBatchPre(){
		     return "score_batch_insert"  ;   //跳转到批量页面
	  }
	  
	  //URL解析:/ZZUPrj/Score/doBatchCreate
	  @RequestMapping("/doBatchCreate")
	  public String doBatchCreate(@RequestParam("dataFile") MultipartFile dataFile){
		     String page="redirect:/Score/list"  ;    //程序正常执行就跳转到学生列表页
		     try{
		    	 InputStream input = dataFile.getInputStream();  //输入流
             	 List<Score> scores=this.excelUtils
             			                .readAsScoreList(input)  ;  //将Excel解析成成绩列表
             	 this.scoreService.doCreateBatch(scores)  ;   //批量增加
		     }catch(Exception e){
		    	   page="err"  ;     //程序出现异常,直接跳转到错误页
		     }
		     return page ;
	  }
	  
   /*Ajax的两组请求参数:
    *  1.var url="/ZZUPrj/Score/findScoreByTermAndStudentId"  ;             //请求URL
	*  2.var args={"time":new Date(),"term":term,"studentId":studentId} ;   //请求参数
    */
	@ResponseBody
	@RequestMapping("/findScoreByTermAndStudentId")
	public String findScoreByTermAndStudentId(@RequestParam("term")Integer term,
			                                  @RequestParam("studentId")String studentId)
	                                                                                     {
		   String jsonStr=null ;
		   try{
			   Map<String, Object> map=new HashMap<String,Object>() ;   //保存SQL参数
			   map.put("term", term)  ;   //学期
			   map.put("studentId", studentId) ;  //学号
			   List<Score> scores=this.scoreService.findByStudentIdAndTerm(map) ;
			   jsonStr=new ObjectMapper().writeValueAsString(scores) ;  //转化成JSON字符串
		   }catch(Exception e){
			   jsonStr="{\"err\":\"err\"}" ;   //返回错误消息
		   }
		   return jsonStr   ;   //返回JSON格式的数据
		
	}
	
	
	
	
	
	
	
}
