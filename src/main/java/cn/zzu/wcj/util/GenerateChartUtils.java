package cn.zzu.wcj.util;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.TextAnchor;
import org.springframework.stereotype.Component;

import cn.zzu.wcj.entity.CreditPerTermAcca;
/**
 * 图表生成工具,主要是将统计数据传进来,生成图表
 * @author ZZU·WangChengjian
 *
 */
@Component
public class GenerateChartUtils implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 根据每学期的学分统计列表生成图表
	 * @param creditPerTermAccas 每学期学分统计:学期,总学分,达标学分
	 * @param HttpSession ServletAPI,主要用于保存图表
	 * @return 生成图表的文件
	 * @throws Exception
	 */
	public String genJFreeChart(List<CreditPerTermAcca> creditPerTermAccas,
			                                           HttpSession session )throws  Exception{
		    String fileName=null ;
		    int len=creditPerTermAccas.size()  ;  //创建数组时需要用到
		    double[][] data=new double [len][2] ;     ;  //数据采集
		    String[] rowKeys=new String[len] ;
		    for(int x=0;x<len;x++){
		    	CreditPerTermAcca creditPerTermAcca = creditPerTermAccas.get(x)  ; //总学分
		    	data[x][0]=creditPerTermAcca.getSumPassCredit() ;  //二位数组的第x个一维数组的第一个元素是达标学分
		    	data[x][1]=creditPerTermAcca.getSumCredit() ; //二维数组的第x个一维数组的第二个元素是总学分
		    	rowKeys[x]="第"+(creditPerTermAcca.getTerm())+"学期" ; //采集学期
		    }
		    String[] columnKeys=new String[]{"已修学分","总学分"} ;  //每一个二维数组的一维数组
		    //1.数据采集
		    CategoryDataset dataset=DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
		    this.dealWithEnc();    //解决乱码问题 
		    //2.创建JFreeChart
		    JFreeChart chart=ChartFactory.createBarChart("学分修习统计图", "学期", "学分", 
		    		                                     dataset, PlotOrientation.VERTICAL, 
		    	                                          true  ,  true  ,  true  )    ;
		    this.exagChart(chart);   //渲染图表 
		    //3.生成图表文件
		    fileName=ServletUtilities.saveChartAsPNG(chart, 1000, 500,null, session)  ;//得到图表文件名
		    return fileName  ;
	}
	
	public  void dealWithEnc(){   //乱码解决的方法
        // 创建主题样式
       StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
       // 设置标题字体
       standardChartTheme.setExtraLargeFont(new Font("宋书", Font.BOLD, 20));
       // 设置图例的字体
       standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
       // 设置轴向的字体
       standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
       //设置表格工厂的主题样式
       ChartFactory.setChartTheme(standardChartTheme);
	}
	
	
	public  void exagChart(JFreeChart chart){    //柱状统计图渲染
        CategoryPlot plot=chart.getCategoryPlot() ;
        // 设置网格背景颜色
		plot.setBackgroundPaint(Color.white);
		// 设置网格竖线颜色
		plot.setDomainGridlinePaint(Color.pink);
		// 设置网格横线颜色
		plot.setRangeGridlinePaint(Color.pink);
		
		// 显示每个柱的数值，并修改该数值的字体属性
		BarRenderer3D renderer=new BarRenderer3D();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		renderer.setItemLabelAnchorOffset(10D);  
		
		// 设置平行柱的之间距离
		renderer.setItemMargin(0.4);
		
		plot.setRenderer(renderer);
      }
	
	
	
}
