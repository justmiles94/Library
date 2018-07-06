package com.tools.library;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class MyHttpServerImp {

	static File dir = new File("dir");

	public static void main(String[] args) throws Exception {
		InetSocketAddress addr;
		if (args.length == 1) {
			try {
				addr = new InetSocketAddress(Integer.parseInt(args[0]));
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}else {
			addr = new InetSocketAddress(0);
		}
		HttpServer server = HttpServer.create(addr, 0);
		server.createContext("/", new MyHandler());
		server.setExecutor(null); // creates a default executor
		server.start();
	}

	static class MyHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange t) throws IOException {
			HttpContext context = t.getHttpContext();
			String uri = t.getRequestURI().toString();
			FileFilter notDir = new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					String query = uri.replace(context.getPath(), "");
					if (query.contains("?")) {
						query = query.split("?")[0];
					}
					return pathname.isFile() && pathname.getName().equals(query);
				}
			};
			File[] list = dir.exists() ? dir.listFiles(notDir) : new File[0];
			String response = "";
			try {
				if (uri.equals(File.separator)) {
					response = getFileText("index");
					t.sendResponseHeaders(200, response.length());
				} else if (list.length > 0) {
					response = getFileText(list[0].getName());
					t.sendResponseHeaders(200, response.length());
				} else {
					response = "Not found";
					t.sendResponseHeaders(400, response.length());
				}
			} catch (IOException e) {
				response = "Error";
				t.sendResponseHeaders(404, response.length());
			}
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}

	public static String getFileText(String name) throws IOException {
		try {
			return new String(Files.readAllBytes(Paths.get(dir.getName() + File.separator + name)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}