package com.atguigu.java;

public class TestTime {
	public static void main(String[] args) {
		long firstNum = 768;
		long secondNum = 1000000000;
		long record = 0;
		for(int i = 1;i <= firstNum;i++){
			record += secondNum;
		}
		System.out.println(record);
	}
}
