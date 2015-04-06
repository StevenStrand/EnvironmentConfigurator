package com.jamfsoftware.object;

import java.util.ArrayList;

public class TomcatObject {
	private String path;
	private String tomcatVersion;
	private String javaVersion;
	private String port;
	private ArrayList<JSSObject> jssList;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTomcatVersion() {
		return tomcatVersion;
	}
	public void setTomcatVersion(String tomcatVersion) {
		this.tomcatVersion = tomcatVersion;
	}
	public String getJavaVersion() {
		return javaVersion;
	}
	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public ArrayList<JSSObject> getJssList() {
		return jssList;
	}
	public void setJssList(ArrayList<JSSObject> jssList) {
		this.jssList = jssList;
	}
}
