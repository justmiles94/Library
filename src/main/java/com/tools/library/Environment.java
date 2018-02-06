package com.tools.library;

public class Environment {

	public static final String OSNAME_PROPERTY = "os.name";

	public boolean isWindows() {
		return System.getProperty(OSNAME_PROPERTY).contains("Windows");
	}

	public boolean isMac() {
		return System.getProperty(OSNAME_PROPERTY).contains("Mac");
	}

}
