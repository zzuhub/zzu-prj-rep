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
 * ͼ�����ɹ���,��Ҫ�ǽ�ͳ�����ݴ�����,����ͼ��
 * @author ZZU��WangChengjian
 *
 */
@Component
public class GenerateChartUtils implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ����ÿѧ�ڵ�ѧ��ͳ���б�����ͼ��
	 * @param creditPerTermAccas ÿѧ��ѧ��ͳ��:ѧ��,��ѧ��,���ѧ��
	 * @param HttpSession ServletAPI,��Ҫ���ڱ���ͼ��
	 * @return ����ͼ����ļ�
	 * @throws Exception
	 */
	public String genJFreeChart(List<CreditPerTermAcca> creditPerTermAccas,
			                                           HttpSession session )throws  Exception{
		    String fileName=null ;
		    int len=creditPerTermAccas.size()  ;  //��������ʱ��Ҫ�õ�
		    double[][] data=new double [len][2] ;     ;  //���ݲɼ�
		    String[] rowKeys=new String[len] ;
		    for(int x=0;x<len;x++){
		    	CreditPerTermAcca creditPerTermAcca = creditPerTermAccas.get(x)  ; //��ѧ��
		    	data[x][0]=creditPerTermAcca.getSumPassCredit() ;  //��λ����ĵ�x��һά����ĵ�һ��Ԫ���Ǵ��ѧ��
		    	data[x][1]=creditPerTermAcca.getSumCredit() ; //��ά����ĵ�x��һά����ĵڶ���Ԫ������ѧ��
		    	rowKeys[x]="��"+(creditPerTermAcca.getTerm())+"ѧ��" ; //�ɼ�ѧ��
		    }
		    String[] columnKeys=new String[]{"����ѧ��","��ѧ��"} ;  //ÿһ����ά�����һά����
		    //1.���ݲɼ�
		    CategoryDataset dataset=DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
		    this.dealWithEnc();    //����������� 
		    //2.����JFreeChart
		    JFreeChart chart=ChartFactory.createBarChart("ѧ����ϰͳ��ͼ", "ѧ��", "ѧ��", 
		    		                                     dataset, PlotOrientation.VERTICAL, 
		    	                                          true  ,  true  ,  true  )    ;
		    this.exagChart(chart);   //��Ⱦͼ�� 
		    //3.����ͼ���ļ�
		    fileName=ServletUtilities.saveChartAsPNG(chart, 1000, 500,null, session)  ;//�õ�ͼ���ļ���
		    return fileName  ;
	}
	
	public  void dealWithEnc(){   //�������ķ���
        // ����������ʽ
       StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
       // ���ñ�������
       standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));
       // ����ͼ��������
       standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15));
       // �������������
       standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15));
       //���ñ�񹤳���������ʽ
       ChartFactory.setChartTheme(standardChartTheme);
	}
	
	
	public  void exagChart(JFreeChart chart){    //��״ͳ��ͼ��Ⱦ
        CategoryPlot plot=chart.getCategoryPlot() ;
        // �������񱳾���ɫ
		plot.setBackgroundPaint(Color.white);
		// ��������������ɫ
		plot.setDomainGridlinePaint(Color.pink);
		// �������������ɫ
		plot.setRangeGridlinePaint(Color.pink);
		
		// ��ʾÿ��������ֵ�����޸ĸ���ֵ����������
		BarRenderer3D renderer=new BarRenderer3D();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		renderer.setItemLabelAnchorOffset(10D);  
		
		// ����ƽ������֮�����
		renderer.setItemMargin(0.4);
		
		plot.setRenderer(renderer);
      }
	
	
	
}
