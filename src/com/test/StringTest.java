package com.test;

public class StringTest {
public static void main(String[] args) {
	long t1 = System.currentTimeMillis();
	String a="a";
	String b="b";
	String c=a+b;
	long t2 = System.currentTimeMillis();
	System.err.println(t2-t1);
	StringBuffer s=new StringBuffer();
	s.append("a");
	s.append("b");
	s.toString();
	long t3= System.currentTimeMillis();
	System.err.println(t3-t2);
	StringBuilder st=new StringBuilder();
	st.append("a");
	st.append("b");
	st.toString();
	long t4= System.currentTimeMillis();
	System.err.println(t4-t3);
}
}
