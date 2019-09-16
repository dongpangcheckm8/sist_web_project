package com.sist;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReadTemplate implements BufferedReaderCallBack {

	public int doSomethingWithReader(BufferedReader br) throws IOException {
		String line ="";
		int sum =0;
		while( (line=br.readLine()) !=null){
			
			sum+=Integer.parseInt(line);
		}
		return sum;
	}

}
