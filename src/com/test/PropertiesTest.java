package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;
/**
 * Properties ����ѧϰ
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
		 * ��properties���ݱ��浽xml��
		 */
		System.out.println("******************");
		 OutputStream out=new FileOutputStream(System.getProperty("user.dir")+"/file/test.xml");
         p.storeToXML(out, "������ҽ�properties�ļ��е����ݼ��ص�xml�ļ��е� ");
         fileInputStream.close();
         out.close();
	     p.put("school", "�ӱ���ѧ");
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
