package com.test.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.test.Girls;
/**
 * 反射
 * @author twjitm
 *
 */
public class Testreflect {
	public static void main(String[] args) {
		try {
			Girls girls=new Girls();
			girls.setId(1);
			girls.setName("lishuoning");
			girls.setAge(22);
			Field f=girls.getClass().getField("nickname");
			System.out.println(f.getName());
			Field[] fs=girls.getClass().getDeclaredFields();
			for(Field field:fs){
				field.setAccessible(true);
				System.out.println(field.get(girls));;
			}
			f.set(girls, "twjitm");
			System.out.println(f.get(girls));
			
			/*Method method= Girls.class.getClass().getMethod("setId", Integer.class);/Test/src/com/test/Girls.java
	          method.invoke(1, Girls.class);*/
			@SuppressWarnings("unchecked")
			Class<Girls> class1 = (Class<Girls>) Class.forName("com.test.Girls");
			Method setid = class1.getMethod("setId",Integer.class);
			System.out.println(setid.getAnnotations());
			//Proxy p=Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), Girls.class, new InvocationHandler() );
		
		} catch (Exception  e) {
			e.printStackTrace();
		}
	 }
	}
