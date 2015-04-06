package com.jamfsoftware.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jamfsoftware.object.JSSObject;
import com.jamfsoftware.object.TomcatObject;

public class FindEnvironment {
	public static ArrayList<TomcatObject> tomcatInstances = new ArrayList<TomcatObject>();
	
	public static ArrayList<TomcatObject> findEnvironment() {
		tomcatInstances = new ArrayList<TomcatObject>();

		for (String instance : ReadDirectory.getTomcatInstances()) {
			TomcatObject tomcat = new TomcatObject();
			String command = ReadDirectory.getTomcatContainer().concat(instance).concat("/bin/catalina.sh version");
			
			try {
				Document doc = ReadXML.loadXMLDocument(ReadDirectory.getTomcatContainer().concat(instance).concat("/conf/server.xml"));
				NodeList nList = ReadXML.getNodeList(doc, "Connector");
				Node nNode = ReadXML.getXMLNode(nList, 1);
				String port = ReadXML.getAttribute(nNode, "port");
				tomcat.setPort(port);
			} catch (Exception e) {
				
			}

			try {
				Runtime r = Runtime.getRuntime();

				Process p = r.exec("bash " + command);

				BufferedReader in = new BufferedReader(new InputStreamReader(
						p.getInputStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					if (inputLine.contains("Server version")) {
						tomcat.setTomcatVersion(inputLine.split(":")[1]);
					} else if (inputLine.contains("JVM Version")) {
						tomcat.setJavaVersion(inputLine.split(":")[1]);
					}
				}
				in.close();
				tomcat.setPath(ReadDirectory.getTomcatContainer().concat(instance).concat("/webapps/"));
			} catch (IOException e) {
				System.out.println(e);
			}
			tomcatInstances.add(tomcat);
		}

		return tomcatInstances;
	}

	public static ArrayList<TomcatObject> populateEnvironment(ArrayList<TomcatObject> listTomcats) {
		for (TomcatObject tomcat : listTomcats) {
			ArrayList<JSSObject> jssList = FindInstalledJSS.buildList(tomcat.getPath());
			tomcat.setJssList(jssList);
		}
		return listTomcats;
	}
}
