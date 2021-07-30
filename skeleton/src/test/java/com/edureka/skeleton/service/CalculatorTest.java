package com.edureka.skeleton.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.edureka.skeleton.service.Calculator;
import com.edureka.skeleton.service.MyClass;

class CalculatorTest {
	
	@Mock
	private MyClass myClassMock;
	
	@Test
	void add_solitary() {
		MockitoAnnotations.initMocks(this);
		Calculator calculator = new Calculator(myClassMock);
		when(myClassMock.enrich(ArgumentMatchers.anyInt()))
						.thenReturn(5);
		
		int resultActual = calculator.add(2, 3);
		
		assertEquals(10, resultActual);
	}

	@Test
	void add_sociable() {
		MyClass myClass = new MyClass();
		Calculator calculator = new Calculator(myClass);
		
		int resultActual = calculator.add(2, 3);
		
		assertEquals(9, resultActual);
	}

}
