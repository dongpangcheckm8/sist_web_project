package com.sist;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
/**
 * Template callback예제
 * @author sist1
 *
 */
public class Calculator {
	Logger log = Logger.getLogger(this.getClass());
	
	public int fileReadTemplate(String filePath
			                   ,BufferedReaderCallBack callBack) 
			throws IOException{
		int sum = 0;
		BufferedReader br=null;
		try{
			br = new BufferedReader(new FileReader(filePath));
			sum = callBack.doSomethingWithReader(br);

		}catch(IOException ioe){
			log.debug("ioe:"+ioe.getMessage());
			throw ioe;
		}finally{
			if(br !=null){
				try{
					br.close();
				}catch(IOException io){
					log.debug("io:"+io.getMessage());
				}
			}
		}
		
		return sum;
	}
	
	
	public int calcSum(String filePath) throws IOException{

		BufferedReaderCallBack sumCallBack=new BufferedReaderCallBack(){
			public int doSomethingWithReader(BufferedReader br)
					throws IOException{
				String line ="";
				int sum =0;
				while( (line=br.readLine()) !=null){
					
					sum+=Integer.parseInt(line);
				}
				return sum;
			}
			
		}; 
		
		return fileReadTemplate(filePath, sumCallBack);
	}
}
