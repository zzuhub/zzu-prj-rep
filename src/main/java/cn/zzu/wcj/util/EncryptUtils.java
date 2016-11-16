package cn.zzu.wcj.util;

import org.apache.shiro.codec.Base64;

import org.springframework.stereotype.Component;


@Component
public class EncryptUtils {
   

	
	
    public  String encBase64(String str) {  
        return Base64.encodeToString(str.getBytes());  
    }  
    
    
    public  String decBase64(String str){  
        return Base64.decodeToString(str);  
    } 
      
    
	
}
