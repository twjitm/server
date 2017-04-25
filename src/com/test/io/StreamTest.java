package com.test.io;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class StreamTest {
	
	
	public static void main(String[] args) {
	try {
		DataInputStream inputStream=	new  DataInputStream(new FileInputStream(new File("d:/test.txt")));
		byte[] b=new byte[inputStream.available()];
		int len=inputStream.available();
		/*while ((inputStream.read())!=-1) {
			inputStream.read(b, 0, len);
			System.out.println(new String(b,"gbk"));
		}*/
		b=new byte[inputStream.available()];
		inputStream.readFully(b, 0, len);
		for(byte by:b){
			System.out.println((char)by);
		}
		
		inputStream.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}
