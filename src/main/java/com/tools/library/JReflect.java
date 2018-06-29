package com.tools.library;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JReflect {

	public static void main(String... args) throws UnknownHostException, IOException {
		printGet(JReflect.class).forEach(System.out::println);
	}
	
	public static List<String> printGet(Class clazz){
		List<String> response = new ArrayList<String>();
		clazz.getClass();
		for (Method method : clazz.getMethods()) {
			if(method.getName().startsWith("get") && method.getParameterCount() == 0)
				try {
					response.add(method.getName() + " " + method.invoke(clazz).toString());
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					response.add(e.getMessage());
				}
		}
		return response;
	}
	
	public static List<String> printGet(Object clazz) {
		List<String> response = new ArrayList<String>();
		clazz.getClass();
		for (Method method : clazz.getClass().getMethods()) {
			if(method.getName().startsWith("get") && method.getParameterCount() == 0)
				try {
					response.add(method.getName() + " " + method.invoke(clazz).toString());
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					response.add(e.getMessage());
				}
		}
		return response;
	}
	
	public static void printMethods(Class clazz) {
		for (Method method : clazz.getDeclaredMethods()) {
		    String name = method.getName();
		    System.out.println(name);
		    System.out.println(Arrays.toString(method.getParameterTypes()));
		    System.out.println(method.getReturnType());
		    System.out.println();
		}
	}
	
}
