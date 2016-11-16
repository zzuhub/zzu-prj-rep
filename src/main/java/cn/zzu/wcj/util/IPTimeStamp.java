package cn.zzu.wcj.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class IPTimeStamp {
        private SimpleDateFormat sdf=null  ;     //���ڸ�ʽ��
        private String  ip=null   ;                    //IP��ַ
        
    
		
		public IPTimeStamp(){}  
        public IPTimeStamp(String ip){     //����IP��ַ
        	    this.ip=ip  ;          
        }
        
        public String getIp() {
    			return ip;
    	}
        
    	public void setIp(String ip) {
    			this.ip = ip;
    	}
        
        
        public String getIPTimeStampRand(){      //�ϴ��ļ�����������
        	      StringBuffer buf=new StringBuffer()  ;
        	      if(this.ip!=null){
        	    	         String []s=this.ip.split("\\.");   //���IP��ַ
        	    	         for(int x=0;x<s.length;x++){    //������� 
        	    	        	    buf.append(this.addZero(s[x], 3)) ;  
        	    	         }
        	    	         this.sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS") ;  //ʱ�����ʽ
        	    	         buf.append(this.sdf.format(new Date()))   ;     //�õ���ʽ����ʱ��� 
        	    	         Random rand=new Random()  ;     //����������� 
        	    	         for(int x=0;x<3;x++){
        	    	        	       buf.append(rand.nextInt(10))  ;    //�������ɵ������ 
        	    	         }
        	      }
        	      return buf.toString()   ;
        }
        private String addZero(String str,int len){   //�ַ���,λ��
        	    StringBuffer buf=new StringBuffer(str)   ;
        	    while(buf.length()<len){
        	    	   buf.insert(0, 0);   //������� 
        	    }
        	    return buf.toString()  ;
        }
    /*   ���Գɹ�
        public static void main(String []args){
        	    System.out.println(new IPTimeStamp("127.0.0.1").getIPTimeStampRand());
        	    System.out.println(new IPTimeStamp("192.168.1.1").getIPTimeStampRand());
        } 
     */
}
