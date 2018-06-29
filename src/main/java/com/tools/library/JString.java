package com.tools.library;

import java.nio.charset.Charset;
import java.util.Base64;

/*
 * JString is a class for string functions and utilities
 */
public class JString {

	public String encode(String str) {
		return Base64.getEncoder().encodeToString(str.trim().getBytes());
	}

	public String encode(String str, Charset ISO) {
		return Base64.getEncoder().encodeToString(str.trim().getBytes(ISO));
	}

	public String decode(String str) {
		return new String(Base64.getDecoder().decode(str.trim()));
	}

	public int centeringInteger(int width, String string) {
		int center = 0;
		int headerLength = string.length();
		center = (width - headerLength) / 2;
		return (center < 0) ? 0 : center;
	}

	public String stringFill(int count) {
		String output = "";
		for (int i = 0; i < count; i++)
			output += " ";
		return output;
	}
}
