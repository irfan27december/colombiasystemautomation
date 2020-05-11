package com.buddi.colombiaapp.testngexample;

import org.testng.annotations.Test;

public class testngexample2 {
	
	@Test(priority=0)
	public void testA(){
		System.out.println("testA");
	}
	
	@Test(priority=1)
	public void testB(){
		System.out.println("testB");
	}

}
