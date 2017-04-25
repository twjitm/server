package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;
/**
 * Properties 测试学习
 * @author Administrator
 *
 */
public class PropertiesTest {
	
public static void main(String[] args) {
	try {
		PropertiesTest pt=new PropertiesTest();
		System.out.println();
		
		FileInputStream fileInputStream=new FileInputStream(System.getProperty("user.dir")+"/file/test.properties");
		
		Properties p=new Properties();
		p.load(fileInputStream);
		for(Object obj:p.keySet()){
			System.out.println("------");
			System.out.println(p.get(obj));
		}
		/**
		 * 将properties内容保存到xml中
		 */
		System.out.println("******************");
		 OutputStream out=new FileOutputStream(System.getProperty("user.dir")+"/file/test.xml");
         p.storeToXML(out, "这个是我将properties文件中的内容加载到xml文件中的 ");
         fileInputStream.close();
         out.close();
	     p.put("school", "河北大学");
         fileInputStream=null;
         fileInputStream=new FileInputStream(System.getProperty("user.dir")+"/file/test.xml");
         p.loadFromXML(fileInputStream);
         for(Object obj:p.keySet()){
        	 System.out.println(p.get(obj));
         }
	} catch (Exception e) {
		e.printStackTrace();
	}

 
}
}
