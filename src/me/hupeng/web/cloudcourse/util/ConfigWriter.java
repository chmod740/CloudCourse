package me.hupeng.web.cloudcourse.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigWriter {
	//属�?文件的路�?  
    static String profilepath="config.properties";   
    /**  
    * 采用静�?方法  
    */   
    private Properties props = new Properties();   
    public ConfigWriter() {
		// TODO Auto-generated constructor stub
    	 try {   
             props.load(this.getClass().getResourceAsStream("/config.properties"));   
         } catch (FileNotFoundException e) {   
             e.printStackTrace();   
             System.exit(-1);   
         } catch (IOException e) {          
             System.exit(-1);   
         }   
	}
    
	/**  
	    * 更新（或插入）一对properties信息(主键及其键�?)  
	    * 如果该主键已经存在，更新该主键的值；  
	    * 如果该主键不存在，则插件�?��键�?�? 
	    * @param keyname 键名  
	    * @param keyvalue 键�?  
	    */   
	    public void writeProperties(String keyname,String keyvalue) {          
	        try {   
	            // 调用 Hashtable 的方�?put，使�?getProperty 方法提供并行性�?   
	            // 强制要求为属性的键和值使用字符串。返回�?�?Hashtable 调用 put 的结果�?   
	            OutputStream fos = new FileOutputStream(profilepath);   
	            props.setProperty(keyname, keyvalue);   
	            // 以�?合使�?load 方法加载�?Properties 表中的格式，   
	            // 将此 Properties 表中的属性列表（键和元素对）写入输出�?  
	            props.store(fos, "Update '" + keyname + "' value");   
	        } catch (IOException e) {   
	            System.err.println("属�?文件更新错误");   
	        }   
	    }   
}
