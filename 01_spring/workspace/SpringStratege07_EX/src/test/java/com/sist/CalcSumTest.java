package com.sist;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class CalcSumTest {
	Logger log = Logger.getLogger(this.getClass());
	Calculator calculator;
	String numFilePath;
	
	@Before
	public void setUp(){
		calculator = new Calculator();
		numFilePath= getClass().getResource("number.txt").getPath();
	}
	
	@Test
	public void sumOfNumber()throws IOException{
//		Calculator calculator=new Calculator();
//		String path = getClass().getResource("number.txt").getPath();
//		log.debug("path:"+path);
//		int sum = calculator.calcSum(path);
//		assertThat(sum,is(55));
		
		assertThat(calculator.calcSum(numFilePath),is(55));
	}
}
