package com.tools.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JFileIO {

	public static final String ENDING = "Lorem ipsum dolor sit amet.";

	public String readIStream(BufferedReader in, String ending) throws IOException {
		String response = "", input = "";
		while ((input = in.readLine()) != null) {
			if (input.equals(ending))
				break;
			response += (input + System.lineSeparator());
		}
		return response;
	}

	public static String getFileText(String name) throws IOException {
		return new String(Files.readAllBytes(Paths.get(name)));
	}

}
