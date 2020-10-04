package com.buddi.colombiaapp.samples;

public class DebuggingExample {
	public static void main(String args[]) {
		System.out.println("This is single dimensional array...");
		String singleDimensionalArray[] = new String[]{"a", "b", "c", "d", "e", "f"};
		System.out.println("Size of the array is:"+singleDimensionalArray.length);
		int sizeOfArray = singleDimensionalArray.length;
		for(int i = 0; i < sizeOfArray; i++){
			System.out.println("Value present "+"at position :"+i+" in the array is: "+singleDimensionalArray[i]);
		}
		System.out.println("End of the program...");
	}
}