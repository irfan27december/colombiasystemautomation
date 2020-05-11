package com.buddi.colombiaapp.testngexample;

import org.testng.annotations.Test;

public class testngexample1 {
	
	@Test(priority=0)
	public void test1(){
		System.out.println("test1");
	}
	
	@Test(priority=1)
	public void test2(){
		System.out.println("test2");
	}

}
