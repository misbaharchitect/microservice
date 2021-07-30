package com.edureka.skeleton.service;

public class Calculator {
	private final MyClass myClass;

	public Calculator(MyClass myClass) {
		this.myClass = myClass;
	}
	
	public int add(int a, int b) {
		int c = myClass.enrich(a);
		int d = myClass.enrich(b);
		
		return c + d;
	}

}

