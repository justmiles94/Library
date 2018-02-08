package com.tools.library;

import java.util.Map;

public class PrintUtils {

	public void printMap(Map<String, String> map) {
		map.forEach((key, value) -> {
			System.out.println(key + " " + value);
		});
	}

}
