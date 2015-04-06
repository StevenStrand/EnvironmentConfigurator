package com.jamfsoftware.event;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.slf4j.LoggerFactory;

public class ReadDirectory {
	private final static org.slf4j.Logger logger = LoggerFactory
			.getLogger(ReadDirectory.class);

	public static ArrayList<String> getDirectories(String url) {
		ArrayList<String> results = new ArrayList<String>();
		File file = new File(url);
		String[] subDirectories = file.list();
		if (file.isDirectory()) {
			for (String filename : subDirectories) {
				if (!filename.startsWith(".")) {
					results.add(filename);
				}
			}
			logger.debug("Files found.");
		} else {
			logger.error("Not a valid file.");
		}

		return results;
	}

	public static boolean checkWebInf(String url) {
		File search = new File(url);
		boolean isJava = false;
		if (search.isDirectory()) {
			String[] subDirectories = search.list();
			for (String filename : subDirectories) {
				if (filename.toString().equalsIgnoreCase("WEB-INF")) {
					isJava = true;
					logger.debug("Java Web Application found");
					break;
				}
			}
		}
		return isJava;
	}

	public static boolean checkIsJSS(String url) {
		File search = new File(url + "/WEB-INF/xml");
		boolean isJSS = false;
		if (search.isDirectory()) {
			String[] subDirectories = search.list();
			for (String filename : subDirectories) {
				if (filename.toString().equalsIgnoreCase("version.xml")) {
					isJSS = true;
					logger.debug("Java Web Application found");
					break;
				}
			}
		}
		return isJSS;
	}

	public static String getResource(String resource) {
		URL resourceFile = ReadDirectory.class.getResource(resource);
		return resourceFile.getFile();
	}

	public static URL getResourceURL(String resource) {
		URL resourceFile = ReadDirectory.class.getResource(resource);
		return resourceFile;
	}

	public static String getContextName() {
		String resourceFile = getResource("/logback.xml");
		String[] pathList;
		if (System.getProperty("os.name").startsWith("Windows")) {
			pathList = resourceFile.split("\\");
		} else {
			pathList = resourceFile.split("/");
		}
		int length = pathList.length;

		return pathList[length - 4];
	}

	public static String getTomcatWebappRoot() {
		String resourceFile = getResource("/logback.xml");
		String TomcatRoot = "";
		String separator;
		if (System.getProperty("os.name").startsWith("Windows")) {
			separator = "\\";
		} else {
			separator = "/";
			TomcatRoot = "/";
		}
		String[] pathList = resourceFile.split(separator);
		int count = 1;
		while (count < pathList.length - 4) {
			TomcatRoot = TomcatRoot.toString().concat(
					pathList[count].toString());
			TomcatRoot = TomcatRoot.toString().concat(separator.toString());
			count++;
		}
		return TomcatRoot;
	}
	
	public static String getTomcatContainer() {
		String tomcatContainer = getTomcatWebappRoot();
		String separator;
		String[] pathList;
		if (System.getProperty("os.name").startsWith("Windows")) {
			separator = "\\";
			pathList = tomcatContainer.split(separator);
		} else {
			separator = "/";
			pathList = tomcatContainer.split(separator);
			tomcatContainer = "/";
		}
		
		int count = 1;
		while (count < pathList.length - 2) {
			tomcatContainer = tomcatContainer.toString().concat(
					pathList[count].toString());
			tomcatContainer = tomcatContainer.toString().concat(separator.toString());
			count++;
		}
		return tomcatContainer;
	}
	
	public static ArrayList<String> getTomcatInstances() {
		ArrayList<String> tomcatInstances = new ArrayList<String>();
		ArrayList<String> allDirectories = getDirectories(getTomcatContainer());
		for (String directory : allDirectories) {
			if(directory.toLowerCase().contains("tomcat")) {
				tomcatInstances.add(directory);
			}
		}
		return tomcatInstances;
	}
}
