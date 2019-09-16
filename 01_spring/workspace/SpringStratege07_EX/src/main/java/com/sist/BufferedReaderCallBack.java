package com.sist;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderCallBack {
	int doSomethingWithReader(BufferedReader br)
			throws IOException;
}
