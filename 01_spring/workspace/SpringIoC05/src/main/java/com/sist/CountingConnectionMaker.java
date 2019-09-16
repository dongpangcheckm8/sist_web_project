package com.sist;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectiomMaker {
	private int counter=0;
	
	public int getCounter() {
		return counter;
	}

	private ConnectiomMaker connectiomMaker;
	
	
	public CountingConnectionMaker(){}
	public CountingConnectionMaker(ConnectiomMaker connectiomMaker){
		this.connectiomMaker = connectiomMaker;
		
	}
	
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		counter++;
		
		return connectiomMaker.makeConnection();
	}

}
