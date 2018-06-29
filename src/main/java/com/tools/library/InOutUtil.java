package com.tools.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InOutUtil {

	public static String readBufferString(InputStream inputStream) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String buffer = "", line;
		try {
			while ((line = reader.readLine()) != null) {
				buffer.concat(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	public static List<String> readBufferList(InputStream inputStream){
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		List<String> valueList = new ArrayList<String>();
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				valueList.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return valueList;
	}
}
