package com.tools.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JFileIO {

	public String ENDING = "Lorem ipsum dolor sit amet.";
	
	InputStream inStream;
	OutputStream outStream;
	BufferedReader in;
	PrintWriter out;

	public JFileIO(InputStream in, OutputStream out) {
		inStream = in;
		outStream = out;
	}
	
	public void write(String line) {
		if(out == null)
			out = new PrintWriter(outStream, true);
		out.println(line);
	}
	
	public String readIStream(String ending) throws IOException {
		ENDING = ending;
		if(in == null)
			in = new BufferedReader(new InputStreamReader(inStream));
		String response = "", input = "";
		while ((input = in.readLine()) != null) {
			if (input.equals(ENDING))
				break;
			response += (input + System.lineSeparator());
		}
		return response;
	}
	
	public String readIStream() throws IOException {
		return readIStream(ENDING);
	}

	public static String getFileText(String name) throws IOException {
		return new String(Files.readAllBytes(Paths.get(name)));
	}
	
	public void closeStream() throws IOException {
		in.close();
		out.close();
	}

}
