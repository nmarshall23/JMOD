package com.jeffpeng.si.core.util.descriptors;

public class AlloyDescriptor {
	public String one;
	public String two;
	public String result;
	public int amount;
	
	public AlloyDescriptor(String one, String result,int amount){
		this(one,null,result,amount);
	}
	
	public AlloyDescriptor(String one, String two, String result, int amount){
		this.one = one;
		this.two = two;
		this.result = result;
		this.amount = amount;
		
	}
}
