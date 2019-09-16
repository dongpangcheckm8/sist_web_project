package com.sist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {
	@Bean
	public GscPanDao gscPanDao(){	
		return new GscPanDao(countingConnectionMaker());
	}

	@Bean
	public ConnectiomMaker countingConnectionMaker(){
		return new CountingConnectionMaker(connectiomMaker());
	}
	
	@Bean
	public ConnectiomMaker connectiomMaker(){
		return new SConnectionMaker();
	}
	
}
