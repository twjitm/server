package com.test;

public enum TestType {
TYPE1(0,"0"),
TYPE2(1,"1");
private Integer value;
private String startment;
private TestType(Integer value,String startment){
	this.startment=startment;
	this.value=value;
}
public static TestType part(Integer value){
	for(TestType t:values()){
		if(t.value==value){
			return t;
		}else{
			return null;
		}
	}
	return null;
}

}
