package com.test;

public class EnumTest {

	public static void main(String[] args) {
		 TestType key = TestType.TYPE1;
		switch (key) {
		case TYPE1:
		System.out.println(key.part(0));
			System.out.println("1111");
			break;

		default:
			break;
		}
	}
	
}
